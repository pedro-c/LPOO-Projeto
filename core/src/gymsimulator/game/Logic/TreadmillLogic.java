package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.Random;

/**
 * Created by pedro on 30/05/2016.
 */
public class TreadmillLogic {

    private int score=0;
    private int startTimer=0;
    private int timer=1000;
    private boolean endGame=false;
    private int highscoreTreadmill;
    private boolean saveScores = true;
    Preferences prefs;
    private int height;
    private int width;
    private int foot1_x;
    private int foot1_y;
    private int foot2_x;
    private int foot2_y;
    private int foot3_x;
    private int foot3_y;
    private int foot4_x;
    private int foot4_y;
    private int falseFoot_x;
    private int falseFoot_y;
    private boolean foot1Clicked=false;
    private boolean foot2Clicked=false;
    private boolean foot3Clicked=false;
    private boolean foot4Clicked=false;
    private boolean footClick=false;
    private int lowerFoot=2;
    private int falseFoot_xR;
    private int falseFoot_xL;
    private boolean gameReady=false;
    private boolean gameStart=false;
    private int deltaY=0;
    private String userName="";
    private FileHandle file;

    /**
     * Constructor. Sets up variables
     */

    public TreadmillLogic(){
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreTreadmill=prefs.getInteger("highscoreTreadmill");

        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        foot1_x=200;
        foot3_y=height-height/3;
        foot2_x=200+((width-400)/2);
        foot2_y=height-2*height/3;
        foot3_x=200;
        foot1_y=0;
        foot4_x=200+((width-400)/2);
        foot4_y=height;
        falseFoot_x=200;
        falseFoot_y=height-2*height/3;
        falseFoot_xR=200+((width-400)/2);
        falseFoot_xL=200;

    }


    /**
     * Updates the game stats
     * @param dt delta time to be subtracted to the timer
     */


    public int update(float dt){
        if(timer<=0)
            endGame=true;

        if(!endGame && gameStart){
            timer-= dt;
            if(footClick) {
                moveFeetDown();
                if (gameReady) {
                    score++;
                    timer+=5;

                    setNewLowerFoot();
                    setNewFeetPosition();
                }
            }
        }

        if (endGame==true) {
            if ((score > highscoreTreadmill) && saveScores) {
                //Gdx.input.getTextInput(this, "Name", " ", "InsertYourName");
                prefs.putInteger("highscoreTreadmill", score);
                prefs.flush();
                saveToFile(score);
                highscoreTreadmill=score;
                saveScores=false;
            }

        }


        return 0;

    }

    /**
     * Sets randomly the new position of every foot
     */
    public void setNewFeetPosition(){
        Random rn = new Random();
        int rand;
        if (foot1_y < 0) {
            foot1_y = height;
            foot1Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot1_x = 200 + ((width - 400) / 2);
            } else
                foot1_x = 200;
        }

