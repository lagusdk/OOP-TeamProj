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

    // 상속 활용 -> 각 카테고리별 메뉴 항목 추가하기
    public static class Hamburger extends MenuItem {
        public Hamburger(String name, int price, int time) {
            super(name, price, time); // 부모 클래스의 생성자 호출
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
    
    public MenuList()
    {
    	hamburgers.add(new Hamburger("데리버거", 3300, 300));
    	hamburgers.add(new Hamburger("치킨버거", 4000, 300));
    	hamburgers.add(new Hamburger("새우버거", 4700, 240));
    	hamburgers.add(new Hamburger("불고기버거", 4700, 300));

    	desserts.add(new Dessert("포테이토", 1800, 180));
    	desserts.add(new Dessert("양념감자", 2300, 180));
    	desserts.add(new Dessert("치즈스틱", 2400, 90));
    	desserts.add(new Dessert("치킨너겟", 2700, 300));
    	desserts.add(new Dessert("오징어링", 2600, 140));
    	desserts.add(new Dessert("화이어윙", 3100, 300));
    	desserts.add(new Dessert("소프트콘", 900, 60));
    	
    	beverages.add(new Beverage("토네이도", 2800, 60));
    	beverages.add(new Beverage("콜라", 2000, 15));
    	beverages.add(new Beverage("사이다", 2000, 15));
    	beverages.add(new Beverage("아이스티", 2300, 25));
    	beverages.add(new Beverage("아메리카노", 2500, 25));

    }
  
    // 메뉴 추가한거 확인용(메뉴 리스트 수정하고 저장 후 실행해서 확인 가능 in Console)
    // 나중에는 지워도될듯
    public static void printMenu() {
        System.out.println("햄버거 메뉴 리스트");
        for (MenuItem item : hamburgers) {
            System.out.println("\t"+ item.name + "\t | \t"+ "가격: " + item.price + "원"+ "\t"+ "대기시간: " + item.time + "초");
        }

        System.out.println("\n디저트 메뉴 리스트");
        for (MenuItem item : desserts) {
        	System.out.println("\t"+ item.name + "\t | \t"+ "가격: " + item.price + "원"+ "\t"+ "대기시간: " + item.time + "초");
        }

        System.out.println("\n음료 메뉴 리스트");
        for (MenuItem item : beverages) {
        	System.out.println("\t"+ item.name + "    \t | \t"+ "가격: " + item.price + "원"+ "\t"+ "대기시간: " + item.time + "초");
        }
    }
        
        
       
}