package com.nexmo.quickstart.voice;

import com.nexmo.client.incoming.DtmfOutput;
import com.nexmo.client.incoming.InputEvent;
import com.nexmo.client.voice.ncco.*;
import spark.Route;
import spark.Spark;

import java.util.ArrayList;
import java.util.Arrays;
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

            SpeechSettings speechSettings = new SpeechSettings();
            speechSettings.setUuid(new ArrayList<>(Collections.singletonList(req.queryParams("uuid"))));
            speechSettings.setStartTimeout(5);
            speechSettings.setLanguage(SpeechSettings.Language.ENGLISH_UNITED_STATES);

            InputAction input = InputAction.builder()
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
            InputEvent<DtmfOutput> event = InputEvent.fromJson(req.body());

            TalkAction response = TalkAction.builder(String.format("You said %s, Goodbye.",
                    event.getSpeech().getResults().iterator().next().getText()
            )).build();
            res.type("application/json");

            return new Ncco(response).toJson();
        };

        Spark.port(3000);
        Spark.get("/webhooks/answer", answerCallRoute);
        Spark.post("/webhooks/asr", speechInputRoute);
    }
}
