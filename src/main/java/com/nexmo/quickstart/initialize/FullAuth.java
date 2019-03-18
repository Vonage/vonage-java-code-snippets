package com.nexmo.quickstart.initialize;

import com.nexmo.client.NexmoClient;

import static com.nexmo.quickstart.Util.envVar;

/**
 * Example of configuring a NexmoClient with authentication for
 * both API secret and Application (JWT) authentication credentials.
 */
public class FullAuth {
    public static void main(String[] argv) throws Exception {
        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
        String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        String NEXMO_APPLICATION_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        NexmoClient client = NexmoClient.builder()
                .apiKey(NEXMO_API_KEY)
                .apiSecret(NEXMO_API_SECRET)
                .applicationId(NEXMO_APPLICATION_ID)
                .privateKeyPath(NEXMO_APPLICATION_PRIVATE_KEY_PATH)
                .build();
    }
}
