package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

    Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();
        

        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(3, mis.getShotLeft());
        
        assertEquals(20, rl.getShotLeft());

    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());
        assertEquals("missile", mis.getWeaponType());
        assertEquals("rocketLauncher", rl.getWeaponType());
        assertEquals("rocketlauncher", rl.getWeaponType().toLowerCase());
    }

    
    @Test
    void testGetShotLeft() {
    	// missile
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotLeft());
        assertEquals(1, base.getShotsCount());
        mis.shootAt(1, 0, this.base);
        assertEquals(1, mis.getShotLeft());
        assertEquals(2, base.getShotsCount());

        // rocketLauncher
        assertEquals(20, rl.getShotLeft());
        rl.shootAt(3, 0, this.base);
        assertEquals(3, base.getShotsCount());
        assertEquals(19, rl.getShotLeft());
        rl.shootAt(4, 0, this.base);
        assertEquals(4, base.getShotsCount());
        assertEquals(18, rl.getShotLeft());
        rl.shootAt(5, 0, this.base);
        rl.shootAt(6, 0, this.base);
        assertEquals(6, base.getShotsCount());
        assertEquals(16, rl.getShotLeft());
    }

    @Test
    void testDecrementShotleft() {
    	// missile
        assertEquals(3, mis.getShotLeft());
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotLeft());
        mis.decrementShotLeft();
        assertEquals(1, mis.getShotLeft());
        mis.decrementShotLeft();
        assertEquals(0, mis.getShotLeft());
        // edge case, when weapon left with 0
        mis.decrementShotLeft();
        assertEquals(0, mis.getShotLeft());
        
        // rocketLauncher
        assertEquals(20, rl.getShotLeft());
        rl.decrementShotLeft();
        assertEquals(19, rl.getShotLeft());
        for (int i = 18; i >= 1; i --) {
            rl.decrementShotLeft();
            assertEquals(i, rl.getShotLeft());
        }
        assertEquals(1, rl.getShotLeft());
        // edge cases when weapon left with 0
        rl.decrementShotLeft();
        assertEquals(0, rl.getShotLeft());

    }

    @Test
    void testShootAt() {
    	
    	// missile for 3 * 3 area
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        assertTrue(base.getTargetsArray()[0][1].isHitAt(0, 1));
        assertTrue(base.getTargetsArray()[1][0].isHitAt(1, 0));
        assertTrue(base.getTargetsArray()[1][1].isHitAt(1, 1));
        assertFalse(base.getTargetsArray()[0][2].isHitAt(0, 2));
        assertFalse(base.getTargetsArray()[1][2].isHitAt(1, 2));
        assertFalse(base.getTargetsArray()[2][0].isHitAt(2, 1));
        assertEquals(2, mis.getShotLeft());

        
        // rocketLucher for 1 * 1 area
        rl.shootAt(5, 5, base);
        assertTrue(base.getTargetsArray()[5][5].isHitAt(5, 5));
        assertEquals(2, base.getShotsCount());
        assertFalse(base.getTargetsArray()[5][6].isHitAt(5, 6));
        assertFalse(base.getTargetsArray()[5][4].isHitAt(5, 4));
        assertFalse(base.getTargetsArray()[4][5].isHitAt(4, 5));
        assertFalse(base.getTargetsArray()[6][5].isHitAt(6, 5));
        assertEquals(19, rl.getShotLeft());

    }

}
