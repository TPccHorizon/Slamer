package ch.uzh.slamer.backend.controller;

import ch.uzh.slamer.backend.model.dto.ReviewDTO;
import ch.uzh.slamer.backend.service.ReviewService;
import org.jooq.meta.derby.sys.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowCredentials = "false", origins = "${security.allowed-origin}")
@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST, path = "/reviews")
    public ResponseEntity<Boolean> addReview(@RequestBody ReviewDTO review) {
        boolean created = reviewService.addReview(review, false);
        if (created) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/reviews/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable int id) {
        ReviewDTO reviewDTO = reviewService.getReviewBySlaId(id);
        if (reviewDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/reviews")
    public ResponseEntity<Boolean> reviseRewiev(@RequestBody ReviewDTO reviewDTO) {
        boolean updated = reviewService.addReview(reviewDTO, true);
        if (updated) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.CONFLICT);
    }
}
