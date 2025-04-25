package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

import java.time.Duration;

public class GenerateToken {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        TokenOptions tokenOptions = TokenOptions.builder()
                .role(Role.MODERATOR)
                .expiryLength(Duration.ofHours(6))
                .data("name=John,id=123")
                .build();

        String jwt = videoClient.generateToken(VIDEO_SESSION_ID, tokenOptions);

        System.out.println("Generated token: " + jwt);
    }
}
