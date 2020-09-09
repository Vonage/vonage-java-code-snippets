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
package com.vonage.quickstart.voice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vonage.client.VonageClient;
import com.vonage.client.voice.CallInfoPage;
import com.vonage.client.voice.CallsFilter;
import com.vonage.quickstart.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class RetrieveInfoForAllCalls {
    public static void main(String... args) throws Exception {
        Util.configureLogging();

        final String VONAGE_APPLICATION_ID = Util.envVar("VONAGE_APPLICATION_ID");
        final String VONAGE_PRIVATE_KEY_PATH = Util.envVar("VONAGE_PRIVATE_KEY_PATH");

        VonageClient client = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build();

        CallsFilter filter = CallsFilter.builder()
                .dateStart(getYesterdaysDate())
                .dateEnd(getTodaysDate())
                .build();

        CallInfoPage calls = client.getVoiceClient().listCalls(filter);

        // com.fasterxml.jackson.databind.ObjectMapper;
        System.out.println(new ObjectMapper().writer().writeValueAsString(calls));
    }

    private static Date getTodaysDate() {
        return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    }

    private static Date getYesterdaysDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }
}
