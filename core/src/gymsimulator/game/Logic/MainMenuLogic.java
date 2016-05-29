package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.States.RunnerGameState;
import gymsimulator.game.gymSimulator;



/**
 * Created by pedro on 17/05/2016.
 */
public class MainMenuLogic {

    private static final int WEIGHT_BALANCING = 1;
    private static final int TREADMILL = 2;
    public int returnValue = 0;

    public MainMenuLogic(){

    }


    public int update(OrthographicCamera gamecam, float dt, Hud hud){

        returnValue=0;
        if(Gdx.input.isTouched()){
            if((gamecam.position.x - gymSimulator.V_WIDTH/2) > 0 && (gamecam.position.x + gymSimulator.V_WIDTH/2) < 1600){
                gamecam.position.x -= Gdx.input.getDeltaX()*dt*12;
            }else if((gamecam.position.x - gymSimulator.V_WIDTH/2) < 0){
                gamecam.position.x = gymSimulator.V_WIDTH/2+1;
            }else if((gamecam.position.x + gymSimulator.V_WIDTH/2) > 1600){
                gamecam.position.x = 1600-gymSimulator.V_WIDTH/2-1;
            }

            if(gamecam.position.x > (gymSimulator.V_WIDTH*2))
                hud.showLabels(true);
            else{
                hud.showLabels(false);
            }
            if(gamecam.position.x > (gymSimulator.V_WIDTH*2+gymSimulator.V_WIDTH/4)){
                returnValue=WEIGHT_BALANCING;

            }

            if(gamecam.position.x > (gymSimulator.V_WIDTH*3+gymSimulator.V_WIDTH/4)){
                returnValue=TREADMILL;
            }


        }

        return returnValue;
    }


}
