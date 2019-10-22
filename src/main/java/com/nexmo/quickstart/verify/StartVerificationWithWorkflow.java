package com.nexmo.quickstart.verify;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;
import com.nexmo.client.verify.VerifyRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartVerificationWithWorkflow {
    public static void main(String[] args) throws Exception {
        configureLogging();

        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");

        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String NEXMO_BRAND = envVar("BRAND_NAME");

        VerifyRequest request = new VerifyRequest(RECIPIENT_NUMBER,NEXMO_BRAND);

        request.setWorkflow(VerifyRequest.Workflow.TTS_TTS);

        NexmoClient client = NexmoClient.builder().apiKey(NEXMO_API_KEY).apiSecret(NEXMO_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient().verify(request);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("RequestID: %s", response.getRequestId());
        } else {
            System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
