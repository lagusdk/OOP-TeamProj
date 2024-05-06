// SwingLess 진짜 프로젝트 파일(일단은)
import java.util.Scanner;

public class Main {
	static MenuList menulist = new MenuList(); 
	static Scanner scanner = new Scanner(System.in);
	
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
			
			System.out.println("주문을 계속할까?");
			loop_or_not = answer();
			
		} while(loop_or_not);
		
		// Todo 주문 내역 출력하기 구현
		// Todo 결제하기 구현
		// Todo 대기 후 음식 픽업 구현
	}
	
	public static void OrderLoops() {
		// 여기에 메뉴 출력, 메뉴 선택 구현
		String answer;
		
		System.out.println("[키오스크] 메뉴를 선택해주세요.");
		System.out.print(">>");
		answer = scanner.nextLine();

		if(answer.matches(".*메뉴.*")) {	// '메뉴' 단어만 포함되면 작동하도록 정규표현식 사용
			MenuList.main(null); // MenuList의 함수 사용, 필요 시 변경
			MenuList.printMenu();
		}
		else {
			if(answer.matches(".*불고기.*")) {
				if(answer.matches(".*세트.*")) {
					System.out.println("[키오스크] 세트 구성품을 변경하시겠습니까?");
				}
				else if(answer.matches(".*단품.*")) {
					Specific_menu("불고기버거");
				}
				else {
					
				}
			}
		}
	
	}
	
	public static void Specific_menu(String name) {
		// Todo 이거 이름으로 어떻게 가격 시간에 접근하죠??
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
