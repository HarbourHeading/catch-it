# Catch-it

Catch is a game where you catch falling objects. Each caught object gives the player a point
and each missed object makes the player lose health. The game is written in java and
built with maven.

# Motivation

The catch game has been reproduced many times before - often as a beginner project - but none of them try to modernize it.
This project is meant to be a game of catch that utilizes state-of-the-art tools and ideas while keeping the gameplay largely the same.

![game](https://github.com/HarbourHeading/CatchGame/assets/69332989/3f50022d-11c5-4898-8cd2-0c74659820f8)

## Quick Start

Skip to the last step if you downloaded the jar file directly from [releases](https://github.com/HarbourHeading/catch-it/releases/latest).

```
1. git clone https://github.com/HarbourHeading/catch-it.git
2. cd catch-it
3. mvn install
4. cd target
5. java -jar catch-it.jar
```

## To do

- [ ] Create database for storing top scores.
- [ ] Add logger, stderr piped to file.
- [ ] Change game speed based on screen size.
- [ ] Implement different sorting algorithm for score file.
- [X] ~~Add "Press ESC to unpause" display to pause screen.~~
- [ ] Fix player position not resetting on restart.

## Contributing

### Clone the repo

```
git clone https://github.com/HarbourHeading/catch-it.git
cd catch-it 
```

### Install the project

```
mvn install
```

### Submit a pull request

If you want to contribute, please fork the repository and open a pull request.
