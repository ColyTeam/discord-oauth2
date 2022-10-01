package dev.coly.discordoauth2.objects;

import com.google.gson.annotations.SerializedName;

public class Tokens {

    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("expires_in")
    public int expiresIn;
    @SerializedName("refresh_token")
    public String refreshToken;
    public String scope;

}
