package com.hubery.exam.utils;

import java.util.List;
import java.util.RandomAccess;

public class MathUtils {

	public static int average(List<Integer> list) {
		int sum = 0;
		if (list instanceof RandomAccess) {
			System.out.println("RandomAccess");
			for (int i = 0; i < list.size(); i++) {
				sum += list.get(i);
			}
		} else {
			for (int i : list) {
				sum += i;
			}
		}
		return sum / list.size();
	}

}
