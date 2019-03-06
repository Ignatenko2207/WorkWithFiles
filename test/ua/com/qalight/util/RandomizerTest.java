package ua.com.qalight.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomizerTest {

	@Test
	void testGetNumberInRange() {
		int result = Randomizer.getNumberInRange(10, 50);
		assertTrue(result >= 10 && result <= 50);
	}

}
