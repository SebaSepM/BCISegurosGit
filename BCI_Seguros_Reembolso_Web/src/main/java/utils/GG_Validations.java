package main.java.utils;

public class GG_Validations {

	public static void trueBooleanCondition(boolean condition, String successMessage, String errorMessage, String currentEvent) {
		if (condition) {
			GG_Utils.outputInfo("[OK] " + successMessage);
		}
		else {
			GG_Utils.eventFailed(currentEvent, errorMessage + ": " + condition);
		}
	}
}
