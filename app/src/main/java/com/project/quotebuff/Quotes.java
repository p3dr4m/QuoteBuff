package com.project.quotebuff;

import java.util.List;

public class Quotes {
    String content;
    String author;
    int length;
    List<String> tags;
    String _id;

    public Quotes() {
    }

    public Quotes(String id, String author, String content) {
        this._id = id;
        this.author = author;
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
