package dev.coly.discordoauth2.objects;

import com.google.gson.annotations.SerializedName;

public class Tokens {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    private String scope;

}
