package dev.coly.discordoauth2;

import com.google.gson.Gson;
import dev.coly.discordoauth2.objects.Guild;
import dev.coly.discordoauth2.objects.User;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class DiscordAPI {

    public static final String DISCORD_API = "https://discord.com/api";
    public static Gson gson = new Gson();

    private static void setHeaders(Connection connection, String accessToken) {
        connection.header("Authorization", "Bearer " + accessToken);
        connection.header("User-Agent", "Coly-Discord-OAuth2, 1.0, platform " +
                System.getProperty("os.name") + " " + System.getProperty("os.version"));
    }

    /**
     * Get information about the user of the access token.
     *
     * @param accessToken   The access token from the user.
     * @return              information about the user.
     * @throws IOException  if actions fails.
     */
    public static User getUser(String accessToken) throws IOException {
        Connection connection = Jsoup.connect(DISCORD_API + "/users/@me").ignoreContentType(true);
        setHeaders(connection, accessToken);
        return gson.fromJson(connection.get().body().text(), User.class);
    }

    /**
     * Get all {@link Guild}s the user has joined.
     *
     * @param accessToken   The access token from the user.
     * @return              an array of all {@link Guild}s.
     * @throws IOException  if actions fails.
     */
    public static Guild[] getGuilds(String accessToken) throws IOException {
        Connection connection = Jsoup.connect(DISCORD_API + "/users/@me/guilds").ignoreContentType(true);
        setHeaders(connection, accessToken);
        return gson.fromJson(connection.get().body().text(), Guild[].class);
    }

}
