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
    	new Hamburger("데리버거", 3300, 300);
    	new Hamburger("치킨버거", 4000, 300);
    	new Hamburger("새우버거", 4700, 240);
    	new Hamburger("핫크리스피버거", 5900, 300);
    	new Hamburger("불고기버거", 4700, 300);
    	new Hamburger("빅불버거", 7100, 300);
    	new Hamburger("한우불고기", 8400, 360);

    	new Dessert("포테이토", 1800, 180);
    	new Dessert("양념감자", 2300, 180);
    	new Dessert("치즈스틱", 2400, 90);
    	new Dessert("오징어링", 2600, 140);
    	
    	new Dessert("치킨너겟", 2700, 300);
    	new Dessert("화이어윙", 3100, 360);
    	new Dessert("치킨휠레", 3100, 300);
    	
    	new Dessert("소프트콘", 900, 60);
    	new Dessert("토네이도", 2800, 60);
    	
    	new Beverage("콜라", 2000, 15);
    	new Beverage("사이다", 2000, 15);
    	new Beverage("아이스티", 2300, 25);
    	new Beverage("레몬에이드", 2500, 25);
    	new Beverage("아메리카노", 2500, 25);
    	new Beverage("카페라떼", 3000, 25);
    	new Beverage("오렌지주스", 2500, 25);
    }
  
    // 메뉴 추가한거 확인용(메뉴 리스트 수정하고 저장 후 실행해서 확인 가능 in Console)
    // 나중에는 지워도될듯
    public static void printMenu() {
    	System.out.println("[ 햄버거 메뉴 리스트 ]");
        for (MenuItem item : hamburgers) {
        	System.out.println(item.name + "\t |   "+ "W " + item.price + "\t"+ item.time + " sec");
        }

        System.out.println("\n[ 디저트 메뉴 리스트 ]");
        for (MenuItem item : desserts) {
        	System.out.println(item.name + "\t |   "+ "W " + item.price + "\t"+ item.time + " sec");
        }

        System.out.println("\n[ 음료 메뉴 리스트 ]");
        for (MenuItem item : beverages) {
        	System.out.println(item.name + "\t |   "+ "W " + item.price+ "\t"+ item.time + " sec");
        }
    }
        
        
       
}