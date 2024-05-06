// SwingLess 진짜 프로젝트 파일(일단은)
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	static MenuList menulist = new MenuList(); 
	static Scanner scanner = new Scanner(System.in);
	
	
	// 주문 내역 저장을 위한 class
	static class OrderHistory {
    	public String name;	// 메뉴 이름
        public int price; 	// 가격
        public int time;	// 대기 시간
        public int number;	// 개수
   
        //public static List<OrderHistory> orderhistory = new ArrayList<>();
        static ArrayList<OrderHistory> orderhistory = new ArrayList<>();
        
	    // 메뉴 항목을 초기화하는 생성자
	    public OrderHistory(String name, int price, int time, int number) {
	        this.name = name;
	    	this.price = price;
	        this.time = time;
	        this.number = number;
	        
		    orderhistory.add(this);
	    }
	    
	    // 주문 내역을 출력하는 함수(임시, 두 번씩 출력되는 개같은 오류 있으니 추후 수정 요망)
	    public static void printOrder() {
            for (OrderHistory item : orderhistory) {
                System.out.println(item);
            }
        }
    }
	
	// main 시뮬레이션 함수
	public static void Simulation() {
		// 메인 시뮬레이션	
		System.out.println("[system] 롯X리아 입장");
		System.out.println("직원 \"어서오세요.\"");		
		System.out.println("");
		
		System.out.println("[system] 키오스크 주문 메뉴");
		
		// 포장&매장 선택(필요 시 함수로 수정)
		String answer;
		int packaging_or_not = -1;
		while(true) {
			System.out.println("[키오스크] 포장과 매장 중 이용 방법을 선택해주세요.");
			System.out.print(">>");
			answer = scanner.nextLine();
			System.out.println("");
			
			if(answer.equals("포장")) {
				packaging_or_not = 0;
				break;
			}
			else if(answer.equals("매장")) {
				packaging_or_not = 1;
				break;
			}
		}
		
		// 메뉴 선택
		boolean loop_or_not = false;
		do {
			OrderLoops(); // 메뉴 선택
			OrderHistory.printOrder();
			
			System.out.println("주문을 계속할까?");
			loop_or_not = answer();
			
		} while(loop_or_not);
		
		// Todo 주문 내역 출력하기 구현
		// Todo 결제하기 구현
		// Todo 대기 후 음식 픽업 구현
	}
	
	// 반복 주문을 위한 함수 
	public static void OrderLoops() {
		// 여기에 메뉴 출력, 메뉴 선택 구현
		String answer;
		boolean loop_or_not = false;
		
		do {
			System.out.println("[키오스크] 메뉴를 선택해주세요.");
			System.out.print(">>");
			answer = scanner.nextLine();
			
			if(answer.matches(".*메뉴.*")) {	// '메뉴' 단어만 포함되면 메뉴를 출력, (참고)정규표현식 사용
				MenuList.printMenu();	// MenuList의 함수 사용, 필요 시 변경
			}
			else {
				if(answer.matches(".*(데리|치킨|새우|불고기)+.*")) {
					while(true) {
						if(answer.matches(".*세트.*")) {
							Order_burger_set(answer);
							break;
						}
						else if(answer.matches(".*단품.*")) {
							Order_burger_single(answer);
							break;
						}
						else {
							System.out.println("[키오스크] 세트와 단품 중 어느 것을 주문하시겠습니까?");
							System.out.print(">>");
							answer = scanner.nextLine();
						}
					}
					loop_or_not = false;
				}
				else {
					System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");
					loop_or_not = true;
				}
			}
			
			
		} while(loop_or_not);
	}
		
	// 버거 단품을 주문 내역에 추가하는 함수
	public static void Order_burger_single(String answer) {
		if(answer.matches(".*데리.*")) {
			Specific_menu("데리버거");
		}
		else if(answer.matches(".*치킨.*")) {
			Specific_menu("치킨버거");
		}
		else if(answer.matches(".*새우.*")) {
			Specific_menu("새우버거");
		}
		else if(answer.matches(".*불고기.*")) {
			Specific_menu("불고기버거");
		}
	}
	
	// 버거 세트를 주문 내역에 추가하는 함수(다형성)
	public static void Order_burger_set(String answer) {
		if(answer.matches(".*(데리|치킨|새우|불고기)+.*")) {
			Order_burger_single(answer);
			System.out.println("[키오스크] 세트 구성품을 변경하시겠습니까?");
			if(answer()) {
				Order_burger_set();
			}
			else {
				Specific_menu("포테이토");
				Specific_menu("콜라");
			}
		}
		else {
			System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");
		}
	}
	
	// 버거 세트 구성품을 변경하여 주문 내역에 추가하는 함수(다형성)
	public static void Order_burger_set() {	
		String answer;
		boolean buffer_desserts = false;
		boolean buffer_beverages = false;
		boolean loop_or_not = false;
		
		do {
			System.out.println("[키오스크] 변경하실 메뉴를 선택해주세요.");
			System.out.print(">>");
			answer = scanner.nextLine();
			
		    for(MenuList.MenuItem item : MenuList.desserts) {
		        if(answer.equals(item.name) && buffer_desserts == false) {
		            OrderHistory orderhistory = new OrderHistory(item.name, item.price, item.time, 1);
		            buffer_desserts = true;
		        }
		    }
		    for(MenuList.MenuItem item : MenuList.beverages) {
		        if(answer.equals(item.name) && buffer_beverages == false) {
		            OrderHistory orderhistory = new OrderHistory(item.name, item.price, item.time, 1);
		            buffer_beverages = true;
		        }
		    }
		    
		    if(buffer_desserts ^ buffer_beverages) {
		    	System.out.println("[키오스크] 다른 세트 구성품도 변경하시겠습니까?");
		    	loop_or_not = answer();
		    	
		    	if(loop_or_not == false) {
		    		if(buffer_desserts) {
		    			Specific_menu("콜라");
		    		}
		    		else if(buffer_beverages) {
		    			Specific_menu("포테이토");
		    		}
		    	}
		    }
		    else if(buffer_desserts && buffer_beverages){
		    	loop_or_not = false;
		    }
		    else if(!(buffer_desserts || buffer_beverages)){
		    	System.out.println("[키오스크] 세트 구성품을 다시 선택해주세요!");
		    	loop_or_not = true;
		    }
		} while(loop_or_not);
	}
	
	
	// 인자로 전달받은 메뉴 name과 동일한 메뉴를 MenuList에서 찾고, 해당하는 price와 time을 orderhistory에 저장
	public static void Specific_menu(String name) {
	    for(MenuList.MenuItem item : MenuList.hamburgers) {
	        if(name.equals(item.name)) {
	            OrderHistory orderhistory = new OrderHistory(item.name, item.price, item.time, 1);
	        }
	    }
	    for(MenuList.MenuItem item : MenuList.desserts) {
	        if(name.equals(item.name)) {
	            OrderHistory orderhistory = new OrderHistory(item.name, item.price, item.time, 1);
	        }
	    }
	    for(MenuList.MenuItem item : MenuList.beverages) {
	        if(name.equals(item.name)) {
	            OrderHistory orderhistory = new OrderHistory(item.name, item.price, item.time, 1);
	        }
	    }
	}
	
	
	public static void main(String[] args) {
	
		// 메인 화면
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			
			System.out.println("[system] 메인 메뉴");
			System.out.println("롯X리아에 입장하시겠습니까?");
			
			if(answer()) {
				Simulation();
			}
			else {
				System.out.println("[system] 시뮬레이션 종료");
				break;
			}
		}
		
		scanner.close();
	}
	
	// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
	public static boolean answer() {	
		while(true) {
			System.out.print(">>");
			String answer = scanner.nextLine();
			System.out.println("");
			if(answer.equals("예") | answer.equals("네")) {
				return true;
			}
			else if(answer.equals("아니요")) {
				return false;
			}
		}
	}

}
