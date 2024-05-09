
// 대기 후 음식 픽업 함수
		public static void FoodPickup() {
			int maxWaitingTime = 0;
			
		    for (MenuItem menuItem : hamburgers) {
		        if menuItem.time > maxWaitingTime) {
		            maxWaitingTime = menuItem.time;
		        }
		    }
		    for (MenuItem menuItem : desserts) {
		        if (menuItem.time > maxWaitingTime) {
		            maxWaitingTime = menuItem.time;
		        }
		    }
		    for (MenuItem menuItem : beverages) {
		        if (menuItem.time > maxWaitingTime) {
		            maxWaitingTime = menuItem.time;
		        }
		    }
		    
		    if (maxWaitingTime > 300) {
		        maxWaitingTime = 300;
		    }
	        
            try {
            	System.out.println("[system] 음식을 준비하고 있습니다...");
    	        System.out.println("[system] 대기 시간: " + maxWaitingTime/60 + "분" + maxWaitingTime%60 + "초");
                Thread.sleep(maxWaitingTime*50);
                System.out.println("[system] 음식이 준비되었습니다. 픽업해주세요!");
                System.out.println("[system] 픽업 완료!");
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }