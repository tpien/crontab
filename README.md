# Contab format definition
![crontab_definition.png](crontab_definition.png)

# Requirements
JDK 15+

# Run from console
Example:
` ~$ java CrontabExpressionParser "*/15 0 1,15 * 1-5 /usr/bin/find" `

#Output:

![crontab_output_exemple.png](crontab_output_exemple.png)

# Unit tests classes:
- CrontabUnitTests
- CrontabIntegralTests