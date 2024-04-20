# Catch Game

Catch is a game where you catch falling objects. Each caught object gives the player a point
and each missed object makes the player lose health. The game is written in java and
built with maven.

## Play

The game starts in a paused state. Press the ESC key to unpause. Use A or left arrow to move left,
and D or right arrow to move right. Upon losing all 5 lives, you are greeted with a game over screen.
The game over screen displays the top 3 scores. If your score beats one of them, it replaces the lower score.
Press F to restart.

![game](https://github.com/HarbourHeading/CatchGame/assets/69332989/3f50022d-11c5-4898-8cd2-0c74659820f8)

## Setup

Skip to the last step if you downloaded the jar file directly.

```
1. git clone https://github.com/HarbourHeading/CatchGame.git
2. cd CatchGame
3. mvn install
4. cd target
5. java -jar catchGame-0.0.1-SNAPSHOT.jar
```

## To do

- [ ] Create database for storing top scores.
- [ ] Add logger, stderr piped to file.
- [ ] Change game speed based on screen size.
- [ ] Implement sorting algorithm for score file.
- [ ] Fix player position not resetting on restart.

## Contributing

The project is open-source. Any and all contributions are welcome.<br>
All feedback in respect to the project is just as appreciated!
