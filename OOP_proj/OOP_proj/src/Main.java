// SwingLess 진짜 프로젝트 파일(일단은)
import java.util.Scanner;

public class Main {

	public static void Simulation() {
		// 메인 시뮬레이션
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("");
		System.out.println("[system] 롯X리아 입장");
		System.out.println("직원 \"어서오세요.\"");		

		System.out.println("[system] 키오스크 주문 메뉴");
		
		// 포장&매장 선택(필요 시 함수로 수정)
		String answer;
		int packaging_or_not = -1;
		while(true) {
			System.out.println("[키오스크] 포장과 매장 중 이용 방법을 선택해주세요.");
			System.out.print(">>");
			answer = scanner.nextLine();
			
			if(answer.equals("포장")) {
				packaging_or_not = 0;
				break;
			}
			else if(answer.equals("매장")) {
				packaging_or_not = 1;
				break;
			}
			else {
				System.out.println("");
			}
		}
		
		// 메뉴 선택
		boolean loop_or_not = true;
		do {
			OrderLoops(); // 메뉴 선택
			
			while(true) { // 주문 계속 여부 입력
				System.out.println("주문을 계속할까?");
				System.out.print(">>");
				answer = scanner.nextLine();
				
				if(answer.equals("예") | answer.equals("네")) {
					loop_or_not = true;
					break;
				}
				else if(answer.equals("아니요")) {
					loop_or_not = false;
					break;
				}
				else {
					System.out.println("");
				}
			}
		} while(loop_or_not);
		
		// Todo 주문 내역 출력하기 구현
		// Todo 결제하기 구현
		// Todo 대기 후 음식 픽업 구현
	}
	
	public static void OrderLoops() {
		// 여기에 메뉴 출력, 메뉴 선택 구현
		System.out.println("[메뉴 출력]"); // Todo 이거 구현하기.
		System.out.println("[메뉴 선택]"); // Todo 이거 구현하기.
		
	}
	
	public static void main(String[] args) {
		// 메인 화면
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			System.out.println("[system] 메인 메뉴");
			
			System.out.println("롯X리아에 입장하시겠습니까?");
			System.out.print(">>");
			String answer = scanner.nextLine();
			
			if(answer.equals("예") | answer.equals("네")) {
				Simulation();
			}
			else if(answer.equals("아니요")) {
				System.out.println("[system] 시뮬레이션 종료");
				break;
			}
			else {
				System.out.println("");
			}
			
		}
	}

}
