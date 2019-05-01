package com.cgk.engineering.team.coreservice.repository;

import com.cgk.engineering.team.coreservice.model.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Article findBy_id(ObjectId _id);
    Article findByTitle(String title);
}