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
package com.vonage.quickstart.verify2;

import com.vonage.client.VonageClient;
import com.vonage.client.verify2.*;
import static com.vonage.quickstart.EnvironmentVariables.*;
import java.util.UUID;

public class CheckVerificationCode {
    public static void main(String[] args) throws Exception {
		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		try {
			client.getVerify2Client().checkVerificationCode(VERIFY_REQUEST_UUID, VERIFY_CODE);
			System.out.println("SUCCESS - code matches!");
		}
		catch (VerifyResponseException ex) {
			switch (ex.getStatusCode()) {
				case 400: // Code does not match
				case 404: // Already verified or not found
				case 409: // Workflow does not support code
				case 410: // Incorrect code provided too many times
				case 429: // Rate limit exceeded
				default:  // Unknown or internal server error (500)
					ex.printStackTrace();
			}
		}
	}
}
