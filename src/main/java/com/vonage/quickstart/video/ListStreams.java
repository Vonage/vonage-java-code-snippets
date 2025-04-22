package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import com.vonage.quickstart.util.JsonPrinter;

import java.util.List;

public class ListStreams {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        List<GetStreamResponse> streams = videoClient.listStreams(VIDEO_SESSION_ID);

        System.out.println("Retrieved list of archives: ");
        JsonPrinter.print(streams);
    }
}
