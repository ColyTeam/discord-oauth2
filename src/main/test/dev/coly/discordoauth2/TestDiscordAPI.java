package dev.coly.discordoauth2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestDiscordAPI {

    @Test
    public void testGetUser() {
        try {
            Assertions.assertNotNull(DiscordAPI.getUser(System.getenv("ACCESS_TOKEN")));
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    public void testGetGuild() {
        try {
            Assertions.assertNotNull(DiscordAPI.getGuilds(System.getenv("ACCESS_TOKEN")));
        } catch (IOException e) {
            Assertions.fail(e);
        }
    }

}
