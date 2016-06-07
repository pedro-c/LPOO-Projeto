package gymsimulator.game.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gymsimulator.game.Logic.MainMenuLogic;
import gymsimulator.game.Scenes.Hud;
import gymsimulator.game.gymSimulator;

public class MainMenu implements Screen {

    gymSimulator game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    public MainMenuLogic menuLogic;
    public AssetManager manager;
    private Texture bestScore;
    private Image scoresButton;
    private boolean loaded=false;



    public MainMenu(gymSimulator game, AssetManager manager){
        this.game=game;
        this.manager=manager;
        menuLogic = new MainMenuLogic();
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(gymSimulator.V_WIDTH/gymSimulator.PPM, gymSimulator.V_HEIGHT/gymSimulator.PPM,gamecam );
        hud = new Hud(game.batch);

        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("gym.tmx", TiledMap.class);



        gamecam.position.set(gymSimulator.V_WIDTH+gymSimulator.V_WIDTH/6, gymSimulator.V_HEIGHT/2,0);


        world = new World(new Vector2(0,-10), true);
        b2dr = new Box2DDebugRenderer();




    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        int ret=menuLogic.update(gamecam, dt, hud);
        switch (ret){
            case 0:
                break;
            case 1:
                hud.setLabelPlay("PLAY WEIGHT BALANCING");
                hud.playSelectedGame.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new WeightLiftingState(game, manager));
                        dispose();
                    }

                });
                break;
            case 2:
                hud.setLabelPlay("PLAY TREADMILL");
                hud.playSelectedGame.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new RunnerGameState(game, manager));
                        dispose();
                    }

                });
                break;
            case 3:
                hud.setLabelPlay("PLAY WEIGHT LIFTING                              ");
                hud.playSelectedGame.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new AbsGameState(game, manager));
                        dispose();
                }

                });
                break;
            case 4:
                hud.setLabelPlay("PLAY MULTIPLAYER FIGHT");
                hud.playSelectedGame.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new MultiplayerFight(game, manager));
                        dispose();
                    }

                });
                break;
        }



    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f, 6, 2);

        gamecam.update();
        renderer.setView(gamecam);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,(float)150/255,(float)136/255,0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            if(!loaded){
                hud.setLabelPlay("LOADING...");
                manager.load("gym.tmx", TiledMap.class);
                manager.load("bestScores.png", Texture.class);
                manager.finishLoading();
                map = manager.get("gym.tmx");
                bestScore = manager.get("bestScores.png", Texture.class);
                scoresButton = new Image(bestScore);
                renderer = new OrthoCachedTiledMapRenderer(map, 1/gymSimulator.PPM);

                scoresButton.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y){
                        game.setScreen(new ScoresState(game, manager));
                        dispose();
                    }

                });

                hud.setLabelPlay(" ");
                loaded=true;
            }else {

                update(delta);




                renderer.render();

                b2dr.render(world, gamecam.combined);

                game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
                if(gamecam.position.x < gymSimulator.V_WIDTH+gymSimulator.V_WIDTH/5){
                    game.batch.begin();
                    game.batch.draw(bestScore, 0, 0, 300, 300);
                    game.batch.end();

                }
                if(gamecam.position.x <gymSimulator.V_WIDTH - gymSimulator.V_WIDTH/6){
                    game.setScreen(new ScoresState(game, manager));
                    dispose();
                }

            }
        hud.stage.draw();
        }







    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
        manager.clear();
    }

}
