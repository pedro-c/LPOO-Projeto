package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by pedro on 31/05/2016.
 */
public class AbsLogic {

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
    public boolean saveScores = false;
    private int traceSpeed;
    Preferences prefs;
    public int delta = 0;
    public boolean lift = false;

    public AbsLogic(){
        endGame=false;
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreAbs=prefs.getInteger("highscoreAbs");
    }

    public int update(float dt) {

        delta++;
        if (!endGame) {
            traceSpeed=20+2*score;
            if (trace_x >= statusBarMaxX){
                changeBarDirection = -1;
                incScore=true;
            }

            else if (trace_x <= statusBarMinX){
                changeBarDirection = 1;
                incScore=true;
            }


            trace_x += traceSpeed * changeBarDirection;

            if(Gdx.input.justTouched()){
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
        }

        if(timer <= 0){
            endGame=true;
            if(score > highscoreAbs){
                if(saveScores==false)
                prefs.putInteger("highscoreAbs", score);
                prefs.flush();
                saveScores=true;
            }

        }
        return 0;
    }
}
