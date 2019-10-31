package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.Conversation;
import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class CreateConversation {
    public static void main(String[] args) throws Exception {
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String NAME = "customer_chat";
        final String DISPLAY_NAME = "Customer Chat";
        final String IMAGE_URL = "https://www.example.com/image.png";

        Conversation conversation = Conversation.builder().
                name(NAME).
                displayName(DISPLAY_NAME).
                imageUrl(IMAGE_URL).
                build();

        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().CreateConversation(conversation);
    }
}
