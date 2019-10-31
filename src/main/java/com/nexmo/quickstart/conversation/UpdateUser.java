package com.nexmo.quickstart.conversation;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.conversation.User;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class UpdateUser {
    public static void main(String[] args) throws Exception{
        configureLogging();

        final String USER_ID = "USR-fcf0506a-93a2-40fe-98ec-c3eab75fa838";

        final String NEXMO_APPLICATION_ID = envVar("NEXMO_APPLICATION_ID");
        final String NEXMO_PRIVATE_KEY_PATH = envVar("NEXMO_PRIVATE_KEY_PATH");
        final String NAME = "Bob Barker";
        final String DISPLAY_NAME = "Bob";
        final String IMAGE_URL = "https://www.example.com/image.png";

        User user = User.builder().
                name(NAME).
                display_name(DISPLAY_NAME).
                image_url(IMAGE_URL).
                build();

        user.setId(USER_ID);

        NexmoClient client = NexmoClient.builder().
                applicationId(NEXMO_APPLICATION_ID).
                privateKeyPath(NEXMO_PRIVATE_KEY_PATH).
                build();

        client.getConversationsClient().UpdateConversation(user);
    }
}
