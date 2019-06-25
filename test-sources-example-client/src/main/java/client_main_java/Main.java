package client_main_java;

import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {
		System.out.println("Local resources:");
		verifyClassPresent("client_main_java.Main");
		verifyResourcePresent("client_main_resources/resources");
		verifyClassAbsent("client_Test_java.Test");
		verifyResourceAbsent("client_test_resources/resources");
		
		System.out.println("Local dependencies:");
		verifyClassPresent("com.google.common.collect.ImmutableList");
		verifyClassAbsent("org.junit.Test");
	}

	static void verifyClassPresent(String path) {
		verify(path, true, p -> isClassAvailable(p));
	}
	static void verifyResourcePresent(String path) {
		verify(path, true, p -> isResourceAVailable(p));
	}
	
	static void verifyClassAbsent(String path) {
		verify(path, false, p -> isClassAvailable(p));
	}
	
	static void verifyResourceAbsent(String path) {
		verify(path, false, p -> isResourceAVailable(p));
	}
	
	static void verify(String path, boolean shouldBeAvailable, Predicate<String> availableFunction) { 
		boolean available = availableFunction.test(path);
		char checkmark = available == shouldBeAvailable ? '\u2713' : '\u2715';
		System.out.println(checkmark + " " + path + (available ? " (present)" : " (absent)"));
	}

	public static boolean isClassAvailable(String className) {
		try {
			Class.forName(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean isResourceAVailable(String resourceName) {
		return Main.class.getClassLoader().getResource(resourceName) != null;
	}
}
