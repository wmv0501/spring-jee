This rule is at the heart of logback. It assumes that levels are ordered as follows: TRACE < DEBUG < INFO <  WARN < ERROR.

ex.
<level value="TRACE" />
    - Using any log level from application will be printed.

<level value="INFO" />
    - Only INFO, WARD, ERROR log level will be printed.

