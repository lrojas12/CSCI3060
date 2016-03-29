###Additional Back-End Requirements

**(Taken from the Slack channel)**

1. Do **not** merge the transaction files into one.

2. Do **not** count login or logout when keeping track of the total number of transactions.

	**Do**, however, append them to the "master" transaction file anyway.

<br>
######Other, to take into account:

1. The `account holder` field should *always* be filled in - aside from `admin` `login` and `logout`.

2. Aim to fix the bugs that are being uncovered, as they will make Phase 6 easier.

<br>
######To do

[   ] Add headers to *all* source code files.