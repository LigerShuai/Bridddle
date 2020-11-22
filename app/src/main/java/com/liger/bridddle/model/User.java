package com.liger.bridddle.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Liger
 * @date 2020/11/21 18:54
 */
public class User {

    private long id;
    private String name;

    @SerializedName("html_url")
    private String url;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
