package ch.uzh.slamer.backend.service;

import ch.uzh.slamer.backend.repository.PenaltyRepository;
import codegen.tables.pojos.Penalty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {

    @Autowired
    PenaltyRepository penaltyRepository;

    public Penalty addToSlo(Penalty penalty){
        return penaltyRepository.add(penalty);
    }
}
