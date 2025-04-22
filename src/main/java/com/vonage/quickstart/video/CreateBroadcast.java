package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import com.vonage.quickstart.util.JsonPrinter;

import java.time.Duration;

public class CreateBroadcast {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        Broadcast broadcast = videoClient.createBroadcast(
                Broadcast.builder(VIDEO_SESSION_ID)
                        .hls(Hls.builder().lowLatency(true).build())
                        .resolution(Resolution.HD_LANDSCAPE)
                        .streamMode(StreamMode.AUTO)
                        .maxDuration(Duration.ofMinutes(45))
                        .build());

        System.out.println("Started broadcast: ");
        JsonPrinter.print(broadcast);
    }
}