        if (foot2_y < 0) {
            foot2_y = height;
            foot2Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot2_x = 200 + ((width - 400) / 2);
            } else
                foot2_x = 200;
        }

        if (foot3_y < 0) {
            foot3_y = height;
            foot3Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot3_x = 200 + ((width - 400) / 2);
            } else
                foot3_x = 200;
        }

        if (foot4_y < 0) {
            foot4_y = height;
            foot4Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot4_x = 200 + ((width - 400) / 2);
            } else
                foot4_x = 200;
        }
    }

    /**
     * Sets the foot that the user must touch
     */
    public void setNewLowerFoot(){
        if (lowerFoot == 4)
            lowerFoot = 1;
        else
            lowerFoot++;


        if (lowerFoot == 1) {
            if (foot1_x == 200)
                falseFoot_x = 200 + ((width - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 2) {
            if (foot2_x == 200)
                falseFoot_x = 200 + ((width - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 3) {
            if (foot3_x == 200)
                falseFoot_x = 200 + ((width - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 4) {
            if (foot4_x == 200)
                falseFoot_x = 200 + ((width - 400) / 2);
            else
                falseFoot_x = 200;
        }
    }

    /**
     * Creates the animation by moving the feet down
     */
    public void moveFeetDown(){
        if (deltaY < height / 3) {
            gameReady = false;
            foot1_y -= height / 9;
            foot2_y -= height / 9;
            foot3_y -= height / 9;
            foot4_y -= height / 9;
            deltaY += height / 9;

        } else {
            deltaY = 0;
            gameReady = true;
            footClick = false;
        }
    }

    /**
     * Saves highscore if needed
     */
    public void saveToFile(int score){
        if(score > highscoreTreadmill){
            String filename;
            filename = "treadHighScores.txt";
            file = Gdx.files.local(filename);

            if(file.exists()){
                file.writeString(((Integer)(score)).toString(), false);
            }
            else {
                try {
                    file.file().createNewFile();
                    file.writeString(((Integer)(score)).toString(), false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @return getter for this game highscore
     */

    public int getHighscoreTreadmill() {
        return highscoreTreadmill;
    }

    /**
     *
     * @param highscoreTreadmill New game highscore
     */

    public void setHighscoreTreadmill(int highscoreTreadmill) {
        this.highscoreTreadmill = highscoreTreadmill;
    }

    /**
     *
     * @return getter for foot 2 X coordinate
     */
    public int getFoot2_x() {
        return foot2_x;
    }

    /**
     *
     * @param foot2_x new foot 2 x coordinate
     */
    public void setFoot2_x(int foot2_x) {
        this.foot2_x = foot2_x;
    }

    /**
     *
     * @return getter for timer
     */

    public int getTimer() {
        return timer;
    }

    /**
     *
     * @param timer new timer value
     */

    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     *
     * @return getter for foot 4 Y coordinate
     */
    public int getFoot4_y() {
        return foot4_y;
    }

    /**
     *
     * @param foot4_y new foot 4 Y coordinate
     */

    public void setFoot4_y(int foot4_y) {
        this.foot4_y = foot4_y;
    }

    /**
     *
     * @return Whether the foot 3 is clicked
     */

    public boolean isFoot3Clicked() {
        return foot3Clicked;
    }

    /**
     *
     * @param foot3Clicked sets the foot 3 clicked flag according to given bool
     */

    public void setFoot3Clicked(boolean foot3Clicked) {
        this.foot3Clicked = foot3Clicked;
    }

    /**
     *
     * @return getter for foot 3 Y coordinate
     */
    public int getFoot3_y() {
        return foot3_y;
    }
    /**
     *
     * @param foot3_y new foot 3 y coordinate
     */

    public void setFoot3_y(int foot3_y) {
        this.foot3_y = foot3_y;
    }

    /**
     *
     * @return getter for foot 1 X coordinate
     */
    public int getFoot1_x() {
        return foot1_x;
    }
    /**
     *
     * @param foot1_x new foot 1 x coordinate
     */

    public void setFoot1_x(int foot1_x) {
        this.foot1_x = foot1_x;
    }

    /**
     *
     * @return getter false foot x
     */

    public int getFalseFoot_x() {
        return falseFoot_x;
    }

    /**
     *
     * @param falseFoot_x new false foot x coordinate
     */

    public void setFalseFoot_x(int falseFoot_x) {
        this.falseFoot_x = falseFoot_x;
    }

    /**
     *
     * @return wheter the game has ended
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     *
     * @param endGame sets the end of the game flag
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }


    /**
     *
     * @return wheter the foot is clicked
     */
    public boolean isFootClick() {
        return footClick;
    }

    /**
     *
     * @param footClick sets the footClick flag acording to bool
     */
    public void setFootClick(boolean footClick) {
        this.footClick = footClick;
    }


    /**
     *
     * @return wheter the foot is clicked
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName sets the userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     *
     * @return wheter the game if ready to be played or not
     */
    public boolean isGameReady() {
        return gameReady;
    }


    /**
     *
     * @param gameReady sets the flag that tells if the game is ready to start or not
     */
    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }


    /**
     *
     * @return getter fot the foot 4 x coordinate
     */
    public int getFoot4_x() {
        return foot4_x;
    }
    /**
     *
     * @param foot4_x sets the foot 4 x coordinate
     */
    public void setFoot4_x(int foot4_x) {
        this.foot4_x = foot4_x;
    }

    /**
     *
     * @return getter for the lower foot index
     */
    public int getLowerFoot() {
        return lowerFoot;
    }

    /**
     *
     * @param lowerFoot sets the lower foot index
     */
    public void setLowerFoot(int lowerFoot) {
        this.lowerFoot = lowerFoot;
    }

    /**
     *
     * @return wehter the score has been saved or not
     */
    public boolean isSaveScores() {
        return saveScores;
    }

    /**
     *
     * @param saveScores sets the flag telling if the score has been saved
     */
    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    /**
     *
     * @return geeter for deltaY
     */
    public int getDeltaY() {
        return deltaY;
    }

    /**
     *
     * @param deltaY sets the deltaY
     */

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    /**
     *
     * @return geeter the falsefoot left x coordinate
     */
    public int getFalseFoot_xL() {
        return falseFoot_xL;
    }
    /**
     *
     * @param falseFoot_xL sets the left false foot x coordinate
     */
    public void setFalseFoot_xL(int falseFoot_xL) {
        this.falseFoot_xL = falseFoot_xL;
    }


    /**
     *
     * @return wether foot 2 has been clicked
     */
    public boolean isFoot2Clicked() {
        return foot2Clicked;
    }
    /**
     *
     * @param foot2Clicked sets the flag that tells if the foot 2 has been clicked
     */
    public void setFoot2Clicked(boolean foot2Clicked) {
        this.foot2Clicked = foot2Clicked;
    }
    /**
     *
     * @return getter for the foot 3 x coordinate
     */
    public int getFoot3_x() {
        return foot3_x;
    }
    /**
     *
     * @param foot3_x sets the foot 3 x coordinate
     */
    public void setFoot3_x(int foot3_x) {
        this.foot3_x = foot3_x;
    }
    /**
     *
     * @return getter for the foot 2 y coordinate
     */

    public int getFoot2_y() {
        return foot2_y;
    }
    /**
     *
     * @param foot2_y sets the foot 2 y coordinate
     */
    public void setFoot2_y(int foot2_y) {
        this.foot2_y = foot2_y;
    }
    /**
     *
     * @return getter for the false right foot x coordinate
     */
    public int getFalseFoot_xR() {
        return falseFoot_xR;
    }
    /**
     *
     * @param falseFoot_xR sets the false right foot x coordinate
     */
    public void setFalseFoot_xR(int falseFoot_xR) {
        this.falseFoot_xR = falseFoot_xR;
    }
    /**
     *
     * @return getter for the score
     */
    public int getScore() {
        return score;
    }
    /**
     *
     * @param score sets the score
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     *
     * @return wether the game has started
     */
    public boolean isGameStart() {
        return gameStart;
    }
    /**
     *
     * @param gameStart sets the flag that tells if the game has started
     */
    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    /**
     *
     * @return wether foot 1 has beem clicked
     */
    public boolean isFoot1Clicked() {
        return foot1Clicked;
    }
    /**
     *
     * @param foot1Clicked sets the flag that tells if the foot 1 has been clicked
     */
    public void setFoot1Clicked(boolean foot1Clicked) {
        this.foot1Clicked = foot1Clicked;
    }
    /**
     *
     * @return getter for the foot 1 y coordinate
     */
    public int getFoot1_y() {
        return foot1_y;
    }
    /**
     *
     * @param foot1_y sets the foot 1 y coordinate
     */

    public void setFoot1_y(int foot1_y) {
        this.foot1_y = foot1_y;
    }
    /**
     *
     * @return wether foot 4 has beem clicked
     */
    public boolean isFoot4Clicked() {
        return foot4Clicked;
    }
    /**
     *
     * @param foot4Clicked sets the flag that tells if the foot 4 has been clicked
     */
    public void setFoot4Clicked(boolean foot4Clicked) {
        this.foot4Clicked = foot4Clicked;
    }
    /**
     *
     * @return getter for the false foot y coordinate
     */
    public int getFalseFoot_y() {
        return falseFoot_y;
    }
    /**
     *
     * @param falseFoot_y sets the false foot y coordinate
     */
    public void setFalseFoot_y(int falseFoot_y) {
        this.falseFoot_y = falseFoot_y;
    }
    /**
     *
     * @return getter for the startTimer flag
     */
    public int getStartTimer() {
        return startTimer;
    }
    /**
     *
     * @param startTimer sets the start timer flag
     */
    public void setStartTimer(int startTimer) {
        this.startTimer = startTimer;
    }

    /**
     *
     * @return Screen Height
     */

    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height New screen height value
     */

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return Screen Width
     */

    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width New screen width value
     */

    public void setWidth(int width) {
        this.width = width;
    }

}
