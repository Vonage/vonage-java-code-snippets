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
package com.vonage.quickstart.numbers;

import com.nexmo.client.VonageClient;
import com.nexmo.client.VonageClientException;
import com.nexmo.client.VonageResponseParseException;
import com.nexmo.client.numbers.NumbersClient;
import com.nexmo.client.numbers.UpdateNumberRequest;
import com.vonage.quickstart.Util;

public class UpdateNumber {
    private static final String VONAGE_API_KEY = Util.envVar("VONAGE_API_KEY");
    private static final String VONAGE_API_SECRET = Util.envVar("VONAGE_API_SECRET");
    private static final String COUNTRY_CODE = Util.envVar("COUNTRY_CODE");
    private static final String VONAGE_NUMBER = Util.envVar("VONAGE_NUMBER");
    private static final String SMS_CALLBACK_URL = Util.envVar("SMS_CALLBACK_URL");
    private static final UpdateNumberRequest.CallbackType VOICE_CALLBACK_TYPE = UpdateNumberRequest.CallbackType.valueOf(Util.envVar("VOICE_CALLBACK_TYPE"));
    private static final String VOICE_CALLBACK_VALUE = Util.envVar("VOICE_CALLBACK_VALUE");
    private static final String VOICE_STATUS_URL = Util.envVar("VOICE_STATUS_URL");

    public static void main(String[] args) {
        Util.configureLogging();

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
