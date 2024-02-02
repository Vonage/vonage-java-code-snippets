/*
 * Copyright 2023 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vonage.quickstart.voice;

import com.vonage.client.VonageClient;
import com.vonage.client.voice.EventWebhook;
import spark.Route;
import spark.Spark;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.vonage.quickstart.Util.envVar;
import static com.vonage.quickstart.Util.configureLogging;

public class DownloadRecording {
    public static void main(String[] args) throws Exception {
        configureLogging();

        final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        /*
         * A recording webhook endpoint which automatically downloads the specified recording to a file in the
         * current working directory, called "downloaded_recording.mp3"
         */
        Route downloadRoute = (req, res) -> {
            EventWebhook event = EventWebhook.fromJson(req.body());
            String recordingUrl = event.getRecordingUrl().toString();
            Path recordingFile = Paths.get("downloaded_recording.mp3");
            System.out.println("Downloading from " + recordingUrl);
            client.getVoiceClient().saveRecording(recordingUrl, recordingFile);
            return "OK";
        };

        Spark.port(3000);
        Spark.post("/recording", downloadRoute);
    }
}
