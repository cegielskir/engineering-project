package com.cgk.engineering.team.coreservice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Article {

    @Id
    private ObjectId _id;

    private String title;
    private String author;
    private String description;
    private String content;

    public Article() {}

    public Article(ObjectId _id, String title, String author, String description, String content) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
