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
package com.nexmo.quickstart.account;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.account.Network;
import com.nexmo.client.account.PricingResponse;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;

import java.util.List;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class GetSmsPrice {
    public static void main(String[] args) throws Exception {
        configureLogging();

        String API_KEY = envVar("API_KEY");
        String API_SECRET = envVar("API_SECRET");

        AuthMethod auth = new TokenAuthMethod(API_KEY, API_SECRET);
        NexmoClient client = new NexmoClient(auth);

        PricingResponse response = client.getAccountClient().getSmsPrice("US");
        printResponseInformation(response);

        // Print the first two networks.
        printNetworkInformation(response.getNetworks().subList(0, 2));
    }

    private static void printResponseInformation(PricingResponse response) {
        System.out.println("SMS Price Information");
        System.out.format("  %-15s %s\n", "Dialing Prefix", response.getDialingPrefix());
        System.out.format("  %-15s %s %s\n", "Default Price", response.getDefaultPrice(), response.getCurrency());
        System.out.format("  %-15s %s (%s)\n",
                          "Country",
                          response.getCountry().getDisplayName(),
                          response.getCountry().getCode());
    }

    private static void printNetworkInformation(List<Network> networks) {
        System.out.println("\n  Networks");
        for (Network network : networks) {
            System.out.format("    %-15s %s (%s)\n", "Name", network.getName(), network.getCode());
            System.out.format("    %-15s %s %s\n", "Price", network.getPrice(), network.getCurrency());
            System.out.format("    %-15s %s %s\n\n", "MCC MNC", network.getMcc(), network.getMnc());
        }
    }
}
