# Logback logger
This rule is at the heart of logback. It assumes that levels are ordered as follows: TRACE < DEBUG < INFO <  WARN < ERROR.

ex.
<level value="TRACE" />
    - Using any log level from application will be printed.

<level value="INFO" />
    - Only INFO, WARD, ERROR log level will be printed.

# maven-enforcer-plugin (Maven)
http://maven.apache.org/enforcer/maven-enforcer-plugin/
The Enforcer plugin provides goals to control certain environmental constraints such as Maven version, JDK version and OS family along with many more standard rules and user created rules.

