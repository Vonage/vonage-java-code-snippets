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
package com.nexmo.quickstart.numbers;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.numbers.*;

import static com.nexmo.quickstart.Util.configureLogging;
import static com.nexmo.quickstart.Util.envVar;

public class SearchNumbers {
    private static final String NEXMO_API_KEY = envVar("NEXMO_API_KEY");
    private static final String NEXMO_API_SECRET = envVar("NEXMO_API_SECRET");
    private static final String NUMBER_SEARCH_CRITERIA = envVar("NUMBER_SEARCH_CRITERIA");
    private static final SearchPattern NUMBER_SEARCH_PATTERN = SearchPattern.valueOf(envVar("NUMBER_SEARCH_PATTERN"));
    private static final String COUNTRY_CODE = envVar("COUNTRY_CODE");
    private static final String[] NEXMO_NUMBER_FEATURES = envVar("NEXMO_NUMBER_FEATURES").split(",");

    public static void main(String[] args) {
        configureLogging();

        NexmoClient client = NexmoClient.builder()
                .apiKey(NEXMO_API_KEY)
                .apiSecret(NEXMO_API_SECRET)
                .build();

        NumbersClient numbersClient = client.getNumbersClient();

        SearchNumbersFilter filter = new SearchNumbersFilter(COUNTRY_CODE);
        filter.setFeatures(NEXMO_NUMBER_FEATURES);
        filter.setPattern(NUMBER_SEARCH_CRITERIA);
        filter.setSearchPattern(NUMBER_SEARCH_PATTERN);

        SearchNumbersResponse response = numbersClient.searchNumbers(filter);

        System.out.println("Here are "
                + response.getNumbers().length
                + " of the "
                + response.getCount()
                + " matching numbers available for purchase."
        );

        for (AvailableNumber number : response.getNumbers()) {
            System.out.println("Tel: " + number.getMsisdn());
            System.out.println("Cost: " + number.getCost());
        }
    }
}
