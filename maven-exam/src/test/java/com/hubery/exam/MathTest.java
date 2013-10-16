package com.hubery.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.hubery.exam.utils.MathUtils;

public class MathTest {

	@Test
	public void test() {
		int stuNum = 80 * 10000;
		List<Integer> scores = new ArrayList<>(stuNum);
		for (int i = 0; i < stuNum; i++) {
			scores.add(new Random().nextInt(150));
		}
		long start = System.currentTimeMillis();
		System.out.println("average:" + MathUtils.average(scores));
		System.out.println("run time:" + (System.currentTimeMillis() - start)
				+ "ms");
	}
}
