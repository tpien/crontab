package ts;

import ts.parser.CrontabParser;

public class CrontabExpressionParser {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Please provide exactly one parameter, the example: \"*/15 0 1,15 * 1-5 /usr/bin/find\" ");
            return;
        }
        CrontabParser.getInstance().parseCronTab(args[0]);
    }
}