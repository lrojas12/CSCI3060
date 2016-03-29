###Additional Back-End Requirements

**(Taken from the Slack channel)**

1. Do **not** merge the transaction files into one.

2. Do **not** count login or logout when keeping track of the total number of transactions.

	**Do**, however, append them to the "master" transaction file anyway.

3. Keep track **only** of the standard transactions that the user *actually* gets charged for.

<br>
######Other, to take into account:

1. The `account holder` field should *always* be filled in - aside from `admin` `login` and `logout`.

2. Aim to fix the bugs that are being uncovered, as they will make Phase 6 easier.

<br>
######To do

[ **x** ] Add headers to *all* source code files.

[ **x** ] Change `WatermelonHelper.java` class name to something else.

[   ] Format failed constraint errors: The error message should contain the **type** and **description** of the error and the **transaction** that caused it to occur.

[   ] Format *fatal* errors: The message should contain the **type**, **description** and **file** that cause the error.

[ **x** ] Add the transaction tracking where missing.

[   ] Check transaction file formatting (*fatal error*)

[  **x** ] Check account numbers assigned to accounts in the front end are, indeed, unique

[ **x** ] Get rid of redundancy

[ **x** ] Add comments and code format
