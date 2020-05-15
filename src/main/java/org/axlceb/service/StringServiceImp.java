package org.axlceb.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axlceb.exception.NegativesNotAllowedException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * I have decided to leave the different stages of the development process of the 'add' methods which correspond to the
 * different points that the task had.
 * @see <a href="{@docRoot}/java.base/README.md">README.md</a>.
 */
@Slf4j
public class StringServiceImp implements StringService {

    // Just a simplified implementation example of static factory method
    public static StringService create() {
        return new StringServiceImp();
    }

    // 'add' method to task 1. Accept 0,1 or 2 numbers
    public int add1(@NonNull String numbers) {

        if (numbers.isEmpty()) {
            this.log("<empty>", List.of(),0);
            return 0;
        }

        var sum = (Integer) Arrays.stream(numbers.split(","))
                .map(Integer::parseInt)
                .limit(2)
                .mapToInt(Integer::intValue)
                .sum();

        return sum;
    }

    // 'add' method to task 2 and 3. Accept multiple numbers
    public int add2(@NonNull String numbers) {

        if (numbers.isEmpty()) {
            this.log("<empty>", List.of(),0);
            return 0;
        }

        var sum = (Integer) Arrays.stream(numbers.split(",|\\s|\\n"))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();

        return sum;
    }

    // 'add' method to task 4. Support different delimiters.
    public int add3(@NonNull String numbers) {
        var separator = "<empty>";
        var regex = ",|\\s|\\n";
        var content = numbers;

        if (numbers.isEmpty()) {
            this.log(separator, List.of(),0);
            return 0;
        } else
        // Get the custom separator and added to the existing: ' ', ',' and '\n'
        if (numbers.matches("^//(.+)\n((?s).*?)")) {
            separator = this.getSeparator(numbers);
            regex = regex.concat("|").concat(separator);
            content = numbers.substring(numbers.indexOf("\n") + 1);
        }

        // TODO: remove for release
        log.debug("Content to process: " + content);

        var listOfNumbers = Arrays.stream(content.split(regex))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var sum = (Integer) Arrays.stream(content.split(regex))
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .sum();

        this.log(separator, listOfNumbers, sum);
        return sum;
    }

    // 'add' method to task 5, 6 and 7. Negative numbers, bigger than 1000 and any length delimiters
    @Override
    public int add(@NonNull String numbers) {
        var separator = "<empty>";
        var regex = ",|\\s|\\n";
        var content = numbers;
        int sum = 0;

        log.info("numbers:\n{}", numbers);

        if (numbers.isEmpty()) {
            this.log(separator, List.of(),0);
            return 0;
        } else
        if (numbers.matches("^//(.+)\n((?s).*?)")) {
            separator = this.getSeparator(numbers);
            regex = regex.concat("|").concat(separator);
            content = numbers.substring(numbers.indexOf("\n") + 1);
        }

        var listOfNegativeNumbers = Arrays.stream(content.split(regex))
                .map(Integer::parseInt)
                .filter(i -> i < 0)
                .collect(Collectors.toList());

        try {
            if (!listOfNegativeNumbers.isEmpty()) throw new NegativesNotAllowedException(listOfNegativeNumbers);

            var listOfNumbers = Arrays.stream(content.split(regex))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            sum = (Integer) Arrays.stream(content.split(regex))
                    .map(Integer::parseInt)
                    .filter(i -> i <= 1000)
                    .mapToInt(Integer::intValue)
                    .sum();

            this.log(separator, listOfNumbers, sum);

        } catch(NegativesNotAllowedException e) {
            log.error(e.getMessage());
        }

        return sum;
    }

    /**
     * Get the separator from the string and return the regex compatible pattern
     * @param numbers the string to treat
     * @return exact regex compatible pattern
     */
    private String getSeparator(String numbers) {
        return Pattern.quote(StringUtils.substringBetween(numbers, "//", StringUtils.LF));
    }

    private void log(String separator, List<Integer> listOfNumbers, Integer sum) {
        log.info("--- Results ---\nseparator: {}\nlistOfNumbers: {}\nsum: {}", separator, listOfNumbers, sum);
    }
}
