public class Color {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static String txtSystem(String content) {
		return "\u001B[30;47m" + "[system] " + content + ANSI_RESET;
	}

	public static String txtOrderlist(String content) {
		return "\u001B[30;47m" + content + ANSI_RESET;
	}

	public static String txtKiosk(String content) {
		return "\033[0;30;103m" + "[키오스크]" + ANSI_RESET + " " + content;
	}

	public static String txtLotte() {
		return "\033[0;33;1;41m" + "롯데리아" + "\u001B[0;30;47m";
	}

	public static String txtMenu(String content) {
		return "\033[0;30;103m" + content + ANSI_RESET;
	}
}