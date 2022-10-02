package dev.coly.discordoauth2.objects;

import java.util.List;

public class Guild {

    private String id;
    private String name;
    private String icon;
    private boolean owner;
    private int permissions;
    private List<String> features;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public boolean isOwner() {
        return owner;
    }

    public int getPermissions() {
        return permissions;
    }

    public List<String> getFeatures() {
        return features;
    }
}
