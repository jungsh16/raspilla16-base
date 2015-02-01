package kr.co.tmon.crm.util.editdistance;

import kr.co.tmon.crm.util.editdistance.adapter.Optimizer;
import kr.co.tmon.crm.util.editdistance.adapter.KoreanOptimizer;

import org.apache.commons.lang3.math.NumberUtils;

public class EditDistance {

	private static Optimizer optimizer = new KoreanOptimizer();

	/**
	 * 언어에 따른 어댑터를 적용한 Edit Distance 반환.
	 * 
	 * Default 어댑터: {@link KoreanOptimizer}
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static int getDistance(String left, String right) {
		if (optimizer != null) {
			left = optimizer.getOptimizedString(left);
			right = optimizer.getOptimizedString(right);
		}

		int leftLength = left.length();
		int rightLength = right.length();

		int[][] distanceMatrix = new int[leftLength + 1][rightLength + 1];

		for (int count = 0; count <= leftLength; count++)
			distanceMatrix[count][0] = count;

		for (int count = 0; count <= rightLength; count++)
			distanceMatrix[0][count] = count;

		for (int leftIndex = 1; leftIndex < distanceMatrix.length; leftIndex++) {
			for (int rightIndex = 1; rightIndex < distanceMatrix[leftIndex].length; rightIndex++) {
				if (left.charAt(leftIndex - 1) == right.charAt(rightIndex - 1))
					distanceMatrix[leftIndex][rightIndex] = distanceMatrix[leftIndex - 1][rightIndex - 1];
				else
					distanceMatrix[leftIndex][rightIndex] = NumberUtils.min(new int[] { distanceMatrix[leftIndex - 1][rightIndex] + 1, distanceMatrix[leftIndex - 1][rightIndex - 1] + 1, distanceMatrix[leftIndex][rightIndex - 1] + 1 });
			}
		}

		return distanceMatrix[leftLength][rightLength];
	}

	public static void setOptimizer(Optimizer optimizer) {
		EditDistance.optimizer = optimizer;
	}

	public static Optimizer getOptimizer() {
		return optimizer;
	}

}
