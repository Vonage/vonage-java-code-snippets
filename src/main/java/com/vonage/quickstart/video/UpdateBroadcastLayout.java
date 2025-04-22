package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class UpdateBroadcastLayout {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        StreamCompositionLayout layout = StreamCompositionLayout.standardLayout(ScreenLayoutType.VERTICAL);
        videoClient.updateBroadcastLayout(VIDEO_BROADCAST_ID, layout);

        System.out.println("Updated broadcast layout");
    }
}
