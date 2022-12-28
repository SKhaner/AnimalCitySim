import java.util.Random;

public class Mouse extends Creature {

    int roundsAlive = 0;
    int x;
    int y;
    Random rnd;

    public Mouse(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        this.x = x;
        this.y = y;
        this.rnd = rnd;
        super.lab = LAB_BLUE;
        super.stepLen = 1;
    }
    
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
        roundsAlive++;
    }

    public void takeAction(){

        if(roundsAlive % 20 == 0){ // to produce a new baby at the same spot after 20 rounds
            city.creaturesToAdd.add(new Mouse(this.getX() ,this.getY() ,city ,rand));
        }

        if(roundsAlive == 30){// mouse dies after 30 rounds
            dead = true;
        }

        if(rnd.nextInt(10) < 2){// 20% of the time mouse randomly changes direction
            randomTurn();
        }
        
    }
}
