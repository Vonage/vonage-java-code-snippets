package com.vonage.quickstart.video;

import com.vonage.client.VonageClient;
import com.vonage.client.video.*;
import static com.vonage.quickstart.EnvironmentVariables.*;

import java.net.URI;

public class SipDial {
    public static void main(String[] args) throws Exception {

        VideoClient videoClient = VonageClient.builder()
                .applicationId(VONAGE_APPLICATION_ID)
                .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
                .build().getVideoClient();

        SipDialRequest request = SipDialRequest.builder()
                .uri(URI.create("sip:user@sip.partner.com"), false)
                .sessionId(VIDEO_SESSION_ID).token(VIDEO_TOKEN).build();

        SipDialResponse parsed = videoClient.sipDial(request);

        System.out.println("Dialed sip");
    }
}
