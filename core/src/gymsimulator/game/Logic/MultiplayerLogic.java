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
    public boolean playerRedDAttacking=false;
    public boolean playerBlueAttacking=false;
    public float deltaBcounter=0;
    public float deltaRcounter=0;
    public boolean playerRedStartDefend=false;
    public boolean playerBlueStartDefend=false;
    public int buttonRSsize=0;
    public int buttonBSsize=0;
    private boolean endGame=false;


    public MultiplayerLogic(){

    }

    public int update(float dt) {


        if(blueHealth==0 || redHealth==0)
            endGame=true;
        if(!endGame) {
            float delta = Gdx.graphics.getDeltaTime();
            if (playerRedStartDefend == true && deltaRcounter < 225) {
                deltaRcounter++;
                buttonRSsize++;
                playerRedDefending = true;
            } else if (playerRedStartDefend == false && deltaRcounter > 0) {
                deltaRcounter--;
                buttonRSsize--;
                playerRedDefending = false;
            } else
                playerRedDefending = false;

            if (playerBlueStartDefend == true && deltaBcounter < 225) {
                deltaBcounter++;
                buttonBSsize++;
                playerBlueDefending = true;
            } else if (playerBlueStartDefend == false && deltaBcounter > 0) {
                deltaBcounter--;
                buttonBSsize--;
                playerBlueDefending = false;
            } else
                playerBlueDefending = false;
        }
        return 0;
    }

    public void playerRedAttacking(){
        if(!playerBlueDefending){
            if(blueHealth>0)
                blueHealth-=25;
        }
        playerRedDAttacking=true;

    }
    public void playerBlueAttacking(){
        if(!playerRedDefending){
            if(redHealth>0)
                redHealth-=25;
        }
        playerBlueAttacking=true;
    }

    public void setPlayerRedDefending(boolean bool){
        playerRedStartDefend=bool;

    }
    public void setPlayerBlueDefending(boolean bool){
        playerBlueStartDefend=bool;
    }




}
