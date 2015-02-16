package kr.co.tmon.data.util.editdistance.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KoreanOptimizerTest {

	@Test
	public void testGetOptimizedString() throws Exception {
		KoreanOptimizer koreanAdapter = new KoreanOptimizer();
		assertEquals("ᄌ@ᅥᆼᄉ@ᅳᆼᄒ@ᅧᆫsitting", koreanAdapter.getOptimizedString("정승현sitting"));
		assertEquals("ᄀ@ᅡᆧᄂ@ᅡᆧᄃ@ᅡᆧ", koreanAdapter.getOptimizedString("가나다"));
		assertEquals("ᄋ@ᅵᆰ", koreanAdapter.getOptimizedString("읽"));
	}
}
