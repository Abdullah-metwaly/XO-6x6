# XO 6x6 cells

XO 6x6 is a java game for an old tictactoe game version with advanced players with a higher level of difficulty.

## Installation

Maven is the main tool for handling the game to make the production of the project's files.
To be able to compile the project using the terminal we use this line.
```bash
mvn compile
```
For packaging the project must use:
```bash
mvn package
```
For generating the site
```bash
mvn site
```

## Game interface
#0  | #1 | #2 | #3 | #4 | #5
--- | --- | --- | --- |--- |---             
#0  | #1 | #2 | #3 | #4 | #5
#0  | #1 | #2 | #3 | #4 | #5
#0  | #1 | #2 | #3 | #4 | #5
#0  | #1 | #2 | #3 | #4 | #5
#0  | #1 | #2 | #3 | #4 | #5


______________________________________

A valid move must have an empty cell all around it in order to say whether it's valid for a player or the player can't do it. In order to win either to finish last in a row or in draw cases the last player also wins.


```Java
Used technologies

MVC model is followed to manage the pattern of classes
JavaFX for creating the GUI of the game
For DB JPA for managing the results 

```

## Fulfilled requirements
-Apache Maven-specific

-Portability

-Documentation

-Logging

-Unit Tests

-Handling of Files

-Packaging

## License
[UNIDEB](https://unideb.hu)