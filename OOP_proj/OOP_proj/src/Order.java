
// 주문 내역 저장 함수를 제공하는 클래스
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Order {
	static Scanner scanner = new Scanner(System.in);

	// 주문 내역 저장을 위한 class
	static class OrderHistory {
		public String name; // 메뉴 이름
		public int price; // 가격
		public int time; // 대기 시간
		public String option; // (양념감자)시즈닝, (선데아이스크림)맛 저장

		public static ArrayList<OrderHistory> orderhistory = new ArrayList<>();

		// 메뉴 항목을 초기화하는 생성자
		public OrderHistory(String name, int price, int time, String option) {
			this.name = name;
			this.price = price;
			this.time = time;
			this.option = option;

			orderhistory.add(this);
		}

		public static void MakeOrderHistory(String name, int price, int time, String option) {
			boolean exists = false;
			for (NoOverLap item : NoOverLap.noOverLap) {
				if (item.name.equals(name) && ((item.option == null && option == null)
						|| (item.option != null && item.option.equals(option)))) {
					new OrderHistory(name, price, time, null);
					item.count++;
					exists = true;
					break;
				}
			}
			if (!exists) {
				new NoOverLap(name, price, time, option, 1);
			}
		}
	}

	public static class NoOverLap extends OrderHistory {
		public int count; // 주문 내역 개수

		public static ArrayList<NoOverLap> noOverLap = new ArrayList<>();

		public NoOverLap(String name, int price, int time, String option, int count) {
			super(name, price, time, option);
			this.count = count;

			noOverLap.add(this);
		}
	}

	// 주문과 관련된 함수를 저장하는 class
	static public class OrderMethod {
		static String burgerName = ".*(데리|(치킨버거)|새우|(티렉스|(?i)trex)|크리스피|불고기|빅불|한우|돈까스|라이스)+.*";

		// 반복 주문을 위한 함수
		public static void orderLoops() {
			String answer, buffer;

			while (true) {
				System.out.println(Color.txtKiosk("메뉴를 한 개 선택해주세요.(메뉴판 요청 시 메뉴 출력)"));
				System.out.print(">>");
				buffer = scanner.nextLine();
				answer = buffer;

				// 음식 이름과 매치되는 경우
				if (Pattern.matches(burgerName, buffer)) {
					OrderBurgers.checkBurgerSet(buffer, answer);
					break;
				} else if (OrderDesserts.storageMenu(answer) || OrderBeverages.storageMenu(answer)) {
					break;
				} else if (Function.answer(answer) == 0) {
					break;
				}
				// 단어 '메뉴'와 매치되는 경우
				else if (buffer.matches(".*(메뉴|뭐|종류)+.*")) {
					MenuList.printMenu();
					continue;
				} else {
					System.out.println(Color.txtKiosk("올바른 메뉴를 선택해주세요."));
					continue;
				}
			}
		}

		// 메뉴를 배열에 저장하는 함수
		public static boolean storageMenu(String name) {
			for (MenuList.MenuItem item : MenuList.hamburgers) {
				if (name.equals(item.name))
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
			}
			for (MenuList.MenuItem item : MenuList.desserts) {
				if (name.equals(item.name))
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
			}
			for (MenuList.MenuItem item : MenuList.beverages) {
				if (name.equals(item.name))
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
			}
			return false;
		}

		// 디저트 혹은 음료의 사이즈를 저장하는 함수
		public static void optioningSize(MenuList.MenuItem item) {
		}
	}

	public static class OrderBurgers extends OrderMethod {
		// 버거를 저장하는 함수
		public static boolean storageMenu(String name, boolean set) {
			for (MenuList.MenuItem item : MenuList.hamburgers) {
				if (item instanceof MenuList.Hamburger) {
					MenuList.Hamburger hamburger = (MenuList.Hamburger) item;
					if (name.equals(hamburger.name) && set == hamburger.set)
						OrderHistory.MakeOrderHistory(hamburger.name, hamburger.price, hamburger.time, null);
				}
			}
			return false;
		}

		// 버거 세트와 단품 여부를 결정하는 함수
		public static void checkBurgerSet(String buffer, String answer) {
			while (true) {
				if (buffer.matches(".*(세트)+.*")) {
					orderBurgerSet(answer);
					break;
				} else if (buffer.matches(".*(단품|버거만)+.*")) {
					orderBurger(answer, false);
					break;
				} else {
					System.out.println(Color.txtKiosk("세트와 단품 중 어느 것을 주문하시겠습니까?"));
					System.out.print(">>");
					buffer = scanner.nextLine();
				}
			}
		}

		// 버거 단품을 주문 내역에 추가하는 함수
		public static void orderBurger(String answer, boolean set) {
			if (answer.matches(".*데리+.*"))
				storageMenu("데리버거", set);
			else if (answer.matches(".*치킨+.*"))
				storageMenu("치킨버거", set);
			else if (answer.matches(".*새우+.*"))
				storageMenu("새우버거", set);
			else if (answer.matches(".*(티렉스|(?i)trex)+.*"))
				storageMenu("티렉스버거", set);
			else if (answer.matches(".*크리스피+.*"))
				storageMenu("핫크리스피버거", set);
			else if (answer.matches(".*불고기+.*"))
				storageMenu("불고기버거", set);
			else if (answer.matches(".*빅불+.*"))
				storageMenu("빅불버거", set);
			else if (answer.matches(".*한우+.*"))
				storageMenu("한우불고기버거", set);
			else if (answer.matches(".*돈까스+.*"))
				storageMenu("왕돈까스버거", set);
			else if (answer.matches(".*라이스+.*"))
				storageMenu("전주비빔라이스버거", set);
		}

		// 버거 세트를 주문 내역에 추가하는 함수
		public static void orderBurgerSet(String answer) {
			if (Pattern.matches(burgerName, answer)) {
				orderBurger(answer, true);
				System.out.println(Color.txtKiosk("세트 구성품(포테이토와 콜라)을 변경하시겠습니까?"));
				if (Function.answer()) {
					changeBurgerSet();
				} else {
					storageMenu("포테이토(R)");
					storageMenu("콜라(R)");
				}
			} else
				System.out.println(Color.txtKiosk("올바른 메뉴를 선택해주세요."));
		}

		// 버거 세트 구성품을 변경하여 주문 내역에 추가하는 함수
		public static void changeBurgerSet() {
			String answer;
			boolean buffer_desserts = false, buffer_beverages = false;

			while (true) {
				System.out.println(Color.txtKiosk("구성품을 어떤 메뉴로 변경할까요?"));
				System.out.print(">>");
				answer = scanner.nextLine();

				if (Function.answer(answer) == 0) {
					System.out.println(Color.txtKiosk("구성품 변경을 취소할까요?"));
					if (Function.answer())
						break;
				}

				if (OrderDesserts.storageMenu(answer))
					buffer_desserts = true;
				else if (OrderBeverages.storageMenu(answer))
					buffer_beverages = true;

				if (buffer_desserts && buffer_beverages) {
					break;
				} else if (buffer_desserts ^ buffer_beverages) {
					System.out.println(Color.txtKiosk("다른 세트 구성품도 변경하시겠습니까?"));
					if (Function.answer() == false) {
						if (buffer_desserts)
							storageMenu("콜라(R)");
						else if (buffer_beverages)
							storageMenu("포테이토(R)");
						break;
					}
				} else if (!(buffer_desserts || buffer_beverages)) {
					if (answer.matches("(.*라지\\s?세트*.)|(.*라지*.)")) {
						System.out.println(Color.txtKiosk("라지 세트를 선택하셨습니다."));
						storageMenu("포테이토(L)");
						storageMenu("콜라(L)");
						break;
					} else
						System.out.println(Color.txtKiosk("세트 구성품을 다시 선택해주세요!"));

				}
			}
		}
	}

	public static class OrderDesserts extends OrderMethod {
		// 디저트를 주문 내역에 추가하는 함수
		public static boolean storageMenu(String answer) {
			if (answer.matches(".*(포테이토|감자튀김|감튀|후렌치|프렌치|후라이|프라이)+.*"))
				answer = "포테이토(R)";
			else if (answer.matches(".*(웨지)+.*"))
				answer = "웨지포테이토";
			else if (answer.matches(".*(선데)+.*"))
				answer = "선데아이스크림";
			else if (answer.matches(".*(치킨다리|치킨한조각|치킨1조각)+.*"))
				answer = "치킨한조각";

			for (MenuList.MenuItem item : MenuList.desserts) {
				if (answer.equals(item.name)) {
					if (answer.equals("포테이토(R)"))
						optioningSize(item);
					else if (answer.equals("양념감자"))
						optioningSeasoning(item);
					else if (answer.equals("토네이도"))
						optioningTornado(item);
					else if (answer.equals("선데아이스크림"))
						optioningIcecream(item);
					else
						OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
					return true;
				}
			}
			return false;
		}

		public static void optioningSize(MenuList.MenuItem item) {
			while (true) {
				System.out.println("[키오스크] 포테이토의 사이즈를 선택해주세요.(R/L)");
				System.out.print(">>");
				String buffer = scanner.nextLine();

				if (buffer.matches(".*(레귤러|기본|보통|R|r)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
					break;
				} else if (buffer.matches(".*(라지|큰|특|L|l)+.*")) {
					String nameLarge = (item.name).substring(0, (item.name).length() - 3) + "(L)";
					for (MenuList.MenuItem menu : MenuList.desserts) {
						if (nameLarge.equals(menu.name))
							OrderHistory.MakeOrderHistory(menu.name, menu.price, menu.time, null);
					}
					break;
				}
			}
		}

		// 양념감자 시즈닝을 저장하는 함수
		public static void optioningSeasoning(MenuList.MenuItem item) {
			while (true) {
				System.out.println(Color.txtKiosk("양념감자 시즈닝을 선택해주세요. (어니언, 치즈, 칠리 중 선택)"));
				System.out.print(">>");
				String answer = scanner.nextLine();
				if (answer.matches(".*(어니언)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "어니언");
					break;
				} else if (answer.matches(".*(치즈)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "치즈");
					break;
				} else if (answer.matches(".*(칠리)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "칠리");
					break;
				}
			}
		}

		// 토네이도를 저장하는 함수
		public static void optioningTornado(MenuList.MenuItem item) {
			while (true) {
				System.out.println(Color.txtKiosk("토네이도 맛을 선택해주세요. (초코쿠키, 스트로베리, 더블초코 중 선택)"));
				System.out.print(">>");
				String answer = scanner.nextLine();
				if (answer.matches(".*(초코쿠키|쿠키)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "초코쿠키");
					break;
				} else if (answer.matches(".*(스트로베리|딸기|베리)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "스트로베리");
					break;
				} else if (answer.matches(".*(더블초코)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "더블초코");
					break;
				}
			}
		}

		// 선데아이스크림 저장하는 함수
		public static void optioningIcecream(MenuList.MenuItem item) {
			while (true) {
				System.out.println(Color.txtKiosk("선데아이스크림 맛을 선택해주세요. (플레인, 스트로베리, 허쉬초코 중 선택)"));
				System.out.print(">>");
				String answer = scanner.nextLine();
				if (answer.matches(".*(플레인)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "플레인");
					break;
				} else if (answer.matches(".*(스트로베리|딸기)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "스트로베리");
					break;
				} else if (answer.matches(".*(초코)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, "허쉬초코");
					break;
				}
			}
		}
	}

	public static class OrderBeverages extends OrderMethod {
		// 음료를 주문 내역에 추가하는 함수
		public static boolean storageMenu(String answer) {
			boolean tansan = false; // 콜라 혹은 사이다 여부를 저장하는 불린 변수
			if (answer.matches(".*콜라+.*")) {
				answer = "콜라(R)";
				tansan = true;
			}
			if (answer.matches(".*사이다+.*")) {
				answer = "사이다(R)";
				tansan = true;
			}

			if (tansan) {
				for (MenuList.MenuItem item : MenuList.beverages) {
					if (answer.equals(item.name)) {
						optioningSize(item);
						return true;
					}
				}
			} else {
				for (MenuList.MenuItem item : MenuList.beverages) {
					if (answer.equals(item.name)) {
						OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
						return true;
					}
				}
			}
			return false;
		}

		public static void optioningSize(MenuList.MenuItem item) {
			while (true) {
				System.out.println(Color.txtKiosk("음료의 사이즈를 선택해주세요.(R/L)"));
				System.out.print(">>");
				String buffer = scanner.nextLine();

				if (buffer.matches(".*(레귤러|기본|보통|R|r)+.*")) {
					OrderHistory.MakeOrderHistory(item.name, item.price, item.time, null);
					break;
				} else if (buffer.matches(".*(라지|큰|특|L|l)+.*")) {
					String nameLarge = (item.name).substring(0, (item.name).length() - 3) + "(L)";
					for (MenuList.MenuItem menu : MenuList.beverages) {
						if (nameLarge.equals(menu.name))
							OrderHistory.MakeOrderHistory(menu.name, menu.price, menu.time, null);
					}
					break;
				}
			}
		}
	}

}