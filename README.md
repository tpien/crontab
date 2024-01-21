# Contab format definition
![crontab_definition.png](crontab_definition.png)

# Unit tests class


# Run from console



Example:
` ~$ java CrontabExpressionParser "*/15 0 1,15 * 1-5 /usr/bin/find" `

#Output:
- minute 0 15 30 45
- hour 0
- day of month 1 15
- month 1 2 3 4 5 6 7 8 9 10 11 12
- day of week 1 2 3 4 5
- command /usr/bin/find