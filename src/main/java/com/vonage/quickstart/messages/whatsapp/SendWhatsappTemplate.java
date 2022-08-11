/*
 * Copyright  2022 Vonage
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
package com.vonage.quickstart.messages.whatsapp;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageResponse;
import com.vonage.client.messages.MessageResponseException;
import com.vonage.client.messages.MessagesClient;
import com.vonage.client.messages.whatsapp.Locale;
import com.vonage.client.messages.whatsapp.Policy;
import com.vonage.client.messages.whatsapp.WhatsappTemplateRequest;

import java.util.List;
import java.util.Map;

import static com.vonage.quickstart.Util.configureLogging;
import static com.vonage.quickstart.Util.envVar;

public class SendWhatsappTemplate {

	public static void main(String[] args) throws Exception {
		configureLogging();

		String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
		String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
		String VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER");
		String TO_NUMBER = envVar("TO_NUMBER");
		String WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE");
		String WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAMES");

		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		MessagesClient messagesClient = client.getMessagesClient();

		var message = WhatsappTemplateRequest.builder()
				.from(VONAGE_WHATSAPP_NUMBER).to(TO_NUMBER)
				.policy(Policy.DETERMINISTIC).locale(Locale.ENGLISH_UK)
				.name(WHATSAPP_TEMPLATE_NAMESPACE+':'+WHATSAPP_TEMPLATE_NAME)
				.parameters(List.of(
					"Vonage Verification",
					"64873",
					"10"
				))
				.build();

		try {
			MessageResponse response = messagesClient.sendMessage(message);
			System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
		}
		catch (MessageResponseException mrx) {
			switch (mrx.getStatusCode()) {
				default: throw mrx;
				case 401: // Bad credentials
					throw new IllegalStateException(mrx.getTitle(), mrx);
				case 422: // Invalid
					throw new IllegalStateException(mrx.getDetail(), mrx);
				case 402: // Low balance
					client.getAccountClient().topUp("transactionID");
					break;
				case 429: // Rate limit
					Thread.sleep(12_000);
					break;
			}
		}
	}
}
