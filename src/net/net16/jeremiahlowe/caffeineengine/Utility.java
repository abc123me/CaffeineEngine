package net.net16.jeremiahlowe.caffeineengine;

public class Utility {
	private static final String LS = System.lineSeparator();
	public static final void onLibraryError(Exception e, String libname, String libauthor, String libversion){
		System.err.println("----------------------------");
		System.err.println("Error in library " + libname + "(" + libversion + ") by " + libauthor + ": " + LS + e);
		e.printStackTrace();
		System.err.println("Please report this exception to me via my personal email: ");
		System.err.println("jeremiah_abc123me@hotmail.com");
		System.err.println("----------------------------");
	}
}
