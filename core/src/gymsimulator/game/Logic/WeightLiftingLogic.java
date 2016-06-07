package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.util.Random;


/**
 * Created by Tiago on 31/05/2016.
 */
public class WeightLiftingLogic  {

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

    /**
     * Constructor. Sets up variables
     */

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



    /**
     *
     * @param delta Delta time
     * @return
     */
    public void update(float delta) {

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

    }

    /**
     *
     * @param delta Delta time to be subtracted to the timer
     * @return Whether or not the program should keep waiting
     */
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

    /**
     *
     * @param delta Delta time to be subtracted to the timer
     * @return Whether or not the game is over
     */
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

    /**
     * Calculates acceleration of the trace
     * @return Acceleration
     */

    public double calcAcceleration(){

        double  mult = (double)score / 9 + 0.2;

        double acc = accInput * mult;

        return acc;
    }

    /**
     * Saves highscore if needed
     */

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

    /**
     * @return Lose vibrate Pattern
     */

    public static long[] getLoseVibratePattern() {
        return loseVibratePattern;
    }

    /**
     *
     * @return getter for Trace's X coordinatie
     */

    public int getTrace_x() {
        return trace_x;
    }

    /**
     *
     * @param trace_x New Trace's X coordinate value
     */

    public void setTrace_x(int trace_x) {
        this.trace_x = trace_x;
    }

    /**
     *
     * @return getter for Trace's Y coordinatie
     */

    public int getTrace_y() {
        return trace_y;
    }

    /**
     *
     * @param trace_y New Trace's Ycoordinate value
     */

    public void setTrace_y(int trace_y) {
        this.trace_y = trace_y;
    }

    /**
     *
     * @return endGame value
     */

    public boolean isEndGame() {
        return endGame;
    }

    /**
     *
     * @param endGame New endGame value
     */

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    /**
     *
     * @return BarDirection (-1 for left, 1 for right)
     */

    public int getChangeBarDirection() {
        return changeBarDirection;
    }

    /**
     *
     * @param changeBarDirection new BarDirection value
     */

    public void setChangeBarDirection(int changeBarDirection) {
        this.changeBarDirection = changeBarDirection;
    }

    /**
     *
     * @return StatusBar Max  X coordinate
     */

    public int getStatusBarMaxX() {
        return statusBarMaxX;
    }

    /**
     *
     * @param statusBarMaxX New StatusBar Max X coordinate
     */
    public void setStatusBarMaxX(int statusBarMaxX) {
        this.statusBarMaxX = statusBarMaxX;
    }

    /**
     *
     * @return StatusBar Min  X coordinate
     */
    public int getStatusBarMinX() {
        return statusBarMinX;
    }

    /**
     *
     * @param statusBarMinX New StatusBar Min X coordinate
     */
    public void setStatusBarMinX(int statusBarMinX) {
        this.statusBarMinX = statusBarMinX;
    }

    /**
     *
     * @return StatusBar Green part Min X Coordinate
     */

    public int getStatusGreenBarMinX() {
        return statusGreenBarMinX;
    }

    /**
     *
     * @param statusGreenBarMinX New StatusBar Green Part Min X coordinate
     */

    public void setStatusGreenBarMinX(int statusGreenBarMinX) {
        this.statusGreenBarMinX = statusGreenBarMinX;
    }

    /**
     *
     * @return StatusBar Green part Max X Coordinate
     */

    public int getStatusGreenBarMaxX() {
        return statusGreenBarMaxX;
    }

    /**
     *
     * @param statusGreenBarMaxX New StatusBar Green Part Max X coordinate
     */

    public void setStatusGreenBarMaxX(int statusGreenBarMaxX) {
        this.statusGreenBarMaxX = statusGreenBarMaxX;
    }

    /**
     *
     * @return Score
     */

    public int getScore() {
        return score;
    }

    /**
     *
     * @param score New Score
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return Whether the score can be incremented
     */

    public boolean isIncScore() {
        return incScore;
    }

    /**
     *
     * @param incScore Whether the score can be incremented
     */

    public void setIncScore(boolean incScore) {
        this.incScore = incScore;
    }

    /**
     *
     * @return Game Timer
     */

    public int getTimer() {
        return timer;
    }

    /**
     *
     * @param timer New Game Timer
     */

    public void setTimer(int timer) {
        this.timer = timer;
    }

    /**
     *
     * @return Current liftTimer
     */

    public int getLiftTimer() {
        return liftTimer;
    }

    /**
     *
     * @param liftTimer New liftTimer
     */

    public void setLiftTimer(int liftTimer) {
        this.liftTimer = liftTimer;
    }

    /**
     *
     * @return Whether the timer should start to count
     */

    public boolean isStartTimer() {
        return startTimer;
    }

    /**
     *
      * @param startTimer Whether the timer should start to count
     */
    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    /**
     *
     * @return Current Highscore
     */

    public int getHighscoreLifting() {
        return highscoreLifting;
    }

    /**
     *
     * @param highscoreLifting New Highscore
     */

    public void setHighscoreLifting(int highscoreLifting) {
        this.highscoreLifting = highscoreLifting;
    }

    /**
     *
     * @return Trace speed
     */

    public float getTraceSpeed() {
        return traceSpeed;
    }

    /**
     *
     * @param traceSpeed New trace speed
     */

    public void setTraceSpeed(float traceSpeed) {
        this.traceSpeed = traceSpeed;
    }

    /**
     *
     * @return Last Input from the accelerometer
     */

    public double getAccInput() {
        return accInput;
    }

    /**
     *  Overides accelerometer input
     * @param accInput New acceleration
     */

    public void setAccInput(double accInput) {
        this.accInput = accInput;
    }

    /**
     *
     * @return Whether or not the bar is lifted
     */

    public boolean isLifted() {
        return lifted;
    }

    /**
     *
     * @param lifted Whether or not the bar is lifted
     */

    public void setLifted(boolean lifted) {
        this.lifted = lifted;
    }

    /**
     *
     * @return Whether the game should start
     */

    public boolean isGameStart() {
        return gameStart;
    }

    /**
     *
     * @param gameStart Whether the game should start
     */

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    /**
     *
     * @return Whether the scores are to be saved
     */

    public boolean isSaveScores() {
        return saveScores;
    }

    /**
     *
     * @param saveScores Whether the scores are to be saved
     */

    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    /**
     *
     * @return User's name
     */

    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName New User na
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return Size of right arm
     */

    public int getRightArmSize() {
        return rightArmSize;
    }

    /**
     *
     * @param rightArmSize New right arm size
     */

    public void setRightArmSize(int rightArmSize) {
        this.rightArmSize = rightArmSize;
    }

    /**
     *
     * @return Size of left arm
     */

    public int getLeftArmSize() {
        return leftArmSize;
    }

    /**
     *
     * @param leftArmSize New left arm size
     */

    public void setLeftArmSize(int leftArmSize) {
        this.leftArmSize = leftArmSize;
    }

    /**
     *
     * @return Rotation
     */

    public float getWeightRotation() {
        return weightRotation;
    }

    /**
     *
     * @param weightRotation New rotation
     */

    public void setWeightRotation(float weightRotation) {
        this.weightRotation = weightRotation;
    }
}
