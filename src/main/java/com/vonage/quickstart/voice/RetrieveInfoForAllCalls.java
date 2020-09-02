package com.vonage.quickstart.voice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexmo.client.VonageClient;
import com.nexmo.client.voice.CallInfoPage;
import com.nexmo.client.voice.CallsFilter;
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
