# Approtionment

This project was made entirely by me (Nate Mackintosh).

To use the file, run:

**Apportionment.jar [input.csv/xlsx] [#reps]**

If the #reps argument is empty, the program will default to 435. You can optionally add **"--hamilton"** to the end if you would like the program to use the Hamilton apportionment algorithm. Otherwise, this program uses the Hunter-Hill algorithn. Apportionment will read the states' population off the csv file and print the apportioned representatives using Hamilton's method.
