package gymsimulator.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import gymsimulator.game.Logic.MultiplayerLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 31/05/2016.
 */
public class MultiplayerFight implements Screen {


    private final gymSimulator game;
    private MultiplayerLogic mpLogic;
    private Texture fistR;
    private Texture fistB;
    private Texture shieldR;
    private Texture shieldB;

    //Red Player
    private Texture RPlayer;
    private Texture fistRRA;
    private Texture fistRLA;
    private Texture fistRRD;
    private Texture fistRLD;
    private Texture RRDD;
    private Texture RLDD;

    //Blue Player
    private Texture BPlayer;
    private Texture fistBRA;
    private Texture fistBLA;
    private Texture fistBRD;
    private Texture fistBLD;
    private Texture BRDD;
    private Texture BLDD;

    //Ring
    private Texture ring;

    private Image buttonFistR;
    private Image buttonFistB;
    private Image buttonShieldR;
    private Image buttonShieldB;
    private Texture healthBarRed;
    private Texture healthBarGreen;
    private Texture buttonShadow;
    private Texture knockout;
    private Image knockoutButton;
    SpriteBatch spriteBatch;
    public Stage stage;

    private Hud hud;



    public MultiplayerFight(final gymSimulator game){

        this.game=game;
        hud = new Hud(game.batch);
        stage = new Stage();
        mpLogic = new MultiplayerLogic();
        fistR = new Texture("fistR.png");
        fistB = new Texture("fistB.png");
        shieldR = new Texture("shieldR.png");
        shieldB = new Texture("shieldB.png");
        healthBarGreen = new Texture("HBgreen.png");
        healthBarRed = new Texture("HBred.png");
        buttonShadow = new Texture("buttonShadow1.png");


        //Red Player
        RPlayer = new Texture("Rplayer.png");
        fistRRA = new Texture("RRA.png");
        fistRLA = new Texture("RLA.png");
        fistRRD = new Texture("RRD.png");
        fistRLD = new Texture("RLD.png");
        RRDD = new Texture("RRDD.png");
        RLDD = new Texture("RLDD.png");

        //Blue Player
        BPlayer = new Texture("Bplayer.png");
        fistBRA = new Texture("BRA.png");
        fistBLA = new Texture("BLA.png");
        fistBRD = new Texture("BRD.png");
        fistBLD = new Texture("BLD.png");
        BRDD = new Texture("BRDD.png");
        BLDD = new Texture("BLDD.png");

        //Ring
        ring = new Texture("ring.png");

        //KnockOut
        knockout = new Texture("knockout.png");

        buttonFistB = new Image(fistB);
        buttonFistR = new Image(fistR);
        buttonShieldB = new Image(shieldB);
        buttonShieldR = new Image(shieldR);
        knockoutButton = new Image(knockout);
        spriteBatch = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonFistB);
        stage.addActor(buttonFistR);
        stage.addActor(buttonShieldB);
        stage.addActor(buttonShieldR);
        stage.addActor(knockoutButton);

        buttonFistB.setPosition(100, Gdx.graphics.getHeight()/12);
        buttonFistR.setPosition(Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250);
        buttonShieldB.setPosition(100, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250);
        buttonShieldR.setPosition(Gdx.graphics.getWidth()-350, Gdx.graphics.getHeight()/12);
        knockoutButton.setPosition(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2-175);


