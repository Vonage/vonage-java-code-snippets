/*
 * Copyright  2020 Vonage
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
import com.vonage.client.verify.SearchVerifyResponse;
import com.vonage.client.verify.VerifyClient;
import com.vonage.client.verify.VerifyStatus;
import static com.vonage.quickstart.Util.envVar;

public class SearchVerification {
    private static final String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");
    private static final String REQUEST_ID = envVar("REQUEST_ID");

    public static void main(String... args) throws Exception {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        VerifyClient verifyClient = client.getVerifyClient();

        SearchVerifyResponse response = verifyClient.search(REQUEST_ID);
        if (response.getStatus() == VerifyStatus.OK) {
            response.getVerificationRequests().forEach(it -> {
                System.out.println(it.getRequestId() + " " + it.getStatus());
            });
        }
    }
}
