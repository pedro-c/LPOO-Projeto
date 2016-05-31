package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;

import java.util.Random;

/**
 * Created by pedro on 30/05/2016.
 */
public class TreadmillLogic {

    int rightScreen;
    public int score;
    public int startTimer=0;
    public boolean endGame=false;
    public float footPrint_x;
    public float footPrint_y = Gdx.graphics.getHeight()/3;
    public TreadmillLogic(){
        rightScreen=0;
    }
    int rand;

    public int update(float dt){

        if(rightScreen==0){
            Random random = new Random();
            rand = random.nextInt(4) + 1;
            if(rand==1 || rand==3){
                footPrint_x = Gdx.graphics.getWidth()/2+ Gdx.graphics.getWidth()/6;
            }else if(rand == 2 || rand==4){
                footPrint_x = Gdx.graphics.getWidth()/3;
            }
            rightScreen=1;
        }

        if(Gdx.input.isTouched()){
            if(endGame==false){
                if(Gdx.input.getX() > Gdx.graphics.getWidth()/2){
                    if(rand==1 || rand==3){
                        startTimer=1;
                        rightScreen=0;
                        score++;
                        return 1;
                    }
                    else return -1;
                }else {
                    if (rand == 2 || rand==4) {
                        startTimer=1;
                        rightScreen = 0;
                        score++;
                        return 1;
                    } else return -1;
                }
            }
        }
        return -1;
    }

}
