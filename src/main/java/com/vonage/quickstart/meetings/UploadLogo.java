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
package com.vonage.quickstart.meetings;

import com.vonage.client.VonageClient;
import com.vonage.client.meetings.LogoType;
import static com.vonage.quickstart.EnvironmentVariables.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class UploadLogo {
	public static void main(String[] args) throws Exception {
		UUID THEME_ID = UUID.fromString(envVar("THEME_ID"));
		LogoType LOGO_TYPE = LogoType.fromString(envVar("LOGO_TYPE"));
		Path LOGO_FILEPATH = Paths.get(envVar("LOGO_FILEPATH"));

		VonageClient client = VonageClient.builder()
				.applicationId(VONAGE_APPLICATION_ID)
				.privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
				.build();

		client.getMeetingsClient().updateThemeLogo(THEME_ID, LOGO_TYPE, LOGO_FILEPATH);
		System.out.println("Updated '"+LOGO_TYPE+"' logo for theme "+THEME_ID);
	}
}
