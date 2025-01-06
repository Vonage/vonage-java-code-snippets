/*
 * Copyright 2025 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vonage.quickstart.users;

import com.vonage.client.VonageClient;
import com.vonage.client.users.User;
import com.vonage.client.users.channels.*;
import static com.vonage.quickstart.Util.envVar;

public class CreateUser {
    private static final String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");
    private static final String USER_NAME = envVar("USER_NAME");
    private static final String USER_DISPLAY_NAME = envVar("USER_NAME");

    public static void main(String... args) {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        User user = client.getUsersClient().createUser(
            User.builder()
                .name(USER_NAME)
                .displayName(USER_DISPLAY_NAME)
                .imageUrl("https://example.com/profile.jpg")
                .channels(
                    new Pstn("448001234567"),
                    new Sms("447700900000"),
                    new Viber("447700900000"),
                    new Whatsapp("447700900000"),
                    new Viber("447700900000"),
                    new Messenger("12345abcd"),
                    new Vbc(123),
                    new Sip("sip:4442138907@sip.example.com;transport=tls", "myUserName", "P@ssw0rd"),
                    new Websocket("wss://example.com/socket")
                )
                .build()
        );
    }
}
