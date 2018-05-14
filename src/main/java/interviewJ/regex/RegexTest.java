package interviewJ.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ray on 18-5-13.
 */
public class RegexTest {

    public static void main(String[] args) {
        String[] strs = {"132828198606050041", "23120198404030018"};
        Pattern pattern = Pattern.compile("\\d{17}[0-9A-Za-z]|\\d{14}[0-9a-zA-Z]");
        for (int i = 0; i < strs.length; i++) {
            Matcher matcher = pattern.matcher(strs[i]);
            System.out.println(strs[i] + ":" + matcher.matches());
        }

        Pattern pattern2 = Pattern.compile("\\d{6}(\\d{8}).*");
        Pattern pattern3 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
        for (int i = 0; i < strs.length; i++) {
            Matcher matcher = pattern2.matcher(strs[i]);
            boolean b = matcher.find();
            if (b) {
                String s = matcher.group(1);
                Matcher matcher2 = pattern3.matcher(s);
                if (matcher2.find()) {
                    System.out.println("birthday: " + matcher2.group(1) + "year " + matcher2.group(2) + "month " + matcher2.group(3) + "day.");
                }
            }
        }

    }
}
