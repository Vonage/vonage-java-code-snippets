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
import com.vonage.client.messages.whatsapp.WhatsappCustomRequest;
import static com.vonage.quickstart.Util.*;
import static com.vonage.quickstart.Util.*;
import java.util.List;
import java.util.Map;

public class SendWhatsappContact {

	public static void main(String[] args) throws Exception {
		configureLogging();

		System.out.println(VonageClient.builder()
				.applicationId(envVar("VONAGE_APPLICATION_ID"))
				.privateKeyPath(envVar("VONAGE_PRIVATE_KEY_PATH"))
				.build()
				.getMessagesClient()
				.useSandboxEndpoint()
				.sendMessage(WhatsappCustomRequest.builder()
					.from(envVar("MESSAGES_SANDBOX_WHATSAPP_NUMBER"))
					.to(envVar("MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER"))
					.custom(Map.of(
						"type", "contacts",
						"contacts", List.of(Map.of(
							"addresses", List.of(
								Map.of(
									"city", "Menlo Park",
									"country", "United States",
									"state", "CA",
									"country_code", "us",
									"street", "1 Hacker Way",
									"type", "HOME",
									"zip", "94025"
								),
								Map.of(
									"city", "Menlo Park",
									"country", "United States",
									"state", "CA",
									"country_code", "us",
									"street", "200 Jefferson Dr",
									"type", "WORK",
									"zip", "94025"
								)
							),
							"birthday", "2012-08-18",
							"emails", List.of(
								Map.of(
									"email", "test@fb.com",
									"type", "WORK"
								),
								Map.of(
									"email", "test@whatsapp.com",
									"type", "WORK"
								)
							),
							Map.of("name", Map.of(
								"first_name", "Jayden",
								"last_name", "Smith",
								"formatted_name", "J. Smith"
							)),
							Map.of("org", Map.of(
								"company", "WhatsApp",
								"department", "Design",
								"title", "Manager"
							)),
							Map.of("phones", List.of(
								Map.of(
									"phone", "+1 (940) 555-1234",
									"type", "HOME"
								),
								Map.of(
									"phone", "+1 (650) 555-1234",
									"type", "WORK",
									"wa_id", "16505551234"
								)
							)),
							Map.of("urls", List.of(
								Map.of(
									"url", "https://www.facebook.com",
									"type", "WORK"
								)
							))
						))
				))
				.build()
			).getMessageUuid()
		);
	}
}
