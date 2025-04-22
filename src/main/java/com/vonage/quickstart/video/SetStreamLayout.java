package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class SetStreamLayout {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        SessionStream stream = SessionStream.builder(VIDEO_STREAM_ID)
                .layoutClassList("full", "focus").build();

        videoClient.setStreamLayout(VIDEO_SESSION_ID, stream);

        System.out.println("Set stream layout");
    }
}
