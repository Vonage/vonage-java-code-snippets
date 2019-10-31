package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.Conversation;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class UpdateConversation {
    public static void main(String[] args) throws Exception{
        configureLogging();
        final String CONVERSATION_ID = "123456";
        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        Conversation conversation = Conversation.builder().
                name("steve lorello").
                displayName("steve").
                imageUrl("httpw://www.example.com").
                build();
        conversation.setId(CONVERSATION_ID);
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().UpdateConversation(conversation);
    }
}
