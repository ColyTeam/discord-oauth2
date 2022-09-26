package dev.coly.discordoauth2;

import dev.coly.discordoauth2.objects.Tokens;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestDiscordOAuth2 {

    private DiscordOAuth2 getDiscordOAuth2() {
        return new DiscordOAuth2(System.getenv("CLIENT_ID"), System.getenv("CLIENT_SECRET"),
                System.getenv("REDIRECT_URL"), DiscordOAuth2.Scope.IDENTIFY, DiscordOAuth2.Scope.GUILDS);
    }

    @Test
    public void testGetAuthorizationUrl() {
        String url = getDiscordOAuth2().getAuthorizationUrl();
        Assertions.assertNotNull(url);
    }

    @Test
    public void testGetTokens() {
        try {
            Tokens tokens = getDiscordOAuth2().getTokens(System.getenv("CODE"));
            Assertions.assertNotNull(tokens);
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testRefreshTokens() {
        try {
            Assertions.assertNotNull(getDiscordOAuth2().refreshTokens(System.getenv("ACCESS_TOKEN")));
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
