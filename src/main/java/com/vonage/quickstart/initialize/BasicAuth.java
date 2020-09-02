package com.vonage.quickstart.initialize;

import com.nexmo.client.VonageClient;
import com.vonage.quickstart.Util;

/**
 * Example of configuring a NexmoClient with an API secret.
 */
public class BasicAuth {
    public static void main(String[] argv) throws Exception {
        String VONAGE_API_KEY = Util.envVar("VONAGE_API_KEY");
        String VONAGE_API_SECRET = Util.envVar("VONAGE_API_SECRET");

        VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();
    }
}
