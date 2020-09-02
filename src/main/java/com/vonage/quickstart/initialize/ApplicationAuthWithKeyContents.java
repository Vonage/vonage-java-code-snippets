package com.vonage.quickstart.initialize;

import com.nexmo.client.VonageClient;
import com.vonage.quickstart.Util;

/**
 * Example of configuring a NexmoClient with Application (JWT) authentication credentials.
 */
public class ApplicationAuthWithKeyContents {
    public static void main(String[] argv) throws Exception {

        String VONAGE_APPLICATION_ID = Util.envVar("VONAGE_APPLICATION_ID");
        String VONAGE_APPLICATION_PRIVATE_KEY= Util.envVar("VONAGE_PRIVATE_KEY");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyContents(VONAGE_APPLICATION_PRIVATE_KEY)
                .build();
    }
}
