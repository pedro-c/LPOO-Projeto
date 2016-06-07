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

    /**
     * Constructor. Sets up variables
     */
    public MultiplayerLogic(){

    }

    /**
     * Updates the game stats
     * @param dt delta time to be subtracted to the timer
     */

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

    /**
     * If the blue player is not defending sets the red player for attack
     */
    public void playerRedAttacking(){
        deltaSwitchFistR=0;
        if(!playerBlueDefending && !endGame){
            playerRedDAttacking=true;
        }


    }
    /**
     * If the red player is not defending sets the blue player for attack
     */
    public void playerBlueAttacking(){
        deltaSwitchFistB=0;
        if(!playerRedDefending && !endGame){
            playerBlueAttacking=true;
        }

    }
    /**
     * Resizes the red shield shadow according to user input
     */
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

    /**
     * Resizes the blue shield shadow according to user input
     */
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
    /**
     * function that makes an attack from redPlayer, taking life from blue player if possible
     */
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
    /**
     * function that makes an attack from bluePlayer, taking life from read player if possible
     */
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

    /**
     * @param bool function that sets the player red defending flag
     */
    public void setPlayerRedDefending(boolean bool){
        playerRedStartDefend=bool;

    }
    /**
     * @param bool function that sets the player blue defending flag
     */
    public void setPlayerBlueDefending(boolean bool){
        playerBlueStartDefend=bool;
    }

    /**
     *
     * @return blue player flag that tells if the player is defending
     */
    public boolean isPlayerBlueStartDefend() {
        return playerBlueStartDefend;
    }

    /**
     *
     * @param playerBlueStartDefend New Trace's Ycoordinate value
     */
    public void setPlayerBlueStartDefend(boolean playerBlueStartDefend) {
        this.playerBlueStartDefend = playerBlueStartDefend;
    }

    /**
     *
     * @return getter delta B counter
     */


    public float getDeltaBcounter() {
        return deltaBcounter;
    }
    /**
     *
     * @param deltaBcounter sets the delta b counter
     */
    public void setDeltaBcounter(float deltaBcounter) {
        this.deltaBcounter = deltaBcounter;
    }
    /**
     *
     * @return wheter the player red is defending
     */
    public boolean isPlayerRedStartDefend() {
        return playerRedStartDefend;
    }
    /**
     *
     * @param playerRedStartDefend sets the flag that tells if the player red is defending
     */
    public void setPlayerRedStartDefend(boolean playerRedStartDefend) {
        this.playerRedStartDefend = playerRedStartDefend;
    }
    /**
     *
     * @return wether the player blue is attacking
     */
    public boolean isPlayerBlueAttacking() {
        return playerBlueAttacking;
    }
    /**
     *
     * @param playerBlueAttacking sets the flag that tells if the player blue is attacking
     */
    public void setPlayerBlueAttacking(boolean playerBlueAttacking) {
        this.playerBlueAttacking = playerBlueAttacking;
    }
    /**
     *
     * @return wether the player blue is attacking
     */
    public boolean isPlayerRedDAttacking() {
        return playerRedDAttacking;
    }
    /**
     *
     * @param playerRedDAttacking sets the flag that tells if the player red is attacking
     */
    public void setPlayerRedDAttacking(boolean playerRedDAttacking) {
        this.playerRedDAttacking = playerRedDAttacking;
    }

    /**
     *
     * @return wether the player blue is defending
     */
    public boolean isPlayerBlueDefending() {
        return playerBlueDefending;
    }
    /**
     *
     * @return wether to switch fist
     */
    public boolean isSwitchFistB() {
        return switchFistB;
    }
    /**
     *
     * @param switchFistB sets the flag switchFist
     */
    public void setSwitchFistB(boolean switchFistB) {
        this.switchFistB = switchFistB;
    }
    /**
     *
     * @return wether the player red is defending
     */
    public boolean isPlayerRedDefending() {
        return playerRedDefending;
    }
    /**
     *
     * @return red delta counter
     */
    public float getDeltaRcounter() {
        return deltaRcounter;
    }
    /**
     *
     * @param deltaRcounter sets the red delta counter
     */
    public void setDeltaRcounter(float deltaRcounter) {
        this.deltaRcounter = deltaRcounter;
    }
    /**
     *
     * @return wether to switch fist
     */
    public boolean isSwitchFistR() {
        return switchFistR;
    }
    /**
     *
     * @param switchFistR sets the flag switchFist
     */
    public void setSwitchFistR(boolean switchFistR) {
        this.switchFistR = switchFistR;
    }
    /**
     *
     * @return red button size
     */
    public int getButtonRSsize() {
        return buttonRSsize;
    }
    /**
     *
     * @param buttonRSsize sets the red button size
     */
    public void setButtonRSsize(int buttonRSsize) {
        this.buttonRSsize = buttonRSsize;
    }
    /**
     *
     * @return blue player health
     */
    public int getBlueHealth() {
        return blueHealth;
    }
    /**
     *
     * @param blueHealth sets the blue player health
     */
    public void setBlueHealth(int blueHealth) {
        this.blueHealth = blueHealth;
    }
    /**
     *
     * @return blue button size
     */
    public int getButtonBSsize() {
        return buttonBSsize;
    }
    /**
     *
     * @param buttonBSsize sets the blue button size
     */
    public void setButtonBSsize(int buttonBSsize) {
        this.buttonBSsize = buttonBSsize;
    }
    /**
     *
     * @return red player health
     */
    public int getRedHealth() {
        return redHealth;
    }
    /**
     *
     * @param redHealth sets the red player health
     */
    public void setRedHealth(int redHealth) {
        this.redHealth = redHealth;
    }
    /**
     *
     * @return whether the game has ended
     */
    public boolean isEndGame() {
        return endGame;
    }
    /**
     *
     * @param endGame sets the endGame flag
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

}
