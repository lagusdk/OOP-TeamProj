import java.util.Scanner;
import java.util.InputMismatchException;

public abstract class Payment {
	static Scanner scanner = new Scanner(System.in);

	static int random = (int) ((Math.random() * (50 - 0)) + 1); // 난수를 이용한 주문 번호 생성

	// 결제 함수
	public static void paymentLoop() {
		boolean isLoop = true;
		int totalAmount = Function.calculateTotal();
		int payedAmount = totalAmount;

		int paymentMethod = 0;
		while (isLoop) {
			System.out.println(Function.Color.txtKiosk("결제 방법을 선택해주세요. (숫자 입력)"));
			System.out.println("1. 현금 결제\t2. 카드 결제\t3. 쿠폰 사용");
			System.out.print(">>");
			try {
				paymentMethod = scanner.nextInt();
				switch (paymentMethod) {
				case 1:
					cash.payment(totalAmount);
					isLoop = false;
					break;
				case 2:
					card.payment(totalAmount);
					isLoop = false;
					break;
				case 3:
					payedAmount = coupon.payment(totalAmount);
					totalAmount = payedAmount;
					System.out.println(Function.Color.txtKiosk("쿠폰이 적용되었습니다! 남은 금액을 결제해주세요."));
					Function.timer();
					break;
				default:
					System.out.println(Function.Color.txtKiosk("1부터 3까지의 수를 입력해주세요."));
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(Function.Color.txtKiosk("잘못된 입력입니다. 숫자를 입력해주세요."));
				scanner.next();
			}
		}

		Function.printOrder();
		System.out.println("지불 금액\t: " + payedAmount + "원");

		System.out.println("주문번호\t: " + random);
		System.out.println("========================================");
	}

	public abstract int payment(int totalAmount);

	public class cash {
		// 현금 결제 함수
		static int payment(int totalAmount) {
			System.out.println(Function.Color.txtKiosk("현금 결제를 진행합니다."));

			int cash;
			while (true) {
				try {
					System.out.println(Function.Color.txtKiosk("아래 투입구에 현금을 넣어주세요. (숫자 입력)"));
					System.out.print(">>");
					cash = scanner.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println(Function.Color.txtKiosk("잘못된 입력입니다. 투입할 금액에 해당하는 숫자를 입력해주세요."));
					scanner.next();
				}
			}

			// 현금 결제 진행
			if (cash >= totalAmount) {
				int change = cash - totalAmount;
				System.out.println(Function.Color.txtKiosk("주문이 완료되었습니다. 거스름돈은 " + change + "원 입니다."));
			} else {
				System.out.println(Function.Color.txtKiosk("입력한 현금이 부족합니다. 추가로 현금을 넣으시겠습니까?"));
				if (Function.answer())
					payment(totalAmount - cash); // 부족한 금액을 넣는 코드
				else
					System.out.println(Function.Color.txtKiosk("주문이 취소되었습니다."));
			}

			return totalAmount;
		}
	}

	public class card {
		// 카드 결제 함수
		static int payment(int totalAmount) {
			System.out.println(Function.Color.txtKiosk("카드 결제를 진행합니다."));
			System.out.println(Function.Color.txtKiosk("카드를 넣어주세요."));
			Function.timer();

			while (true) {
				System.out.println(Function.Color.txtSystem("결제를 계속할까요?"));
				if (Function.answer()) {
					// 카드 삽입 확인 메시지
					System.out.println(Function.Color.txtKiosk("카드를 삽입했습니다."));
					System.out.println(Function.Color.txtKiosk("카드 결제 중..."));

					// 서명 과정
					if (totalAmount >= 50000) {
						System.out.println(Function.Color.txtKiosk("결제 금액이 5만원 이상이므로 서명이 필요합니다."));
						System.out.println(Function.Color.txtKiosk("아래에 서명해주세요."));
						System.out.print(">>");

						// 사용자 이름 입력 받기
						String userName = scanner.nextLine();
						System.out.println(Function.Color.txtKiosk("" + userName + "님의 서명이 완료되었습니다."));
					}
					System.out.println(Function.Color.txtKiosk("결제가 완료되었습니다."));
					break;
				} else {
					System.out.println(Function.Color.txtKiosk("결제가 취소되었습니다."));
				}
			}
			return totalAmount;
		}

	}

	public class coupon {
		// 쿠폰 결제 함수
		static int payment(int totalAmount) {
			boolean isLoop = true;

			System.out.println(Function.Color.txtKiosk("쿠폰 사용을 진행합니다."));
			Function.printOrder();
			System.out.println("합계:\t" + totalAmount + "원");
			System.out.println("=================");

			int couponChoice;
			int discount = 0;

			while (isLoop) {
				try {
					System.out.println(Function.Color.txtKiosk("보유하고 있는 쿠폰을 선택해주세요."));
					System.out.println("1. 10% 할인 쿠폰\t2. 20% 할인 쿠폰\t3. 30% 할인 쿠폰");
					System.out.print(">>");
					couponChoice = scanner.nextInt();

					switch (couponChoice) {
					case 1:
						discount = 10;
						isLoop = false;
						break;
					case 2:
						discount = 20;
						isLoop = false;
						break;
					case 3:
						discount = 30;
						isLoop = false;
						break;
					default:
						System.out.println(Function.Color.txtKiosk("올바른 쿠폰을 선택해주세요."));
						break;
					}
				} catch (InputMismatchException e) {
					System.out.println(Function.Color.txtKiosk("잘못된 입력입니다. 사용할 쿠폰에 해당하는 숫자를 입력해주세요."));
					scanner.next();
				}
			}

			// 쿠폰을 적용하여 결제
			int discountedPrice = totalAmount * (100 - discount) / 100;
			return discountedPrice;
		}
	}

}
