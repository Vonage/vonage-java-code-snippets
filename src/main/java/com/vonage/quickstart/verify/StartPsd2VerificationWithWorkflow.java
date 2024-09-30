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
package com.vonage.quickstart.verify;

import com.vonage.client.VonageClient;
import com.vonage.client.verify.*;
import static com.vonage.quickstart.Util.envVar;

public class StartPsd2VerificationWithWorkflow {
    private static final String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");
    private static final String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
    private static final String PAYEE_NAME = envVar("PAYEE_NAME");
    private static final Double AMOUNT = Double.valueOf(envVar("AMOUNT"));

    public static void main(String[] args) {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        VerifyResponse response = client.getVerifyClient().psd2Verify(
                RECIPIENT_NUMBER, AMOUNT, PAYEE_NAME, Psd2Request.Workflow.SMS_SMS
        );

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("Request ID: %s", response.getRequestId());
        }
        else {
            System.out.printf("Error: %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
