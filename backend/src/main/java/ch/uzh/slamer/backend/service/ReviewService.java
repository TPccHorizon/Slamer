package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.*;
import ch.uzh.slamer.backend.repository.ReviewRepository;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import ch.uzh.slamer.backend.repository.SlaRepository;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ch.uzh.slamer.backend.model.enums.SlaStatus.*;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ServiceLevelObjectiveRepository sloRepository;

    @Autowired
    SlaRepository slaRepository;

    @Autowired
    ModelMapper mapper;

    public ReviewDTO getReviewBySlaId(int slaId) {
        List<SlaReview> reviews = reviewRepository.getReviewsOfSla(slaId);
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setSlaId(slaId);
        for (SlaReview review: reviews) {
            switch (review.getProperty()) {
                case "validFrom":
                    reviewDTO.setValidFrom(review);
                    break;
                case "validTo":
                    reviewDTO.setValidTo(review);
                    break;
                case "servicePrice":
                    reviewDTO.setServicePrice(review);
                    break;
            }
        }

        List<ServiceLevelObjective> slos = sloRepository.getAllBySlaId(slaId);
        SlaDTO tempSla = new SlaDTO();
        mapSlos(tempSla, slos);
        reviewDTO.setSlos(tempSla.getSlos());
        return reviewDTO;
    }

    public boolean addReview(ReviewDTO review, boolean isRevision) {
        SlaReview validFrom = review.getValidFrom();
        validFrom.setSlaId(review.getSlaId());

        SlaReview validTo = review.getValidTo();
        validTo.setSlaId(review.getSlaId());

        SlaReview servicePrice = review.getServicePrice();
        servicePrice.setSlaId(review.getSlaId());

        List<ServiceLevelObjective> slos = review.getSlos()
                .stream()
                .map(sloDTO -> mapper.map(sloDTO, ServiceLevelObjective.class))
                .collect(Collectors.toList());
        System.out.println("isRevision: " + isRevision);
        System.out.println("service Price: " + servicePrice.getValue());
        Sla sla;
        try {
            sla = slaRepository.findById(review.getSlaId());
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            if (isRevision) {
                reviewRepository.update(validFrom);
                reviewRepository.update(validTo);
                reviewRepository.update(servicePrice);
                updateSlos(slos);
                sla.setValidFrom(Date.valueOf(validFrom.getValue()));
                sla.setValidTo(Date.valueOf(validTo.getValue()));
                sla.setServicePrice(BigDecimal.valueOf(Integer.valueOf(servicePrice.getValue())));
                return setStateAfterRevision(sla);
            } else {
                reviewRepository.add(validFrom);
                reviewRepository.add(validTo);
                reviewRepository.add(servicePrice);
                updateSlos(slos);
                return setStateAfterReview(review, sla);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return false;
        }
    }

    private boolean hasSlaBeenAccepted(ReviewDTO review) {
        boolean priceAccepted = review.getServicePrice().getAccepted();
        boolean validFromAccepted = review.getValidFrom().getAccepted();
        boolean validToAccepted = review.getValidTo().getAccepted();
        if (!(priceAccepted && validFromAccepted && validToAccepted)) {
            return false;
        }
        for (ServiceLevelObjectiveDTO slo: review.getSlos()) {
            if (!slo.isAccepted()) {
                return false;
            }
        }
        return true;
    }

    private boolean setStateAfterReview(ReviewDTO review, Sla sla) {
        if (!sla.getStatus().equals(REQUESTED.getStatus())) {
            return false;
        }
        if (hasSlaBeenAccepted(review)) {
            sla.setStatus(ACCEPTED.getStatus());
        } else {
            sla.setStatus(REJECTED.getStatus());
        }
        slaRepository.update(sla);
        return true;
    }

    public boolean setStateAfterRevision(Sla sla) {
        if (!sla.getStatus().equals(REJECTED.getStatus())) {
            return false;
        }
        sla.setStatus(REQUESTED.getStatus());
        slaRepository.update(sla);
        return true;
    }

    private void updateSlos(List<ServiceLevelObjective> slos) {
        try {
            for (ServiceLevelObjective slo: slos) {
                sloRepository.update(slo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        }
    }

    private void mapSlos(SlaDTO slaDTO, List<ServiceLevelObjective> slos) {
        for (ServiceLevelObjective slo: slos) {
            ServiceLevelObjectiveDTO sloDto;
            if (slo.getSloType().equals("Uptime")) {
                sloDto = mapper.map(slo, UptimeDTO.class);
            } else if (slo.getSloType().equals("Throughput")) {
                sloDto = mapper.map(slo, ThroughputDTO.class);
            } else {
                sloDto = mapper.map(slo, AverageResponseTimeDTO.class);
            }
            slaDTO.addSlo(sloDto);
        }
    }
}
