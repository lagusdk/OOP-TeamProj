// 주문 내역 저장 함수를 제공하는 클래스
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
   static Scanner scanner = new Scanner(System.in);
   
   // 주문 내역 저장을 위한 class
   static class OrderHistory {
       public String name;   // 메뉴 이름
        public int price;    // 가격
        public int time;   // 대기 시간
        public String option;   // 시즈닝 혹은 맛 저장

        public static ArrayList<OrderHistory> orderhistory = new ArrayList<>();



        
       // 메뉴 항목을 초기화하는 생성자
       public OrderHistory(String name, int price, int time, String option) {
           this.name = name;
          this.price = price;
           this.time = time;
           this.option = option;
           
          orderhistory.add(this);
       }
    }
   
   // 주문과 관련된 함수를 저장하는 class
   static public class OrderMethod {

      // 반복 주문을 위한 함수 
      public static void orderLoops() {
         String answer, buffer;
         boolean isLoop = false;
         
         do {
            System.out.println("[키오스크] 메뉴를 한 개 선택해주세요.");
            System.out.print(">>");
            buffer = scanner.nextLine();
            answer = buffer;
            
            // 단어 '메뉴'와 매치되는 경우
            if(buffer.matches(".*(메뉴|뭐|종류)+.*")) {
               MenuList.printMenu();
               isLoop = true;
            }
            // 음식 이름과 매치되는 경우
            else if(buffer.matches(".*(데리|(치킨버거)|새우|치즈|(티렉스|(?i)trex)|크리스피|불고기|빅불|한우|돈까스|라이스)+.*")) {   // 버거와 매치되는 경우 
               checkBurgerSet(buffer, answer);
               isLoop = false;
            }
            else if(orderDesserts(answer) || orderBeverages(answer)) { // 음료 혹은 디저트와 매치되는 경우
               isLoop = false;
            }
            else if(Function.answer(answer) == 0) {
               isLoop = false;
            }
            else {
               System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");   // 매치되지 않는 경우
               isLoop = true;
            }
         } while(isLoop);
      }
      
      // 버거 세트와 단품 여부를 결정하는 함수
      public static void checkBurgerSet(String buffer, String answer) {
         while(true) {
            if(buffer.matches(".*(세트)+.*")) {
               orderBurgerSet(answer);
               break;
            }
            else if(buffer.matches(".*(단품|버거만)+.*")) {
               orderBurger(answer);
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
      public static void orderBurger(String answer) {
         if(answer.matches(".*데리.*"))                  storageMenu("데리버거");
         else if(answer.matches(".*치킨.*"))               storageMenu("치킨버거");
         else if(answer.matches(".*새우.*"))               storageMenu("새우버거");
         else if(answer.matches(".*치즈.*"))               storageMenu("치즈버거");
         else if(answer.matches(".*(티렉스|(?i)trex).*"))   storageMenu("티렉스버거");
         else if(answer.matches(".*크리스피.*"))          storageMenu("핫크리스피버거");
         else if(answer.matches(".*불고기.*"))             storageMenu("불고기버거");
         else if(answer.matches(".*빅불.*"))             storageMenu("빅불버거");
         else if(answer.matches(".*한우.*"))             storageMenu("한우불고기버거");
         else if(answer.matches(".*돈까스.*"))               storageMenu("왕돈까스버거");
         else if(answer.matches(".*라이스.*"))               storageMenu("전주비빔라이스버거");
      }
      
      // 버거 세트를 주문 내역에 추가하는 함수
      public static void orderBurgerSet(String answer) {
         if(answer.matches(".*(데리|(치킨버거)|새우|치즈|티렉스|크리스피|불고기|빅불|한우|돈까스|라이스)+.*")) {
            orderBurger(answer);
            System.out.println("[키오스크] 세트 구성품(포테이토와 콜라)을 변경하시겠습니까?");
            if(Function.answer()) {
               changeBurgerSet();
            }
            else {
               storageMenu("포테이토(R)");
               storageMenu("콜라(L)");
            }
         }
         else System.out.println("[키오스크] 올바른 메뉴를 선택해주세요.");
      }
      
      // 버거 세트 구성품을 변경하여 주문 내역에 추가하는 함수
      public static void changeBurgerSet() {   
         String answer;
         boolean buffer_desserts = false, buffer_beverages = false;
         boolean isLoop = false;
         
         do {      
            System.out.println("[키오스크] 변경하실 메뉴를 선택해주세요.");
            System.out.print(">>");
            answer = scanner.nextLine();
            
            if(orderDesserts(answer))      buffer_desserts = true;
            else if(orderBeverages(answer))   buffer_beverages = true;   
             
             if(buffer_desserts ^ buffer_beverages) {
                System.out.println("[키오스크] 다른 세트 구성품도 변경하시겠습니까?");
                isLoop = Function.answer();
                if(isLoop == false) {
                   if(buffer_desserts)         storageMenu("콜라(R)");
                   else if(buffer_beverages)   storageMenu("포테이토(L)");
                }
             }
             else if(buffer_desserts && buffer_beverages){
                isLoop = false;
             }
             else if(!(buffer_desserts || buffer_beverages)) {
                if(answer.matches("(.*라지\\s?세트*.)|(.*라지*.)")) {
                   new OrderHistory("포테이토(L)", 2200, 180, null);
                  new OrderHistory("콜라(L)", 2200, 15, null);
                  System.out.println("[키오스크] 라지 세트를 선택하셨습니다.");
                  isLoop = false;
               }
                else {
                   System.out.println("[키오스크] 세트 구성품을 다시 선택해주세요!");
                   isLoop = true;
                }
             }
         } while(isLoop);
      }
      
      // 디저트를 주문 내역에 추가하는 함수
      public static boolean orderDesserts(String answer) {
         if(answer.matches(".*(포테이토|감자튀김|감튀|후렌치|프렌치|후라이|프라이)+.*"))   answer = "포테이토(R)";
         else if(answer.matches(".*(양념감자)+.*"))                           answer = "양념감자";
         else if(answer.matches(".*(토네이도)+.*")) 							answer = "토네이도";
         else if(answer.matches(".*(선데아이스크림)+.*")) 							answer = "선데아이스크림";
         else if(answer.matches(".*(치킨다리|치킨한조각|치킨1조각)+.*"))            answer = "치킨한조각";
         
         for(MenuList.MenuItem item : MenuList.desserts) {
              if(answer.equals(item.name)) {
                 if(answer.equals("포테이토(R)")) {
                    optioningSize(answer, item);
                 }
                 else if(answer.equals("양념감자")) {
                    optioningSeasoning(answer, item);
                 }
                    
                    else if(answer.equals("토네이도")) {
                        optioningtornado(answer, item);
                 }
                    else if(answer.equals("선데아이스크림")) {
                        optioningicecream(answer, item);
                 }
                    
                 else {
                    storageMenu(answer);   
                 }
                  return true;
              }
          }
          return false;
      }
      
      // 음료를 주문 내역에 추가하는 함수
      public static boolean orderBeverages(String answer) {
         boolean tansan = false;
         if(answer.matches(".*콜라+.*"))   {
            answer = "콜라(R)";
            tansan = true;
         }
         if(answer.matches(".*사이다+.*")) {
            answer = "사이다(R)";
            tansan = true;
         }
         
         if (tansan) {
            for(MenuList.MenuItem item : MenuList.beverages) {
                 if(answer.equals(item.name)) {
                    optioningSize(answer, item);
                    return true;
                 }
             }
         }
         else {
            for(MenuList.MenuItem item : MenuList.beverages) {
                 if(answer.equals(item.name)) {
                    storageMenu(answer);
                    return true;
                 }
             }
         }
          return false;
      }
      
      // 파라미터 name을 MenuList와 대조하여, 해당하는 price와 time을 orderhistory에 저장하는 함수
      public static void storageMenu(String name) {
          for(MenuList.MenuItem item : MenuList.hamburgers) {
              if(name.equals(item.name)) {
                  new OrderHistory(item.name, item.price, item.time, null);
              }
          }
          for(MenuList.MenuItem item : MenuList.desserts) {
              if(name.equals(item.name)) {
                  new OrderHistory(item.name, item.price, item.time, null);
              }
          }
          for(MenuList.MenuItem item : MenuList.beverages) {
              if(name.equals(item.name)) {
                  new OrderHistory(item.name, item.price, item.time, null);
              }
          }
      }
      
      // 디저트 혹은 음료의 사이즈를 저장하는 함수
      public static void optioningSize(String answer, MenuList.MenuItem item) {
         while(true) {
            System.out.println("[키오스크] 사이즈를 선택해주세요.(R/L)");
            System.out.print(">>");
            String buffer = scanner.nextLine();
             if(buffer.matches(".*(레귤러|기본|보통|R|r)+.*")) {
                new OrderHistory(item.name, item.price, item.time, null);
                break;
             }
             else if(buffer.matches(".*(라지|큰|특|L|l)+.*")) {
                answer = (item.name).substring(0, (item.name).length() - 3)+"(L)";
                
                for(MenuList.MenuItem menuItem : MenuList.desserts) {
                     if(answer.equals(menuItem.name)) {
                         new OrderHistory(menuItem.name, menuItem.price, menuItem.time, null);
                     }
                 }
                for(MenuList.MenuItem menuItem : MenuList.beverages) {
                     if(answer.equals(menuItem.name)) {
                         new OrderHistory(menuItem.name, menuItem.price, menuItem.time, null);
                     }
                 }
                break;
             }
         }
      }
      
      // 양념감자 시즈닝을 저장하는 함수
      public static void optioningSeasoning(String answer, MenuList.MenuItem item) {
         while(true) {
            System.out.println("[키오스크] 양념감자 시즈닝을 선택해주세요. (어니언, 치즈, 칠리 가능)");
            System.out.print(">>");
            answer = scanner.nextLine();
             if(answer.matches(".*(어니언)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "어니언");
                break;
             }
             else if(answer.matches(".*(치즈)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "치즈");
                break;
             }
             else if(answer.matches(".*(칠리)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "칠리");
                break;
             }
         }
      }
   }
      
   // 토네이도를 저장하는 함수
      public static void optioningtornado(String answer, MenuList.MenuItem item) {
         while(true) {
            System.out.println("[키오스크] 토네이도 맛을 선택해주세요. (초코쿠키, 스트로베리, 더블초코 가능)");
            System.out.print(">>");
            answer = scanner.nextLine();
             if(answer.matches(".*(초코쿠키)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "초코쿠키");
                break;
             }
             else if(answer.matches(".*(스트로베리)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "스트로베리");
                break;
             }
             else if(answer.matches(".*(더블초코)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "더블초코");
                break;
             }
         }
      }
      
   // 선데아이스크림 저장하는 함수
      public static void optioningicecream(String answer, MenuList.MenuItem item) {
         while(true) {
            System.out.println("[키오스크] 선데아이스크림 맛을 선택해주세요. (플레인, 스트로베리, 허쉬초코 가능)");
            System.out.print(">>");
            answer = scanner.nextLine();
             if(answer.matches(".*(플레인)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "플레인");
                break;
             }
             else if(answer.matches(".*(스트로베리)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "스트로베리");
                break;
             }
             else if(answer.matches(".*(허쉬초코)+.*")) {
                new OrderHistory(item.name, item.price, item.time, "허쉬초코");
                break;
             }
         }
      }
      
      public static void SetMenu() {
   }
}