        buttonFistB.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.playerBlueAttacking();
                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.playerBlueAttacking=false;
            }

        });

        buttonFistR.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.playerRedAttacking();

                return true;
            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.playerRedDAttacking=false;
            }

        });

        buttonShieldB.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerBlueDefending(false);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerBlueDefending(true);

                return true;
            }

        });

        buttonShieldR.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerRedDefending(false);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button){
                mpLogic.setPlayerRedDefending(true);

                return true;
            }

        });

        knockoutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenu(game));
                dispose();
            }

        });


    }

    public void update(float dt){
        mpLogic.update(dt);
        handleInput(dt);
    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(fistB, 50, Gdx.graphics.getHeight()/12, 250, 250 );
        spriteBatch.draw(fistR, Gdx.graphics.getWidth()-300,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250 , 250, 250 );
        spriteBatch.draw(shieldB, 50, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250, 250, 250 );
        spriteBatch.draw(shieldR, Gdx.graphics.getWidth()-300, Gdx.graphics.getHeight()/12, 250, 250 );

        spriteBatch.draw(buttonShadow, 50+130-mpLogic.buttonBSsize/2, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/12-250+125-mpLogic.buttonBSsize/2, mpLogic.buttonBSsize, mpLogic.buttonBSsize );
        spriteBatch.draw(buttonShadow, Gdx.graphics.getWidth()-300+120-mpLogic.buttonRSsize/2, Gdx.graphics.getHeight()/12+125-mpLogic.buttonRSsize/2, mpLogic.buttonRSsize, mpLogic.buttonRSsize );


        spriteBatch.draw(healthBarRed, Gdx.graphics.getWidth()-400, 100, 50,Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12);
        spriteBatch.draw(healthBarGreen, Gdx.graphics.getWidth()-400, 100, 50,  mpLogic.redHealth );

        spriteBatch.draw(healthBarRed, 350, 100, 50,  Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12 );
        spriteBatch.draw(healthBarGreen, 350, 100+(Gdx.graphics.getHeight()-2*Gdx.graphics.getHeight()/12-mpLogic.blueHealth), 50, mpLogic.blueHealth );

        //draw Ring
        spriteBatch.draw(ring, 425, 0, Gdx.graphics.getWidth()-850,Gdx.graphics.getHeight());



        //draw Player Blue
        spriteBatch.draw(BPlayer, 600, Gdx.graphics.getHeight()/2-125, 201, 252);
        if(mpLogic.playerBlueDefending){
            spriteBatch.draw(BLDD, 630, Gdx.graphics.getHeight()/2-160+225, 232, 88);
            spriteBatch.draw(BRDD, 650, Gdx.graphics.getHeight()/2-150, 232, 88);
        }else if(mpLogic.playerBlueAttacking && mpLogic.switchFistB==false){
            spriteBatch.draw(fistBLA, 630, Gdx.graphics.getHeight()/2-160+225, 232, 88);
            spriteBatch.draw(fistBRD, 650, Gdx.graphics.getHeight()/2-150, 232, 88);
        }else if(mpLogic.playerBlueAttacking && mpLogic.switchFistB==true){
            spriteBatch.draw(fistBLD, 630, Gdx.graphics.getHeight()/2-160+225, 232, 88);
            spriteBatch.draw(fistBRA, 650, Gdx.graphics.getHeight()/2-150, 232, 88);
        }else{
            spriteBatch.draw(fistBLD, 630, Gdx.graphics.getHeight()/2-160+225, 232, 88);
            spriteBatch.draw(fistBRD, 650, Gdx.graphics.getHeight()/2-150, 232, 88);
        }

        //draw Player Red
        spriteBatch.draw(RPlayer, Gdx.graphics.getWidth()-800, Gdx.graphics.getHeight()/2-125, 201, 252);
        if(mpLogic.playerRedDefending){
            spriteBatch.draw(RRDD, Gdx.graphics.getWidth()-865, Gdx.graphics.getHeight()/2-175+252, 232, 88);
            spriteBatch.draw(RLDD, Gdx.graphics.getWidth()-890, Gdx.graphics.getHeight()/2-155, 232, 88);
        }else if(mpLogic.playerRedDAttacking && mpLogic.switchFistR==false){
            spriteBatch.draw(fistRLA, Gdx.graphics.getWidth()-865, Gdx.graphics.getHeight()/2-175+252, 232, 88);
            spriteBatch.draw(fistRRD, Gdx.graphics.getWidth()-890, Gdx.graphics.getHeight()/2-150, 232, 88);
        }else if(mpLogic.playerRedDAttacking && mpLogic.switchFistR==true){
            spriteBatch.draw(fistRLD, Gdx.graphics.getWidth()-865, Gdx.graphics.getHeight()/2-175+252, 232, 88);
            spriteBatch.draw(fistRRA, Gdx.graphics.getWidth()-890, Gdx.graphics.getHeight()/2-150, 232, 88);
        }else{
            spriteBatch.draw(fistRLD, Gdx.graphics.getWidth()-865, Gdx.graphics.getHeight()/2-175+252, 232, 88);
            spriteBatch.draw(fistRRD, Gdx.graphics.getWidth()-890, Gdx.graphics.getHeight()/2-150, 232, 88);
        }

        //draw knockout banner
        if(mpLogic.endGame){
            spriteBatch.draw(knockout, Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2-175, 500, 350);
        }


        spriteBatch.end();
        hud.stage.draw();




    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fistR.dispose();
        fistB.dispose();
        shieldR.dispose();
        shieldB.dispose();
        RPlayer.dispose();
        fistRRA.dispose();
        fistRLA.dispose();
        fistRRD.dispose();
        fistRLD.dispose();
        RRDD.dispose();
        RLDD.dispose();
        BPlayer.dispose();
        fistBRA.dispose();
        fistBLA.dispose();
        fistBRD.dispose();
        fistBLD.dispose();
        BRDD.dispose();
        BLDD.dispose();
        ring.dispose();
        healthBarRed.dispose();
        healthBarGreen.dispose();
        buttonShadow.dispose();
        knockout.dispose();
        spriteBatch.dispose();
        stage.dispose();


    }
}