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
package com.vonage.quickstart.messages.viber;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.viber.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class SendViberVideo {
    public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		var response = client.getMessagesClient().sendMessage(
				ViberVideoRequest.builder()
					.to(TO_NUMBER)
					.from(VONAGE_VIBER_SERVICE_MESSAGE_ID)
					.url("https://example.com/video.mp4")
					.thumbUrl("https://example.com/image.jpg")
					.category(Category.TRANSACTION)
					.fileSize(42).duration(35).ttl(86400)
					.build()
		);
		System.out.println("Message sent successfully. ID: "+response.getMessageUuid());
	}
}
