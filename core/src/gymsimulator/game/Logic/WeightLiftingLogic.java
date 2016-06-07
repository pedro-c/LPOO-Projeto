package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.Random;


/**
 * Created by Tiago on 31/05/2016.
 */
public class WeightLiftingLogic implements Input.TextInputListener {

    Preferences prefs;

    private static final long[] loseVibratePattern = new long[] {0, 100, 30, 100};
    private int trace_x;
    private int trace_y;
    private boolean endGame=false;
    private int changeBarDirection;
    private int statusBarMaxX;
    private int statusBarMinX;
    private int statusGreenBarMinX;
    private int statusGreenBarMaxX;
    private int score;
    private boolean incScore;
    private int timer;
    private int liftTimer;
    private boolean startTimer;
    private int highscoreLifting = 0;
    private float traceSpeed;
    private double accInput;
    private boolean lifted = false;
    private int leftArmSize=200;
    private int rightArmSize=200;
    private float weightRotation=0;
    private boolean gameStart=false;
    private boolean saveScores=true;
    private String userName="";
    private FileHandle file;



    public WeightLiftingLogic()
    {
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreLifting = prefs.getInteger("highscoreWeight");


        changeBarDirection = 1;
        statusBarMaxX=((Gdx.graphics.getWidth()/4) * 3)-25;
        statusBarMinX=(Gdx.graphics.getWidth()/4)+ 5;
        statusGreenBarMinX =  (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/16);
        statusGreenBarMaxX = (Gdx.graphics.getWidth()/2)+(Gdx.graphics.getWidth()/16);

        Random rand = new Random();

        trace_x = statusGreenBarMaxX - (statusGreenBarMaxX - statusGreenBarMinX)/2 ;
        trace_y = Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6;

        score = 0;
        incScore = false;
        timer=8*100;
        liftTimer = 150;
        startTimer= false;
    }

    @Override
    public void input (String text) {
        this.userName=text;
    }

    @Override
    public void canceled () {

    }
    public int update(float delta) {

        accInput = Gdx.input.getAccelerometerY();
        if(!endGame && gameStart) {
            if(trace_x-(Gdx.graphics.getWidth()/4)+ 5<statusBarMaxX/2)
                weightRotation=trace_x/100;
            else
                weightRotation=-trace_x/100;
            if (!lifted) {
                endGame = lift(delta);
                if(endGame)
                    saveScore();
            }
            else
                lifted = wait(delta);
        }

        return 0;
    }

    public boolean wait(float delta){

        liftTimer -= delta;
        if(liftTimer <= 0) {

            liftTimer = 150 - 4 * score;
            if(liftTimer < 80)
                liftTimer = 80;
            Random rand = new Random();
            trace_x = rand.nextInt(statusBarMaxX - statusBarMinX + 1) + statusBarMinX;
            return false;
        }
        return true;
    }

    public boolean lift(float delta){
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

        traceSpeed += calcAcceleration();

        trace_x += traceSpeed;

        if (trace_x > statusBarMaxX) {
            trace_x = statusBarMaxX;
            traceSpeed = 1;
        }
        if (trace_x < statusBarMinX) {
            trace_x = statusBarMinX;
            traceSpeed = -1;
        }

        liftTimer -= delta;

        if (liftTimer <= 0) {
            if (trace_x > statusGreenBarMinX && trace_x < statusGreenBarMaxX) {
                Gdx.input.vibrate(75);
                lifted = true;
                liftTimer = 40;
                score++;

            } else {
                Gdx.input.vibrate(loseVibratePattern, -1);
                return true;
            }
        }
        return false;
    }

    public double calcAcceleration(){

        double  mult = (double)score / 9 + 0.2;

        double acc = accInput * mult;

        return acc;
    }

    public void saveScore()
    {
            if(score > highscoreLifting && saveScores) {
                //Gdx.input.getTextInput(this, "Name", " ", "InsertYourName");
                prefs.putInteger("highscoreWeight", score);
                prefs.flush();
                saveScores=false;
                highscoreLifting=score;
                saveToFile(score);
            }
    }

    public void saveToFile(int score){
        String filename;
        filename = "weightHighScores.txt";
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

    public static long[] getLoseVibratePattern() {
        return loseVibratePattern;
    }

    public int getTrace_x() {
        return trace_x;
    }

    public void setTrace_x(int trace_x) {
        this.trace_x = trace_x;
    }

    public int getTrace_y() {
        return trace_y;
    }

    public void setTrace_y(int trace_y) {
        this.trace_y = trace_y;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public int getChangeBarDirection() {
        return changeBarDirection;
    }

    public void setChangeBarDirection(int changeBarDirection) {
        this.changeBarDirection = changeBarDirection;
    }

    public int getStatusBarMaxX() {
        return statusBarMaxX;
    }

    public void setStatusBarMaxX(int statusBarMaxX) {
        this.statusBarMaxX = statusBarMaxX;
    }

    public int getStatusBarMinX() {
        return statusBarMinX;
    }

    public void setStatusBarMinX(int statusBarMinX) {
        this.statusBarMinX = statusBarMinX;
    }

    public int getStatusGreenBarMinX() {
        return statusGreenBarMinX;
    }

    public void setStatusGreenBarMinX(int statusGreenBarMinX) {
        this.statusGreenBarMinX = statusGreenBarMinX;
    }

    public int getStatusGreenBarMaxX() {
        return statusGreenBarMaxX;
    }

    public void setStatusGreenBarMaxX(int statusGreenBarMaxX) {
        this.statusGreenBarMaxX = statusGreenBarMaxX;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIncScore() {
        return incScore;
    }

    public void setIncScore(boolean incScore) {
        this.incScore = incScore;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getLiftTimer() {
        return liftTimer;
    }

    public void setLiftTimer(int liftTimer) {
        this.liftTimer = liftTimer;
    }

    public boolean isStartTimer() {
        return startTimer;
    }

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    public int getHighscoreLifting() {
        return highscoreLifting;
    }

    public void setHighscoreLifting(int highscoreLifting) {
        this.highscoreLifting = highscoreLifting;
    }

    public float getTraceSpeed() {
        return traceSpeed;
    }

    public void setTraceSpeed(float traceSpeed) {
        this.traceSpeed = traceSpeed;
    }

    public double getAccInput() {
        return accInput;
    }

    public void setAccInput(double accInput) {
        this.accInput = accInput;
    }

    public boolean isLifted() {
        return lifted;
    }

    public void setLifted(boolean lifted) {
        this.lifted = lifted;
    }

    public int getLeftArmSize() {
        return leftArmSize;
    }

    public void setLeftArmSize(int leftArmSize) {
        this.leftArmSize = leftArmSize;
    }

    public int getRightArmSize() {
        return rightArmSize;
    }

    public void setRightArmSize(int rightArmSize) {
        this.rightArmSize = rightArmSize;
    }

    public float getWeightRotation() {
        return weightRotation;
    }

    public void setWeightRotation(float weightRotation) {
        this.weightRotation = weightRotation;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isSaveScores() {
        return saveScores;
    }

    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
}
