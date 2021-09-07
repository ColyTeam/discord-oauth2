package dev.coly.discordoauth2;

import dev.coly.discordoauth2.objects.Tokens;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;

import static dev.coly.discordoauth2.DiscordAPI.DISCORD_API;

public class DiscordOAuth2 {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
    private final String[] scopes;

    /**
     * Interact with the Discord OAuth2. clientId and clientSecret can be found in the Discord developer portal. The
     * redirectUrl has to be registered under OAuth2 in the Discord developer portal. The redirect url also contains the
     * code you have to save, to let the user be logged in.
     *
     * @param clientId      The clientId of your Discord app.
     * @param clientSecret  The clientSecret of your Discord app.
     * @param redirectUrl   The url the user will be redirected to, after they authorized the connection. This has to be
     *                      registered in the Discord developer portal.
     * @param scopes        An array of scopes. This will define, which actions your app can perform.
     */
    public DiscordOAuth2(String clientId, String clientSecret, String redirectUrl, Scope... scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.scopes = getScopesAsStrings(scopes);
    }

    /**
     * Interact with the Discord OAuth2. clientId and clientSecret can be found in the Discord developer portal. The
     * redirectUrl has to be registered under OAuth2 in the Discord developer portal. The redirect url also contains the
     * code you have to save, to let the user be logged in.
     *
     * @param clientId      The clientId of your Discord app.
     * @param clientSecret  The clientSecret of your Discord app.
     * @param redirectUrl   The url the user will be redirected to, after they authorized the connection. This has to be
     *                      registered in the Discord developer portal.
     * @param scopes        An array of scopes. This will define, which actions your app can perform.
     */
    public DiscordOAuth2(String clientId, String clientSecret, String redirectUrl, String... scopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.scopes = scopes;
    }

    /**
     * Get the url that the user will be open the start the authorization.
     *
     * @return      the url for authorization.
     */
    public String getAuthorizationUrl() {
        return getAuthorizationUrl(null);
    }

    /**
     * Get the url that the user will be open the start the authorization.
     *
     * @param state that will be used for security. Will be ignored if null or empty.
     * @return      the url for authorization.
     */
    public String getAuthorizationUrl(String state) {
        return DISCORD_API + "/oauth2/authorize?" +
                "response_type=code" +
                "&client_id=" + clientId +
                "&redirect_url=" + redirectUrl +
                (state != null && !state.isEmpty() ? "&state=" + String.join("%20", scopes) : "") +
                "&scope=" + String.join("%20", scopes);
    }

    /**
     * Get scopes array as a string array. All scopes will be lowercase.
     *
     * @param scopes    The scope array that will be transformed.
     * @return          scopes array as a string array
     */
    public String[] getScopesAsStrings(Scope[] scopes) {
        return Arrays.stream(scopes).map(scope -> scope.toString().toLowerCase().replaceAll("_", "."))
                .toArray(String[]::new);
    }

    /**
     * Get access and refresh tokens from code. The code will be included with the returnUrl.
     *
     * @param code          The code from the user.
     * @return              access and refresh token to authorize further actions.
     * @throws IOException  when getting the tokens fails.
     */
    public Tokens getTokens(String code) throws IOException {
        String response = Jsoup.connect(DISCORD_API + "/oauth2/token")
                .data("client_id", clientId)
                .data("client_secret", clientSecret)
                .data("grant_type", "authorization_code")
                .data("code", code)
                .data("redirect_url", redirectUrl)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .ignoreContentType(true).post().body().text();
        return DiscordAPI.gson.fromJson(response, Tokens.class);
    }

    /**
     * Refresh the access token using the refresh token.
     *
     * @param refreshToken  The refresh token previously received.
     * @return              the new access token.
     * @throws IOException  when getting the tokens fails.
     */
    public Tokens refreshTokens(String refreshToken) throws IOException {
        String response = Jsoup.connect(DISCORD_API + "/oauth2/token")
                .data("client_id", clientId)
                .data("client_secret", clientSecret)
                .data("grant_type", "refresh_token")
                .data("refresh_token", refreshToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .ignoreContentType(true).post().body().text();
        return DiscordAPI.gson.fromJson(response, Tokens.class);
    }

    public enum Scope {
        IDENTIFY, EMAIL, CONNECTIONS, GUILDS, GUILDS_JOIN, GDM_JOIN, RCP, RCP_NOTIFICATIONS_READ, RPC_VOICE_READ,
        RPC_VOICE_WRITE, RPC_ACTIVITES_WRITE, BOT, WEBHOOK_INCOMING, MESSAGES_READ, APPLICATIONS_BUILDS_UPLOAD,
        APPLICATIONS_BUILDS_READ, APPLICATIONS_STORE_UPDATE, APPLICATIONS_ENTITLEMENTS, ACTIVITES_READ, ACTIVITES_WRITE,
        RELATIONSHIPS_READ
    }

}
