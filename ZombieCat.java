import java.util.Random;

public class ZombieCat extends Cat {

    int creaturesAte = 0;
    int roundsAlive = 0;
    int x;
    int y;
    Creature closestFood;
    int foodDistance;
    boolean foundFood = false;

    public ZombieCat(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        this.x = x;
        this.y = y;
        super.lab = LAB_RED;
        super.stepLen = 3;
    }

    // public void foundFood(Creature f){
    //     f.lab = LAB_BLACK;
    //     int foodDirection = f.getDir();
    //     setDir(foodDirection);
    // }

    public void step(){// move forward 1 step, not implemented right, use setx and sety, fix!!!
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

    }

    public void takeAction(){
        if(roundsAlive == 100 && creaturesAte == 0){
            dead = true;
        }
        if(moves % 100 == 0){
            creaturesAte = 0;
        }
        if(foundFood == false){//when cat istn chasing a mouse, color = yellow
            super.lab = LAB_RED;
        }
        for(Creature c : city.creatures){
            if(c.dead == true){
                continue;
            }
            if(c instanceof ZombieCat == false && c.getX() == this.getX() && c.getY() == this.getY()){
                //case to eat cat
                if(c instanceof Cat == true){
                    int tempX = c.getX();
                    int tempY = c.getY();
                    c.dead = true;//level 2
                    city.creaturesToAdd.add(new ZombieCat(tempX ,tempY ,city ,rand));
                    // super.x = this.x; //change cat position so its same as zombie// do i need this??
                    // super.y = this.y;
                }
                //case to eat anything else
                if(c instanceof Cat == false){
                    c.dead = true;
                }
                this.creaturesAte++;
            }
            if(c instanceof ZombieCat == false && dist(c) < 40){
                // case to chase food
                foundFood = true;
                closestFood = c;
                foodDistance = dist(c);
                if(dist(c) < foodDistance){//get closest mouse
                    closestFood = c;
                    foodDistance = dist(c);
                }
            }
        }

        if(foundFood == true){
            super.lab = LAB_BLACK;//set color to cyan
            if(closestFood.getY() < this.getY()){//case for when mouse is north of cat
                this.setDir(0);
            }
            if(closestFood.getY() > this.getY()){//case for when mouse is south of cat
                this.setDir(2);
            }
            if(closestFood.getX() < this.getX()){//case for when mouse is west of cat
                this.setDir(3);
            }
            if(closestFood.getX() > this.getX()){//case for when mouse is east of cat
                this.setDir(1);
            }
        } else{
            super.lab = LAB_RED;
        }
        roundsAlive++;
    }

    
    
}
