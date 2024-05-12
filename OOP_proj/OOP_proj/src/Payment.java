import java.util.Scanner;

import java.util.InputMismatchException;

public class Payment {
	// 결제 함수
	static Scanner scanner = new Scanner(System.in);
	
	public static void PaymentLoop() {
		int totalAmount = addOrderHistory();
		
	    System.out.println("[system] 결제 방법을 선택해주세요.");
	    System.out.println("1. 현금 결제");
	    System.out.println("2. 카드 결제");
	    System.out.println("3. 쿠폰 결제");
	    
	    try { 
		    int paymentMethod = scanner.nextInt();
		    scanner.nextLine();

		    switch (paymentMethod) {
		        case 1:
		            cashPayment(totalAmount);
		            break;
		        case 2:
		            cardPayment(totalAmount);
		            break;
		        case 3:
		        	couponPayment(totalAmount);
		            break;
		        default:
		            System.out.println("올바른 결제 방법을 선택해주세요.");
		            break;
		    }
		} catch (InputMismatchException e) {
            System.out.println("[시스템] 잘못된 입력입니다. 숫자를 입력해주세요.");
            scanner.nextLine(); 
            //handlePayment();
        }
    }
	
	// 가격 총합을 계산하는 함수
	static int addOrderHistory() {
		int sum = 0;
    	for (Order.OrderHistory item : Order.OrderHistory.orderhistory) {
    		sum =+ item.price;
        }
    	return sum;
    }

	// 현금 결제 함수
	static void cashPayment(int totalAmount) {
	    System.out.println("=== 현금 결제 ===");
	    System.out.println("주문 총액: " + totalAmount + "원");

	    System.out.println("현금을 넣어주세요:");
	    int cash = scanner.nextInt();

	    // 현금 결제 진행
	    if (cash >= totalAmount) {
	        int change = cash - totalAmount;
	        System.out.println("주문이 완료되었습니다. 거스름돈은 " + change + "원 입니다.");
	    } else {
	        System.out.println("입력한 현금이 부족합니다. 추가로 현금을 넣으시겠습니까? (y/n)");
	        String response = scanner.next();
	        if (response.equalsIgnoreCase("y")) {
	            cashPayment(totalAmount - cash); // 부족한 금액을 넣는 코드
	      } else {
	            System.out.println("주문이 취소되었습니다.");
	        }
	    }
	}


	// 카드 결제 함수
	static void cardPayment(int totalAmount) {
		System.out.println("=== 카드 결제 ===");
		System.out.println("주문 총액: " + totalAmount + "원");
		
	    System.out.println("[system] 카드를 넣어주세요.");

	    //카드 삽입 확인 메시지 
	    System.out.println("카드를 삽입했습니다.");

	 // 결제 진행 여부 확인
        System.out.println("결제를 진행하시겠습니까? (y/n)");
        if (Main.answer()) {
            System.out.println("[system] 카드 결제 중...");

            // 서명 과정
            if (totalAmount >= 50000) {
                System.out.println("[system] 결제 금액이 5만원 이상이므로 서명이 필요합니다.");
                System.out.println("[system] 서명을 위해 사용자의 이름을 입력해주세요:");

                // 사용자 이름 입력 받기
                String userName = scanner.nextLine();
                System.out.println("[system] " + userName + "님의 서명이 완료되었습니다.");
            }

            // 결제 완료 메시지 출력
            System.out.println("결제가 완료되었습니다. 주문 감사합니다.");
        } else {
            System.out.println("결제가 취소되었습니다.");
        }
    }

	// 쿠폰 결제 함수
    static void couponPayment(int totalAmount) {
    	System.out.println("===쿠폰 결제 ===");
		System.out.println("주문 총액: " + totalAmount + "원");
		
        System.out.println("[system] 보유하고 있는 쿠폰을 선택해주세요.");
        System.out.println("1. 10% 할인 쿠폰");
        System.out.println("2. 20% 할인 쿠폰");
        System.out.println("3. 30% 할인 쿠폰");

        int couponChoice = scanner.nextInt();
        scanner.nextLine();

        int discount = 0;
        switch (couponChoice) {
            case 1:
                discount = 10;
                break;
            case 2:
                discount = 20;
                break;
            case 3:
                discount = 30;
                break;
            default:
                System.out.println("올바른 쿠폰을 선택해주세요.");
                couponPayment(totalAmount);
                break;
        }

        // 쿠폰을 적용하여 결제
        int totalPrice = addOrderHistory();
        int discountedPrice = totalPrice * (100 - discount) / 100;

        System.out.println("최종 결제 금액: " + discountedPrice);
    }
}
