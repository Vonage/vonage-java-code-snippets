package com.vonage.quickstart.voice;

import com.nexmo.client.VonageClient;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;
import com.nexmo.client.voice.VoiceName;
import com.vonage.quickstart.Util;

public class SendTalkToCall {
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
        CallEvent call = client
                .getVoiceClient()
                .createCall(new Call(TO_NUMBER, VONAGE_NUMBER, "http://s3.sammachin.com/silent_loop.json"));

        Thread.sleep(5000);

        final String UUID = call.getUuid();
        final String TEXT = "Hello World! Would you like to know more? I bet you would";
        client.getVoiceClient().startTalk(UUID, TEXT, VoiceName.KIMBERLY, 0);

        Thread.sleep(5000);
        client.getVoiceClient().stopTalk(UUID);
    }
}
