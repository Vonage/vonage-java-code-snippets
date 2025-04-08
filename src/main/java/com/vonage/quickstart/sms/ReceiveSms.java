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
package com.vonage.quickstart.sms;

import com.vonage.client.sms.MessageEvent;
import spark.Route;
import spark.Spark;

public class ReceiveSms {
    public static void main(String[] args) throws Exception {
        /*
         * Route to handle incoming SMS GET request.
         */
        Route inboundSmsAsGet = (req, res) -> {
            System.out.println("msisdn: " + req.queryParams("msisdn"));
            System.out.println("messageId: " + req.queryParams("messageId"));
            System.out.println("text: " + req.queryParams("text"));
            System.out.println("type: " + req.queryParams("type"));
            System.out.println("keyword: " + req.queryParams("keyword"));
            System.out.println("messageTimestamp: " + req.queryParams("message-timestamp"));

            res.status(204);
            return "";
        };

        /*
         * Route to handle incoming SMS with POST form-encoded or JSON body.
         */
        Route inboundSmsAsPost = (req, res) -> {
            // The body will be form-encoded or a JSON object:
            if (req.contentType().startsWith("application/x-www-form-urlencoded")) {
                System.out.println("msisdn: " + req.queryParams("msisdn"));
                System.out.println("messageId: " + req.queryParams("messageId"));
                System.out.println("text: " + req.queryParams("text"));
                System.out.println("type: " + req.queryParams("type"));
                System.out.println("keyword: " + req.queryParams("keyword"));
                System.out.println("messageTimestamp: " + req.queryParams("message-timestamp"));
            } else {
                MessageEvent event = MessageEvent.fromJson(req.body());

                System.out.println("msisdn: " + event.getMsisdn());
                System.out.println("messageId: " + event.getMessageId());
                System.out.println("text: " + event.getText());
                System.out.println("type: " + event.getType());
                System.out.println("keyword: " + event.getKeyword());
                System.out.println("messageTimestamp: " + event.getMessageTimestamp());
            }

            res.status(204);
            return "";
        };

        Spark.port(8080);
        Spark.get("/webhooks/inbound-sms", inboundSmsAsGet);
        Spark.post("/webhooks/inbound-sms", inboundSmsAsPost);
    }
}
