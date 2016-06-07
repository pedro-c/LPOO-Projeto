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

    @Test
    public void testLift() {
        WeightLiftingLogic Case = new WeightLiftingLogic();
        float delta = Gdx.graphics.getDeltaTime();


        Case.trace_x = 1;
        Case.score = 5;
        Case.statusBarMinX = 100;
        Case.statusBarMaxX = 200;

        Case.traceSpeed = (float)0.7;
        Case.lift(delta);

        double act = (double)Case.traceSpeed;

        int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0, counter5 = 0;

        for(int i = 0; i < 160000; ++i)
        {
            Case.traceSpeed = (float)0.7;
            Case.liftTimer = 150;
            Case.trace_x = 101;
            Case.lift(delta);


            if(Math.abs(Case.traceSpeed-0.2) <= 0.001)
                counter1++;
            else if(Math.abs(Case.traceSpeed-0.4) <= 0.001)
                counter2++;
            else if(Math.abs(Case.traceSpeed-0.6) <= 0.001)
                counter3++;
            else if(Math.abs(Case.traceSpeed) <= 0.001)
                counter4++;
            else if(Math.abs(Case.traceSpeed+0.2) <= 0.001)
                counter5++;

        }

        assertTrue((counter1 > 65000 && counter1 < 95000));
        assertTrue((counter2 > 14000 && counter2 < 26000));
        assertTrue((counter3 > 14000 && counter2 < 26000));
        assertTrue((counter4 > 14000 && counter2 < 26000));
        assertTrue((counter5 > 14000 && counter2 < 26000));

        Case.traceSpeed = (float)0.7;
        Case.liftTimer = 150;
        Case.trace_x = 1;
        Case.lift(delta);

        double exp = -1;
        assertEquals((double)Case.traceSpeed, exp, 0.001);

        Case.traceSpeed = (float)0.7;
        Case.liftTimer = 150;
        Case.trace_x = 201;
        Case.lift(delta);

        exp = 1;
        assertEquals((double)Case.traceSpeed, exp, 0.001);

        Case.liftTimer = 0;
        Case.score = 4;
        Case.trace_x = 155;
        Case.statusGreenBarMaxX = 170;
        Case.statusGreenBarMinX = 130;
        boolean ret = Case.lift(delta);

        exp = 5;

        assertEquals(exp, Case.score, 0.001);
        assertFalse(ret);

        Case.liftTimer = 0;
        Case.score = 4;
        Case.trace_x = 190;

        ret = Case.lift(delta);

        assertTrue(ret);
    }
}
