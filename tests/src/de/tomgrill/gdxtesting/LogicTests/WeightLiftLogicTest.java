package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import gymsimulator.game.Logic.WeightLiftLogic;

import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class WeightLiftLogicTest {

    @Test
    public void testTraceSpeed(){
        WeightLiftLogic Case = new WeightLiftLogic();

        Case.setScore(3);
        int exp = 26;
        int act = Case.calcTraceSpeed();

        assertEquals(exp, act);

    }

    @Test
    public void testBarMovement(){
        WeightLiftLogic Case = new WeightLiftLogic();

        Case.setScore(3);
        Case.setStatusBarMinX(100);
        Case.setStatusBarMaxX(200);
        Case.setTrace_x(10);

        int speed = Case.calcTraceSpeed();

        int exp = 126;
        Case.barMovement();
        int act = Case.getTrace_x();

        assertEquals(act, exp);

    }

    @Test
    public void testHandleTouch(){
        WeightLiftLogic Case = new WeightLiftLogic();

        Case.setScore(3);
        Case.setStatusBarMinX(100);
        Case.setStatusBarMaxX(200);
        Case.setTrace_x(120);
        Case.setIncScore(true);
        Case.setTimer(100);

        Case.handleTouch();

        int exp = 4;
        int act = Case.getScore();

        assertEquals(act, exp);

        exp = 150;
        act = Case.getTimer();

        assertEquals(act, exp);

        Case.setTrace_x(80);

        Case.handleTouch();

        exp = 3;
        act = Case.getScore();

        assertEquals(act, exp);

    }

}