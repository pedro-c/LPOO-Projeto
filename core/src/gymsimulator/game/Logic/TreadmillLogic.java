package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;

/**
 * Created by pedro on 30/05/2016.
 */
public class TreadmillLogic {

    int rightScreen;
    public int score;
    public int startTimer=0;
    public boolean endGame=false;
    public TreadmillLogic(){
        rightScreen=0;
    }


    public int update(float dt){

        if(Gdx.input.isTouched()){
            if(endGame){
                if(Gdx.input.getX() > Gdx.graphics.getWidth()/2){
                    if(rightScreen==0){
                        startTimer=1;
                        rightScreen=1;
                        score++;
                        return 1;
                    }
                    else return -1;
                }else {
                    if (rightScreen == 1) {
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
