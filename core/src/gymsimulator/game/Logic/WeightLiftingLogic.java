package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import java.util.Random;

/**
 * Created by Tiago on 31/05/2016.
 */
public class WeightLiftingLogic {


    public int trace_x;
    public int trace_y;
    public boolean endGame;
    public int changeBarDirection;
    public int statusBarMaxX;
    public int statusBarMinX;
    public int statusGreenBarMinX;
    public int statusGreenBarMaxX;
    public int score;
    public boolean incScore;
    public int timer;
    public int liftTimer;
    public boolean startTimer;
    public int highscoreLifting;
    public boolean saveScores;
    private float traceSpeed;


    public WeightLiftingLogic()
    {
        changeBarDirection = 1;
        statusBarMaxX=((Gdx.graphics.getWidth()/4) * 3)-25;
        statusBarMinX=(Gdx.graphics.getWidth()/4)+ 5;
        statusGreenBarMinX =  (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/16);
        statusGreenBarMaxX = (Gdx.graphics.getWidth()/2)+(Gdx.graphics.getWidth()/16);

        Random rand = new Random();

        trace_x = rand.nextInt(statusBarMaxX - statusBarMinX + 1) + statusBarMinX;
        trace_y = Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6;

        score = 0;
        incScore = false;
        timer=8*100;
        liftTimer = 150;
        startTimer= false;
        highscoreLifting=0;
        saveScores = false;
    }

    public int update(float delta) {

        if(!endGame) {

            if (traceSpeed > 0)
                traceSpeed -= 0.5;
            else if (traceSpeed < 0)
                traceSpeed += 0.5;

            Random rand = new Random();

            int randAcc = rand.nextInt(9);
            switch (randAcc) {
                case 1:
                    traceSpeed += 0.2;
                    break;
                case 2:
                    traceSpeed += 0.4;
                    break;
                case 3:
                    traceSpeed += -0.2;
                    break;
                case 4:
                    traceSpeed += -0.4;
                    break;
                default:
                    traceSpeed += 0;
                    break;
            }

            traceSpeed += (Gdx.input.getAccelerometerY() / 4);

            trace_x += traceSpeed;

            if(trace_x > statusBarMaxX) {
                trace_x = statusBarMaxX;
                traceSpeed = 1;
            }
            if(trace_x < statusBarMinX) {
                trace_x = statusBarMinX;
                traceSpeed = -1;
            }

            liftTimer -= delta;

            if (liftTimer <= 0) {
                if (trace_x > statusGreenBarMinX && trace_x < statusGreenBarMaxX) {
                    score++;
                    trace_x = rand.nextInt(statusBarMaxX - statusBarMinX + 1) + statusBarMinX;
                    liftTimer = 150 - 4 * score;
                    if(liftTimer < 80)
                        liftTimer = 80;
                } else
                    endGame = true;
            }
        }

        return 0;
    }
}
