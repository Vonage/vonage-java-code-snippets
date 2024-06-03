/*
 * Copyright 2024 Vonage
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
package com.vonage.quickstart.messages;

import com.vonage.client.messages.InboundMessage;
import static com.vonage.quickstart.Util.configureLogging;
import spark.Route;
import spark.Spark;

public class IncomingMessage {

	public static void main(String[] args) {
		configureLogging();

		Route inboundRoute = (request, response) -> {
			InboundMessage messageDetails = InboundMessage.fromJson(request.body());
			System.out.println(
					"Message ID "+messageDetails.getMessageUuid()+" of type " +
					messageDetails.getMessageType()+" was sent from " +
					messageDetails.getFrom()+" to "+messageDetails.getTo()+" via "+
					messageDetails.getChannel()+" at "+messageDetails.getTimestamp()
			);
			return "OK";
		};

		Spark.port(3000);
		Spark.post("/webhooks/inbound-message", inboundRoute);
	}
}
