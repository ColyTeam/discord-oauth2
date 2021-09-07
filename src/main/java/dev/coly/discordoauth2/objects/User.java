package dev.coly.discordoauth2.objects;

import com.google.gson.annotations.SerializedName;

public class User {

    private String id;
    private String username;
    private String avatar;
    private String discriminator;
    private boolean bot;
    private boolean system;
    @SerializedName("mfa_enabled")
    private boolean mfaEnabled;
    private String locale;
    private boolean verified;
    private String email;
    private long flags;
    @SerializedName("premium_type")
    private int premiumType;

}
