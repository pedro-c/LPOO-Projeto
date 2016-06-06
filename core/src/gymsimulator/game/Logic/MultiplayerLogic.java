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
    public boolean endGame=false;


    public boolean switchFistR=false;
    public boolean switchFistB=false;

    private int deltaSwitchFistR=0;
    private int deltaSwitchFistB=0;

    public MultiplayerLogic(){

    }

    public int update(float dt) {


        if(blueHealth<=0 || redHealth<=0)
            endGame=true;

        if(!endGame) {
            Gdx.graphics.getDeltaTime();
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


            if(switchFistR == false){
                if(deltaSwitchFistR==0){
                    if(blueHealth>0 && playerRedDAttacking)
                        blueHealth-=25;
                }
                deltaSwitchFistR++;
                if(deltaSwitchFistR>24){
                    switchFistR=true;
                    deltaSwitchFistR=0;
                }
            }else if(switchFistR == true){
                if(deltaSwitchFistR==0){
                    if(blueHealth>0 && playerRedDAttacking)
                        blueHealth-=25;
                }
                deltaSwitchFistR--;
                if(deltaSwitchFistR<-24){
                    switchFistR=false;
                    deltaSwitchFistR=0;
                }
            }

            if(switchFistB == false){
                if(deltaSwitchFistB==0){
                    if(redHealth>0 && playerBlueAttacking)
                        redHealth-=25;
                }
                deltaSwitchFistB++;
                if(deltaSwitchFistB>24){
                    switchFistB=true;
                    deltaSwitchFistB=0;
                }
            }else if(switchFistB == true){
                if(deltaSwitchFistB==0){
                    if(redHealth>0 && playerBlueAttacking)
                        redHealth-=25;
                }
                deltaSwitchFistB--;
                if(deltaSwitchFistB<-24){
                    switchFistB=false;
                    deltaSwitchFistB=0;
                }
            }
        }
        return 0;
    }

    public void playerRedAttacking(){
        deltaSwitchFistR=0;
        if(!playerBlueDefending && !endGame){
            playerRedDAttacking=true;
        }


    }
    public void playerBlueAttacking(){
        deltaSwitchFistB=0;
        if(!playerRedDefending && !endGame){
            playerBlueAttacking=true;
        }

    }

    public void setPlayerRedDefending(boolean bool){
        playerRedStartDefend=bool;

    }
    public void setPlayerBlueDefending(boolean bool){
        playerBlueStartDefend=bool;
    }




}
