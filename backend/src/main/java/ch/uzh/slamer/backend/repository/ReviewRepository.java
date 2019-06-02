package ch.uzh.slamer.backend.repository;

import ch.uzh.slamer.backend.exception.RecordNotFoundException;
import codegen.tables.pojos.SlaReview;
import codegen.tables.records.SlaReviewRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static codegen.tables.SlaReview.SLA_REVIEW;

@Repository
public class ReviewRepository extends AbstractRepository<SlaReviewRecord, Integer, SlaReview>{

    public ReviewRepository(DSLContext context) {
        super(context, SLA_REVIEW, SLA_REVIEW.ID, SlaReview.class);
    }

    @Transactional
    @Override
    public SlaReview update(SlaReview entity) {
        SlaReviewRecord record = context.newRecord(SLA_REVIEW, entity);
        record.update();
        return record.into(SlaReview.class);
    }
}
