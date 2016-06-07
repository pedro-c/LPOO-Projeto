package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.Random;

/**
 * Created by pedro on 30/05/2016.
 */
public class TreadmillLogic implements Input.TextInputListener  {

    private int score=0;
    private int startTimer=0;
    private int timer=1000;
    private boolean endGame=false;
    private int highscoreTreadmill;
    private boolean saveScores = true;
    Preferences prefs;
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



    public TreadmillLogic(){
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreTreadmill=prefs.getInteger("highscoreTreadmill");

        foot1_x=200;
        foot3_y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/3;
        foot2_x=200+((Gdx.graphics.getWidth()-400)/2);
        foot2_y=Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/3;
        foot3_x=200;
        foot1_y=0;
        foot4_x=200+((Gdx.graphics.getWidth()-400)/2);
        foot4_y=Gdx.graphics.getHeight();
        falseFoot_x=200;
        falseFoot_y=Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/3;
        falseFoot_xR=200+((Gdx.graphics.getWidth()-400)/2);
        falseFoot_xL=200;

    }


    @Override
    public void input (String text) {
        this.userName=text;
    }

    @Override
    public void canceled () {

    }


    public int update(float dt){
        if(timer<=0)
            endGame=true;

        if(!endGame && gameStart){
            timer--;
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

    public void setNewFeetPosition(){
        Random rn = new Random();
        int rand;
        if (foot1_y < 0) {
            foot1_y = Gdx.graphics.getHeight();
            foot1Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot1_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            } else
                foot1_x = 200;
        }

        if (foot2_y < 0) {
            foot2_y = Gdx.graphics.getHeight();
            foot2Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot2_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            } else
                foot2_x = 200;
        }

        if (foot3_y < 0) {
            foot3_y = Gdx.graphics.getHeight();
            foot3Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot3_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            } else
                foot3_x = 200;
        }

        if (foot4_y < 0) {
            foot4_y = Gdx.graphics.getHeight();
            foot4Clicked = false;
            rand = rn.nextInt(2);
            if (rand == 1) {
                foot4_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            } else
                foot4_x = 200;
        }
    }

    public void setNewLowerFoot(){
        if (lowerFoot == 4)
            lowerFoot = 1;
        else
            lowerFoot++;


        if (lowerFoot == 1) {
            if (foot1_x == 200)
                falseFoot_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 2) {
            if (foot2_x == 200)
                falseFoot_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 3) {
            if (foot3_x == 200)
                falseFoot_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            else
                falseFoot_x = 200;
        } else if (lowerFoot == 4) {
            if (foot4_x == 200)
                falseFoot_x = 200 + ((Gdx.graphics.getWidth() - 400) / 2);
            else
                falseFoot_x = 200;
        }
    }
    public void moveFeetDown(){
        if (deltaY < Gdx.graphics.getHeight() / 3) {
            gameReady = false;
            foot1_y -= Gdx.graphics.getHeight() / 9;
            foot2_y -= Gdx.graphics.getHeight() / 9;
            foot3_y -= Gdx.graphics.getHeight() / 9;
            foot4_y -= Gdx.graphics.getHeight() / 9;
            deltaY+=Gdx.graphics.getHeight() / 9;

        } else {
            deltaY = 0;
            gameReady = true;
            footClick = false;
        }
    }

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

    public int getHighscoreTreadmill() {
        return highscoreTreadmill;
    }

    public void setHighscoreTreadmill(int highscoreTreadmill) {
        this.highscoreTreadmill = highscoreTreadmill;
    }

    public int getFoot2_x() {
        return foot2_x;
    }

    public void setFoot2_x(int foot2_x) {
        this.foot2_x = foot2_x;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getFoot4_y() {
        return foot4_y;
    }

    public void setFoot4_y(int foot4_y) {
        this.foot4_y = foot4_y;
    }

    public boolean isFoot3Clicked() {
        return foot3Clicked;
    }

    public void setFoot3Clicked(boolean foot3Clicked) {
        this.foot3Clicked = foot3Clicked;
    }

    public int getFoot3_y() {
        return foot3_y;
    }

    public void setFoot3_y(int foot3_y) {
        this.foot3_y = foot3_y;
    }

    public int getFoot1_x() {
        return foot1_x;
    }

    public void setFoot1_x(int foot1_x) {
        this.foot1_x = foot1_x;
    }

    public int getFalseFoot_x() {
        return falseFoot_x;
    }

    public void setFalseFoot_x(int falseFoot_x) {
        this.falseFoot_x = falseFoot_x;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isFootClick() {
        return footClick;
    }

    public void setFootClick(boolean footClick) {
        this.footClick = footClick;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public void setGameReady(boolean gameReady) {
        this.gameReady = gameReady;
    }

    public int getFoot4_x() {
        return foot4_x;
    }

    public void setFoot4_x(int foot4_x) {
        this.foot4_x = foot4_x;
    }

    public int getLowerFoot() {
        return lowerFoot;
    }

    public void setLowerFoot(int lowerFoot) {
        this.lowerFoot = lowerFoot;
    }

    public boolean isSaveScores() {
        return saveScores;
    }

    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public int getFalseFoot_xL() {
        return falseFoot_xL;
    }

    public void setFalseFoot_xL(int falseFoot_xL) {
        this.falseFoot_xL = falseFoot_xL;
    }

    public boolean isFoot2Clicked() {
        return foot2Clicked;
    }

    public void setFoot2Clicked(boolean foot2Clicked) {
        this.foot2Clicked = foot2Clicked;
    }

    public int getFoot3_x() {
        return foot3_x;
    }

    public void setFoot3_x(int foot3_x) {
        this.foot3_x = foot3_x;
    }

    public int getFoot2_y() {
        return foot2_y;
    }

    public void setFoot2_y(int foot2_y) {
        this.foot2_y = foot2_y;
    }

    public int getFalseFoot_xR() {
        return falseFoot_xR;
    }

    public void setFalseFoot_xR(int falseFoot_xR) {
        this.falseFoot_xR = falseFoot_xR;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isFoot1Clicked() {
        return foot1Clicked;
    }

    public void setFoot1Clicked(boolean foot1Clicked) {
        this.foot1Clicked = foot1Clicked;
    }

    public int getFoot1_y() {
        return foot1_y;
    }

    public void setFoot1_y(int foot1_y) {
        this.foot1_y = foot1_y;
    }

    public boolean isFoot4Clicked() {
        return foot4Clicked;
    }

    public void setFoot4Clicked(boolean foot4Clicked) {
        this.foot4Clicked = foot4Clicked;
    }

    public int getFalseFoot_y() {
        return falseFoot_y;
    }

    public void setFalseFoot_y(int falseFoot_y) {
        this.falseFoot_y = falseFoot_y;
    }

    public int getStartTimer() {
        return startTimer;
    }

    public void setStartTimer(int startTimer) {
        this.startTimer = startTimer;
    }


}
