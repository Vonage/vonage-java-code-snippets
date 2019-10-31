package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.Event;
import com.nexmo.client.conversation.TextEventBody;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class CreateEvent {
    public static void main(String[] args) throws Exception {
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String CONVERSATION_ID = "CON-0bccb43e-f7e1-4b4c-889e-f7d3776ac0c3";
        final String TYPE = "text";
        final String TEXT ="Hello World";
        final String TO ="MEM-fda88570-4307-44b4-b66d-3022f612879d";
        final String FROM ="MEM-fda88570-4307-44b4-b66d-3022f612879c";
        Event event = Event.builder().
                conversation_id(CONVERSATION_ID).
                type(TYPE).
                body(new TextEventBody(TEXT)).
                to(TO).
                from(FROM).
                build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().CreateEvent(event);

    }
}
