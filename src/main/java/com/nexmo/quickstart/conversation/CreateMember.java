package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.CreateMemberRequest;
import com.nexmo.client.conversation.Channel;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class CreateMember {
    public static void main(String[] args) throws Exception {
        configureLogging();
        final String CONVERSATION_ID="CON-0bccb43e-f7e1-4b4c-889e-f7d3776ac0c3";
        final String USER_NAME = "Bob Barker";
        final String CHANNEL_TYPE = "app";
        final String ACTION = "invite";

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");

        CreateMemberRequest member = CreateMemberRequest.builder().
                conversation_id(CONVERSATION_ID).
                user_name(USER_NAME).
                channel(new Channel(CHANNEL_TYPE)).
                action(ACTION).
                build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().CreateMember(member);

    }
}
