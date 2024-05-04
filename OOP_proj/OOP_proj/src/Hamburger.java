// SwingLess 진짜 프로젝트 파일(일단은)
import java.util.Scanner;

public class Hamburger {

	public static void Simulation() {
		System.out.println("직원 \"어서오세요~\"");
		
		// Todo 여기서부터 메인 시뮬레이션 내용 추가
	}
	
	
	public static void main(String[] args) {
		// 메인 화면 루프
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("===== 객체지향프로그래밍 팀프로젝트 =====");
			System.out.println("");
			System.out.println("[system]롯X리아에 입장하시겠습니까?");
			System.out.print(">>");
			String answer = scanner.nextLine();
			
			if(answer.equals("예")) {
				Simulation();
			}
			else if(answer.equals("아니요")) {
				System.out.println("[system]시뮬레이션 종료");
				break;
			}
			else {
				System.out.println("");
			}
			
		}
	}

}
