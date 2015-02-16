package kr.co.tmon.data.util.editdistance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EditDistanceTest {

	private static final int EXPECTED_RESULT_ONE = 1;
	private static final int EXPECTED_RESULT_THREE = 3;

	@Test
	public void testGetDistance() throws Exception {
		assertEquals(EXPECTED_RESULT_THREE, EditDistance.getDistance("sitting", "kitten"));
		assertEquals(EXPECTED_RESULT_THREE, EditDistance.getDistance("kitten", "sitting"));

		assertEquals(EXPECTED_RESULT_THREE, EditDistance.getDistance("Sunday", "Saturday"));

		assertEquals(EXPECTED_RESULT_THREE, EditDistance.getDistance("승현sitting", "승현kitten"));

		assertEquals(EXPECTED_RESULT_THREE, EditDistance.getDistance("ABCDEFG", "AEFG"));

		assertEquals(EXPECTED_RESULT_ONE, EditDistance.getDistance("현", "혀"));

		System.out.println(EditDistance.getDistance("정승현", "정승혀"));
		System.out.println(EditDistance.getDistance("정승현", "김재현"));
		System.out.println(EditDistance.getDistance("정승현", "한영준"));
	}
}
