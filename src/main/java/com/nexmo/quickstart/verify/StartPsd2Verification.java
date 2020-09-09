package com.nexmo.quickstart.verify;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.verify.VerifyResponse;
import com.nexmo.client.verify.VerifyStatus;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class StartPsd2Verification {
    public static void main(String[] args) {
        configureLogging();

        String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
        String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
        String RECIPIENT_NUMBER = envVar("RECIPIENT_NUMBER");
        String PAYEE = envVar("PAYEE");
        double AMOUNT = Double.parseDouble(envVar("AMOUNT"));


        NexmoClient client = NexmoClient.builder().apiKey(NEXMO_API_KEY).apiSecret(NEXMO_API_SECRET).build();
        VerifyResponse response = client.getVerifyClient().psd2Verify(RECIPIENT_NUMBER, AMOUNT, PAYEE);

        if (response.getStatus() == VerifyStatus.OK) {
            System.out.printf("Request ID: %s", response.getRequestId());
        } else {
            System.out.printf("Error: %s: %s", response.getStatus(), response.getErrorText());
        }
    }
}
