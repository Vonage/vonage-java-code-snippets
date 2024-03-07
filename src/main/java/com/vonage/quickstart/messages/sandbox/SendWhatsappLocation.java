/*
 * Copyright 2023 Vonage
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
package com.vonage.quickstart.messages.sandbox;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.whatsapp.WhatsappLocationRequest;
import static com.vonage.quickstart.Util.*;

public class SendWhatsappLocation {

	public static void main(String[] args) throws Exception {
		System.out.println(VonageClient.builder()
				.apiKey(envVar("VONAGE_API_KEY"))
				.apiSecret(envVar("VONAGE_API_SECRET"))
				.build()
				.getMessagesClient()
				.sendMessage(WhatsappLocationRequest.builder()
						.to(envVar("TO_NUMBER"))
						.from(envVar("VONAGE_WHATSAPP_NUMBER"))
						.longitude(-122.1503115)
						.latitude(37.4843538)
						.name("Facebook HQ")
						.address("1 Hacker Way, Menlo Park, CA 94025")
					.build()
				).getMessageUuid()
		);
	}
}
