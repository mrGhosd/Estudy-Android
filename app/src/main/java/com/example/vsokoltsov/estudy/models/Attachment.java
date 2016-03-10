package com.example.vsokoltsov.estudy.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vsokoltsov on 11.03.16.
 */
public class Attachment {
    @SerializedName("attachable_type")
    private String attachableType;
    @SerializedName("attachable_id")
    private String attachableId;
    private int id;
    private String type;
    private String url;

    public String getAttachableType() {
        return attachableType;
    }

    public void setAttachableType(String attachableType) {
        this.attachableType = attachableType;
    }

    public String getAttachableId() {
        return attachableId;
    }

    public void setAttachableId(String attachableId) {
        this.attachableId = attachableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
