import java.util.Scanner;

public class Function {
	static Scanner scanner = new Scanner(System.in);

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static boolean answer() {
		while (true) {
			System.out.print(">>(y/n)");
			String answer = scanner.nextLine();
			System.out.println("");
			if (answer.matches(".*(예|응|네|그래|오냐|ㅇ|(?i)y|(?i)yes)+.*"))
				return true;
			else if (answer.matches(".*(아니|별로|그닥|글쎄|그만|ㄴ|(?i)no|(?i)n)+.*"))
				return false;
		}
	}

	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static int answer(String str) {
		if (str.matches(".*(예|응|네|그래|오냐|ㅇ|웅|(?i)y|(?i)yes)+.*"))
			return 1;
		else if (str.matches(".*(아니|별로|그닥|글쎄|그만|ㄴ|(?i)no|(?i)n)+.*"))
			return 0;
		return -1;
	}

	// 주문 내역을 출력하는 함수
	public static void printOrder() {
		int totalAmount = calculateTotal();
		String itemName = null;
		String itemOptions = null;

		System.out.println("");
		System.out.println("=============== 주문 내역 ==============");
		for (Order.OrderHistory item : Order.OrderHistory.orderhistory) {
			itemName = item.name;
			itemOptions = item.option == null ? "" : "(" + item.option + ")";
			if (item.name.length() < 8)
				System.out.printf("%-11s%-5s\t\t%5d원\n", itemName, itemOptions, item.price);
			else
				System.out.printf("%-11s%-5s\t%5d원\n", itemName, itemOptions, item.price);
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

}
