import java.util.Scanner;

public class Main {
	static MenuList menulist = new MenuList(); 
	static Scanner scanner = new Scanner(System.in);
	
	static int packaging_or_not = -1; // 포장 혹은 매장 선택 변수
	
	public static void main(String[] args) {
		// 메인 화면
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			Order.OrderHistory.orderhistory.clear();
			Order.OrderHistory.totalAmount = 0;
			
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			
			System.out.println("[system] 메인 메뉴");
			System.out.println("[system] 롯데리아에 입장하시겠습니까?");
			
			if(answer()) {
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
			loop_or_not = answer();
		} while(loop_or_not);
		
		Order.OrderHistory.printOrder();
		Payment.PaymentLoop();
		Pickup.FoodPickup();
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