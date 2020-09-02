package com.vonage.quickstart.verify;

import com.nexmo.client.VonageClient;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;
import com.nexmo.client.verify.VerifyRequest;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartVerificationWithWorkflow {
    public static void main(String[] args) {
        configureLogging();

        String VONAGE_API_KEY = envVar("VONAGE_API_KEY");
        String VONAGE_API_SECRET = envVar("VONAGE_API_SECRET");

        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String VONAGE_BRAND = envVar("BRAND_NAME");

        VerifyRequest request = new VerifyRequest(RECIPIENT_NUMBER,VONAGE_BRAND);

        request.setWorkflow(VerifyRequest.Workflow.TTS_TTS);

        VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient().verify(request);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("RequestID: %s", response.getRequestId());
        } else {
            System.out.printf("ERROR! %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
