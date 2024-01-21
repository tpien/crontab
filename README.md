# Contab format definition
![crontab_definition.png](crontab_definition.png)

# Run from console
Compile JDK 15+:

`cd src`

`javac CrontabExpressionParser.java`

Run example:

`java CrontabExpressionParser "*/15 0 1,15 * 1-5 /usr/bin/find" `

Output:

![crontab_output_exemple.png](crontab_output_exemple.png)

# Unit tests classes:
- CrontabUnitTests
- CrontabIntegralTests