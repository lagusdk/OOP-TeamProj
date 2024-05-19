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
		System.out.println(Color.txtOrderlist("=============== 주문내역 ==============="));
		for (Order.NoOverLap item : Order.NoOverLap.noOverLap) {
			itemName = item.name;
			itemOptions = item.option == null ? "" : "(" + item.option + ")";
			System.out.printf(Color.txtOrderlist("%-11s%-5s\t%d\t%5d원\t\n"), itemName, itemOptions, item.count,
					item.price * item.count);
		}

		System.out.println(Color.txtOrderlist("————————————————————————————————————————"));
		System.out.printf(Color.txtOrderlist("합계\t\t\t\t%5d원\t\n"), totalAmount);
		System.out.println(Color.txtOrderlist("*세트 가격이 자동으로 적용됩니다.*\t"));
		System.out.println(Color.txtOrderlist("========================================"));

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
}
