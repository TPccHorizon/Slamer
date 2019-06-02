package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.SlaReview;
import codegen.tables.records.SlaReviewRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static codegen.tables.SlaReview.SLA_REVIEW;

@Repository
public class ReviewRepository extends AbstractRepository<SlaReviewRecord, Integer, SlaReview>{

    public ReviewRepository(DSLContext context) {
        super(context, SLA_REVIEW, SLA_REVIEW.ID, SlaReview.class);
    }

    @Override
    public SlaReview update(SlaReview entity) throws RecordNotFoundException {
        return null;
    }
}
