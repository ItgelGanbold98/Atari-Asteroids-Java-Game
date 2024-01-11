
[![My Skills](https://skillicons.dev/icons?i=git,java,idea&perline=14)](https://github.com/ItgelGanbold98)

# Atari-Asteroids-Java-Game :alien: :astronaut:

This is a recreation of the 1979 classic Atari Asteroids game :new_moon: using Java and its library JavaFX. 
The game is altered slightly from the original in terms of gameplay mechanics and aesthetic design choices.

# Personal Contributions

This is a collaborative project that involved 5 people. My role included:
  -  Project leading
  -  Coding the base game loop and many of the logic
  -  Designing the menu aesthetic
I was voted by the group as the person who contributed the most 😃
    

## Game Design

The game starts in Round 1. A single large asteroid is spawned with the player at random, while the player always spawn in the centre
of the screen. Shooting a giant asteroid creates 2 medium asteroids and shooting a medium asteroid creates two small asteroids. 
Once, there is only one small asteroid left, an alien ship is spawned. Alien will always shoot at the precise location of the player,
meaning it forces the player to move around, using the WASD keys. Once an alien and all the asteroids are successfully destroyed, 
the round is concluded. 

<img src = '/Screenshots/sc1.png'/>

The game allows users to input their names. In the future, a high-score functionality may be implemented. 

<img src = '/Screenshots/sc2.png'/>

<img src = '/Screenshots/sc3.png'/>

Other unique game mechanics include Hyper-jump. This allows the player to get out of a sticky situation by teleporting to a different location.
However, it should be used sparingly as only one HJ is allowed per round. The game difficulty is exponential as the no. of asteroids roughly scale as
$2^n$, where $n$ is the round number.


