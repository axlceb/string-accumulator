package org.axlceb.exception;

import java.util.List;

public class NegativesNotAllowedException extends RuntimeException {

    // Just as an example of using custom Exception
    public NegativesNotAllowedException(List<Integer> numbers) {
        super("negatives not allowed " + numbers);
    }
}
