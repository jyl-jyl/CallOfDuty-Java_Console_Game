package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetTest {

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
    void testTarget() {

        // Armory
        assertEquals(2, armory.getHit().length);
        assertEquals(3, armory.getHit()[0].length);
        assertEquals(2, armory.getWidth());
        assertEquals(3, armory.getLength());

        // Barrack
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);
        assertEquals(1, barrack.getWidth());
        assertEquals(3, barrack.getLength());

        
        // create HeadQaurter instance and place it 
        HeadQuarter hd = new HeadQuarter(base);
        base.placeTargetAt(hd, 9, 0, true);
        assertEquals(1, hd.getHit().length);
        assertEquals(6, hd.getHit()[0].length);
        assertEquals(6, hd.getLength());
        assertEquals(1, hd.getWidth());

        
        // Tank
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);
        assertEquals(1, tank.getLength());
        assertEquals(1, tank.getLength());
        
        // OilDrum
        assertEquals(1, od.getHit().length);
        assertEquals(1, od.getHit()[0].length);
        assertEquals(1, od.getLength());
        assertEquals(1, od.getLength());
        
        // SentryTower       
        assertEquals(1, st.getHit().length);
        assertEquals(1, st.getHit()[0].length);
        assertEquals(1, st.getLength());
        assertEquals(1, st.getLength());
        
        // Ground
        Target gr = base.getTargetsArray()[5][0];
        assertTrue(gr.getHit() == null);
        assertEquals(1, gr.getLength());
        assertEquals(1, gr.getWidth());

    }

    @Test
    void testToString() {
        assertEquals("O", st.toString());
        assertEquals("T", tank.toString());

        assertEquals("O", armory.toString());
        assertEquals("O", od.toString());
        assertEquals("O", barrack.toString());
        
        // destroy all targets
        base.shootAt(1, 3);
        base.shootAt(2, 1);
        base.shootAt(0, 6);
        assertTrue (base.win());
        assertEquals("X", armory.toString());
        assertEquals("X", barrack.toString());
        assertEquals("X", st.toString());
        assertEquals("X", tank.toString());
        assertEquals("X", od.toString());

        // Ground
        assertEquals("-", base.getTargetsArray()[9][0].toString());
        assertEquals("-", base.getTargetsArray()[8][0].toString());


    }
    
    

    @Test
    void testGetTargetName() {
        assertEquals("tank", tank.getTargetName().toLowerCase());
        assertEquals("sentrytower", st.getTargetName().toLowerCase());
        assertEquals("oildrum", od.getTargetName().toLowerCase());
        
        HeadQuarter hd = new HeadQuarter(base);
        assertEquals("headquarter", hd.getTargetName().toLowerCase());
        assertEquals("barrack", barrack.getTargetName().toLowerCase());
        assertEquals("armory", armory.getTargetName().toLowerCase());
        
        // Ground
        Target gr = base.getTargetsArray()[8][0];
        assertEquals("ground", gr.getTargetName().toLowerCase());

    }

    @Test
    void testExplode() {
        assertFalse(armory.isDestroyed());
        od.explode();
        assertTrue(armory.isDestroyed());
        
        assertTrue(od.isDestroyed());
        assertTrue(st.isDestroyed());
        
        // create and place a new oildrum
        OilDrum od1 = new OilDrum(base);
        base.placeTargetAt(od1, 0, 8, false);
        assertFalse(barrack.isDestroyed());
        assertFalse(od1.isDestroyed());
        od1.explode();
        assertTrue(barrack.isDestroyed());
        
        // create and place a new tank 
        Tank t2 = new Tank(base);
        base.placeTargetAt(t2, 1, 8, false);
        assertFalse(t2.isDestroyed());
        t2.explode();
        assertTrue(od1.isDestroyed());
        
        // create and place a new armory
        Armory ar1 = new Armory(base);
        base.placeTargetAt(ar1, 8, 0, true);
        // create and place a new barrack
        Barrack br1 = new Barrack(base);
        base.placeTargetAt(br1, 7, 4, false);
        assertFalse(br1.isDestroyed());
        ar1.explode();
        assertTrue(br1.isDestroyed());

    }


    @Test
    void testGetShot() {
    	// armory
        Armory am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, false);
        am.getShot(5, 6);
        assertEquals(1, am.getHit()[0][1]);
        
        // headquarter
        HeadQuarter hq = new HeadQuarter(this.base);
        assertTrue(this.base.okToPlaceTargetAt(hq, 3, 8, false));
        this.base.placeTargetAt(hq, 3, 8, false);
        hq.getShot(3, 8);
        hq.getShot(4, 8);
        hq.getShot(8, 8);
        assertEquals(1, hq.getHit()[0][0]);
        assertEquals(1, hq.getHit()[1][0]);
        assertEquals(0, hq.getHit()[2][0]);
        assertEquals(0, hq.getHit()[3][0]);
        assertEquals(0, hq.getHit()[4][0]);
        assertEquals(1, hq.getHit()[5][0]);
        
        // barrack
        Barrack br = new Barrack(this.base);
        assertTrue(this.base.okToPlaceTargetAt(br, 9, 4, true));
        this.base.placeTargetAt(br, 9, 4, true);
        br.getShot(9, 4);
        br.getShot(9, 5);
        assertEquals(1, br.getHit()[0][0]);
        assertEquals(1, br.getHit()[0][1]);
        assertEquals(0, br.getHit()[0][2]);
        
        // for oildrum, hitarray was not used to indicate its condition
        OilDrum od = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(od, 2, 3, true));
        this.base.placeTargetAt(od, 2, 3, true);
        od.getShot(2, 3);
        assertEquals(0, od.getHit()[0][0]);
        assertEquals(1, od.getHitSingleTarget());

        
        // for tank, hitarray was not used to indicate its condition
        Tank t = new Tank(this.base);
        assertTrue(this.base.okToPlaceTargetAt(t, 8, 4, true));
        this.base.placeTargetAt(t, 8, 4, true);
        t.getShot(8, 4);
        assertEquals(0, t.getHit()[0][0]);
        assertEquals(1, t.getHitSingleTarget());
        
        // for sentryTower, hitarray was not used to indicate its condition
        st.getShot(2, 4);
        assertEquals(0, st.getHit()[0][0]);
        assertEquals(1, st.getHitSingleTarget());
        
        // for Ground, hitarray was not used to indicate its condition
        assertTrue (! Helper.ifTargetNotGround(base.getTargetsArray()[6][1]));
        Ground gr = (Ground) base.getTargetsArray()[6][1];
        gr.getShot(6, 1);
        assertEquals(1, gr.getHitSingleTarget());

    }

    @Test
    void testIsDestroyed() {
    	// oil drum
        assertFalse(od.isDestroyed());
        od.getShot(2, 1);
        assertTrue(od.isDestroyed());
        assertTrue(tank.isDestroyed());
        
        // tank
        Tank t = new Tank(base);
        base.placeTargetAt(t, 3, 1, true);
        assertFalse(t.isDestroyed());
        t.getShot(3, 1);
        assertFalse(t.isDestroyed());
        t.getShot(3, 1);
        assertTrue(t.isDestroyed());
        
        // SentryTower
        SentryTower st1 = new SentryTower(base);
        base.placeTargetAt(st1, 4, 1, true);
        assertFalse(st1.isDestroyed());
        st1.getShot(4, 1);
        assertTrue(st1.isDestroyed());
        
        // armory
        Armory am1 = new Armory(base);
        base.placeTargetAt(am1, 5, 5, false);
        assertFalse(am1.isDestroyed());
        am1.getShot(5, 5);
        am1.getShot(5, 6);
        am1.getShot(6, 5);
        am1.getShot(6, 6);
        am1.getShot(7, 5);
        am1.getShot(7, 6);
        assertTrue(am1.isDestroyed());
        
        // Ground cannot be destroyed
        Ground gr = (Ground) base.getTargetsArray()[9][0];
        assertFalse(gr.isDestroyed());
        gr.getShot(9, 0);
        assertFalse(gr.isDestroyed());

    }

    @Test
    void testIsHitAt() {
    	// armory
        Armory am = new Armory(this.base);
        assertTrue(this.base.okToPlaceTargetAt(am, 5, 5, false));
        this.base.placeTargetAt(am, 5, 5, false);
        assertFalse(am.isHitAt(5, 5));
        am.getShot(5, 5);
        assertTrue(am.isHitAt(5, 5));
        
        // headquarter
        HeadQuarter hq = new HeadQuarter(this.base);
        assertTrue(this.base.okToPlaceTargetAt(hq, 3, 8, false));
        this.base.placeTargetAt(hq, 3, 8, false);
        assertFalse(hq.isHitAt(3, 8));
        assertFalse(hq.isHitAt(4, 8));
        assertFalse(hq.isHitAt(5, 8));
        assertFalse(hq.isHitAt(8, 8));
        hq.getShot(3, 8);
        hq.getShot(4, 8);
        hq.getShot(8, 8);
        assertTrue(hq.isHitAt(3, 8));
        assertTrue(hq.isHitAt(4, 8));
        assertFalse(hq.isHitAt(5, 8));
        assertTrue(hq.isHitAt(8, 8));
        
        // barrack
        Barrack br = new Barrack(this.base);
        assertTrue(this.base.okToPlaceTargetAt(br, 9, 0, true));
        this.base.placeTargetAt(br, 9, 0, true);
        assertFalse(br.isHitAt(9, 0));
        assertFalse(br.isHitAt(9, 2));
        br.getShot(9, 0);
        br.getShot(9, 1);
        assertTrue(br.isHitAt(9, 0));
        assertTrue(br.isHitAt(9, 1));
        assertFalse(br.isHitAt(9, 2));
        
        // tank
        Tank tk = new Tank(this.base);
        assertTrue(this.base.okToPlaceTargetAt(tk, 6, 0, true));
        this.base.placeTargetAt(tk, 6, 0, true);
        assertFalse(tk.isHitAt(6, 0));
        tk.getShot(6, 0);
        assertTrue(tk.isHitAt(6, 0));
        tk.getShot(6, 0);
        assertTrue(tk.isHitAt(6, 0));
        
        // oildrum
        OilDrum od1 = new OilDrum(this.base);
        assertTrue(this.base.okToPlaceTargetAt(od1, 9, 3, true));
        this.base.placeTargetAt(od1, 9, 3, true);
        assertFalse(od1.isHitAt(9, 3));
        od1.getShot(9, 3);
        assertTrue(od1.isHitAt(9, 3));
        
        // sentrytower
        assertFalse(st.isHitAt(2, 4));
        st.getShot(2, 4);
        assertTrue(st.isHitAt(2, 4));
        
        // Ground
        Target gr = base.getTargetsArray()[8][0];
        assertTrue(! Helper.ifTargetNotGround(gr));
        gr.getShot(8, 0);
        assertTrue(gr.isHitAt(8, 0));

    }
    
    

}
