package com.vonage.quickstart.voice;

import com.nexmo.client.VonageClient;
import com.nexmo.client.voice.Call;
import com.vonage.quickstart.Util;

public class OutboundTextToSpeechWithEventUrl {
    public static void main(String[] args) throws Exception {
        Util.configureLogging();

        String VONAGE_NUMBER = Util.envVar("VONAGE_NUMBER");
        String TO_NUMBER = Util.envVar("TO_NUMBER");
        String EVENT_URL = Util.envVar("EVENT_URL");

        final String VONAGE_APPLICATION_ID = Util.envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = Util.envVar("VONAGE_PRIVATE_KEY_PATH");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        Call call = new Call(
                TO_NUMBER,
                VONAGE_NUMBER,
                "https://nexmo-community.github.io/ncco-examples/first_call_talk.json"
        );
        call.setEventUrl(EVENT_URL);
        client.getVoiceClient().createCall(call);
    }
}
