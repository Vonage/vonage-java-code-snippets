package com.nexmo.quickstart.voice;/*
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

import com.nexmo.client.HttpWrapper;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.endpoints.CreateCallMethod;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

public class CustomCreateCallMethod extends CreateCallMethod {
    private static final String DEFAULT_URI = "https://api-eu.nexmo.com/v1/calls";

    public CustomCreateCallMethod(HttpWrapper httpWrapper) {
        super(httpWrapper);
    }

    @Override
    public RequestBuilder makeRequest(Call request) throws NexmoClientException, UnsupportedEncodingException {
        return RequestBuilder
                .post(DEFAULT_URI)
                .setHeader("Content-Type", "application/json")
                .setEntity(new StringEntity(request.toJson()));
    }
}
