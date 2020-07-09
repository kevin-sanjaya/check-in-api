package com.gymdesk.service;

import org.apache.commons.lang.ArrayUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MemberCheckInRecordServiceTest {

	@Autowired
	private MemberService memberService;

	@Test
	void asd() {
		int x = -121;

		String s = String.valueOf(x);

		assert s.equals("-121");


		Map<Integer, Integer> a = new HashMap<>();

		int[] array = {1, 2, 3};
		ArrayUtils.removeElement(array, 2);
		System.out.println(array.length);
	}

}
