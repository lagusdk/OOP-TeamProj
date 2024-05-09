// 주문 내역 저장 함수를 제공하는 클래스
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	static Scanner scanner = new Scanner(System.in);
	
		// 주문 내역 저장을 위한 class
		static class OrderHistory {
	    	public String name;	// 메뉴 이름
	        public int price; 	// 가격
	        public int time;	// 대기 시간
	        public String etc;
	        public int number;	// 개수
	        static int totalAmount = 0;	// 주문 총액

	        static ArrayList<OrderHistory> orderhistory = new ArrayList<>();
	        
		    // 메뉴 항목을 초기화하는 생성자
		    public OrderHistory(String name, int price, int time, String etc, int number) {
		        this.name = name;
		    	this.price = price;
		        this.time = time;
		        this.etc = etc;
		        this.number = number;
		        
			    orderhistory.add(this);
		    }
		    
		    // 주문 내역을 출력하는 함수(임시, 두 번씩 출력되는 오류가 있으니 추후 수정 요망)
		    public static void printOrder() {
		    	for (OrderHistory item : orderhistory) {
		    		System.out.println(item.name + "\t(" + item.etc + ")\t" + item.price + "원");
	                totalAmount += item.price;
	            }
		    	System.out.println("\n할인 전 총액\t" + totalAmount + "원");
	        }
	    }
		
		
		static public class OrderMethod {

			// 반복 주문을 위한 함수 
			public static void OrderLoops() {
				String answer, buffer;
				boolean loop_or_not = false;
				
				do {
					System.out.println("[키오스크] 메뉴를 한 개 선택해주세요.");
					System.out.print(">>");
					buffer = scanner.nextLine();
					answer = buffer;

					// 단어 '메뉴'와 매치되는 경우
					if(buffer.matches(".*(메뉴|뭐|종류)+.*")) {
						MenuList.printMenu();	// MenuList의 함수 사용, 필요 시 변경
					}
					// 음식 이름과 매치되는 경우
					if(buffer.matches(".*(데리|(치킨버거)|새우|불고기)+.*")) {	// 버거와 매치되는 경우 
						single_or_set(buffer, answer);
						loop_or_not = false;
						}
					else if(Order_desserts(answer) || Order_beverages(answer)) { // 음료 혹은 디저트와 매치되는 경우
								loop_or_not = false;
					}
					else {
						System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");	// 매치되지 않는 경우
						loop_or_not = true;
						}
				} while(loop_or_not);
			}
		
			// 버거 세트와 단품 여부를 결정하는 함수
			public static void single_or_set(String buffer, String answer) {
				while(true) {
					if(buffer.matches(".*(세트)+.*")) {
						Order_burger_set(answer);
						break;
					}
					else if(buffer.matches(".*(단품|버거만)+.*")) {
						Order_burger_single(answer);
						break;
					}
					else {
						System.out.println("[키오스크] 세트와 단품 중 어느 것을 주문하시겠습니까?");
						System.out.print(">>");
						buffer = scanner.nextLine();
					}
				}
			}


			// 버거 단품을 주문 내역에 추가하는 함수
			public static void Order_burger_single(String answer) {
				if(answer.matches(".*데리.*"))		Optioning_menu("데리버거");
				else if(answer.matches(".*치킨.*"))	Optioning_menu("치킨버거");
				else if(answer.matches(".*새우.*"))	Optioning_menu("새우버거");
				else if(answer.matches(".*불고기.*"))	Optioning_menu("불고기버거");
			}
			
			// 버거 세트를 주문 내역에 추가하는 함수
			public static void Order_burger_set(String answer) {
				if(answer.matches(".*(데리|(치킨버거)|새우|불고기)+.*")) {
					Order_burger_single(answer);
					System.out.println("[키오스크] 세트 구성품(포테이토와 콜라)을 변경하시겠습니까?");
					if(answer()) {
						Order_change_set();
					}
					else {
						Optioning_menu("포테이토");
						Optioning_menu("콜라");
					}
				}
				else System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");
			}
			
			// 버거 세트 구성품을 변경하여 주문 내역에 추가하는 함수
			public static void Order_change_set() {	
				String answer;
				boolean buffer_desserts = false, buffer_beverages = false;
				boolean loop_or_not = false;
				
				do {		
					System.out.println("[키오스크] 변경하실 메뉴를 선택해주세요.");
					System.out.print(">>");
					answer = scanner.nextLine();
					
					if(Order_desserts(answer))			buffer_desserts = true;
					else if(Order_beverages(answer))	buffer_beverages = true;	
				    
				    if(buffer_desserts ^ buffer_beverages) {
				    	System.out.println("[키오스크] 다른 세트 구성품도 변경하시겠습니까?");
				    	loop_or_not = answer();
				    	if(loop_or_not == false) {
				    		if(buffer_desserts)			Optioning_menu("콜라");
				    		else if(buffer_beverages)	Optioning_menu("포테이토");
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
			
			// 디저트를 주문 내역에 추가하는 함수
			public static boolean Order_desserts(String answer) {
				if(answer.matches(".*(포테이토|감자튀김|후렌치|프렌치|후라이|프라이)+.*"))	answer = "포테이토";
				else if(answer.matches(".*(양념감자)+.*"))						answer = "양념감자";
				
				for(MenuList.MenuItem item : MenuList.desserts) {
			        if(answer.equals(item.name)) {
			        	if(answer.equals("포테이토")) {
			        		Order_size(answer, item);
			        	}
			        	else if(answer.equals("양념감자")) {
			        		Optioning_menu(answer, item);
			        	}
			            return true;
			        }
			    }
			    return false;
			}
			
			// 음료를 주문 내역에 추가하는 함수
			public static boolean Order_beverages(String answer) {
			    for(MenuList.MenuItem item : MenuList.beverages) {
			        if(answer.equals(item.name)) {
			        	Order_size(answer, item);
			        	return true;
			        }
			    }
			    return false;
			}
			
			// 입력 받은 name을 MenuList와 대조하여 price와 time을 array에 저장하는 함수(다형성)
			public static void Optioning_menu(String name) {
			    for(MenuList.MenuItem item : MenuList.hamburgers) {
			        if(name.equals(item.name)) {
			            new OrderHistory(item.name, item.price, item.time, null, 1);
			        }
			    }
			    for(MenuList.MenuItem item : MenuList.desserts) {
			        if(name.equals(item.name)) {
			            new OrderHistory(item.name, item.price, item.time, null, 1);
			        }
			    }
			    for(MenuList.MenuItem item : MenuList.beverages) {
			        if(name.equals(item.name)) {
			            new OrderHistory(item.name, item.price, item.time, null, 1);
			        }
			    }
			}
			
			// 디저트 혹은 음료의 사이즈를 저장하는 함수
			public static void Order_size(String answer, MenuList.MenuItem item) {
				while(true) {
					System.out.println("[키오스크] 사이즈를 선택해주세요.(R/L)");
					System.out.print(">>");
					answer = scanner.nextLine();
		    		if(answer.matches(".*(레귤러|기본|보통|R|r)+.*")) {
		    			new OrderHistory(item.name, item.price, item.time, "레귤러", 1);
		    			break;
		    		}
		    		else if(answer.matches(".*(라지|큰|특|L|l)+.*")) {
		    			new OrderHistory(item.name, item.price, item.time, "라지", 1);
		    			break;
		    		}
				}
			}
			
			// 양념감자 시즈닝을 저장하는 함수(다형성)
			public static void Optioning_menu(String answer, MenuList.MenuItem item) {
				while(true) {
					System.out.println("[키오스크] 양념감자 시즈닝을 선택해주세요. (어니언, 치즈, 칠리 가능)");
					System.out.print(">>");
					answer = scanner.nextLine();
		    		if(answer.matches(".*(어니언)+.*")) {
		    			new OrderHistory(item.name, item.price, item.time, "어니언", 1);
		    			break;
		    		}
		    		else if(answer.matches(".*(치즈)+.*")) {
		    			new OrderHistory(item.name, item.price, item.time, "치즈", 1);
		    			break;
		    		}
		    		else if(answer.matches(".*(칠리)+.*")) {
		    			new OrderHistory(item.name, item.price, item.time, "칠리", 1);
		    			break;
		    		}
				}
			}
			
			// 예 아니요 형태의 사용자 입력을 boolean 값으로 반환하는 함수
			public static boolean answer() {	
				while(true) {
					System.out.print(">>(y/n)");
					String answer = scanner.nextLine();
					System.
					out.println("");
					if(answer.matches(".*(예|응|네|그래|오냐|ㅇ|(?i)y|(?i)yes)+.*"))		return true;
					else if(answer.matches(".*(아니|별로|그닥|글쎄|ㄴ|(?i)no|(?i)n)+.*"))	return false;
				}
			}
		}
}
