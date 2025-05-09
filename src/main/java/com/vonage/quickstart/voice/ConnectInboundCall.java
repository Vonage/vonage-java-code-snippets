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
package com.vonage.quickstart.voice;

import com.vonage.client.voice.ncco.ConnectAction;
import com.vonage.client.voice.ncco.Ncco;
import com.vonage.client.voice.ncco.PhoneEndpoint;
import spark.Route;
import spark.Spark;
import static com.vonage.quickstart.EnvironmentVariables.*;

public class ConnectInboundCall {
    public static void main(String[] args) {
        /*
         * Route to answer incoming calls with an NCCO response.
         */
        Route answerRoute = (req, res) -> {
            ConnectAction connect = ConnectAction.builder()
                    .endpoint(PhoneEndpoint.builder(VOICE_TO_NUMBER).build())
                    .from(VONAGE_VIRTUAL_NUMBER)
                    .build();

            res.type("application/json");

            return new Ncco(connect).toJson();
        };

        Spark.port(3000);
        Spark.get("/webhooks/answer", answerRoute);
        Spark.post("/webhooks/answer", answerRoute);
    }
}
