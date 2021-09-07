# Discord OAuth2
An wrapper for Discord OAuth2 and basic Discord api requests.

## Installation
Download the jar file from the [release page](https://github.com/ColyTeam/discord-oauth2/releases/latest).

### Maven
```xml
<repository>
    <id>coly</id>
    <url>https://m2.coly.dev/releases</url>
</repository>

<dependency>
    <groupId>dev.coly</groupId>
    <artifactId>discord-oauth2</artifactId>
    <version>VERSION</version>
</dependency>
````
## Usage
This project can only be used as a libary.

Create a DiscordOAuth2 object with client ID, client secret, redirect url and scopes. The client ID and client secret can be found in the [Discord Developer Portal](https://discord.com/developers/applications). Select an application and then click on OAuth2 on the left. Also add any redirect url you use in this portal.
Scopes can be specified as a string or as an enum. This defines what action your application can perform.

```JAVA
DiscordOAuth2 discordOAuth2 = new DiscordOAuth2(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, SCOPES...);
```

### Examples
## Get authorization url
Give this url your users and they will be redirected to your redirectUrl after they have successfully authorized themself. This redirected url will contain a parameter with the name code. You will need this code to request the access token.

```JAVA
//Without state
String url = discordOAuth2.getAuthorizationUrl();
//With state
String url = discordOAuth2.getAuthorizationUrl(STATE);
```

## Get tokens
These tokens should be saved in a database.
```JAVA
Tokens tokens = discordOAuth2.getTokens(CODE);
String accessToken = tokens.accessToken;
String refreshToken = tokens.refreshToken;
```

## Get user
```JAVA
User user = DiscordAPI.getUser(ACCESS_TOKEN);
```

## Get guilds the user has joined
```JAVA
Guild[] guilds = DiscordAPI.getGuilds(ACCESS_TOKEN);
```
