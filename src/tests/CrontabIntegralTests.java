package tests;

import exsceptions.CrontabInputException;
import org.junit.jupiter.api.Test;
import parser.CrontabParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrontabIntegralTests {


    private void performTest(String input, String result) throws Exception {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        CrontabParser.getInstance().parseCronTab(input);

        String consoleOutput = outputStreamCaptor.toString().trim();
        assertEquals(result.replaceAll("\\s+", " ").trim(), consoleOutput.replaceAll("\\s+", " ").trim());
        System.setOut(System.out);
    }

    @Test
    public void parseCronTabValidPattern() throws Exception {
        String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
        String output = """                        
                minute               0 15 30 45
                hour                 0
                day of month         1 15
                month                1 2 3 4 5 6 7 8 9 10 11 12
                day of week          1 2 3 4 5
                command              /usr/bin/find
                """;
        performTest(input, output);
    }


    @Test
    public void parseCronTabValidPatternEdgeEveryMinute() throws Exception {
        String input = "* * * * *  /scripts/script.sh";
        String output = """                        
                minute               0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
                hour                 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
                day of month         1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month                1 2 3 4 5 6 7 8 9 10 11 12
                day of week          0 1 2 3 4 5 6
                command              /scripts/script.sh
                  """;
        performTest(input, output);
    }

    @Test
    public void parseCronTabValidPatternEdgeYearly() throws Exception {
        String input = "0 0 1 1 *  /scripts/script.sh";
        String output = """                        
                minute               0
                hour                 0
                day of month         1
                month                1
                day of week          0 1 2 3 4 5 6
                command              /scripts/script.sh
                   """;
        performTest(input, output);
    }

    @Test
    public void parseCronTabValidPatternEdgeYearlyMultiActions() throws Exception {
        String input = "0 2 * * * bash /script/backup.sh; /scripts/script.sh;";
        String output = """                        
                minute               0
                hour                 2
                day of month         1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
                month                1 2 3 4 5 6 7 8 9 10 11 12
                day of week          0 1 2 3 4 5 6
                command              bash /script/backup.sh; /scripts/script.sh;
                  """;
        performTest(input, output);
    }

    @Test
    public void parseCronTabInValidInputNotAllowedChar() {
        String input = "0 2 * A * /scripts/script.sh;";
        assertThrows(CrontabInputException.class, () ->
                CrontabParser.getInstance().parseCronTab(input)
        );
    }

    @Test
    public void parseCronTabInValidPartsElements() {
        String input = "0 2";
        assertThrows(CrontabInputException.class, () ->
            CrontabParser.getInstance().parseCronTab(input)
        );
    }


}

