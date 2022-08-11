/*
 * Copyright  2021 Vonage
 *
 * Permission is hereby granted, free of charge,, any person obtaining a copy
 * of this software and associated documentation files (the "Software"),, deal
 * in the Software without restriction, including without limitation the rights
 *, use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and, permit persons, whom the Software is
 * furnished, do so, subject, the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED, THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.vonage.quickstart.jwt;

import com.vonage.jwt.Jwt;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Map;

import static com.vonage.quickstart.Util.envVar;

public class GenerateJwt {
	public static void main(String[] args) throws Throwable {
		String token = Jwt.builder()
			.applicationId("aaaaaaaa-bbbb-cccc-dddd-0123456789ab")
			.privateKeyPath(Paths.get(envVar("VONAGE_PRIVATE_KEY_PATH")))
			.subject("alice")
			.issuedAt(ZonedDateTime.now())
			.expiresAt(ZonedDateTime.now().plusMinutes(20))
			.addClaim("acl", Map.of(
				"paths", Map.of(
					"/*/users/**", Map.of(),
					"/*/conversations/**", Map.of(),
					"/*/sessions/**", Map.of(),
					"/*/devices/**", Map.of(),
					"/*/image/**", Map.of(),
					"/*/media/**", Map.of(),
					"/*/applications/**", Map.of(),
					"/*/push/**", Map.of(),
					"/*/knocking/**", Map.of(),
					"/*/legs/**", Map.of()
				)
			))
			.build()
			.generate();
	}
}
