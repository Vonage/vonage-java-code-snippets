package com.nexmo.quickstart.voice;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.JWTAuthMethod;
import com.nexmo.client.voice.Call;

import java.nio.file.FileSystems;

public class OutboundTextToSpeech {
    public static void main(String[] args) throws Exception {
        String APPLICATION_ID = envVar("APPLICATION_ID");
        String PRIVATE_KEY_PATH = envVar("PRIVATE_KEY_PATH");
        String FROM_NUMBER = envVar("FROM_NUMBER");
        String TO_NUMBER = envVar("TO_NUMBER");

        AuthMethod auth = new JWTAuthMethod(
                APPLICATION_ID,
                FileSystems.getDefault().getPath(PRIVATE_KEY_PATH)
        );
        NexmoClient client = new NexmoClient(auth);
        client.getVoiceClient().createCall(new Call(
                TO_NUMBER,
                FROM_NUMBER,
                "https://nexmo-community.github.io/ncco-examples/first_call_talk.json"
        ));
    }

    private static String envVar(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new IllegalArgumentException("You must provide the " + key + " environment variable!");
        }
        return value;
    }
}
