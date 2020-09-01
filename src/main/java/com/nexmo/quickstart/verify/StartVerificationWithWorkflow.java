package com.nexmo.quickstart.verify;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;
import com.nexmo.client.verify.VerifyRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartVerificationWithWorkflow {
    public static void main(String[] args) {
        configureLogging();

        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");

        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String NEXMO_BRAND = envVar("BRAND_NAME");

        NexmoClient client = NexmoClient.builder().apiKey(NEXMO_API_KEY).apiSecret(NEXMO_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient().verify(RECIPIENT_NUMBER,NEXMO_BRAND, VerifyRequest.Workflow.TTS_TTS);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("RequestID: %s", response.getRequestId());
        } else {
            System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
