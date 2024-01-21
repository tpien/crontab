import parser.CrontabParser;

public class CrontabExpressionParser {
    public static void main(String[] args) throws Exception {
        CrontabParser.getInstance().parseCronTab(args[0]);
    }
}