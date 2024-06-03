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
package com.vonage.quickstart.numbers;

import com.vonage.client.VonageClient;
import com.vonage.client.VonageClientException;
import com.vonage.client.VonageResponseParseException;
import com.vonage.client.numbers.NumbersClient;
import com.vonage.client.numbers.UpdateNumberRequest;
import static com.vonage.quickstart.Util.configureLogging;
import static com.vonage.quickstart.Util.envVar;

public class UpdateNumber {
    private static final String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");
    private static final String COUNTRY_CODE = envVar("COUNTRY_CODE");
    private static final String VONAGE_NUMBER = envVar("VONAGE_NUMBER");
    private static final String SMS_CALLBACK_URL = envVar("SMS_CALLBACK_URL");
    private static final UpdateNumberRequest.CallbackType VOICE_CALLBACK_TYPE = UpdateNumberRequest.CallbackType.valueOf(envVar("VOICE_CALLBACK_TYPE"));
    private static final String VOICE_CALLBACK_VALUE = envVar("VOICE_CALLBACK_VALUE");
    private static final String VOICE_STATUS_URL = envVar("VOICE_STATUS_URL");

    public static void main(String[] args) {
        configureLogging();

        VonageClient client = VonageClient.builder()
                .apiKey(VONAGE_API_KEY)
                .apiSecret(VONAGE_API_SECRET)
                .build();

        NumbersClient numbersClient = client.getNumbersClient();

        UpdateNumberRequest request = new UpdateNumberRequest(VONAGE_NUMBER, COUNTRY_CODE);
        request.setVoiceCallbackType(VOICE_CALLBACK_TYPE);
        request.setVoiceCallbackValue(VOICE_CALLBACK_VALUE);
        request.setVoiceStatusCallback(VOICE_STATUS_URL);
        request.setMoHttpUrl(SMS_CALLBACK_URL);

        try {
            numbersClient.updateNumber(request);
        } catch (VonageClientException | VonageResponseParseException e) {
            System.out.println("Error updating number.");
        }

        System.out.println("Number updated.");
    }
}
