/*
 * Copyright 2024 Vonage
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
package com.vonage.quickstart.messages.rcs;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.rcs.RcsCustomRequest;
import static com.vonage.quickstart.Util.envVar;
import java.util.List;
import java.util.Map;

public class SendRcsRichCard {
	private static final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
	private static final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
	private static final String RCS_SENDER_ID = envVar("RCS_SENDER_ID");
	private static final String TO_NUMBER = envVar("TO_NUMBER");
	private static final String IMAGE_URL = envVar("IMAGE_URL");

	public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var response = client.getMessagesClient().sendMessage(
			RcsCustomRequest.builder()
				.from(RCS_SENDER_ID).to(TO_NUMBER)
				.custom(Map.of("contentMessage", Map.of(
						"richCard", Map.of("standaloneCard", Map.of(
							"thumbnailImageAlignment", "RIGHT",
							"cardOrientation", "VERTICAL",
							"cardContent", Map.of(
								"title", "Quick question",
								"description", "Do you like this picture?",
								"media", Map.of(
									"height", "TALL",
									"contentInfo", Map.of(
										"fileUrl", IMAGE_URL,
										"forceRefresh", "false"
									)
								),
								"suggestions", List.of(
									Map.of(
										"reply", Map.of(
											"text", "Yes",
											"postbackData", "suggestion_1"
										)
									),
									Map.of(
										"reply", Map.of(
											"text", "I love it!",
											"postbackData", "suggestion_2"
										)
									)
								)
							)
						))
					))
				).build()
		);
		System.out.println("Message sent successfully. ID: " + response.getMessageUuid());
	}
}