package parser;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface CrontabParserStrategy {
    String process(String part, int min, int max);

    static CrontabParserStrategy processComma() {
        return (input, min, max) -> {
            String[] values = input.split(",");
            return String.join(" ", values);
        };
    }

    static CrontabParserStrategy processRange() {
        return (input, min, max) -> {
            String[] range = input.split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);

            return IntStream.rangeClosed(start, end)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
        };
    }

    static CrontabParserStrategy processAll() {
        return (input, min, max) -> IntStream.rangeClosed(min, max)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

    }

    static CrontabParserStrategy processInterval() {
        return (input, min, max) -> {
            int interval = Integer.parseInt(input.substring(2));
            return IntStream.rangeClosed(min, max)
                    .filter(i -> (i - min) % interval == 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
        };
    }

    static CrontabParserStrategy processSingleValue() {
        return (input, min, max) -> input;
    }


}
