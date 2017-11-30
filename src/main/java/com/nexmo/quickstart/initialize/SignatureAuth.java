package com.nexmo.quickstart.initialize;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.SignatureAuthMethod;

import static com.nexmo.quickstart.Util.envVar;


/**
 * Example of configuring a NexmoClient with signature authentication.
 */
public class SignatureAuth {
    public static void main(String[] argv) throws Exception {
        String NEXMO_API_KEY = envVar("API_KEY");
        String NEXMO_SIGNATURE_SECRET = envVar("SIGNATURE_SECRET");

        AuthMethod signatureAuth = new SignatureAuthMethod(NEXMO_API_KEY, NEXMO_SIGNATURE_SECRET);
        NexmoClient client = new NexmoClient(signatureAuth);
    }
}
