package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.ListEventRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class ListEvents {
    public static void main(String[] args) throws Exception{
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String CONVERSATION_ID = "CON-0bccb43e-f7e1-4b4c-889e-f7d3776ac0c3";
        final String start_id = "1";
        final String end_id = "2";
        ListEventRequest filter = ListEventRequest.
                builder().
                start_id(start_id).
                end_id(end_id).
                conversation_id(CONVERSATION_ID).
                build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().ListEvents(filter);
    }
}
