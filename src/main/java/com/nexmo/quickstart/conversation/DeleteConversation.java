package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class DeleteConversation {
    public static void main(String[] args) throws Exception{
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String CONVERSATION_ID = "123456";
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().DeleteConversation(CONVERSATION_ID);

    }
}
