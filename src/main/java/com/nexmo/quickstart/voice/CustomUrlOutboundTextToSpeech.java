package com.nexmo.quickstart.voice;

import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.JWTAuthMethod;
import com.nexmo.client.voice.Call;

import java.nio.file.FileSystems;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class CustomUrlOutboundTextToSpeech {
    public static void main(String[] args) throws Exception {
        configureLogging();

        String APPLICATION_ID = envVar("APPLICATION_ID");
        String PRIVATE_KEY = envVar("PRIVATE_KEY");
        String FROM_NUMBER = envVar("FROM_NUMBER");
        String TO_NUMBER = envVar("TO_NUMBER");

        AuthMethod auth = new JWTAuthMethod(
                APPLICATION_ID,
                FileSystems.getDefault().getPath(PRIVATE_KEY)
        );

        CustomNexmoClient customNexmoClient = new CustomNexmoClient(auth);
        customNexmoClient.getVoiceClient().createCall(
                new Call(TO_NUMBER,
                        FROM_NUMBER,
                        "https://nexmo-community.github.io/ncco-examples/first_call_talk.json")
        );
    }
}
