package algorithm.search;

public class StringSearch {

    public static void plain(String source, String pattern) {
        int res = 0;
        int sourceLength = source.length();
        int patterLength = pattern.length();

        for (int i = 0; i <= (sourceLength - patterLength); i++) {
            res++;
            String str = source.substring(i, i + patterLength);
            if (str.equals(pattern)) {
                print("match successfully");
                continue;
            }
        }
        print("plain match: total hit " + res + " times");
    }

    public static void KMP(String source, String pattern){

    }
    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
        StringSearch.plain("ababababababababababbaba", "bab");
    }

}
