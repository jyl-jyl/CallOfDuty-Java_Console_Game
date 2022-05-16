package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HelperTest {
	
	HeadQuarter headQuarter;
	Base base;
    Armory armory;
    Barrack barrack;
    SentryTower sentryTower;
    Tank tank;
    OilDrum oilDrum;
    Ground ground;

	@BeforeEach
	void setUp() throws Exception {
		// initiate new base
		Base base = new Base();
		// Create instance for each type of Target
		headQuarter = new HeadQuarter(base);
		armory = new Armory(base);
		barrack = new Barrack(base);
		sentryTower = new SentryTower(base);
		tank = new Tank(base);
		oilDrum = new OilDrum(base);
		ground = new Ground (base);
		
	}

	@Test
	void testIfTargetBuilding() {
		
		assertEquals (true, Helper.ifTargetBuilding(headQuarter));
		assertEquals (true, Helper.ifTargetBuilding(armory));
		assertEquals (true, Helper.ifTargetBuilding(barrack));
		assertEquals (true, Helper.ifTargetBuilding(sentryTower));
		assertEquals (false, Helper.ifTargetBuilding(tank));
		assertEquals (false, Helper.ifTargetBuilding(oilDrum));

	}
	
	@Test
	void testifTargetNotGround() {
		assertEquals (true, Helper.ifTargetNotGround(headQuarter));
		assertEquals (true, Helper.ifTargetNotGround(armory));
		assertEquals (true, Helper.ifTargetNotGround(barrack));
		assertEquals (true, Helper.ifTargetNotGround(sentryTower));
		assertEquals (true, Helper.ifTargetNotGround(tank));
		assertEquals (true, Helper.ifTargetNotGround(oilDrum));
		assertEquals (false, Helper.ifTargetNotGround(ground));
		

	}

}
