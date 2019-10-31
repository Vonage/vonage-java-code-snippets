package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.CursorListFilter;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class ListUsers {
    public static void main(String[] args) throws Exception{
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final int PAGE_SIZE = 2;
        final String ORDER = "asc";
        final String CURSOR = "https://www.example.com";
        CursorListFilter filter = CursorListFilter.
                builder().
                page_size(PAGE_SIZE).
                order(ORDER).
                cursor(CURSOR).build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().ListUsers(filter);
    }
}
