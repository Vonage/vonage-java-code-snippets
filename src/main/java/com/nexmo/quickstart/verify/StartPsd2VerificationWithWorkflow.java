package com.nexmo.quickstart.verify;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.BaseRequest;
import com.nexmo.client.verify.Psd2Request;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartPsd2VerificationWithWorkflow {
    public static void main(String[] args) {
        configureLogging();

        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String PAYEE = envVar("PAYEE");
        String AMOUNT = envVar("AMOUNT");



        NexmoClient client = NexmoClient.builder().apiKey(NEXMO_API_KEY).apiSecret(NEXMO_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient()
                .psd2Verify(RECIPIENT_NUMBER, Double.parseDouble(AMOUNT), PAYEE, Psd2Request.Workflow.SMS_SMS);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("Request ID: %s", response.getRequestId());
        } else {
            System.out.printf("Error: %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
