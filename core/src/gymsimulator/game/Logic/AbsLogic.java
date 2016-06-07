package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

/**
 * Created by pedro on 31/05/2016.
 */
public class AbsLogic{

    public int trace_x= (Gdx.graphics.getWidth()/2)-10;
    public int trace_y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6;
    public boolean endGame;
    public int changeBarDirection=1;
    public int statusBarMaxX=((Gdx.graphics.getWidth()/4) * 3)-25;
    public int statusBarMinX=(Gdx.graphics.getWidth()/4)+ 5;
    public int statusGreenBarMinX =  (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/16);
    public int statusGreenBarMaxX = (Gdx.graphics.getWidth()/2)+(Gdx.graphics.getWidth()/16);
    public int score=0;
    public boolean incScore = false;
    public int timer=8*100;
    public boolean startTimer=false;
    public int highscoreAbs=0;
    public boolean saveScores = true;
    public int traceSpeed;
    Preferences prefs;
    public int delta = 0;
    public boolean lift = false;
    public boolean gameStart = false;
    public java.lang.String userName;
    public boolean touched;
    private FileHandle file;
    MyTextInputListener listener = new MyTextInputListener();

    public AbsLogic(){
        endGame=false;
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreAbs=prefs.getInteger("highscoreAbs");


    }

    public int update(float dt) {
        delta++;
        touched = Gdx.input.justTouched();


        if (!endGame && gameStart) {
            barMovement();

            if(touched)
                handleTouch();
        }

        if(timer <= 0){
            endGame=true;
            saveScore();
        }
        return 0;
    }

    public void saveScore()
    {
        if(score > highscoreAbs && saveScores) {
            prefs.putInteger("highscoreWeight", score);
            prefs.flush();
            saveScores=false;
            highscoreAbs=score;
            saveToFile(score);
        }
    }

    public void handleTouch()
    {

        startTimer=true;
        if(trace_x > statusGreenBarMinX && trace_x < statusGreenBarMaxX){
            if(incScore == true){
                score++;
                incScore=false;
                timer+=50;
                lift=true;
                delta=0;

            }

        }else{
            Gdx.input.vibrate(60);
            if(score > 0){
                score--;
            }
        }
    }

    public void barMovement()
    {
        traceSpeed = calcTraceSpeed();
        if (trace_x >= statusBarMaxX){
            trace_x = statusBarMaxX;
            changeBarDirection = -1;
            incScore=true;
        }

        else if (trace_x <= statusBarMinX){
            trace_x = statusBarMinX;
            changeBarDirection = 1;
            incScore=true;
        }

        trace_x += traceSpeed * changeBarDirection;
    }

    public int calcTraceSpeed()
    {
        int speed = 20 + 2 * score;

        return speed;
    }

    public void saveToFile(int score){
        //Gdx.input.getTextInput(listener, "Name", " ", " ");
        userName=listener.returnUser();
        String filename;
        filename = "absHighScores.txt";
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
