/*
 * Copyright 2024 Vonage
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

import com.vonage.client.voice.EventWebhook;
import com.vonage.client.voice.ncco.InputAction;
import com.vonage.client.voice.ncco.Ncco;
import com.vonage.client.voice.ncco.SpeechSettings;
import com.vonage.client.voice.ncco.TalkAction;
import spark.Route;
import spark.Spark;

import java.util.Collections;

public class AsrInput {

    public static void main(String[] args) {
        /*
         * Route to answer incoming calls.
         */
        Route answerCallRoute = (req, res) -> {
            TalkAction intro = TalkAction
                    .builder("Please say something")
                    .build();

            SpeechSettings speechSettings = SpeechSettings.builder()
                    .language(SpeechSettings.Language.ENGLISH_UNITED_STATES).build();

            InputAction input = InputAction.builder()
                    .type(Collections.singletonList("speech"))
                    .eventUrl(String.format("%s://%s/webhooks/asr", req.scheme(), req.host()))
                    .speech(speechSettings)
                    .build();


            res.type("application/json");

            return new Ncco(intro, input).toJson();
        };

        /*
         * Route which returns NCCO saying which word was recognized.
         */
        Route speechInputRoute = (req, res) -> {
            EventWebhook event = EventWebhook.fromJson(req.body());

            TalkAction response = TalkAction.builder(String.format("You said %s, Goodbye.",
                    event.getSpeech().getResults().get(0).getText()
            )).build();
            res.type("application/json");

            return new Ncco(response).toJson();
        };

        Spark.port(3000);
        Spark.get("/webhooks/answer", answerCallRoute);
        Spark.post("/webhooks/asr", speechInputRoute);
    }
}
