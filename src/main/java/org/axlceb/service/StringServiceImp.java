package org.axlceb.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axlceb.exception.NegativesNotAllowedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class StringServiceImp implements StringService {

    // Just a simplified implementation example of static factory method
    public static StringService create() {
        return new StringServiceImp();
    }

    @Override
    public int add(@NonNull String numbers) {
        List<String> separators = Collections.emptyList();
        var regex = ",|\\s|\\n";
        var content = numbers;
        int sum = 0;

        log.info("numbers:\n{}", numbers);

        if (numbers.isEmpty()) {
            this.log(separators, List.of(),0);
            return 0;
        } else
        // Check fi the string numbers matches the patter that have custom delimiter
        if (numbers.matches("^//(.+)\n((?s).*?)")) {
            separators = this.getSeparators(numbers);
            separators.add(regex);

            regex = String.join("|", separators);


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

            sum = Arrays.stream(content.split(regex))
                    .map(Integer::parseInt)
                    .filter(i -> i <= 1000)
                    .mapToInt(Integer::intValue)
                    .sum();

            this.log(separators, listOfNumbers, sum);

        } catch(NegativesNotAllowedException e) {
            log.error(e.getMessage());
        }

        return sum;
    }

    /**
     * Get the separators from the string splitted with pipe | and return a list of regex compatible pattern
     * @param numbers the string to treat
     * @return List of exact regex compatible pattern to avoid regex metacharacters like +*?
     */
    private List<String> getSeparators(String numbers) {
        List<String> separators = new ArrayList<>(                        // new ArrayList as the list should be mutable
                Arrays.asList(StringUtils
                .substringBetween(numbers, "//", StringUtils.LF)
                .split("\\|")));

        return separators.stream()
                .map(Pattern::quote)
                .collect(Collectors.toList());
    }

    private void log(List<String> separator, List<Integer> listOfNumbers, Integer sum) {
        log.info("--- Results ---\nseparators: {}\nlistOfNumbers: {}\nsum: {}", separator, listOfNumbers, sum);
    }
}
