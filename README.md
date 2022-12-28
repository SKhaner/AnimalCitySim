# Project 2


## Help Received

Please document any help you received in completing this lab. Note that the what you submit should be your own work. Refer to the syllabus for more details. 

received help from Grady and Conner.

## Describe your work


## Part 1: UML Diagram

Note that you must do two tasks here:

1. Add to your repo a document `UML.png` that is a image of your UML diagram
2. Update the document `OOP-design.md` that describes your OOP design, referencing your document.
3. You will receive feedback on your design in a github issue

For your final submission, please update `UML.png` with the final UML diagram and `OOP-design.md` with your final description. Below describe the major changes you made.

# OOP Design Document

![](Project2UML.png)

## Overview

[provide a general overview of your OOP design. Once you do, remove this text!]


[For each of the classes in your OOP design, including ones provided
for you, offer a brief description of their functionality and how they
interact. Be sure to highlight good OOP like encapsulation,
inheritance, and polymorphism. Once you do, remove this text! Also be
sure the headers below match the class names, and not Class 1 and
Class 2 and etc.]


Part 1: UML Diagram

Note that you must do two tasks here:

1. Add to your repo a document UML.png that is a image of your UML diagram
2. Update the document OOP-design.md that describes your OOP design, referencing your document.
3. You will receive feedback on your design in a github issue

For your final submission, please update UML.png with the final UML diagram and OOP-design.md with your final description. Below describe the major changes you made.

I got rid of the chase function in cat and ZombieCat, i noticed I coud just change the direction in take action without doing anything extra. In the iterator in takeAction for cat and ZombieCat I made it so it checks for both mouse and rabbit. I also didnt end up using the X and Y in the constructor for each class, since I just added a getX() and getY() in the creature class. I also got rid of the turnZombie() function in Cat since it didnt work, instead I implemented it in takeAction().


## Part 2: Implementation

What level simulation did you achieve

Level : [4] <-- choose one!

If you completed Level 4, describe the additional creature you added to the simulation.

- rabbit extends from creature
- at the beggining, only 4 rabbits are put in the grid.
- it has most of the same characteristics as mouse, but there a few differences.
    - the step length for rabbit is 4, since they can jump far
    - they are displayed pink
    - and in takeAction() there is an iterator to go through the list of creatures, and if the current rabbit and another rabbit meet at the same spot they make a baby rabbit at the same X and Y.
    - rabbits never die. 
    - they randomly turn 40% of the time. 
- rabbits can be eaten and chased by cats and ZombieCats the same as mouse.

