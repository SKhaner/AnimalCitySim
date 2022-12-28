import java.util.Random;

public class Cat extends Creature {

    int roundsAlive = 0;
    int miceAte = 0;
    int moves = 0;
    int x;
    int y;
    Random rnd;
    Creature closestMouse;
    int mouseDistance;
    boolean foundFood = false;

    public Cat(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        this.x = x;
        this.y = y;
        this.rnd = rnd;
        super.lab = LAB_YELLOW;
        super.stepLen = 2;
    }

    // public void foundFood(Creature m){//include get and set dir
    //     super.lab = LAB_CYAN;//set color to cyan
        //add change dir // set wrong, make it so cat is chasing mouse, not just going in same dir
        //int mouseDirection = m.getDir();
        //super.setDir(mouseDirection);
        // int mouseX = m.getX();
        // int mouseY = m.getY();
        //get x and y of cat and compute differnce to determine how much cat needs to move
        // int catX = this.getX();
        // int catY = this.getY();

        // int xDiff = mouseX - catX;
        // int yDiff = mouseY - catY;

        //if mouse.y < cat.y, set cat dir to north, same logic for east, south, and west
    //     if(m.getY() < this.getY()){//case for when mouse is north of cat
    //         this.setDir(0);
    //     }
    //     if(m.getY() > this.getY()){//case for when mouse is south of cat
    //         this.setDir(2);
    //     }
    //     if(m.getX() < this.getX()){//case for when mouse is west of cat
    //         this.setDir(3);
    //     }
    //     if(m.getX() > this.getX()){//case for when mouse is east of cat
    //         this.setDir(1);
    //     }
    // }
 
    // public void turnZombie(Creature a){
    //     a = (ZombieCat)a;
    //     a.lab = LAB_RED;
    // }

    public void step(){
        int facing = super.getDir();
        if(facing == 0){
            //y-=stepLen;
            setGridPoint(getX(), (getY()-stepLen + City.HEIGHT) % City.HEIGHT);
        }
        if(facing == 1){
            //x+=stepLen;
            setGridPoint((getX()+stepLen + City.WIDTH) % City.WIDTH, getY());
        }
        if(facing == 2){
            //y+=stepLen;
            setGridPoint(getX(), (getY()+stepLen + City.HEIGHT) % City.HEIGHT);
        }
        if(facing == 3){
            //x-=stepLen;
            setGridPoint((getX()-stepLen + City.WIDTH) % City.WIDTH, getY());
        }
        moves++;
    } 

    public void takeAction(){//check order!!!
        
        if(moves % 50 == 0 && miceAte == 0){
            //turnZombie(this);// uncomment when implementing level 3
            this.dead = true;//level 2
            city.creaturesToAdd.add(new ZombieCat(this.getX() ,this.getY() ,city ,rand));
        }
        //every 50 rounds reset miceAte so we can test if mice has been eaten in that 50 rounds,
        // should this be implented before or after turning cat dead?

        if(moves % 50 == 0){// does this properly reset miceAte after every 50 rounds?? is it in the right order???
            miceAte = 0;
        }

        if(foundFood == false){//when cat istn chasing a mouse, color = yellow
            super.lab = LAB_YELLOW;
        }

        for(Creature c : city.creatures){
            if(c.dead == true){
                continue;
            }
            if((c instanceof Mouse || c instanceof rabbit) && (c.getX() == this.getX()) && (c.getY() == this.getY())){
                //case to eat mouse
                c.dead = true;
                this.miceAte++;
                foundFood = false; // once the cat has eaten a mouse, set foundfood to false so it can look for a new target
                super.lab = LAB_YELLOW;
                return;
            }
            // if(c instanceof rabbit && (c.getX() == this.getX()) && (c.getY() == this.getY())){
            //     //case to eat mouse
            //     c.dead = true;
            //     this.miceAte++;
            //     foundFood = false; // once the cat has eaten a mouse, set foundfood to false so it can look for a new target
            //     super.lab = LAB_YELLOW;
            //     return;
            // }
            if((c instanceof Mouse == true || c instanceof rabbit == true) && dist(c) < 20){
                // case to chase mouse
                foundFood = true;
                closestMouse = c;
                mouseDistance = dist(c);
                if(dist(c) < mouseDistance){//get closest mouse
                    closestMouse = c;
                    mouseDistance = dist(c);
                }
            }
            // if(c instanceof rabbit == true && this.dist(c) < 20){
            //     // case to chase mouse
            //     foundFood = true;
            //     closestMouse = c;
            //     mouseDistance = dist(c);
            //     if(dist(c) < mouseDistance){//get closest mouse
            //         closestMouse = c;
            //         mouseDistance = dist(c);
            //     }
            // }
        }

        if(foundFood == true){
            super.lab = LAB_CYAN;//set color to cyan
            if(closestMouse.getY() < this.getY()){//case for when mouse is north of cat
                this.setDir(0);
            }
            if(closestMouse.getY() > this.getY()){//case for when mouse is south of cat
                this.setDir(2);
            }
            if(closestMouse.getX() < this.getX()){//case for when mouse is west of cat
                this.setDir(3);
            }
            if(closestMouse.getX() > this.getX()){//case for when mouse is east of cat
                this.setDir(1);
            }
        } else{
            super.lab = LAB_YELLOW;
        }
        
        if(rnd.nextInt(100) < 5){// 5% of the time cat randomly changes direction
            randomTurn();
        }

        //System.out.println(foundFood);//for debugging

        roundsAlive++;

    }
    
}
