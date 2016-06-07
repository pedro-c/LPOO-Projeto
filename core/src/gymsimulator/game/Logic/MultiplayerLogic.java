package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;

/**
 * Created by pedro on 31/05/2016.
 */
public class MultiplayerLogic {

    private int blueHealth= Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12;
    private int redHealth= Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12;
    private boolean playerRedDefending=false;
    private boolean playerBlueDefending=false;
    private boolean playerRedDAttacking=false;
    private boolean playerBlueAttacking=false;
    private float deltaBcounter=0;
    private float deltaRcounter=0;
    private boolean playerRedStartDefend=false;
    private boolean playerBlueStartDefend=false;
    private int buttonRSsize=0;
    private int buttonBSsize=0;
    private boolean endGame=false;
    private boolean switchFistR=false;
    private boolean switchFistB=false;
    private int deltaSwitchFistR=0;
    private int deltaSwitchFistB=0;

    public MultiplayerLogic(){

    }

    public int update(float dt) {


        if(blueHealth<=0 || redHealth<=0)
            endGame=true;

        if(!endGame) {
            Gdx.graphics.getDeltaTime();
            resizeRedShieldShadow();
            resizeBlueShieldShadow();

            redPlayerAttacking();

            bluePlayerAttacking();
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

    public void resizeRedShieldShadow(){
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
    }

    public void resizeBlueShieldShadow(){

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

    public void redPlayerAttacking(){
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
    }

    public void bluePlayerAttacking(){
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


    public void setPlayerRedDefending(boolean bool){
        playerRedStartDefend=bool;

    }
    public void setPlayerBlueDefending(boolean bool){
        playerBlueStartDefend=bool;
    }


    public boolean isPlayerBlueStartDefend() {
        return playerBlueStartDefend;
    }

    public void setPlayerBlueStartDefend(boolean playerBlueStartDefend) {
        this.playerBlueStartDefend = playerBlueStartDefend;
    }

    public float getDeltaBcounter() {
        return deltaBcounter;
    }

    public void setDeltaBcounter(float deltaBcounter) {
        this.deltaBcounter = deltaBcounter;
    }

    public boolean isPlayerRedStartDefend() {
        return playerRedStartDefend;
    }

    public void setPlayerRedStartDefend(boolean playerRedStartDefend) {
        this.playerRedStartDefend = playerRedStartDefend;
    }

    public boolean isPlayerBlueAttacking() {
        return playerBlueAttacking;
    }

    public void setPlayerBlueAttacking(boolean playerBlueAttacking) {
        this.playerBlueAttacking = playerBlueAttacking;
    }

    public boolean isPlayerRedDAttacking() {
        return playerRedDAttacking;
    }

    public void setPlayerRedDAttacking(boolean playerRedDAttacking) {
        this.playerRedDAttacking = playerRedDAttacking;
    }

    public boolean isPlayerBlueDefending() {
        return playerBlueDefending;
    }

    public boolean isSwitchFistB() {
        return switchFistB;
    }

    public void setSwitchFistB(boolean switchFistB) {
        this.switchFistB = switchFistB;
    }

    public boolean isPlayerRedDefending() {
        return playerRedDefending;
    }

    public float getDeltaRcounter() {
        return deltaRcounter;
    }

    public void setDeltaRcounter(float deltaRcounter) {
        this.deltaRcounter = deltaRcounter;
    }

    public boolean isSwitchFistR() {
        return switchFistR;
    }

    public void setSwitchFistR(boolean switchFistR) {
        this.switchFistR = switchFistR;
    }

    public int getButtonRSsize() {
        return buttonRSsize;
    }

    public void setButtonRSsize(int buttonRSsize) {
        this.buttonRSsize = buttonRSsize;
    }

    public int getBlueHealth() {
        return blueHealth;
    }

    public void setBlueHealth(int blueHealth) {
        this.blueHealth = blueHealth;
    }

    public int getButtonBSsize() {
        return buttonBSsize;
    }

    public void setButtonBSsize(int buttonBSsize) {
        this.buttonBSsize = buttonBSsize;
    }

    public int getRedHealth() {
        return redHealth;
    }

    public void setRedHealth(int redHealth) {
        this.redHealth = redHealth;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

}
