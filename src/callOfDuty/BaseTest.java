package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

    Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // test the composition of the 2D Array of Targets 
        assertEquals(10, base.getTargetsArray()[1].length);
        assertEquals(base.getTargetsArray()[9].length, base.getTargetsArray()[5].length);
        
        // test if every position is filled with Ground Object
        for (int i = 0; i < base.getTargetsArray().length; i ++) {
        	for  (int j = 0; j < base.getTargetsArray()[i].length; j ++) {
        		// test if each position is either placed with Ground or other types of Targets
        		assertEquals (Target.class, base.getTargetsArray()[i][j].getClass().getSuperclass());
      
        	}
        }
        
        // test shotsCount
        assertEquals(0, base.getShotsCount());
        
        // test destroyedTargetCount
        assertEquals(0, base.getDestroyedTargetCount());

    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (base.getTargetsArray()[i][j].getTargetName() != "ground") {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                        case "armory": {
                            armoryCount++;
                            break;
                        }
                        case "headquarter": {
                            headQuarterCount++;
                            break;
                        }
                        case "barrack": {
                            barracksCount++;
                            break;
                        }
                        case "sentrytower": {
                            sentryCount++;
                            break;
                        }
                        case "tank": {
                            tanksCount++;
                            break;
                        }
                        case "oildrum": {
                            odCount++;
                            break;
                        }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // general cases
        assertTrue(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 5, 4, true));
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 5, 4, false));
        assertTrue(this.base.okToPlaceTargetAt(new Barrack(this.base), 8, 7, true));
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 8, 7, false));
        assertTrue(this.base.okToPlaceTargetAt(new SentryTower(this.base), 4, 4, true));
        assertFalse(this.base.okToPlaceTargetAt(new SentryTower(this.base), 2, 4, true));
        assertTrue(this.base.okToPlaceTargetAt(new Tank(this.base), 3, 1, true));
        assertFalse(this.base.okToPlaceTargetAt(new Tank(this.base), 1, 3, true));
        assertTrue(this.base.okToPlaceTargetAt(new OilDrum(this.base), 2, 5, true));
        assertFalse(this.base.okToPlaceTargetAt(new OilDrum(this.base), 0, 6, true));
        
        // edge cases
        // out of targets' boundary
        assertTrue(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 9, 0, true));
        assertFalse(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 9, 9, true));
        // positions already occupied
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 1, true));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 0, 4, true));
        // building target has surrounding other targets
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 3, 5, true));
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 0, 7, true));
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 2, 3, false));
        assertTrue(this.base.okToPlaceTargetAt(new Barrack(this.base), 3, 1, false));
        // cutting the edge of boundary
        assertTrue(this.base.okToPlaceTargetAt(new HeadQuarter(this.base), 9, 4, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 0, 8, false));

        
    }
    
    

    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);
        assertEquals("armory", this.base.getTargetsArray()[5][5].getTargetName());
        assertEquals("armory", this.base.getTargetsArray()[7][6].getTargetName());

        // general cases
        Target headQuarter = new HeadQuarter(base);
        this.base.placeTargetAt(headQuarter, 3, 8, false);
        assertEquals(3, headQuarter.getCoordinate()[0]);
        assertEquals(8, headQuarter.getCoordinate()[1]);
        assertEquals(6, headQuarter.getLength());
        assertEquals(1, headQuarter.getWidth());
        assertEquals(6, headQuarter.getHit().length);
        assertEquals(1, headQuarter.getHit()[0].length);
        assertEquals("headQuarter", this.base.getTargetsArray()[3][8].getTargetName());
        assertEquals("headQuarter", this.base.getTargetsArray()[8][8].getTargetName());
        
        Target tank = new Tank(base);
        this.base.placeTargetAt(tank, 3, 1, false);
        assertEquals(3, tank.getCoordinate()[0]);
        assertEquals(1, tank.getCoordinate()[1]);
        assertEquals(1, tank.getLength());
        assertEquals(1, tank.getWidth());
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);
        assertEquals("tank", this.base.getTargetsArray()[3][1].getTargetName());
        
        // edge cases, targets placed along edges
        Target barrack = new Barrack(base);
        this.base.placeTargetAt(barrack, 9, 0, true);
        assertEquals(9, barrack.getCoordinate()[0]);
        assertEquals(0, barrack.getCoordinate()[1]);
        assertEquals(3, barrack.getLength());
        assertEquals(1, barrack.getWidth());
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);
        assertEquals("barrack", this.base.getTargetsArray()[9][0].getTargetName());
        assertEquals("barrack", this.base.getTargetsArray()[9][2].getTargetName());
        
    }
    
    
    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        Target hd = new HeadQuarter(base);
        this.base.placeTargetAt(hd, 5, 0, true);
        assertTrue(base.isOccupied(5, 0));
        assertTrue(base.isOccupied(5, 1));
        assertTrue(base.isOccupied(5, 2));
        assertTrue(base.isOccupied(5, 3));
        assertTrue(base.isOccupied(5, 4));
        assertTrue(base.isOccupied(5, 5));
        assertFalse(base.isOccupied(5, 6));
        assertFalse(base.isOccupied(6, 0));

        Target br = new Barrack(base);
        this.base.placeTargetAt(br, 0, 9, false);
        assertTrue(base.isOccupied(0, 9));
        assertTrue(base.isOccupied(1, 9));
        assertTrue(base.isOccupied(2, 9));
        assertFalse(base.isOccupied(3, 9));
        assertFalse(base.isOccupied(0, 8));
        
        Target od = new OilDrum(base);
        this.base.placeTargetAt(od, 2, 2, false);
        assertTrue(base.isOccupied(2, 1));
        assertTrue(base.isOccupied(2, 2));
        assertFalse(base.isOccupied(2, 0));
        assertTrue(base.isOccupied(2, 4));


    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));
        base.shootAt(5, 6);
        assertTrue(arm.isHitAt(5, 6));
        base.shootAt(6, 7);
        assertTrue(arm.isHitAt(6, 7));
        
        // shoot at existing target
        base.shootAt(0, 1);
        assertTrue(armory.isHitAt(0, 1));
        assertFalse(armory.isHitAt(0, 0));
        base.shootAt(0, 0);
        assertTrue(armory.isHitAt(0, 0));
        
        // shoot at one-cell target
        base.shootAt(2, 4);
        assertTrue(st.isHitAt(2, 4));
        
        // shoot at explosive target
        OilDrum od1 = new OilDrum (base);
        this.base.placeTargetAt(od1, 9, 0, true);
        base.shootAt(9, 0);
        assertTrue(od1.isHitAt(9, 0));
        assertTrue(this.base.getTargetsArray()[8][1].isHitAt(8, 1));
        assertTrue(this.base.getTargetsArray()[7][2].isHitAt(7, 2));
        
        // shoot at Ground Object
        base.shootAt(5, 0);
        assertEquals("ground", this.base.getTargetsArray()[5][0].getTargetName());
        assertTrue(this.base.getTargetsArray()[5][0].isHitAt(5, 0));

    }

    @Test
    void testIsGameOver() {

    	// create weapon instance 
    	RocketLauncher rl = new RocketLauncher();
    	Missile ms = new Missile();
    	
        assertFalse(base.isGameOver(rl, ms));
        assertEquals (3, ms.getShotLeft());
        assertEquals (20, rl.getShotLeft());

        // fire all the ammunition
        for (int i = 3; i <= 4; i ++) {
        	for (int j = 0; j <= 9; j ++) {
        		rl.shootAt(i, j, base);
        	}
        }
        ms.shootAt(5, 0, base);
        ms.shootAt(5, 1, base);
        ms.shootAt(5, 2, base);
        assertEquals (0, ms.getShotLeft());
        assertEquals (0, rl.getShotLeft());
        assertTrue(base.isGameOver(rl, ms));

        // create a new base
        Base base1 = new Base();
        // create and place some targets
        Armory am1  = new Armory (base1);
        base1.placeTargetAt(am1, 0, 0, true);
        Barrack br1 = new Barrack (base1);
        base1.placeTargetAt(br1, 0, 4, true);
        SentryTower st1 = new SentryTower (base1);
        base1.placeTargetAt(st1, 2, 4, true);
        Tank tk1 = new Tank (base1);
        base1.placeTargetAt(tk1, 2, 1, true);
        OilDrum od1 = new OilDrum (base1);
        base1.placeTargetAt(od1, 1, 3, false);
        // create weapon
    	RocketLauncher rl1 = new RocketLauncher();
    	Missile ms1 = new Missile();
        assertFalse(base.isGameOver(rl1, ms1));
        assertFalse (am1.isDestroyed());
        assertFalse (br1.isDestroyed());
        assertFalse (st1.isDestroyed());
        assertFalse (tk1.isDestroyed());
        assertFalse (od1.isDestroyed());
        // shoot at target until all destroyed
        ms1.shootAt(2, 1, base1);
        rl1.shootAt(1, 3, base1);
        rl1.shootAt(0, 6, base1);
        assertTrue (am1.isDestroyed());
        assertTrue (br1.isDestroyed());
        assertTrue (st1.isDestroyed());
        assertTrue (tk1.isDestroyed());
        assertTrue (od1.isDestroyed());

        // check if missile is still left
        assertTrue (rl1.getShotLeft() > 0);
        assertTrue (ms1.getShotLeft() > 0);
        
        assertTrue(base1.isGameOver(rl1, ms1));
     

    }

    @Test
    void testWin() {
    	
        assertFalse(this.base.win());
        
        // check the status of each target
        assertFalse(armory.isDestroyed());
        assertFalse(barrack.isDestroyed());
        assertFalse(st.isDestroyed());
        assertFalse(tank.isDestroyed());
        assertFalse(od.isDestroyed());
 
        // shoot at the base until all targets destroyed
        base.shootAt(1, 3);
        base.shootAt(2, 1);
        base.shootAt(0, 6);
        
        // check the status of each target
        assertTrue(armory.isDestroyed());
        assertTrue(barrack.isDestroyed());
        assertTrue(st.isDestroyed());
        assertTrue(tank.isDestroyed());
        assertTrue(od.isDestroyed());
        
        assertTrue(this.base.win());

    }

    @Test
    void testIncrementAndSetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());
        base.incrementShotsCount();
        base.incrementShotsCount();
        assertEquals(3, base.getShotsCount());
        
        // create weapons
        RocketLauncher rl = new RocketLauncher();
        Missile ms = new Missile();
        // shoot weapon at targets
        rl.shootAt(1, 1, base);
        ms.shootAt(9, 9, base);
        assertEquals(5, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(6, base.getShotsCount());

    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

        // check if all targets are un-destroyed 
        assertFalse (armory.isDestroyed());
        assertFalse (barrack.isDestroyed());
        assertFalse (st.isDestroyed());
        assertFalse (tank.isDestroyed());
        assertFalse (od.isDestroyed());
        // set destroyedTargetCount to 0
        base.setDestroyedTargetCount(0);
        // destroy all targets
        base.shootAt(1, 3);
        base.shootAt(2, 1);
        base.shootAt(0, 6);
        assertTrue(armory.isDestroyed());
        assertTrue (barrack.isDestroyed());
        assertTrue (st.isDestroyed());
        assertTrue (tank.isDestroyed());
        assertTrue (od.isDestroyed());
        assertEquals(5, base.getDestroyedTargetCount());

    }

    @Test
    void testGetDestroyedTargetCount() {
        
        assertEquals(0, base.getDestroyedTargetCount());
        // shoot at certain targets
        base.shootAt(1, 3);
        base.shootAt(2, 1);
        assertTrue(armory.isDestroyed());
        assertFalse (barrack.isDestroyed());
        assertTrue (st.isDestroyed());
        assertTrue (tank.isDestroyed());
        assertTrue (od.isDestroyed());
        assertEquals(4, base.getDestroyedTargetCount());
        // keep shooting until destroy all
        base.shootAt(0, 6);
        assertTrue (barrack.isDestroyed());
        assertEquals(5, base.getDestroyedTargetCount());

    }


    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);
        
        for (int i = 0; i < 10; i ++) {
        	assertEquals(10, base.getTargetsArray()[i].length);
        }
    }

    @Test
    void testFillWithGround() {
    	// fill the base array with ground target
    	Target[][] targets = base.getTargetsArray();
    	base.fillWithGround(targets);
    	// iterate over all positions 
    	for (Target[] t : targets) {
    		for (Target t1 : t) {
    			assertTrue (t1.getTargetName().equals("ground"));
    			assertEquals (1, t1.getLength());
    			assertEquals (1, t1.getWidth());

    		}
    	}
    	
    }
    
    @Test
    void testIfCanPlaceTarget() {
    	// create a HeadQaurter
    	HeadQuarter hd = new HeadQuarter(base);
    	// create new barrack
    	Barrack br1 = new Barrack (base);
    	assertTrue (base.ifCanPlaceTarget(hd));
    	assertTrue (base.ifCanPlaceTarget(br1));
    	// filled all positions with sentrytower from the 4th row
    	for (int i = 4; i < 10; i ++) {
    		for (int j = 0; j < 10; j ++) {
    			// create a new sentrytower instance
    			SentryTower st = new SentryTower(base);
    			base.placeTargetAt(st, i, j, true);
    		}
    	}
    	assertFalse (base.ifCanPlaceTarget(hd));
    	assertTrue (base.ifCanPlaceTarget(br1));
    	
    	// filled the 3rd row with sentry tower
    	for (int k = 0; k < 10; k ++) {
    		if (! Helper.ifTargetNotGround(base.getTargetsArray()[2][k])) {
       			SentryTower st = new SentryTower(base);
    			base.placeTargetAt(st, 2, k, true);
    		}
    	}
    	
    	assertFalse (base.ifCanPlaceTarget(hd));
    	assertFalse (base.ifCanPlaceTarget(br1));
    	


    }
    		

}
