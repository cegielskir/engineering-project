package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.mainservice.model.BasicComparison;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicComparisonRepository extends MongoRepository<BasicComparison, String> {

    BasicComparison findByFirstArticleIDAndSecondArticleID(ObjectId firstArticleId, ObjectId secondArticleId);

}
