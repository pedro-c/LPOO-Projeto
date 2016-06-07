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

    public int score=0;
    public int startTimer=0;
    public int timer=1000;
    public boolean endGame=false;
    public int highscoreTreadmill;
    public boolean saveScores = true;
    Preferences prefs;
    public int foot1_x;
    public int foot1_y;
    public int foot2_x;
    public int foot2_y;
    public int foot3_x;
    public int foot3_y;
    public int foot4_x;
    public int foot4_y;
    public int falseFoot_x;
    public int falseFoot_y;
    public boolean foot1Clicked=false;
    public boolean foot2Clicked=false;
    public boolean foot3Clicked=false;
    public boolean foot4Clicked=false;
    public boolean footClick=false;
    public int lowerFoot=2;
    public int falseFoot_xR;
    public int falseFoot_xL;
    public boolean gameReady=false;
    public boolean gameStart=false;
    public int deltaY=0;
    public String userName="";
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


        Random rn = new Random();
        int rand;

        if(!endGame && gameStart){
            timer--;
            if(footClick) {
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
                if (gameReady) {
                    score++;
                    timer+=5;
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
                    //footClick = false;
                }

            }
        }


        if (endGame==true) {
            if ((score > highscoreTreadmill) && saveScores) {
                Gdx.input.getTextInput(this, "Name", " ", "InsertYourName");
                prefs.putInteger("highscoreTreadmill", score);
                prefs.flush();
                saveToFile(score);
                highscoreTreadmill=score;
                saveScores=false;
            }

        }


        return 0;

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


}
