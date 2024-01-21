package parser;

import exsceptions.CrontabInputException;

public class CrontabParser {
    private static CrontabParser instance;

    private CrontabParser() {
    }

    public static CrontabParser getInstance() {
        if (instance == null)
            instance = new CrontabParser();
        return instance;
    }


    public void parseCronTab(String input) throws Exception {

        String[] parts = input.split("\\s+",6);
        validateInputParts(parts);
        System.out.printf("%-20s %s \n", "minute" , expandField(parts[0], 0, 59));
        System.out.printf("%-20s %s \n", "hour" , expandField(parts[1], 0, 23));
        System.out.printf("%-20s %s \n", "day of month" ,  expandField(parts[2], 1, 31));
        System.out.printf("%-20s %s \n", "month" ,  expandField(parts[3], 1, 12));
        System.out.printf("%-20s %s \n", "day of week" ,  expandField(parts[4], 0, 6));
        System.out.printf("%-20s %s \n", "command" ,  parts[5]);
    }

    private void validateInputParts(String[] parts) throws CrontabInputException {
        if (parts.length < 6) {
            throw new CrontabInputException("Invalid crontab format: Should consist 6 parts separated by white space");
        }
        for (int i =0; i<5;i++) {
            if (!parts[i].matches("^[*,-/0-9]+$"))
                throw new CrontabInputException("Invalid syntax in element: '"+ parts[i] + "' Please use only: numbers or chars: *,-/");
        }
    }

    private String expandField(String input, int min, int max) throws CrontabInputException{
        return selectStrategy(input).process(input, min, max);
    }

    private CrontabParserStrategy selectStrategy(String input) {
        if (input.equals("*"))
            return CrontabParserStrategy.processAll();

        if (input.contains(","))
            return CrontabParserStrategy.processComma();

        if (input.contains("-"))
            return CrontabParserStrategy.processRange();

        if (input.startsWith("*/"))
            return CrontabParserStrategy.processInterval();

        return CrontabParserStrategy.processSingleValue();
    }
}
