package com.cgk.engineering.team.dbservice.repository;

import com.cgk.engineering.team.dbservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    Article findBy_id(ObjectId _id);
    Article findByTitle(String title);
}