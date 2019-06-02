package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.model.dto.ReviewDTO;
import ch.uzh.slamer.backend.repository.ReviewRepository;
import ch.uzh.slamer.backend.repository.ServiceLevelObjectiveRepository;
import codegen.tables.pojos.ServiceLevelObjective;
import codegen.tables.pojos.SlaReview;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ServiceLevelObjectiveRepository sloRepository;

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

        return true;

    }
}
