package com.nexmo.quickstart.voice;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.ncco.Ncco;
import com.nexmo.client.voice.ncco.TalkAction;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class OutboundTextToSpeechWithNcco {
    public static void main(String[] args) throws Exception {
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        NexmoClient client = NexmoClient.builder()
                .applicationId(NEXMO_APPLICATION_ID)
                .privateKeyPath(NEXMO_PRIVATE_KEY_PATH)
                .build();

        final String NEXMO_NUMBER = envVar("NEXMO_NUMBER");
        final String TO_NUMBER = envVar("TO_NUMBER");

        Ncco ncco = new Ncco(TalkAction.builder("This is a text to speech call from Nexmo").build());

        client.getVoiceClient().createCall(new Call(TO_NUMBER, NEXMO_NUMBER, ncco));
    }
}
