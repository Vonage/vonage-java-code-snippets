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

import static spark.Spark.*;

public class ReceiveDlr {
    public static void main(String[] args) throws Exception {
        port(3000);

        get("/webhooks/delivery-receipt", (req, res) -> {
            for (String param : req.queryParams()) {
                System.out.printf("%s: %s\n", param, req.queryParams(param));
            }
            res.status(204);
            return "";
        });

        post("/webhooks/delivery-receipt", (req, res) -> {
            // The body will be form-encoded or a JSON object:
            if (req.contentType().startsWith("application/x-www-form-urlencoded")) {
                for (String param : req.queryParams()) {
                    System.out.printf("%s: %s\n", param, req.queryParams(param));
                }
            } else {
                System.out.println(req.body());
            }

            res.status(204);
            return "";
        });
    }
}
