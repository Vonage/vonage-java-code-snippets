/*
 * Copyright 2025 Vonage
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
import com.vonage.client.voice.PhoneEndpoint;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class OutboundTextToSpeechWithEventUrl {
    public static void main(String[] args) throws Exception {
        
        final String VONAGE_NUMBER = VONAGE_NUMBER;
        final String TO_NUMBER = TO_NUMBER;
        final String EVENT_URL = EVENT_URL;
        final String VONAGE_APPLICATION_ID = VONAGE_APPLICATION_ID;
        final String VONAGE_PRIVATE_KEY_PATH = VONAGE_PRIVATE_KEY_PATH;
        final String ANSWER_URL = "https://nexmo-community.github.io/ncco-examples/talk.json";

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        Call call = Call.builder()
                .from(VONAGE_NUMBER).to(new PhoneEndpoint(TO_NUMBER))
                .answerUrl(ANSWER_URL).eventUrl(EVENT_URL).build();

        var response = client.getVoiceClient().createCall(call);
        System.out.println(response);
    }
}
