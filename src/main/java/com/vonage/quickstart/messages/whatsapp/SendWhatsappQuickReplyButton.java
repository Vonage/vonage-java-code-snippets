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
package com.vonage.quickstart.messages.whatsapp;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.MessageType;
import com.vonage.client.messages.whatsapp.Locale;
import com.vonage.client.messages.whatsapp.Policy;
import com.vonage.client.messages.whatsapp.WhatsappCustomRequest;
import static com.vonage.quickstart.Util.envVar;
import java.util.List;
import java.util.Map;

public class SendWhatsappQuickReplyButton {
	private static final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
	private static final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
	private static final String VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER");
	private static final String TO_NUMBER = envVar("TO_NUMBER");
	private static final String WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME");
	private static final String WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE");

	public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var response = client.getMessagesClient().sendMessage(
			WhatsappCustomRequest.builder()
				.from(VONAGE_WHATSAPP_NUMBER).to(TO_NUMBER)
				.custom(Map.of(
						"type", MessageType.TEMPLATE,
						"template", Map.of(
							"namespace", WHATSAPP_TEMPLATE_NAMESPACE,
							"name", WHATSAPP_TEMPLATE_NAME,
							"language", Map.of(
								"code", Locale.ENGLISH,
								"policy", Policy.DETERMINISTIC
							),
							"components", List.of(
								Map.of(
									"type", "header",
									"parameters", List.of(
										Map.of(
											"type", MessageType.TEXT,
											"text", "12/26"
										)
									)
								),
								Map.of(
									"type", "body",
									"parameters", List.of(
										Map.of(
											"type", MessageType.TEXT,
											"text", "*Ski Trip*"
										),
										Map.of(
											"type", MessageType.TEXT,
											"text", "2019-12-26"
										),
										Map.of(
											"type", MessageType.TEXT,
											"text", "*Squaw Valley Ski Resort, Tahoe*"
										)
									)
								),
								Map.of(
									"type", MessageType.BUTTON,
									"sub_type", "quick_reply",
									"index", 0,
									"parameters", List.of(
										Map.of(
											"type", "payload",
											"payload", "Yes-Button-Payload"
										)
									)
								),
								Map.of(
									"type", MessageType.BUTTON,
									"sub_type", "quick_reply",
									"index", 1,
									"parameters", List.of(
										Map.of(
											"type", "payload",
											"payload", "No-Button-Payload"
										)
									)
								)
							)
						)
				)).build()
		);
		System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
	}
}
