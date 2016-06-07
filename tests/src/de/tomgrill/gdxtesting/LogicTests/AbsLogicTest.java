package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import gymsimulator.game.Logic.AbsLogic;
import gymsimulator.game.Logic.WeightLiftingLogic;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class AbsLogicTest {

    @Test
    public void testTraceSpeed(){
        AbsLogic Case = new AbsLogic();

        Case.score = 3;
        int exp = 26;
        int act = Case.calcTraceSpeed();

        assertEquals(exp, act);

    }

    @Test
    public void testBarMovement(){
        AbsLogic Case = new AbsLogic();

        Case.score = 3;
        Case.statusBarMinX = 100;
        Case.statusBarMaxX = 200;
        Case.trace_x = 10;

        int speed = Case.calcTraceSpeed();

        int exp = 126;
        Case.barMovement();
        int act = Case.trace_x;

        assertEquals(act, exp);

    }

    @Test
    public void testHandleTouch(){
        AbsLogic Case = new AbsLogic();

        Case.score = 3;
        Case.statusGreenBarMinX = 100;
        Case.statusGreenBarMaxX = 200;
        Case.trace_x = 120;
        Case.incScore = true;
        Case.timer = 100;

        Case.handleTouch();

        int exp = 4;
        int act = Case.score;

        assertEquals(act, exp);

        exp = 150;
        act = Case.timer;

        assertEquals(act, exp);

        Case.trace_x = 80;

        Case.handleTouch();

        exp = 3;
        act = Case.score;

        assertEquals(act, exp);




    }

}