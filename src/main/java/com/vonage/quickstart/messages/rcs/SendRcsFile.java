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
import com.vonage.client.messages.rcs.RcsFileRequest;
import static com.vonage.quickstart.Util.envVar;

public class SendRcsFile {
	private static final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
	private static final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
	private static final String RCS_SENDER_ID = envVar("RCS_SENDER_ID");
	private static final String TO_NUMBER = envVar("TO_NUMBER");
	private static final String FILE_URL = envVar("FILE_URL");

	public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var response = client.getMessagesClient().sendMessage(
			RcsFileRequest.builder()
				.from(RCS_SENDER_ID).to(TO_NUMBER)
				.url(FILE_URL)
				.build()
		);
		System.out.println("Message sent successfully. ID: " + response.getMessageUuid());
	}
}
