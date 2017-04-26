/*
 * Copyright (c) 2011-2017 Nexmo Inc
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
package com.nexmo.quickstart.verify;

import java.util.Scanner;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.verify.VerifyClient;
import com.nexmo.client.verify.VerifyResult;
import com.nexmo.client.verify.CheckResult;

import static com.nexmo.quickstart.Util.*;

public class VerificationExample {
    public static void main(String[] args) throws Exception {
        configureLogging();

        String API_KEY = envVar("API_KEY");
        String API_SECRET = envVar("API_SECRET");
        String TO_NUMBER = envVar("TO_NUMBER");

        AuthMethod auth = new TokenAuthMethod(API_KEY, API_SECRET);
        NexmoClient nexmoClient = new NexmoClient(auth);
        VerifyClient client = nexmoClient.getVerifyClient();
        VerifyResult request = client.verify(TO_NUMBER, "MyApp");
        String requestId = request.getRequestId();
        String code = VerificationExample.codeInput();
        CheckResult result = client.check(requestId, code);
        if (result.getStatus() == 0) {
            System.out.println(TO_NUMBER + " verified successfully.");
        }
    }

    public static String codeInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your verification code: ");
        String code = scanner.nextLine();
        System.out.println("Thank you!");
        return code.trim();
    }

    private static String envVar(String key) {
        String value = System.getenv(key);
        if (value == null) {
            throw new IllegalArgumentException("You must provide the " + key + " environment variable!");
        }
        return value;
    }
}
