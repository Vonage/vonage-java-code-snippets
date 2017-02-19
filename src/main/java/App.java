import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.JWTAuthMethod;
import com.nexmo.client.voice.Call;
import com.nexmo.client.voice.CallEvent;

public class App {

    private static String TO_NUMBER = "TO_NUMBER";
    private static String FROM_NUMBER = "447520619555";
    private static String APPLICATION_ID = "07274a86-3df0-4cac-b5aa-838802692bf0";
    private static String PRIVATE_KEY = "private_fixed.key";

    public static void main(String[] args) {
        try {
//            App.test();

            Path path = Paths.get(PRIVATE_KEY);
            JWTAuthMethod auth = new JWTAuthMethod(APPLICATION_ID, path);
//            NexmoClient client = new NexmoClient(auth);
//            Call call = new Call(TO_NUMBER, FROM_NUMBER,
//                    "https://nexmo-community.github.io/ncco-examples/first_call_talk.json");
//            CallEvent event = client.getVoiceClient().createCall(call);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    private static void test() throws Exception {
        Pattern pemPattern = Pattern.compile("-----BEGIN PRIVATE KEY-----\\n(.*\\n)-----END PRIVATE KEY-----", Pattern.MULTILINE | Pattern.DOTALL);

//            Path path = Paths.get("private.key");
        Path path = Paths.get("private.key");

        byte[] privateKey = Files.readAllBytes(path);
        String s = new String(privateKey, StandardCharsets.UTF_8);
        System.out.println(s);

        StringBuilder sb = new StringBuilder("-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/5SukrAilz/F4\n" +
                "1VrICdq17C9i6HkINwD94weRrHx2nQRzcHG23X09J8oDo1/doB1UQOdjTgv6h81D\n" +
                "Rbr0PWHpXxSWcMyoVzCf99/X6R0VUaiFom79HQUsVbvL0auuQ5b48/hICdNHVjF8\n" +
                "Wgb6zAY32cw8cUTV0ZcIv4KGJrBMce7Gam4bv9IP7LgCk1UhY3s3UIWGhrkp1tQ1\n" +
                "vOpg/cpGzhmGSvzQh/+rSsp683jFMgiykuq9/pXcaCtKEltMlC85cF5vOHMkqoDg\n" +
                "w5rkDstUiCGmOuHQrb7T7m1fC93O/snIDSgaowGBXUowuspPiVbZq2GM9sC166nt\n" +
                "V1JepefFAgMBAAECggEALdNJ9VS56fL7HNqCh+PGjYw+ErYmdR5Bh9/IBnM089Ry\n" +
                "ZCj1lvBUwJ54YR8KPTBH4L9xImdYd/cnPwnvZ/UipSr2DSkZzpenvoPgujxqnBU4\n" +
                "k9UjASIFgr1kj/tdrstoScJlR7rE8DO2tJYBNbI0R/Ekk0iPYipfkZC+WE/z6QUf\n" +
                "hUJjgUjgCS/P37zhNtc+spZ6Yx1IMYi8xsL5KxyUDznMHY5i0qYUk2C+fpTHHjeV\n" +
                "k9ObMFwxwbF3LYPWFktDoB+lhLYQV+jpowoZfv8FM4wOOGhjZ1a1nHqF1e++Fyo0\n" +
                "4mp2Cp8spS95YtRzjmolnaZ+oamW/GsQpnl4iFnNTwKBgQD7jxvtCjPCnaF/ePFB\n" +
                "QsSVjtnccc0bkh3+HhZ7lwBW2FvK1xC21I2LVOjJ7tQqLO8iO7fHHMpI3OAHvXq5\n" +
                "JBzrisFZAdYFo82rAxCKUY0nrA4jTIRHfHxPDiLIGuuXKlOaaabk5zbVFtQ+Ub+Y\n" +
                "YL73rkEFRQEHo0i1xZYCQe9/EwKBgQDDSGr6WNFegivMJz96AEKZso2YBM5/s+Py\n" +
                "9onymlyZOmqQlRBoqUTq2t8rmkZsGGKBhEIAeGDdRX0EnBMD0TXUC6umNreO9nFk\n" +
                "RgOqLqL45RnGiCAYHOMgeCdB+EJ2V7gU+39fJV47ktAw+PvcAHpFAE/qDjLW7tRe\n" +
                "NyU3DkFgxwKBgEkCvxssI9uvPmkt4++J/l5d6JNXoC0JWpUfjGzwZZPPFc0oYFyA\n" +
                "EljHGw1IuOFIy1sHNA6hJ4DAQnFcqBrGBXLDgdxgcQqQAShKKmnKw4opIK1UDuxk\n" +
                "j/ut4aBsV8Y27a7aTSsQhFSSXKV9unryUVq3g32RCnx6AqdnImOjWWxrAoGBAIk8\n" +
                "1nmTe6goYPE7T9/EWQm8ssd38UtLt9GiszV8h8DjjcqIB50WACnwoU/6Pq1uwaN1\n" +
                "uBCuiJxYXlCkArT3DRY1EW9hGsTsqqmmjqLF3aB/GQst1Sg8/N1vs18oQkT6xpJw\n" +
                "YIYUM9I4DfmssQteho8BdiftzIrCExsXNB8p9IY3AoGAbefLTbMhbEWecG3cnJ+J\n" +
                "Q0Ba6+AUw5pr1aoQ2WxmbEqSiwa/yd6IOO91EeMPfKYyQSYaf2kc9wXje7QeAvyW\n" +
                "jfLAFQdsQ3wZpuxXhyWvxNJu7gdV5+yGrrjalgfJ56IurCxEd0klXl+B9k6Byycj\n" +
                "/0KBTFQD47ErblpgjxtfLPc=\n" +
                "-----END PRIVATE KEY-----");

        Matcher fileExtracter = pemPattern.matcher(s);
        Matcher hardCodedExtracter = pemPattern.matcher(sb.toString());

        System.out.println("fileExtracter.matches(): " + fileExtracter.matches());
        System.out.println("hardCodedExtracter.matches(): " + hardCodedExtracter.matches());
    }
}
