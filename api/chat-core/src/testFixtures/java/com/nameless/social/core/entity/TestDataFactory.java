package com.nameless.social.core.entity;

public class TestDataFactory {
	public static User createUser(String token, String name, String email) {
		return new User(token, name, email);
	}
}
