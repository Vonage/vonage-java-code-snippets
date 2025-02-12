/*
 * Copyright 2025 Vonage
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
package com.vonage.quickstart;

import com.vonage.client.ApiRegion;
import com.vonage.client.numbers.Feature;
import com.vonage.client.numbers.SearchPattern;
import com.vonage.client.numbers.Type;
import com.vonage.client.numbers.UpdateNumberRequest;
import com.vonage.client.verify.Psd2Request;
import com.vonage.client.verify.VerifyRequest;
import com.vonage.client.voice.TextToSpeechLanguage;
import io.github.cdimascio.dotenv.Dotenv;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains environment variables designed to be statically imported into the quickstart main classes.
 */
public final class EnvironmentVariables {
    private EnvironmentVariables() {}

    private static final Dotenv dotenv = Dotenv.load();

    static {
        if (System.getenv("QUICKSTART_DEBUG") != null) {
            Handler handler = new ConsoleHandler();
            handler.setLevel(Level.FINEST);
            Logger logger = Logger.getLogger("com.vonage");
            logger.setLevel(Level.FINEST);
            logger.addHandler(handler);
        }
    }

    /**
     * Look up a required environment variable and throw an
     * IllegalArgumentException if the variable is not set.
     *
     * @param key the name of the environment variable
     * @return the value
     */
    public static String envVar(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            throw new IllegalArgumentException("You must provide the " + key + " environment variable!");
        }
        return value;
    }

   public static final String
       VONAGE_API_KEY = envVar("VONAGE_API_KEY"),
       VONAGE_API_SECRET = envVar("VONAGE_API_SECRET"),
       VONAGE_SIGNATURE_SECRET = envVar("VONAGE_SIGNATURE_SECRET"),
       VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID"),
       VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH"),
       VONAGE_VIRTUAL_NUMBER = envVar("VONAGE_VIRTUAL_NUMBER"),
       ACCOUNT_ID = envVar("ACCOUNT_ID"),
       ACCOUNT_SECRET = envVar("ACCOUNT_SECRET"),
       ACCOUNT_SECRET_ID = envVar("ACCOUNT_SECRET_ID"),
       ACCOUNT_SMS_CALLBACK_URL = envVar("ACCOUNT_SMS_CALLBACK_URL"),
       APPLICATION_NAME = envVar("APPLICATION_NAME"),
       MESSAGES_TO_NUMBER = envVar("MESSAGES_TO_NUMBER"),
       MESSAGES_MESSAGE_ID = envVar("MESSAGES_MESSAGE_ID"),
       MESSAGES_IMAGE_URL = envVar("MESSAGES_IMAGE_URL"),
       MESSAGES_AUDIO_URL = envVar("MESSAGES_AUDIO_URL"),
       MESSAGES_VIDEO_URL = envVar("MESSAGES_VIDEO_URL"),
       MESSAGES_FILE_URL = envVar("MESSAGES_FILE_URL"),
       MESSAGES_VCARD_URL = envVar("MESSAGES_VCARD_URL"),
       MESSAGES_EMOJI = envVar("MESSAGES_EMOJI"),
       MESSAGES_CAPTION = envVar("MESSAGES_CAPTION"),
       SMS_SENDER_ID = envVar("SMS_SENDER_ID"),
       MMS_SENDER_ID = envVar("MMS_SENDER_ID"),
       RCS_SENDER_ID = envVar("RCS_SENDER_ID"),
       WHATSAPP_SENDER_ID = envVar("WHATSAPP_SENDER_ID"),
       VIBER_SENDER_ID = envVar("VIBER_SENDER_ID"),
       MESSENGER_SENDER_ID = envVar("MESSENGER_SENDER_ID"),
       MESSENGER_RECIPIENT_ID = envVar("MESSENGER_RECIPIENT_ID"),
       WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME"),
       WHATSAPP_OTP = envVar("WHATSAPP_OTP"),
       WHATSAPP_CATALOG_ID = envVar("WHATSAPP_CATALOG_ID"),
       WHATSAPP_PRODUCT_ID = envVar("WHATSAPP_PRODUCT_ID"),
       WHATSAPP_STICKER_ID = envVar("WHATSAPP_STICKER_ID"),
       WHATSAPP_STICKER_URL = envVar("WHATSAPP_STICKER_URL"),
       WHATSAPP_HEADER_IMAGE_URL = envVar("WHATSAPP_HEADER_IMAGE_URL"),
       WHATSAPP_TEMPLATE_NAMESPACE = envVar("WHATSAPP_TEMPLATE_NAMESPACE"),
       WHATSAPP_AUTH_TEMPLATE_NAME = envVar("WHATSAPP_AUTH_TEMPLATE_NAME"),
       WHATSAPP_TEMPLATE_REPLACEMENT_TEXT = envVar("WHATSAPP_TEMPLATE_REPLACEMENT_TEXT"),
       VIBER_THUMB_URL = envVar("VIBER_THUMB_URL"),
       MESSAGES_SANDBOX_VIBER_SERVICE_ID = envVar("MESSAGES_SANDBOX_VIBER_SERVICE_ID"),
       MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_TO_NUMBER"),
       MESSAGES_SANDBOX_FB_ID = envVar("MESSAGES_SANDBOX_FB_ID"),
       MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID = envVar("MESSAGES_SANDBOX_ALLOW_LISTED_FB_RECIPIENT_ID"),
       MESSAGES_SANDBOX_WHATSAPP_NUMBER = envVar("MESSAGES_SANDBOX_WHATSAPP_NUMBER"),
       NV_MSISDN = envVar("NV_MSISDN"),
       NV_REDIRECT_URI = envVar("NV_REDIRECT_URI"),
       SIMSWAP_MSISDN = envVar("SIMSWAP_MSISDN"),
       INSIGHT_NUMBER = envVar("INSIGHT_NUMBER"),
       INSIGHT_CALLBACK_URL = envVar("INSIGHT_CALLBACK_URL"),
       NUMBER_MSISDN = envVar("NUMBER_MSISDN"),
       NUMBER_COUNTRY_CODE = envVar("NUMBER_COUNTRY_CODE"),
       NUMBER_SEARCH_CRITERIA = envVar("NUMBER_SEARCH_CRITERIA"),
       NUMBER_SMS_CALLBACK_URL = envVar("NUMBER_SMS_CALLBACK_URL"),
       NUMBER_VOICE_CALLBACK_URL = envVar("NUMBER_VOICE_CALLBACK_URL"),
       NUMBER_VOICE_STATUS_CALLBACK_URL = envVar("NUMBER_VOICE_STATUS_CALLBACK_URL"),
       VONAGE_REDACT_ID = envVar("VONAGE_REDACT_ID"),
       SMS_TO_NUMBER = envVar("SMS_TO_NUMBER"),
       SUBACCOUNT_KEY = envVar("SUBACCOUNT_KEY"),
       SUBACCOUNT_NAME = envVar("SUBACCOUNT_NAME"),
       SUBACCOUNT_SECRET = envVar("SUBACCOUNT_SECRET"),
       USER_ID = envVar("USER_ID"),
       USER_NAME = envVar("USER_NAME"),
       USER_DISPLAY_NAME = envVar("USER_DISPLAY_NAME"),
       USER_NEW_NAME = envVar("USER_NEW_NAME"),
       USER_NEW_DISPLAY_NAME = envVar("USER_NEW_DISPLAY_NAME"),
       VERIFY_NUMBER = envVar("VERIFY_NUMBER"),
       VERIFY_BRAND_NAME = envVar("VERIFY_BRAND_NAME"),
       VERIFY_PAYEE_NAME = envVar("VERIFY_PAYEE_NAME"),
       VERIFY_REQUEST_ID = envVar("VERIFY_REQUEST_ID"),
       VERIFY_CODE = envVar("VERIFY_CODE"),
       VERIFY_TO_EMAIL = envVar("VERIFY_TO_EMAIL"),
       VERIFY_FROM_EMAIL = envVar("VERIFY_FROM_EMAIL"),
       VERIFY_WHATSAPP_NUMBER = envVar("VERIFY_WHATSAPP_NUMBER"),
       VERIFY_TEMPLATE_NAME = envVar("VERIFY_TEMPLATE_NAME"),
       VOICE_CALL_ID = envVar("VOICE_CALL_ID"),
       VOICE_TO_NUMBER = envVar("VOICE_TO_NUMBER"),
       VOICE_TEXT = envVar("VOICE_TEXT"),
       VOICE_DTMF_DIGITS = envVar("VOICE_DTMF_DIGITS"),
       VOICE_CONFERENCE_NAME = envVar("VOICE_CONFERENCE_NAME"),
       VOICE_NCCO_URL = envVar("VOICE_NCCO_URL"),
       VOICE_ANSWER_URL = envVar("VOICE_ANSWER_URL"),
       VOICE_EVENT_URL = envVar("VOICE_EVENT_URL"),
       VOICE_STREAM_URL = envVar("VOICE_STREAM_URL");

    public static final byte[]
        VONAGE_PRIVATE_KEY_CONTENTS = envVar("VONAGE_PRIVATE_KEY_CONTENTS").getBytes();

   public static final int
       VIBER_VIDEO_DURATION = Integer.parseInt(envVar("VIBER_VIDEO_DURATION")),
       VIBER_VIDEO_FILE_SIZE = Integer.parseInt(envVar("VIBER_VIDEO_FILE_SIZE")),
       VIBER_VIDEO_TTL = Integer.parseInt(envVar("VIBER_VIDEO_TTL")),
       SIMSWAP_MAX_AGE = Integer.parseInt(envVar("SIMSWAP_MAX_AGE"));

   public static final double
       SUBACCOUNT_BALANCE_AMOUNT = Double.parseDouble(envVar("SUBACCOUNT_BALANCE_AMOUNT")),
       SUBACCOUNT_CREDIT_AMOUNT = Double.parseDouble(envVar("SUBACCOUNT_CREDIT_AMOUNT")),
       VERIFY_AMOUNT = Double.parseDouble(envVar("VERIFY_AMOUNT"));

   public static final UUID
           VERIFY_REQUEST_UUID = UUID.fromString(VERIFY_REQUEST_ID),    // TODO temp workaround
           VERIFY_TEMPLATE_ID = UUID.fromString(envVar("VERIFY_TEMPLATE_ID")),
           VERIFY_TEMPLATE_FRAGMENT_ID = UUID.fromString(envVar("VERIFY_TEMPLATE_FRAGMENT_ID"));

   public static final Instant
       SUBACCOUNT_START_DATE = Instant.parse(envVar("SUBACCOUNT_START_DATE"));

   public static final Type
       NUMBER_TYPE = Type.fromString(envVar("NUMBER_TYPE"));

   public static final Feature[]
       NUMBER_FEATURES = Arrays.stream(envVar("NUMBER_FEATURES").split(","))
           .map(Feature::fromString)
           .toArray(Feature[]::new);

   public static final SearchPattern
       NUMBER_SEARCH_PATTERN = SearchPattern.values()[Integer.parseInt(envVar("NUMBER_SEARCH_PATTERN"))];

   public static final UpdateNumberRequest.CallbackType
       NUMBER_VOICE_CALLBACK_TYPE = UpdateNumberRequest.CallbackType.fromString(envVar("NUMBER_VOICE_CALLBACK_TYPE"));

   public static final VerifyRequest.Workflow
       VERIFY_WORKFLOW_ID = VerifyRequest.Workflow.values()[Integer.parseInt(envVar("VERIFY_WORKFLOW_ID")) + 1];

   public static final Psd2Request.Workflow
       VERIFY_PSD2_WORKFLOW_ID = Psd2Request.Workflow.values()[Integer.parseInt(envVar("VERIFY_WORKFLOW_ID")) + 1];

   public static final TextToSpeechLanguage
       VOICE_LANGUAGE = TextToSpeechLanguage.valueOf(envVar("VOICE_LANGUAGE"));

   public static final ApiRegion
       MESSAGES_GEOSPECIFIC_API_HOST = ApiRegion.fromString(envVar("MESSAGES_GEOSPECIFIC_API_HOST"));
}
