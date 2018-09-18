/*
 * Copyright (c) 2011-2018 Nexmo Inc
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
package com.nexmo.quickstart.voice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexmo.client.voice.ncco.Ncco;
import com.nexmo.client.voice.ncco.RecordNcco;
import com.nexmo.client.voice.ncco.TalkNcco;
import spark.Route;
import spark.Spark;

public class RecordMessage {
    public static void main(String[] args) {
        /*
         * Route to answer and connect incoming calls with rec.ording.
         */
        Route answerRoute = (req, res) -> {
            String recordingUrl = String.format("%s://%s/webhooks/recordings", req.scheme(), req.host());

            TalkNcco intro = new TalkNcco(
                    "Please leave a message after the tone, then press #. We will get back to you as soon as we can.");

            RecordNcco record = new RecordNcco();
            record.setEventUrl(recordingUrl);
            record.setEndOnSilence(3);
            record.setEndOnKey('#');
            record.setBeepStart(true);

            TalkNcco outro = new TalkNcco("Thank you for your message. Goodbye");

            Ncco[] nccos = new Ncco[]{intro, record, outro};

            res.type("application/json");

            // com.fasterxml.jackson.databind.ObjectMapper;
            return new ObjectMapper().writer().writeValueAsString(nccos);
        };

        /*
         * Webhook Route which prints out the recording URL it is given to stdout.
         */
        Route recordingRoute = (req, res) -> {
            System.out.println(RecordingPayload.fromJson(req.bodyAsBytes()).getRecordingUrl());

            res.status(204);
            return "";
        };

        Spark.port(3000);
        Spark.get("/webhooks/answer", answerRoute);
        Spark.post("/webhooks/recordings", recordingRoute);
    }
}
