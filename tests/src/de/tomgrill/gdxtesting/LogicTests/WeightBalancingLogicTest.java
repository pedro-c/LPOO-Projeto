package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;
import gymsimulator.game.Logic.WeightBalancingLogic;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class WeightBalancingLogicTest {

    @Test
    public void testCalcAcceleration() {
        WeightBalancingLogic Case = new WeightBalancingLogic();

        Case.setScore(9);
        Case.setAccInput(3);


        double exp = 3.6;
        double act = Case.calcAcceleration();

        assertEquals(act, exp, 0.001);

        Case.setScore(10);
        Case.setAccInput(4);

        exp = 5.244;
        act = Case.calcAcceleration();

        assertEquals(act, exp, 0.001);

    }


    @Test
    public void testWait() {
        WeightBalancingLogic Case = new WeightBalancingLogic();
        float delta = Gdx.graphics.getDeltaTime();

        Case.setTrace_x(1);
        Case.setScore(5);
        Case.setStatusBarMinX(100);
        Case.setStatusBarMaxX(200);
        Case.setLiftTimer(150);

        assertTrue(Case.wait(delta));

        Case.setLiftTimer(0);

        assertFalse(Case.wait(delta));

        assertEquals(Case.getLiftTimer(), 130);

        assertTrue((Case.getTrace_x()) >= 100 && (Case.getTrace_x()) <= 200);

    }

    @Test
    public void testLift() {
        WeightBalancingLogic Case = new WeightBalancingLogic();
        float delta = Gdx.graphics.getDeltaTime();


        Case.setTrace_x(1);
        Case.setScore(5);
        Case.setStatusBarMaxX(200);
        Case.setLiftTimer(150);

        Case.setTraceSpeed((float)0.7);
        Case.lift(delta);

        double act = (double)Case.getTraceSpeed();

        int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0, counter5 = 0;

        for(int i = 0; i < 160000; ++i)
        {
            Case.setTraceSpeed((float)0.7);
            Case.setLiftTimer(150);
            Case.setTrace_x(101);
            Case.lift(delta);


            if(Math.abs(Case.getTraceSpeed()-0.2) <= 0.001)
                counter1++;
            else if(Math.abs(Case.getTraceSpeed()-0.4) <= 0.001)
                counter2++;
            else if(Math.abs(Case.getTraceSpeed()-0.6) <= 0.001)
                counter3++;
            else if(Math.abs(Case.getTraceSpeed()) <= 0.001)
                counter4++;
            else if(Math.abs(Case.getTraceSpeed()+0.2) <= 0.001)
                counter5++;

        }

        assertTrue((counter1 > 65000 && counter1 < 95000));
        assertTrue((counter2 > 14000 && counter2 < 26000));
        assertTrue((counter3 > 14000 && counter2 < 26000));
        assertTrue((counter4 > 14000 && counter2 < 26000));
        assertTrue((counter5 > 14000 && counter2 < 26000));

        Case.setTraceSpeed((float)0.7);
        Case.setLiftTimer(150);
        Case.setTrace_x(1);
        Case.lift(delta);

        double exp = -1;
        assertEquals((double)Case.getTraceSpeed(), exp, 0.001);

        Case.setTraceSpeed((float)0.7);
        Case.setLiftTimer(150);
        Case.setTrace_x(201);
        Case.lift(delta);

        exp = 1;
        assertEquals((double)Case.getTraceSpeed(), exp, 0.001);

        Case.setLiftTimer(0);
        Case.setScore(4);
        Case.setTrace_x(155);
        Case.setStatusGreenBarMaxX(170);
        Case.setStatusGreenBarMinX(130);
        boolean ret = Case.lift(delta);

        exp = 5;

        assertEquals(exp, Case.getScore(), 0.001);
        assertFalse(ret);

        Case.setLiftTimer(0);
        Case.setScore(4);
        Case.setTrace_x(190);

        ret = Case.lift(delta);

        assertTrue(ret);
    }
}
