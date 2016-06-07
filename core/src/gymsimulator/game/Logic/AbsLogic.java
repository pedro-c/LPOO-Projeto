package gymsimulator.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;

/**
 * Created by pedro on 31/05/2016.
 */
public class AbsLogic{

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

    public AbsLogic(){
        endGame=false;
        prefs = Gdx.app.getPreferences("GymHighScores");
        highscoreAbs=prefs.getInteger("highscoreAbs");


    }

    public int update(float dt) {
        delta++;
        if(startTimer & !endGame)
            timer--;
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

    public boolean isStartTimer() {
        return startTimer;
    }

    public void setStartTimer(boolean startTimer) {
        this.startTimer = startTimer;
    }

    public int getHighscoreAbs() {
        return highscoreAbs;
    }

    public void setHighscoreAbs(int highscoreAbs) {
        this.highscoreAbs = highscoreAbs;
    }

    public boolean isSaveScores() {
        return saveScores;
    }

    public void setSaveScores(boolean saveScores) {
        this.saveScores = saveScores;
    }

    public int getTraceSpeed() {
        return traceSpeed;
    }

    public void setTraceSpeed(int traceSpeed) {
        this.traceSpeed = traceSpeed;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public boolean isLift() {
        return lift;
    }

    public void setLift(boolean lift) {
        this.lift = lift;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public FileHandle getFile() {
        return file;
    }

    public void setFile(FileHandle file) {
        this.file = file;
    }


}
