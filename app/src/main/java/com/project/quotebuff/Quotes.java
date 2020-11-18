package com.project.quotebuff;

import java.util.List;

public class Quotes {
    String content;
    String author;
    int length;
    List<String> tags;
    String _id;

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

    public void setContent() {
        this.content = content;
    }

    public void setAuthor () {
        this.author = author;
    }
}
