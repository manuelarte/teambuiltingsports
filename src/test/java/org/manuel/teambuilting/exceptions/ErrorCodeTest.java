package org.manuel.teambuilting.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ErrorCodeTest {

	@Test
	public void testGetMessageOfIdNotFound() {
		final String expected = "Entity Object with id playerId not found";
		final String actual = ErrorCode.ID_NOT_FOUND.getMessage(Object.class.getSimpleName(), "playerId");
		assertEquals(actual, expected);
	}

}
