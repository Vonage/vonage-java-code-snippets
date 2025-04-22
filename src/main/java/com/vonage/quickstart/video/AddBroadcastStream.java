package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class AddBroadcastStream {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        videoClient.addBroadcastStream(VIDEO_BROADCAST_ID, VIDEO_STREAM_ID);

        System.out.println("Added broadcast stream");
    }
}
