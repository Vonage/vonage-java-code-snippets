package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.CursorListFilter;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class ListMembers {

    public static void main(String[] args) throws Exception{
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final int PAGE_SIZE = 2;
        final String ORDER = "asc";
        final String CURSOR = "https://www.example.com";
        final String CONVERSATION_ID = "CON-0bccb43e-f7e1-4b4c-889e-f7d3776ac0c3";
        CursorListFilter filter = CursorListFilter.
                builder().
                page_size(PAGE_SIZE).
                order(ORDER).
                cursor(CURSOR).
                conversation_id(CONVERSATION_ID).
                build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().ListMembers(filter);
    }
}
