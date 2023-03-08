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
import com.vonage.client.messages.whatsapp.WhatsappLocationRequest;
import com.vonage.client.messages.whatsapp.WhatsappSingleProductRequest;
import static com.vonage.quickstart.Util.configureLogging;
import static com.vonage.quickstart.Util.envVar;

public class SendWhatsappSingleProduct {

	public static void main(String[] args) throws Exception {
		configureLogging();

		String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
		String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
		String VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER");
		String TO_NUMBER = envVar("TO_NUMBER");
		String CATALOG_ID = envVar("CATALOG_ID");
		String PRODUCT_ID = envVar("PRODUCT_ID");

		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		MessagesClient messagesClient = client.getMessagesClient();

		var message = WhatsappSingleProductRequest.builder()
				.from(VONAGE_WHATSAPP_NUMBER).to(TO_NUMBER)
				.catalogId(CATALOG_ID)
				.productRetailerId(PRODUCT_ID)
				.bodyText("Check out this cool product")
				.footerText("Sale now on!")
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
