import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in, "ms949");

	static MenuList menulist = new MenuList(); // 생성자 함수를 이용한 메뉴 추가를 위해 클래스 파일 임포트

	public static void main(String[] args) {

		// 메인 화면
		while (true) {
			// 초기화
			Order.OrderHistory.orderhistory.clear();

			// 메인 메뉴
			System.out.println(Color.ANSI_GREEN + "\n===== 객체지향프로그래밍 팀프로젝트 =====\n" + Color.ANSI_RESET);
			System.out.println(Color.txtSystem("메인 메뉴"));
			Function.timer();
			System.out.println(Color.txtSystem(Color.txtLotte()+"에 입장하시겠습니까?"));

			if (Function.answer()) {
				Simulation(); // 시뮬레이션 진행
				System.out.println(Color.txtSystem("시뮬레이션 종료"));
			} else {
				System.out.println(Color.txtSystem("시뮬레이션 종료"));
				System.out.println(Color.ANSI_GREEN + "=========================================" + Color.ANSI_RESET);
				break;
			}
			Function.timer(3);
		}
		scanner.close();
	}

	// 메인 시뮬레이션 함수
	public static void Simulation() {
		System.out.println(Color.txtSystem(Color.txtLotte()+" 입장"));
		Function.timer();
		System.out.println(Color.txtSystem("키오스크를 통해 주문합니다."));
		Function.timer();

		// 포장 혹은 매장 선택
		Pickup.checkTakeout();

		// 메뉴 선택
		while (true) {
			Order.OrderMethod.orderLoops(); // 메뉴 선택
			System.out.println(Color.txtSystem("주문을 계속할까요?"));
			if (Function.answer())
				continue;
			else
				break;
		}

		Function.printOrder(); // 주문 내역 출력
		System.out.println("");

		if (Function.calculateTotal() > 0) {
			Payment.paymentLoop(); // 결제
			Pickup.pickupOrder(); // 픽업
		} else {
			System.out.println(Color.txtSystem("당신은 어떤 음식도 주문하지 않고 뻘쭘하게 매장 밖으로 나왔습니다."));
		}
	}
}