package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;
import gymsimulator.game.Logic.WeightLiftingLogic;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class WeightLiftingLogicTest {

    @Test
    public void testCalcAcceleration() {
        WeightLiftingLogic Case = new WeightLiftingLogic();

        Case.score = 9;
        Case.accInput = 3;


        double exp = 3.6;
        double act = Case.calcAcceleration();

        assertEquals(act, exp, 0.001);

        Case.score = 10;
        Case.accInput = 4;

        exp = 5.244;
        act = Case.calcAcceleration();

        assertEquals(act, exp, 0.001);

    }


    @Test
    public void testWait() {
        WeightLiftingLogic Case = new WeightLiftingLogic();
        float delta = Gdx.graphics.getDeltaTime();

        Case.trace_x = 1;
        Case.score = 5;
        Case.statusBarMinX = 100;
        Case.statusBarMaxX = 200;
        Case.liftTimer = 150;

        assertTrue(Case.wait(delta));

        Case.liftTimer = 0;

        assertFalse(Case.wait(delta));

        assertEquals(Case.liftTimer, 130);

        assertTrue((Case.trace_x) >= 100 && (Case.trace_x) <= 200);

    }
}
