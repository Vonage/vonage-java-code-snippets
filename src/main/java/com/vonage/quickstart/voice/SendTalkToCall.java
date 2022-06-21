/*
 * Copyright  2020 Vonage
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
import com.vonage.client.voice.Call;
import com.vonage.client.voice.CallEvent;
import com.vonage.client.voice.TextToSpeechLanguage;

import static com.vonage.quickstart.Util.configureLogging;
import static com.vonage.quickstart.Util.envVar;

public class SendTalkToCall {
    public static void main(String[] args) throws Exception {
        configureLogging();

        final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
        final String VONAGE_NUMBER = envVar("VONAGE_NUMBER");
        final String TO_NUMBER = envVar("TO_NUMBER");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        final String ANSWER_URL = "https://nexmo-community.github.io/ncco-examples/silent-loop.json";
        CallEvent call = client
                .getVoiceClient()
                .createCall(new Call(TO_NUMBER, VONAGE_NUMBER, ANSWER_URL));

        Thread.sleep(5000);

        final String UUID = call.getUuid();
        final String TEXT = "Hello World! Would you like to know more? I bet you would.";
        client.getVoiceClient().startTalk(UUID,TEXT, TextToSpeechLanguage.AMERICAN_ENGLISH);
    }
}
