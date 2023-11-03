package com.ssafy.trip.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordUtilsTest {

	public static void main(String[] args) {
		String password = "12345";

		List<String> hashes = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			hashes.add(PasswordUtils.simple(password));
		}

		for (int i = 1; i <= 10; i++) {
			System.out.println("[" + i + "]: " + hashes.get(i-1));
		}
		Set<String> set = new HashSet<>(hashes);
		System.out.print("set 크기는? " + set.size());

		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[8];

		hashes.clear();

		for (int i = 0; i < 10; i++) {
			random.nextBytes(salt);
			String digest = PasswordUtils.encode(password, salt);

			System.out.println("salt=" + PasswordUtils.bytesToHex(salt) + ", digest=" + digest);
			hashes.add(digest);
		}

		set = new HashSet<>(hashes);
		System.out.print("set 크기가 반복한 횟수와 같나요? ");
		System.out.println(set.size()==10);
	}
}
