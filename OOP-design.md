# OOP Design Document

[Update the path below so your UML diagram appears in the rendered markdown, once you do, remove this text!]

![](/Project2UML.png)

## Overview

[provide a general overview of your OOP design. Once you do, remove this text!]


[For each of the classes in your OOP design, including ones provided
for you, offer a brief description of their functionality and how they
interact. Be sure to highlight good OOP like encapsulation,
inheritance, and polymorphism. Once you do, remove this text! Also be
sure the headers below match the class names, and not Class 1 and
Class 2 and etc.]


## Mouse
- mouse extends Creature and the constructor method in mouse calls the constructor method in Creature using the Super keyword. 
- mouse has attributes roundsAlive, x, y, and Random rnd.
- x and y are set when the mouse constructor is called using "this.x = x" and the same for y. the color is also set when the constructor is called using "super.lab = LAB_BLUE". stepLen is also set using super and is set to 1
- rounds alive will increment every round the mouse is on the board and when it reaches 20, the mouse constructor is called again using the same x and y of the original mouse. the code would look something like
if(mouse.roundsAlive % 20 = 0){
    creaturestoadd.add(new mouse(this.getX(), this.getY(), city, rnd));
}


## Cat
- Cat extends Creature and the constructor method in Cat calls the constructor method in Creature using the Super keyword. 
- cat has attributes roundsAlive, miceAte, moves, x, y, boolean foundFood, Creature closestFood, and foodDistance.
- x, y, stepLen, and lab are set the same as they are set in mouse. but cat has a step length of 2 and lab = blue
- moves and miceAte are there to determine whether a cat turns into a zombie or not. if(moves % 50 == 0 && miceAte == 0){ turn zombie }
- the miceAte will be reset after every 50 moves so if its greater than 0 we know the cat ate a mouse during the current 50 moves.
- in takeAction of foundFood is set to true, the cat turns lab to cyan if a mouse is found to chase, it would also change the dir by using get and set dir.
- in the iterator in takeAction() there is also an if statemtn that determines if any other prey is closer than the one previously found, if true, then chase the new prey.
- cat turns zombie in takeAction() by setting the cat to dead and making a new zombie cat at this.getX() and this.getY().


## ZombieCat
- zombieCat extends from Cat and inherits everything from it
- zombieCat has creaturesAte instead of miceAte since they can eat both cats and mice.
- the color change when food is found is implemented the same as in Cat. 

## Extra creature: Rabbit
- extends creature
- same constructor as mouse, except color is set to pink
- takeAction(): iterates through creatures and if current rabbit and another rabbit are at the same spot, they make a baby rabbit. they also turn randomly 40% of the time.

