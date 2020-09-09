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
package com.vonage.quickstart.insight;

import com.vonage.client.VonageClient;
import com.vonage.client.insight.AdvancedInsightRequest;
import com.vonage.client.insight.InsightClient;
import com.vonage.quickstart.Util;

public class AdvancedInsightAsync {
    private static final String VONAGE_API_KEY = Util.envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = Util.envVar("VONAGE_API_SECRET");
    private static final String INSIGHT_NUMBER = Util.envVar("INSIGHT_NUMBER");
    private static final String ASYNC_CALLBACK_URL = Util.envVar("ASYNC_CALLBACK_URL");

    public static void main(String... args) {
        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        InsightClient insightClient = client.getInsightClient();

        AdvancedInsightRequest request = AdvancedInsightRequest.builder(INSIGHT_NUMBER)
                .async(true)
                .callback(ASYNC_CALLBACK_URL)
                .build();
        insightClient.getAdvancedNumberInsight(request);
    }
}
