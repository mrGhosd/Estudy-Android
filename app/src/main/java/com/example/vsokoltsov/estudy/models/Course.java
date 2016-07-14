package com.example.vsokoltsov.estudy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vsokoltsov on 15.07.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
    @JsonProperty("title")
    private String title;
    @JsonProperty("short_description")
    private String shortDescription;
    @JsonProperty("image")
    private Attachment image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Attachment getImage() {
        return image;
    }

    public void setImage(Attachment image) {
        this.image = image;
    }
}
