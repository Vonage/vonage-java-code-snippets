package com.vonage.quickstart.voice;

import com.nexmo.client.VonageClient;
import com.nexmo.client.voice.Call;
import com.vonage.quickstart.Util;

public class OutboundTextToSpeech {
    public static void main(String[] args) throws Exception {
        Util.configureLogging();

        final String VONAGE_APPLICATION_ID = Util.envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = Util.envVar("VONAGE_PRIVATE_KEY_PATH");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        final String VONAGE_NUMBER = Util.envVar("VONAGE_NUMBER");
        final String TO_NUMBER = Util.envVar("TO_NUMBER");
        final String ANSWER_URL = "https://developer.nexmo.com/ncco/tts.json";

        client.getVoiceClient().createCall(new Call(TO_NUMBER, VONAGE_NUMBER, ANSWER_URL));
    }
}
