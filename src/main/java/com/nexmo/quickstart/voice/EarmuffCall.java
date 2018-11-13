package com.nexmo.quickstart.voice;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;
import com.nexmo.client.voice.ModifyCallAction;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class EarmuffCall {
    public static void main(String... args) throws Exception {
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        NexmoClient client = new NexmoClient.Builder()
                .applicationId(NEXMO_APPLICATION_ID)
                .privateKeyPath(NEXMO_PRIVATE_KEY_PATH)
                .build();

        final String NEXMO_NUMBER = envVar("NEXMO_NUMBER");
        final String TO_NUMBER = envVar("TO_NUMBER");
        /*
        Establish a call for testing purposes.
         */
        CallEvent call = client.getVoiceClient().createCall(new Call(
                TO_NUMBER,
                NEXMO_NUMBER,
                "https://gist.githubusercontent.com/cr0wst/9417cac4c0d9004805a04aed403ae94a/raw/b95e3cd5126587d25986e0bf832eb33a7538394d/tts_long.json"
        ));

        /*
        Give them time to answer.
         */
        Thread.sleep(10000);

        final String UUID = call.getUuid();
        client.getVoiceClient().modifyCall(UUID, ModifyCallAction.EARMUFF);
        Thread.sleep(3000);
        client.getVoiceClient().modifyCall(UUID, ModifyCallAction.UNEARMUFF);
    }
}
