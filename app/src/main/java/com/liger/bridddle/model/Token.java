package com.liger.bridddle.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * @author Liger
 * @date 2020/11/19 15:06
 */
public class Token {

    @SerializedName("access_token")
    private String token;

    @SerializedName("token_type")
    private String type;

    private String scope;

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getScope() {
        return scope;
    }

    @NotNull
    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
