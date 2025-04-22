package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import com.vonage.quickstart.util.JsonPrinter;

public class GetBroadcast {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        Broadcast broadcast = videoClient.getBroadcast(VIDEO_BROADCAST_ID);

        System.out.println("Retrieved broadcast: ");
        JsonPrinter.print(broadcast);
    }
}
