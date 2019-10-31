package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.SpecificMemberRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class GetMember {
    public static void main(String[] args) throws Exception{
        configureLogging();

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String CONVERSATION_ID = "CON-0bccb43e-f7e1-4b4c-889e-f7d3776ac0c3";
        final String MEMBER_ID = "MEM-0bccb43e-f7e1-4b4c-889e-f7d3776dabcd";
        SpecificMemberRequest request = SpecificMemberRequest.
                builder().
                conversation_id(CONVERSATION_ID).
                member_id(MEMBER_ID).
                build();
        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();
        client.getConversationsClient().GetMember(request);

    }
}
