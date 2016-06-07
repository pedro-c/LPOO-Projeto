package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;



/**
 * Created by pedro on 17/05/2016.
 */
public class MainMenuLogic {

    private static final int WEIGHT_BALANCING = 1;
    private static final int TREADMILL = 2;
    private static final int ABS_CHALLENGE = 3;
    private static final int MULTIPLAYER_FIGHT = 4;
    private int returnValue = 0;



    public MainMenuLogic(){

    }


    public int update(OrthographicCamera gamecam, float dt, Hud hud){

        returnValue=0;
        if(Gdx.input.isTouched()){
            if((gamecam.position.x - gymSimulator.V_WIDTH/2) > 0 && (gamecam.position.x + gymSimulator.V_WIDTH/2) < 7680){
                gamecam.position.x -= Gdx.input.getDeltaX()*dt*30;
            }else if((gamecam.position.x - gymSimulator.V_WIDTH/2) <= 500){
                gamecam.position.x = gymSimulator.V_WIDTH/2+250;
            }else if((gamecam.position.x + gymSimulator.V_WIDTH/2) >= 7680){
                gamecam.position.x = 7680-gymSimulator.V_WIDTH/2-1;
            }

            if(gamecam.position.x > (gymSimulator.V_WIDTH*2))
                hud.showLabels(true);
            else{
                hud.showLabels(false);
            }



            if(gamecam.position.x > (gymSimulator.V_WIDTH*2)){
                returnValue=WEIGHT_BALANCING;
            }
            if(gamecam.position.x > (gymSimulator.V_WIDTH*3)){
                returnValue=TREADMILL;
            }
            if(gamecam.position.x > (gymSimulator.V_WIDTH*4)){
                returnValue=ABS_CHALLENGE;
            }
            if(gamecam.position.x > (gymSimulator.V_WIDTH*5)){
                returnValue=MULTIPLAYER_FIGHT;
            }


        }

        return returnValue;
    }


}
