import java.util.Scanner;

public class Main {
	static MenuList menulist = new MenuList(); 
	static Scanner scanner = new Scanner(System.in);
	
	static int packaging_or_not = -1; // 포장 혹은 매장 선택 변수
	
	public static void main(String[] args) {
		// 메인 화면
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			
			System.out.println("[system] 메인 메뉴");
			System.out.println("[system] 롯데리아에 입장하시겠습니까?");
			
			if(Order.OrderMethod.answer()) {
				Simulation();
			}
			else {
				System.out.println("[system] 시뮬레이션 종료");
				System.out.println("=========================================");
				break;
			}
		}
		
		scanner.close();
	}
	

	// main 시뮬레이션 함수
	public static void Simulation() {
		// 메인 시뮬레이션	
		System.out.println("[system] 롯데리아 입장");
		System.out.println("");
		
		System.out.println("[system] 키오스크 주문 메뉴");
		
		// 포장&매장 선택(필요 시 함수로 수정)
		String answer;
		
		while(true) {
			System.out.println("[키오스크] 포장과 매장 중 이용 방법을 선택해주세요.");
			System.out.print(">>");
			answer = scanner.nextLine();
			System.out.println("");
			
			if(answer.matches(".*(포장|가져)+.*")) {
				packaging_or_not = 0;
				break;
			}
			else if(answer.matches(".*(매장|먹고)+.*")) {
				packaging_or_not = 1;
				break;
			}
		}
		
		// 메뉴 선택
		boolean loop_or_not = false;
		do {
			Order.OrderMethod.OrderLoops(); // 메뉴 선택
			
			System.out.println("주문을 계속할까?");
			loop_or_not = Order.OrderMethod.answer();
			
			// 임시 주문 내역 확인 코드(메뉴 구성 출력, 은송)
			System.out.println("===주문 내역==="); 
			Order.OrderHistory.printOrder();
			System.out.println("==============="); 
			
		} while(loop_or_not);
		
		// Todo 주문 내역 출력하기 구현
		// Todo 결제하기 구현
		// Todo 대기 후 음식 픽업 구현
		
		Pickup.FoodPickup();
	}
}