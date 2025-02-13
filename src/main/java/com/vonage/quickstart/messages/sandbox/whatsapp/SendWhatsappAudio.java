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
package com.vonage.quickstart.messages.sandbox.whatsapp;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.whatsapp.WhatsappAudioRequest;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class SendWhatsappAudio {
    public static void main(String[] args) throws Exception {
		
		System.out.println(VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build()
				.getMessagesClient()
				.useSandboxEndpoint()
				.sendMessage(WhatsappAudioRequest.builder()
					.from(MESSAGES_SANDBOX_WHATSAPP_NUMBER)
					.to(MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER)
					.url(MESSAGES_AUDIO_URL)
					.build()
				).getMessageUuid()
		);
	}
}
