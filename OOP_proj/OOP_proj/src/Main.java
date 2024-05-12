import java.util.Scanner;

public class Main {
	static MenuList menulist = new MenuList(); 
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// 메인 화면
		while(true) {
			// 초기화
			Order.OrderHistory.orderhistory.clear();
			
			// 메인 메뉴
			System.out.println("");
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			System.out.println("[system] 메인 메뉴");
			Function.timer();
			System.out.println("[system] 롯데리아에 입장하시겠습니까?");
			
			if(Function.answer()) {
				Simulation();	// 시뮬레이션 진행
			}
			else {
				System.out.println("[system] 시뮬레이션 종료");
				System.out.println("=========================================");
				break;
			}
			Function.timer(3);
		}
		scanner.close();
	}
	

	// 메인 시뮬레이션 함수
	public static void Simulation() {
		System.out.println("[system] 롯데리아 입장");
		Function.timer();
		System.out.println("[system] 키오스크를 통해 주문합니다.");
		Function.timer();
		
		// 포장 혹은 매장 선택
		Pickup.TakeoutStatus();	
		
		// 메뉴 선택
		boolean isLoop = false;
		do {
			Order.OrderMethod.OrderLoops(); // 메뉴 선택
			System.out.println("[system] 주문을 계속할까요?");
			isLoop = Function.answer();
		} while(isLoop);
		
		// 주문 내역 출력
		Function.printOrder();
		
		// 결제
		Payment.PaymentLoop();
		
		// 픽업
		Pickup.FoodPickup();
	}
}