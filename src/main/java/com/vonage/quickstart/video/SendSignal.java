package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class SendSignal {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        SignalRequest signal = SignalRequest.builder()
                .type("chat")
                .data("Hello, World!")
                .build();

        videoClient.signalAll(VIDEO_SESSION_ID, signal);

        System.out.println("Sent signal");
    }
}
