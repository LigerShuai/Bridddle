package com.liger.bridddle.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

/**
 * @author Liger
 * @date 2020/11/19 15:06
 */
public class Token {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getScope() {
        return scope;
    }

    @NotNull
    @Override
    public String toString() {
        return "Token{" +
                "token='" + accessToken + '\'' +
                ", type='" + tokenType + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
