package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

/**
 * Created by pedro on 31/05/2016.
 */
public class WeightLiftLogic {

    private int trace_x= (Gdx.graphics.getWidth()/2)-10;
    private int trace_y=Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/6;
    private boolean endGame;
    private int changeBarDirection=1;
    private int statusBarMaxX=((Gdx.graphics.getWidth()/4) * 3)-25;
    private int statusBarMinX=(Gdx.graphics.getWidth()/4)+ 5;
    private int statusGreenBarMinX =  (Gdx.graphics.getWidth()/2)-(Gdx.graphics.getWidth()/16);
    private int statusGreenBarMaxX = (Gdx.graphics.getWidth()/2)+(Gdx.graphics.getWidth()/16);
    private int score=0;
    private boolean incScore = false;
    private int timer=8*100;
    private boolean startTimer=false;
    private int highscoreAbs=0;
    private boolean saveScores = true;
    private int traceSpeed;
    Preferences prefs;
    private int delta = 0;
    private boolean lift = false;
    private boolean gameStart = false;
    private java.lang.String userName;
    private boolean touched;
    private FileHandle file;

    /**
     * Constructor. Sets up variables
     */

    public WeightLiftLogic(){
        endGame=false;
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreAbs=prefs.getInteger("highscoreAbs");


    }

    /**
     * Updates the game stats
     * @param dt delta time to be subtracted to the timer
     */

    public void update(float dt) {
        delta++;
        if(startTimer & !endGame)
            timer -= dt;
        if(timer <= 0){
            endGame=true;
        }

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
    }

    /**
     * Saves highscore if needed
     */

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

    /**
     * Handles the changes in the game when the user clicks the screen
     */

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

    /**
     * Handles the bar movement
     */

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

    /**
     * Calculates the tracer's speed
     * @return trace speed
     */

    public int calcTraceSpeed()
    {
        int speed = 20 + 2 * score;

        return speed;
    }

    /**
     * Saves Hihgscore to a file
     * @param score
     */

    public void saveToFile(int score){
        //Gdx.input.getTextInput(listener, "Name", " ", " ");
        //userName=listener.returnUser();
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

    /**
     *
     * @return
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
     * @return Bar's current direction
     */

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
     * @return Whether the timer should start counting
     */

    public boolean isStartTimer() {
        return startTimer;
    }

    /**
     *
     * @param startTimer Whether the timer should start counting
     */

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    /**
     *
     * @return Current Highscore
     */

    public int getHighscoreAbs() {
        return highscoreAbs;
    }

    /**
     *
     * @param highscoreAbs New highscore
     */

    public void setHighscoreAbs(int highscoreAbs) {
        this.highscoreAbs = highscoreAbs;
    }

    /**
     *
     * @return Whether the score should be saved
     */

    public boolean isSaveScores() {
        return saveScores;
    }

    /**
     *
     * @param saveScores Whether the score should be saved
     */

    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    /**
     *
     * @return Trace's current speed
     */

    public int getTraceSpeed() {
        return traceSpeed;
    }

    /**
     *
     * @param traceSpeed New Trace speed
     */

    public void setTraceSpeed(int traceSpeed) {
        this.traceSpeed = traceSpeed;
    }

    /**
     *
     * @return Delta value
     */

    public int getDelta() {
        return delta;
    }

    /**
     *
     * @param delta New Delta Value
     */

    public void setDelta(int delta) {
        this.delta = delta;
    }

    /**
     *
     * @return Whether the bar has been lifted
     */

    public boolean isLift() {
        return lift;
    }

    /**
     *
     * @param lift Whether the bar has been lifted
     */

    public void setLift(boolean lift) {
        this.lift = lift;
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
     * @return User's name
     */

    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName New User name
     */

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return Whether the screen was touched
     */

    public boolean isTouched() {
        return touched;
    }

    /**
     *
     * @param touched Whether the screen was touched
     */

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    /**
     *
     * @return File
     */

    public FileHandle getFile() {
        return file;
    }

    /**
     *
     * @param file New File
     */

    public void setFile(FileHandle file) {
        this.file = file;
    }


}
