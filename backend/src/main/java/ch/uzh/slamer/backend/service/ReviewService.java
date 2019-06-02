package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import ch.uzh.slamer.backend.model.dto.ReviewDTO;
import ch.uzh.slamer.backend.model.dto.ServiceLevelObjectiveDTO;
import ch.uzh.slamer.backend.repository.ReviewRepository;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import ch.uzh.slamer.backend.repository.SlaRepository;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.pojos.Sla;
import codegen.tables.pojos.SlaReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public boolean addNewReview(ReviewDTO review) {
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
        try {
            reviewRepository.add(validFrom);
            reviewRepository.add(validTo);
            reviewRepository.add(servicePrice);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return false;
        }

        // update slos
        try {
            for (ServiceLevelObjective slo: slos) {
                sloRepository.update(slo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return false;
        }
        Sla updatedSla = setStateAfterReview(review);
        slaRepository.update(updatedSla);
        return true;

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

    private Sla setStateAfterReview(ReviewDTO review) {
        Sla sla = null;
        try {
            sla = slaRepository.findById(review.getSlaId());
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (!sla.getStatus().equals(REQUESTED.getStatus())) {
            return null;
        }
        if (hasSlaBeenAccepted(review)) {
            sla.setStatus(ACCEPTED.getStatus());
        } else {
            sla.setStatus(REJECTED.getStatus());
        }
        return sla;
    }

    public Sla setStateAfterRevision(ReviewDTO review) {
        return null;
    }
}
