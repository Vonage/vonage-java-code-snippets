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
package com.vonage.quickstart.application;

import com.vonage.client.VonageClient;
import com.vonage.client.application.Application;
import com.vonage.client.application.ApplicationClient;
import com.vonage.client.application.capabilities.*;
import com.vonage.client.common.HttpMethod;
import com.vonage.client.common.Webhook;

import static com.vonage.quickstart.EnvironmentVariables.*;

public class UpdateApplication {
    public static void main(String... args) {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        ApplicationClient applicationClient = client.getApplicationClient();
        Application existingApplication = applicationClient.getApplication(VONAGE_APPLICATION_ID);

        Capability messages = Messages.builder()
                .addWebhook(Webhook.Type.INBOUND,
                        new Webhook("https://example.com/webhooks/inbound", HttpMethod.POST))
                .addWebhook(Webhook.Type.STATUS,
                        new Webhook("https://example.com/webhooks/status", HttpMethod.POST))
                .build();

        Capability voice = Voice.builder()
                .addWebhook(Webhook.Type.ANSWER,
                        new Webhook("https://example.com/webhooks/answer", HttpMethod.POST))
                .addWebhook(Webhook.Type.EVENT,
                        new Webhook("https://example.com/webhooks/event", HttpMethod.POST))
                .build();

        Capability rtc = Rtc.builder()
                .addWebhook(Webhook.Type.EVENT,
                        new Webhook("https://example.com/webhooks/event", HttpMethod.POST))
                .build();

        Capability vbc = Vbc.builder().build();

        Application application = applicationClient.updateApplication(
                Application.builder(existingApplication)
                        .name(APPLICATION_NAME)
                        .addCapability(messages)
                        .addCapability(voice)
                        .addCapability(rtc)
                        .addCapability(vbc)
                        .build()
        );

        System.out.println("Application Updated:");
        System.out.println("Old: " + existingApplication.toJson());
        System.out.println("New: " + application.toJson());
    }
}
