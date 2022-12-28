import java.util.Random;

public class rabbit extends Creature {
    int roundsAlive = 0;
    int x;
    int y;
    Random rnd;

    public rabbit(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        this.x = x;
        this.y = y;
        this.rnd = rnd;
        super.lab = LAB_PINK;
        super.stepLen = 4; //rabbits can jump 4 spaces at a time
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
        
        //when two rabbits meet they make a baby
        for(Creature c : city.creatures){
            if((c instanceof rabbit) && (c != this) && (c.getX() == this.getX()) && (c.getY() == this.getY())){
                city.creaturesToAdd.add(new rabbit(this.getX() ,this.getY() ,city ,rand));
            }
        }

        if(rnd.nextInt(10) < 4){// 40% of the time mouse randomly changes direction
            randomTurn();
        }
        
    }
}