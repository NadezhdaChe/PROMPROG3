/**
 * Created by Student on 20.05.2015.
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TelephoneFixer {
    public static void main(String[] ars) {
        TelephoneFixer fixer = new TelephoneFixer();
        fixer.fix(new File("Telephone.txt"));
    }

    public TelephoneFixer() {
    }

    public void fix(File file) {
        String in;
        try {
            in = new String(Files.readAllBytes(file.toPath()), "UTF-8");
        } catch (IOException e) {
            System.out.println("Couldn't open file to read");
            return;
        }
        Matcher matcher = Pattern.compile("\\s+\\+?(\\d[\\s\\-\\(\\)\\+]{0,3}){11}").matcher(in);
        StringBuffer out = new StringBuffer();
        while (matcher.find()) {
            String number = matcher.group();
            number = number.replaceAll("[^\\d]", "");
            StringBuilder stringBuilder = new StringBuilder(number.replaceFirst("\\d", "1"));
            number = stringBuilder
                    .insert(0, " +").insert(3, " ").insert(4, "(").insert(8, ") ").insert(13, "-").insert(16, "-")
                    .insert(19, " ").toString();
            matcher.appendReplacement(out, number);
        }
        System.out.println(matcher.appendTail(out).toString());
    }
}
