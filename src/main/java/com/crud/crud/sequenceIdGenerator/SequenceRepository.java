package com.crud.crud.sequenceIdGenerator;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {

}
