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

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public boolean isBot() {
        return bot;
    }

    public boolean isSystem() {
        return system;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public String getLocale() {
        return locale;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getEmail() {
        return email;
    }

    public long getFlags() {
        return flags;
    }

    public int getPremiumType() {
        return premiumType;
    }
}
