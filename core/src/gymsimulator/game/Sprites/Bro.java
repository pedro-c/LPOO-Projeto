package gymsimulator.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import gymsimulator.game.gymSimulator;

/**
 * Created by pedro on 10/05/2016.
 */
public class Bro extends Sprite {

    public World world;
    public Body b2body;

    public Bro (World world){
        this.world=world;
        defineBro();
    }

    public void defineBro(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / gymSimulator.PPM,32 / gymSimulator.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / gymSimulator.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }


}
