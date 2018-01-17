package utils.apachecommonslang;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/*<dependency>
 *<groupId>commons-lang</groupId>
 *<artifactId>commons-lang</artifactId>
 *<version>2.6</version>
 *</dependency>
 */
public class ApacheCommonsLangTest {
    public static void main(String[] args) {
        StringUtils.abbreviate("abcdefg", 6);// ---"abc..."

//        StringUtils.appendIfMissing("abc","xyz");//---"abcxyz"
//        StringUtils.appendIfMissingIgnoreCase("abcXYZ","xyz");//---"abcXYZ"

        StringUtils.capitalize("cat");//---"Cat"
        StringUtils.uncapitalize("Cat");//---"cat"

        StringUtils.center("abcd", 2);//--- "abcd"
        StringUtils.center("ab", -1);//--- "ab"
        StringUtils.center("ab", 4);//---" ab "
        StringUtils.center("a", 4, "yz");//---"yayz"
        StringUtils.center("abc", 7, "");//---"  abc  "

        StringUtils.chomp("abc\r\n");//---"abc"

        StringUtils.contains("abc", "z");//---false
        StringUtils.containsIgnoreCase("abc", "A");//---true

        StringUtils.countMatches("abba", "a");//---2

        StringUtils.deleteWhitespace("   ab  c  ");//---"abc"

        StringUtils.difference("abcde", "abxyz");//---"xyz"

        StringUtils.endsWith("abcdef", "def");//---true
        StringUtils.endsWithIgnoreCase("ABCDEF", "def");//---true
        StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"});//---true

        StringUtils.startsWith("abcdef", "abc");//---true
        StringUtils.startsWithIgnoreCase("ABCDEF", "abc");//---true
        StringUtils.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"});//---true

        StringUtils.equals("abc", "abc");//---true
        StringUtils.equalsIgnoreCase("abc", "ABC");//---true

        StringUtils.getCommonPrefix(new String[] {"abcde", "abxyz"});//---"ab"

        StringUtils.indexOf("aabaabaa", "b");//---2
        StringUtils.indexOf("aabaabaa", "b", 3);//---5(从角标3后查找)
        StringUtils.ordinalIndexOf("aabaabaa", "a", 3);//---1(查找第n次出现的位置)

        StringUtils.lastIndexOf("aabaabaa", 'b');//---5
        StringUtils.lastIndexOf("aabaabaa", 'b', 4);//---2
        StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2);//---1

        StringUtils.isAllUpperCase("ABC");//---true
        StringUtils.isAllLowerCase("abC");//---false

        StringUtils.isBlank(null);StringUtils.isBlank("");StringUtils.isBlank(" ");//---true

        StringUtils.isEmpty(null);StringUtils.isEmpty("");//---true
        StringUtils.isEmpty(" ");//---false

        StringUtils.isNumeric("123");//---false
        StringUtils.isNumeric("12 3");//---false (不识别运算符号、小数点、空格……)
        StringUtils.isNumericSpace("12 3");//---true

        StringUtils.upperCase("aBc");//---"ABC"
        StringUtils.lowerCase("aBc");//---"abc"
        StringUtils.swapCase("The dog has a BONE");//---"tHE DOG HAS A bone"

        StringUtils.replace("aba", "a", "z");//---"zbz"
        StringUtils.overlay("abcdef", "zz", 2, 4);//---"abzzef"(指定区域)
        StringUtils.replaceEach("abcde", new String[]{"ab", "d"},
                new String[]{"w", "t"});//---"wcte"(多组指定替换ab->w，d->t)

        StringUtils.repeat("e", 3);//---"eee"

        StringUtils.reverse("bat");//---"tab"

        StringUtils.remove("queued", 'u');//---"qeed"

        StringUtils.split("a..b.c", '.');//---["a", "b", "c"]
        StringUtils.split("ab:cd:ef", ":", 2);//---["ab", "cd:ef"]
        StringUtils.splitByWholeSeparator("ab-!-cd-!-ef", "-!-", 2);//---["ab", "cd-!-ef"]
        StringUtils.splitByWholeSeparatorPreserveAllTokens("ab::cd:ef", ":");//-["ab"," ","cd","ef"]

        StringUtils.strip(" ab c ");//---"ab c"
        StringUtils.stripToNull(null);//---null
        StringUtils.stripToEmpty(null);//---""

        StringUtils.substring("abcd", 2);//---"cd"
        StringUtils.substring("abcdef", 2, 4);//---"cd"

        StringUtils.left("abc", 2);//---"ab"
        StringUtils.right("abc", 2);//---"bc"
        StringUtils.mid("abcdefg", 2, 4);//---"cdef"

        StringUtils.substringBefore("abcba", "b");//---"a"
        StringUtils.substringBeforeLast("abcba", "b");//---"abc"
        StringUtils.substringAfter("abcba", "b");//---"cba"
        StringUtils.substringAfterLast("abcba", "b");//---"a"

        StringUtils.substringBetween("tagabctag", "tag");//---"abc"
        StringUtils.substringBetween("yabczyabcz", "y", "z");//---"abc"


        RandomStringUtils.randomNumeric(6);
        RandomStringUtils.random(6, "abcdefghijk");
        System.out.println(RandomStringUtils.random(20, true, false));
        System.out.println(RandomStringUtils.random(10, false, true));

        NumberUtils.max(new int[] { 1, 2, 3, 4 });//---4
        NumberUtils.isDigits("153.4");//--false
        NumberUtils.isNumber("0321.1");//---false

        ArrayUtils.contains(new String[]{"2", "33", "66"}, "33");

        Date date = DateUtils.addDays(new Date(), 2);
        DateUtils.isSameDay(new Date(), date);
//        DateUtils.parseDate(str, parsePatterns);

    }
}
