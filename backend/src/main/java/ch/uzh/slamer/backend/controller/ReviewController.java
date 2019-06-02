package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.model.dto.ReviewDTO;
import ch.uzh.slamer.backend.service.ReviewService;
import codegen.tables.pojos.SlaReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed.origin}")
@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST, path = "/reviews")
    public ResponseEntity<Boolean> addReview(@RequestBody ReviewDTO review) {
        boolean created = reviewService.addNewReview(review);
        if (created) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }
}
