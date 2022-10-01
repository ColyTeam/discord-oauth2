package dev.coly.discordoauth2.objects;

import com.google.gson.annotations.SerializedName;

public class User {

    public String id;
    public String username;
    public String avatar;
    public String discriminator;
    public boolean bot;
    public boolean system;
    @SerializedName("mfa_enabled")
    public boolean mfaEnabled;
    public String locale;
    public boolean verified;
    public String email;
    public long flags;
    @SerializedName("premium_type")
    public int premiumType;

}
