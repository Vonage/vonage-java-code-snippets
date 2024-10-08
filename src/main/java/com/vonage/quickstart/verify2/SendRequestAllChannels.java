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
package com.vonage.quickstart.verify2;

import com.vonage.client.VonageClient;
import com.vonage.client.verify2.*;
import static com.vonage.quickstart.Util.envVar;
import java.util.List;

public class SendRequestAllChannels {
	private static final String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
	private static final String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
	private static final String BRAND_NAME = envVar("BRAND_NAME");
	private static final String TO_NUMBER = envVar("TO_NUMBER");
	private static final String WHATSAPP_BUSINESS_NUMBER = envVar("WHATSAPP_BUSINESS_NUMBER");
	private static final String TO_EMAIL = envVar("TO_EMAIL");

	public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var request = VerificationRequest.builder()
				.workflows(List.of(
						new SilentAuthWorkflow(TO_NUMBER),
						new WhatsappCodelessWorkflow(TO_NUMBER, WHATSAPP_BUSINESS_NUMBER),
						new EmailWorkflow(TO_EMAIL),
						new WhatsappWorkflow(TO_NUMBER, WHATSAPP_BUSINESS_NUMBER),
						new SmsWorkflow(TO_NUMBER),
						new VoiceWorkflow(TO_NUMBER)
				))
				.codeLength(7)
				.brand(BRAND_NAME)
				.locale("en-gb")
				.channelTimeout(120)
				.build();

		var requestId = client.getVerify2Client().sendVerification(request).getRequestId();
		System.out.println("Verification sent: "+requestId);
	}
}
