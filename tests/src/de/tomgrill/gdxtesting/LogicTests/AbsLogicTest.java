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

        int exp =
    }

}