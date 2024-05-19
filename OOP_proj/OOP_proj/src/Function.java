import java.util.Scanner;
import java.util.regex.Pattern;

public class Function {
	static Scanner scanner = new Scanner(System.in);

	static String yes = ".*(예|응|네|그래|오냐|ㅇ|(?i)y|(?i)yes)+.*";
	static String no = ".*(아니|별로|그닥|글쎄|그만|ㄴ|(?i)no|(?i)n)+.*";

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static boolean answer() {
		while (true) {
			System.out.print(">>(y/n)");
			String answer = scanner.nextLine();
			System.out.println("");
			if (Pattern.matches(yes, answer))
				return true;
			else if (Pattern.matches(no, answer))
				return false;
		}
	}

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static int answer(String answer) {
		if (Pattern.matches(yes, answer))
			return 1;
		else if (Pattern.matches(no, answer))
			return 0;
		return -1;
	}

	// 주문 내역을 출력하는 함수
	public static void printOrder() {
		int totalAmount = calculateTotal();
		String itemName = null;
		String itemOptions = null;

		System.out.println("");
		System.out.println("================ 주문 내역 ================");
		for (Order.NoOverLap item : Order.NoOverLap.noOverLap) {
			itemName = item.name;
			itemOptions = item.option == null ? "" : "(" + item.option + ")";
			System.out.printf("%-11s%-5s\t%d\t%5d원\n", itemName, itemOptions, item.count, item.price*item.count);
		}
		
		
		System.out.println("————————————————————————————————————————");
		System.out.printf("합계\t\t\t\t%5d원\n", totalAmount);
		System.out.println("*세트 가격이 자동으로 적용됩니다.*");
		System.out.println("========================================");

	}

	// 가격 총합을 계산하는 함수
	static int calculateTotal() {
		int sum = 0;
		int burger = 0, dessert = 0, beverage = 0;
		int potatoLarge = 0, beverageLarge = 0;

		for (Order.OrderHistory item : Order.OrderHistory.orderhistory) {
			for (MenuList.MenuItem menu : MenuList.hamburgers) {
				if (menu instanceof MenuList.Hamburger) {
					if (item.name.equals(menu.name) && item.price == menu.price) {
						sum = sum + item.price;
						burger++;
					}
				}
			}
			for (MenuList.MenuItem menu : MenuList.desserts) {
				if (item.name.equals(menu.name)) {
					sum = sum + item.price;
					dessert++;
					if (item.name.contains("(L)"))
						potatoLarge++;
				}
			}
			for (MenuList.MenuItem menu : MenuList.beverages) {
				if (item.name.equals(menu.name)) {
					sum = sum + item.price;
					beverage++;
					if (item.name.contains("(L)"))
						beverageLarge++;
				}
			}
		}

		int min = Math.min(Math.min(burger, dessert), beverage);
		int minLarge = Math.min(potatoLarge, beverageLarge);
		sum = sum - min * (1800 + 2000) - 100 * minLarge; // 세트 가격만큼 감산
		return sum;
	}

	// 1초씩 딜레이를 주는 함수(다형성)
	static void timer() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 1초씩 딜레이를 주는 함수(다형성)
	static void timer(double n) {
		try {
			Thread.sleep((long) (n * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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
			return ANSI_GREEN + "[system] " + content + ANSI_RESET;
		}

		public static String txtKiosk(String content) {
			return ANSI_CYAN + "[키오스크] " + content + ANSI_RESET;
		}
	}
}
