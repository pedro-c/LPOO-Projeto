package de.tomgrill.gdxtesting.LogicTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


import org.junit.Test;

import gymsimulator.game.Logic.MultiplayerFightLogic;

import org.junit.runner.RunWith;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)

public class MultiplayerFightLogicTest {

    @Test
    public void PlayerAttack()
    {
        MultiplayerFightLogic Case = new MultiplayerFightLogic();

        Case.setBlueHealth(100);
        Case.setRedHealth(100);

        Case.setSwitchFistR(false);
        Case.setPlayerRedDAttacking(true);
        Case.redPlayerAttacking();

        int exp = 75;
        int act = Case.getBlueHealth();

        assertEquals(exp, act);

        Case.setSwitchFistB(false);
        Case.setPlayerBlueAttacking(true);
        Case.bluePlayerAttacking();

        exp = 75;
        act = Case.getRedHealth();

        assertEquals(exp, act);

    }


    @Test
    public void playerDefending(){
        MultiplayerFightLogic Case = new MultiplayerFightLogic();

        Case.setPlayerRedStartDefend(true);
        Case.setDeltaRcounter(0);
        Case.setButtonRSsize(0);
        Case.resizeRedShieldShadow();

        int exp = 1;
        int act = Case.getButtonRSsize();

        assertEquals(act, exp);

        act = (int)Case.getDeltaRcounter();

        assertEquals(act, exp);

        for(int i = 0; i < 225; ++i)
            Case.resizeRedShieldShadow();

        boolean def = Case.isPlayerRedDefending();

        assertFalse(def);

        Case.setPlayerBlueStartDefend(true);
        Case.setDeltaBcounter(0);
        Case.setButtonBSsize(0);
        Case.resizeBlueShieldShadow();

        exp = 1;
        act = Case.getButtonBSsize();

        assertEquals(act, exp);

        act = (int)Case.getDeltaBcounter();

        assertEquals(act, exp);

        for(int i = 0; i < 225; ++i)
            Case.resizeBlueShieldShadow();

        def = Case.isPlayerBlueDefending();

        assertFalse(def);

    }
}
