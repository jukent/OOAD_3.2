# OOAD_3.2
## OOAD5448: Raiders of the Lost Arctangent
by David Chaparro and Julia Kent

Java version: 17.0.1

Expanding the Raiders of the Lost Arctangent Game to use specific treasures and design patterns. Previous iteration can be found at: https://github.com/jukent/OOAD5448_P2.2

--------------------------------

This project is a Java text-based adventure game where 4 classes of characters (Brawlers, Sneakers, Runners, and Thieves) encounter 3 classes of Creatures (Blinkers, Orbiters, and Seekers) in a 4-level 3x3 dungeon. The game ends when either: the characters have collectively found all 24 treasures (win)**, all the creatures are defeated (win), or all of the characters are defeated (lose). At this iteration all character decisions and movement patterns are randomized.

Characters:
- Brawlers: +2 Strength Buff
- Sneakers: 50% Chance of Evasion
- Runners: +1 Move
- Thieves: +1 Strength Buff,  +1 Treasure Hunting Buff

Creatures:
- Blinkers: Begin on 4th level, move by "blinking" to any random room.
- Orbiters: Begin on any level and "orbit" its outer rooms. Can move clockwise or counterclockwise.
- Seekers: Begin on any level and awaits a character in a nearby room, then "seek out" a battle by moving towards the character.

Treasures:
- Sword: +1 Strength Buff
- Gem: +1 Strength Buff to Creatures
- Armor: -1 Strength Buff to Creatures
- Portal (consumable): Allows Characters to "blink" for their movement pattern
- Trap (consumable, immediate):  -1 Health
- Potion (consumable): +1 Health

**Each Character may only hold 1 Treasure of each type at a time.


## How to Run

To run the game, execute `Main.java` within the project. The terminal will then prompt you to press 'Enter' to begin the game.

## Results

Captured output for a single simulated game is in `SingleGameRun.txt` <br/>
All Tracker ouptuts for this single run are captures in `Logger-files/Logger-n.txt` files. <br/>
Final summary captured output from 30 runs is in `MultipleGameRun.txt` <br/>

The results of the 30-game runs can be summarized as: <br/>
Characters won by finding 24 Treasures X times. <br/>
Characters won by defeating all creatures X times. <br/>
Characters lost by all being defeated X time. <br/>

From these results we can see that . . .

## Identified OO Patterns

**Strategy** . . .

**Observer** . . .

**Decorator** . . .


## Changes to UML Diagram

. . .

## Assumptions Made

. . .

## JUnit Testing

. . .

## Citations

. . .
