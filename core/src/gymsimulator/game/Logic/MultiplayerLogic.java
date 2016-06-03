package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;

/**
 * Created by pedro on 31/05/2016.
 */
public class MultiplayerLogic {

    public int blueHealth= Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12;
    public int redHealth= Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12;
    public boolean playerRedDefending=false;
    public boolean playerBlueDefending=false;

    public MultiplayerLogic(){

    }

    public int update(float dt) {

        return 0;
    }

    public void playerRedAttacking(){
        if(!playerBlueDefending){
            blueHealth-=25;
        }
    }
    public void playerBlueAttacking(){
        if(!playerRedDefending){
            redHealth-=25;
        }
    }

    public void setPlayerRedDefending(boolean bool){
        playerRedDefending=bool;
    }
    public void setPlayerBlueDefending(boolean bool){
        playerBlueDefending=bool;
    }




}
