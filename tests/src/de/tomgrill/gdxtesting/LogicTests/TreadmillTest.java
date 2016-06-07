package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import gymsimulator.game.Logic.TreadmillLogic;
import gymsimulator.game.Logic.WeightLiftingLogic;
import org.junit.runner.RunWith;
import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class TreadmillTest {
    @Test
    public void newFeetPosTest() {
        TreadmillLogic Case = new TreadmillLogic();

        int counter1 = 0, counter2 = 0;

        Case.setWidth(800);
        Case.setFoot1_y(-1);

        for(int i = 0; i < 100000; ++i)
        {
            Case.setNewFeetPosition();
            Case.setFoot1_y(-1);

            if(Case.getFoot1_x() == 200)
                counter1++;
            else if(Case.getFoot1_x() == 400)
                counter2++;
        }


        assertTrue(counter1 > 40000 && counter1 < 60000);
        assertTrue(counter2 > 40000 && counter2 < 60000);
    }

    @Test
    public void testNewLowerFoot(){
        TreadmillLogic Case = new TreadmillLogic();

        Case.setWidth(800);
        Case.setLowerFoot(1);
        Case.setNewLowerFoot();
        Case.setFoot2_x(300);
        int exp = 200;
        int act = Case.getFalseFoot_x();

        assertEquals(act, exp);


        Case.setLowerFoot(4);
        Case.setNewLowerFoot();
        Case.setFoot2_x(200);

        exp = 400;
        act = Case.getFalseFoot_x();
        assertEquals(act, exp);

    }

    @Test
    public void moveFeetTest(){
        TreadmillLogic Case = new TreadmillLogic();
        Case.setHeight(900);

        Case.setDeltaY(200);
        Case.setFoot1_y(200);
        Case.setFoot2_y(200);
        Case.setFoot3_y(200);
        Case.setFoot4_y(200);

        Case.moveFeetDown();

        assertEquals(Case.getFoot1_y(), 100);
        assertEquals(Case.getFoot2_y(), 100);
        assertEquals(Case.getFoot3_y(), 100);
        assertEquals(Case.getFoot4_y(), 100);
        assertEquals(Case.getDeltaY(), 300);
        assertFalse(Case.isGameReady());

        Case.setDeltaY(500);

        Case.moveFeetDown();

        assertEquals(Case.getFoot1_y(), 100);
        assertEquals(Case.getFoot2_y(), 100);
        assertEquals(Case.getFoot3_y(), 100);
        assertEquals(Case.getFoot4_y(), 100);
        assertEquals(Case.getDeltaY(), 0);
        assertTrue(Case.isGameReady());
        assertFalse(Case.isFootClick());



    }
}
