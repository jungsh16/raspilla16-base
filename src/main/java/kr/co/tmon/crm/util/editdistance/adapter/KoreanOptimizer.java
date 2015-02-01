package kr.co.tmon.crm.util.editdistance.adapter;

public class KoreanOptimizer implements Optimizer {

	private static final char KOREAN_FIRST_CHAR = '가';
	private static final char KOREAN_LAST_CHAR = '힣';
	private static final char WEIGHTING_CHAR = '@';

	/**
	 * 한국어의 음절을 음소 단위로 분리하여 반환.
	 * 
	 * 음절의 첫번째 음소 뒤에 '@'를 붙여서 Edit Distance 계산 시 가산점을 받을 수 있도록 유도
	 * 
	 * @param originalString
	 * @return
	 */
	@Override
	public String getOptimizedString(String origin) {
		StringBuilder result = new StringBuilder();

		for (int index = 0; index < origin.length(); index++) {
			char charAtIndex = origin.charAt(index);

			if (KOREAN_FIRST_CHAR <= charAtIndex && charAtIndex <= KOREAN_LAST_CHAR) {
				result.appendCodePoint((charAtIndex - 0xAC00) / 0x1C / 0x15 + 0x1100).append(WEIGHTING_CHAR);
				result.appendCodePoint((charAtIndex - 0xAC00) / 0x1C % 0x15 + 0x1161);

				int finalConsonant = (charAtIndex - 0xAC00) % 0x1C + 0x11A7;
				result.appendCodePoint(finalConsonant);
			} else {
				result.append(charAtIndex);
			}
		}

		return result.toString();
	}
}
