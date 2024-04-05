package com.ktg.cni.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class newsModel {



    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


    public newsModel(String id, String name, String description, String shortDescription, String title, String views, String createDate, String updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.title = title;
        this.views = views;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
