package com.vonage.quickstart.voice;

import com.nexmo.client.VonageClient;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;
import com.vonage.quickstart.Util;

public class TransferCall {
    public static void main(String... args) throws Exception {
        Util.configureLogging();

        final String VONAGE_APPLICATION_ID = Util.envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = Util.envVar("VONAGE_PRIVATE_KEY_PATH");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        final String VONAGE_NUMBER = Util.envVar("VONAGE_NUMBER");
        final String TO_NUMBER = Util.envVar("TO_NUMBER");

        /*
        Establish a call for testing purposes.
         */
        CallEvent call = client.getVoiceClient().createCall(new Call(
                TO_NUMBER,
                VONAGE_NUMBER,
                "https://gist.githubusercontent.com/cr0wst/9417cac4c0d9004805a04aed403ae94a/raw/b95e3cd5126587d25986e0bf832eb33a7538394d/tts_long.json"
        ));

        /*
        Give them time to answer.
         */
        Thread.sleep(10000);

        final String UUID = call.getUuid();
        final String NCCO_URL = "https://developer.nexmo.com/ncco/tts.json";
        client.getVoiceClient().transferCall(UUID, NCCO_URL);
    }
}
