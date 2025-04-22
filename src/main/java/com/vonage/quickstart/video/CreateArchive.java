package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import com.vonage.quickstart.util.JsonPrinter;

public class CreateArchive {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        Archive archive = videoClient.createArchive(
                Archive.builder(VIDEO_SESSION_ID)
                        .streamMode(StreamMode.AUTO)
                        .hasVideo(true)
                        .hasAudio(true)
                        .resolution(Resolution.HD_LANDSCAPE)
                        .outputMode(OutputMode.COMPOSED).name("My recording")
                        .build());

        System.out.println("Started archive: ");
        JsonPrinter.print(archive);
    }
}
