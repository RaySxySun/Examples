package basic.lambda;

import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.*;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Sample {
    public static void main(String[] args) {
        // CASE 1: trigger thread run method
        System.out.println("======== CASE1: trigger thread run method ========");
        new Thread(() -> System.out.println("CASE1(async): In Java 8, Lambda expression can trigger Thread() run")).start();

        // CASE 2: listen to swing event
        System.out.println("======== CASE2: listen to swing event========");
        // JButton show = new JButton("Show");
        /*    show.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Event handling without lambda expression is boring");
                }
              });
        */
        // show.addActionListener((e) -> {
        //    System.out.println("Light, Camera, Action. SHOW OFF");
        // });

        // CASE 3: deal with Collection
        System.out.println("======== CASE3: deal with Collection========");
        // prior to Java 8：
        // List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        // for (String feature : features) {
        //     System.out.println(feature);
        // }
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
        features.forEach(System.out::println);

        // CASE 4: java.util.function
        System.out.println("======== CASE4: java.util.function========");
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.toString().startsWith("J"));
        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.toString().endsWith("a"));
        System.out.println("Print all languages :");
        filter(languages, (str)->true);
        System.out.println("Print no language : ");
        filter(languages, (str)->false);
        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.toString().length() > 4);

        // CASE 5: combine Predicate
        System.out.println("======== CASE5: combine predicate ========");
        Predicate<String> startsWithJ = (n) -> n.toString().startsWith("J");
        Predicate<String> fourLetters = (n) -> n.toString().length() == 4;
        languages.stream().filter(startsWithJ.and(fourLetters)).forEach((n) -> System.out.println("the target string is:" + n));

        // CASE 6: map-reduce
        // Tradictional fashion to cal tax based on a given arrays
        System.out.println("======== CASE6: map reduce ========");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        // for (Integer cost : costBeforeTax) {
        //    double price = cost + .12 * cost;
        //    System.out.println(price);
        // }

        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

        // CASE 7: use filter to create a new desired list
        System.out.println("======== CASE7: use FILTER to create a new list (NOT replace) ========");
        List<String> oriList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> filteredList = oriList.stream().filter(x -> x.length() > 3).collect(Collectors.toList());
        System.out.printf("Original list : %s, Filtered List: %s %n", oriList, filteredList);

        // CASE 8: use filter to create a new desired list
        System.out.println("======== CASE8: use MAP to create a String ========");
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        // CASE 9: use distinct()
        System.out.println("======== CASE9: use distinct to create a new list ========");
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);

        // CASE10: get Max, Min, Sum & Avg
        System.out.println("======== CASE10: to get statistics info ========");
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());


    }

    // FOR Case 4
    public static void filter(List<String> names, Predicate condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

}
