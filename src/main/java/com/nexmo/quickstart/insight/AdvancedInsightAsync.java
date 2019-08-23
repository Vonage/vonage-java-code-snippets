/*
 * Copyright (c) 2011-2019 Nexmo Inc
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
package com.nexmo.quickstart.insight;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.insight.AdvancedInsightRequest;
import com.nexmo.client.insight.InsightClient;

public class AdvancedInsightAsync {
//    private static final String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
//    private static final String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
//    private static final String INSIGHT_NUMBER = envVar("INSIGHT_NUMBER");
//    private static final String ASYNC_CALLBACK_URL = envVar("ASYNC_CALLBACK_URL");

    private static final String NEXMO_API_KEY = "d9609dd5";
    private static final String NEXMO_API_SECRET = "owcxKS1UBPGqxPYV";
    private static final String INSIGHT_NUMBER = "12313929567";
    private static final String ASYNC_CALLBACK_URL = "https://cr0wst.ngrok.io/webhooks/insight";
    public static void main(String... args) {
        NexmoClient client = NexmoClient.builder()
                .apiKey(NEXMO_API_KEY)
                .apiSecret(NEXMO_API_SECRET)
                .build();

        InsightClient insightClient = client.getInsightClient();

        AdvancedInsightRequest request = AdvancedInsightRequest.builder(INSIGHT_NUMBER)
                .async(true)
                .callback(ASYNC_CALLBACK_URL)
                .build();
        insightClient.getAdvancedNumberInsight(request);
    }
}
