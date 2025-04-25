/*
 *   Copyright 2025 Vonage
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
                        .build()
        );

        System.out.println("Started broadcast: " + broadcast.toJson());
    }
}
