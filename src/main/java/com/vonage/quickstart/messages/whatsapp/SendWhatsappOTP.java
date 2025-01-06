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
package com.vonage.quickstart.messages.whatsapp;

import com.vonage.client.VonageClient;
import com.vonage.client.messages.whatsapp.*;
import static com.vonage.quickstart.Util.configureLogging;
import static com.vonage.quickstart.Util.envVar;
import java.util.*;

public class SendWhatsappOTP {

	public static void main(String[] args) throws Exception {
		configureLogging();

		String VONAGE_APPLICATION_ID = envVar("VONAGE_APPLICATION_ID");
		String VONAGE_PRIVATE_KEY_PATH = envVar("VONAGE_PRIVATE_KEY_PATH");
		String VONAGE_WHATSAPP_NUMBER = envVar("VONAGE_WHATSAPP_NUMBER");
		String TO_NUMBER = envVar("TO_NUMBER");
		String WHATSAPP_TEMPLATE_NAME = envVar("WHATSAPP_TEMPLATE_NAME");

		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		Map<String, Object> custom = new LinkedHashMap<>(4);
		custom.put("type", "template");
		Map<String, Object> template = new LinkedHashMap<>();
		template.put("name", WHATSAPP_TEMPLATE_NAME);
		custom.put("template", template);
		Map<String, Object> language = new LinkedHashMap<>(2);
		language.put("code", "en_US");
		language.put("policy", "deterministic");
		custom.put("language", language);
		List<Map<String, Object>> components = new ArrayList<>(2);
		Map<String, Object> component1 = new LinkedHashMap<>(2);
		component1.put("type", "body");
		List<Map<String, Object>> comp1Parameters = new ArrayList<>(1);
		Map<String, Object> comp1param1 = new LinkedHashMap<>(2);
		comp1param1.put("type", "text");
		comp1param1.put("text", "123456");
		comp1Parameters.add(comp1param1);
		component1.put("parameters", comp1Parameters);
		components.add(component1);
		Map<String, Object> component2 = new LinkedHashMap<>(4);
		component2.put("type", "button");
		component2.put("sub_type", "url");
		component2.put("index", "0");
		component2.put("parameters", comp1Parameters);
		components.add(component2);
		custom.put("components", components);

		WhatsappCustomRequest message = WhatsappCustomRequest.builder()
				.from(VONAGE_WHATSAPP_NUMBER).to(TO_NUMBER)
				.custom(custom).build();

		client.getMessagesClient().sendMessage(message);
	}
}
