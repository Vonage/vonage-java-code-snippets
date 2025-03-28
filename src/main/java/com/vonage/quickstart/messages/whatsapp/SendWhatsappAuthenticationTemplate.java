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
import com.vonage.client.common.MessageType;
import com.vonage.client.messages.whatsapp.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import java.util.List;
import java.util.Map;

public class SendWhatsappAuthenticationTemplate {
    public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var response = client.getMessagesClient().sendMessage(
			WhatsappCustomRequest.builder()
				.from(WHATSAPP_SENDER_ID).to(MESSAGES_TO_NUMBER)
				.custom(Map.of(
						"type", MessageType.TEMPLATE,
						"template", Map.of(
							"name", WHATSAPP_AUTH_TEMPLATE_NAME,
							"language", Map.of(
								"policy", Policy.DETERMINISTIC,
								"code", Locale.ENGLISH
							),
							"components", List.of(
								Map.of(
									"type", "body",
									"parameters", List.of(
										Map.of(
											"type", MessageType.TEXT,
											"text", WHATSAPP_OTP
										)
									)
								),
								Map.of(
									"type", MessageType.BUTTON,
									"sub_type", "url",
									"index", 0,
									"parameters", List.of(
										Map.of(
											"type", MessageType.TEXT,
											"text", WHATSAPP_OTP
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
