import java.util.ArrayList;
import java.util.List;

public class MenuList {
	// 각 카테고리별 메뉴 항목 리스트를 정적 선언
	public static List<MenuItem> hamburgers = new ArrayList<>();
	public static List<MenuItem> desserts = new ArrayList<>();
	public static List<MenuItem> beverages = new ArrayList<>();

	// 메뉴 항목 나타내는 클래스(상속: 모든 메뉴 항목의 기반 클래스 역할)
	public static class MenuItem {
		public String name; // 메뉴 이름
		public int price; // 가격
		public int time; // 대기 시간

		// 메뉴 항목을 초기화하는 생성자
		public MenuItem(String name, int price, int time) {
			this.name = name;
			this.price = price;
			this.time = time;
		}

	}

	// 상속 활용하여 각 카테고리별 메뉴 항목을 추가
	public static class Hamburger extends MenuItem {
		public boolean set; // 세트

		public Hamburger(String name, int price, int time, boolean set) {
			super(name, price, time); // 부모 클래스의 생성자 호출
			this.set = set;
			hamburgers.add(this); // 생성 시 자동으로 리스트에 추가
		}
	}

	public static class Dessert extends MenuItem {
		public Dessert(String name, int price, int time) {
			super(name, price, time); // 부모 클래스의 생성자 호출
			desserts.add(this); // 생성 시 자동으로 리스트에 추가
		}
	}

	public static class Beverage extends MenuItem {
		public Beverage(String name, int price, int time) {
			super(name, price, time); // 부모 클래스의 생성자 호출
			beverages.add(this); // 생성 시 자동으로 리스트에 추가
		}
	}

	// 메뉴를 추가하는 생성자를 이용하여 클래스 로딩 과정에서 메뉴 생성
	public MenuList() {
		new Hamburger("데리버거", 3300, 300, false);
		new Hamburger("치킨버거", 4000, 300, false);
		new Hamburger("새우버거", 4700, 240, false);
		new Hamburger("티렉스버거", 4700, 390, false);
		new Hamburger("빅불버거", 7100, 360, false);
		new Hamburger("불고기버거", 4700, 300, false);
		new Hamburger("한우불고기버거", 8400, 360, false);
		new Hamburger("핫크리스피버거", 5900, 300, false);
		new Hamburger("왕돈까스버거", 7500, 360, false);
		new Hamburger("전주비빔라이스버거", 6900, 360, false);

		new Hamburger("데리버거", 5600, 300, true);
		new Hamburger("치킨버거", 6300, 300, true);
		new Hamburger("새우버거", 6900, 240, true);
		new Hamburger("티렉스버거", 6900, 390, true);
		new Hamburger("빅불버거", 9000, 360, true);
		new Hamburger("불고기버거", 6900, 300, true);
		new Hamburger("한우불고기버거", 10200, 360, true);
		new Hamburger("핫크리스피버거", 7100, 300, true);
		new Hamburger("왕돈까스버거", 9400, 360, true);
		new Hamburger("전주비빔라이스버거", 8800, 360, true);

		new Dessert("포테이토(R)", 1800, 180);
		new Dessert("포테이토(L)", 2200, 180);
		new Dessert("양념감자", 2300, 180);
		new Dessert("치즈스틱", 2400, 90);
		new Dessert("롱치즈스틱", 2000, 90);
		new Dessert("오징어링", 2600, 140);
		new Dessert("웨지포테이토", 2100, 180);
		new Dessert("콘샐러드", 1900, 30);

		new Dessert("치킨너겟", 2700, 300);
		new Dessert("화이어윙", 3100, 360);
		new Dessert("치킨휠레", 3100, 300);
		new Dessert("치킨1조각", 2800, 600);

		new Dessert("소프트콘", 900, 60);
		new Dessert("토네이도", 2800, 60);
		new Dessert("선데아이스크림", 1900, 30);

		new Beverage("콜라(R)", 2000, 15);
		new Beverage("콜라(L)", 2200, 15);
		new Beverage("사이다(R)", 2000, 15);
		new Beverage("사이다(L)", 2200, 15);
		new Beverage("아이스티", 2300, 25);
		new Beverage("레몬에이드", 2500, 25);
		new Beverage("아메리카노", 2500, 25);
		new Beverage("카페라떼", 3000, 25);
		new Beverage("오렌지주스", 2500, 15);
		new Beverage("우유", 1500, 5);
		new Beverage("핫초코", 2500, 25);

	}

	// 메뉴판 출력
	public static void printMenu() {
		System.out.println("");
		System.out.println("[ 햄버거 ]");
		for (MenuItem item : hamburgers) {
			System.out.println(item.name + "\t |   " + "W " + item.price);
		}

		System.out.println("");

		System.out.println("[ 디저트 ]");
		for (MenuItem item : desserts) {
			System.out.println(item.name + "\t |   " + "W " + item.price);
		}

		System.out.println("");

		System.out.println("[ 음료 ]");
		for (MenuItem item : beverages) {
			System.out.println(item.name + "\t |   " + "W " + item.price);
		}

		System.out.println("");
	}

}