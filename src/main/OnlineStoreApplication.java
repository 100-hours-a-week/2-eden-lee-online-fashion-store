import model.CartItem;
import model.Product;
import model.Stock;
import threads.TimeOutThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OnlineStoreApplication {
    private static final int timeout = 10;  // 15초
    private String name;
    private Stock stocks;
    private List<CartItem> cart;
    private Scanner scanner = new Scanner(System.in);

    public OnlineStoreApplication(String name, String stockPath) {
        this.name = name;
        this.stocks = new Stock(stockPath);
        this.cart = new ArrayList<>();
    }

    private int displayMainMenu(TimeOutThread timer) {
        System.out.println("===========================================");
        System.out.println("  어서오세요! 매일 특별한 당신을 위한 패션 쇼핑몰");
        System.out.println("            " + this.name +" 입니다 💝\n");
        System.out.println("1. 상품 구경하기");
        System.out.println("2. 장바구니 이동하기");
        System.out.println("3. 종료하기");
        System.out.println("===========================================");
        System.out.print("원하는 번호를 입력해주세요> ");

        while(true) {
            timer.start();
            int choice = this.scanner.nextInt();
            timer.stopTimer();

            List<Integer> menu = Arrays.asList(1,2,3);
            if(menu.contains(choice)) {
                return choice;
            }
            else {
                System.out.print("올바른 메뉴 번호를 입력해주세요>");
            }
        }

    }

    private void displayProductMenu(TimeOutThread timer) {
        // TODO: 출력과 관련된 view 클래스 생성
        System.out.println("\n어떤 종류의 상품을 구경하시겠습니까?");
        System.out.println("1. 의류 (상/하의)");
        System.out.println("2. 잡화 (가방/모자/신발)");
        System.out.println("3. 이전");
        System.out.print("> ");

        while(true) {
            timer.start();
            int choice = this.scanner.nextInt();
            timer.stopTimer();

            if(choice == 1 || choice == 2) {
                displayProductChoice(choice, timer);
                break;
            }
            else if(choice == 3) {
                System.out.print("\n");
                return;
            }
            else {
                System.out.print("올바른 메뉴 번호를 입력해주세요>");
            }
        }
    }

    private void displayProductChoice(int productType, TimeOutThread timer) {
        List<Integer> productIds = new ArrayList<>();

        if(productType == 1) {
            stocks.printClothes();
            productIds = stocks.getAllClothesId();
        }
        else if(productType == 2) {
            stocks.printAccessories();
            productIds = stocks.getAllAccessoriesId();
        }

        // 제품번호 선택
        int productId;
        while (true) {
            System.out.print("원하는 제품번호를 입력해주세요> ");
            timer.start();
            productId = scanner.nextInt();
            timer.stopTimer();

            if(productIds.contains(productId)) {
             break;
            }
            System.out.println("올바른 제품번호를 입력해주세요!\n");
        }
        Product product = stocks.getProductById(productId);

        System.out.println("\n선택한 제품: " + product.getName());
        System.out.println("1. 상세정보 확인하기");
        System.out.println("2. 장바구니에 담기");
        System.out.println("3. 메인화면으로");
        System.out.println("--------------------------");

        int choice;
        while(true) {
            System.out.print("원하는 번호를 입력해주세요> ");
            timer.start();
            choice = scanner.nextInt();
            timer.stopTimer();
            if(choice == 1) {
                stocks.printProductDetails(productId);
            }
            else if(choice == 2) {
                System.out.print("구매하실 수량을 입력해주세요> ");
                timer.start();
                int quantity = scanner.nextInt();
                timer.stopTimer();

                if(addToCart(product, quantity)) {
                    break;
                }
            }
            else if(choice == 3) {
                return;
            }
            else {
                System.out.println("올바른 메뉴번호를 입력해주세요!\n");
            }
        }
        displayCart(timer);
    }

    private boolean addToCart(Product product, int quantity) {
        if(product.checkStock(quantity)) {
            cart.add(new CartItem(product, quantity));
            System.out.println("\n제품을 장바구니에 추가하였습니다!");
            return true;
        }
        return false;
    }

    private void displayCart(TimeOutThread timer) {
        int totalCount = cart.size();
        int totalPrice = 0;

        System.out.println(String.format("\n현재 장바구니 (총 %d건)", totalCount));
        System.out.println("------------------------------------");
        for(CartItem cartItem: cart) {
            cartItem.printCartItem();
            totalPrice += cartItem.getTotalPrice();
        }
        System.out.println("------------------------------------");
        System.out.println("총 금액:                 " + totalPrice + "원\n");

        if(totalCount != 0) {
            timer.start();
            System.out.print("결제하시겠습니까? (Y/N)> ");
            String answer = scanner.next();
            timer.stopTimer();
            if(answer.equalsIgnoreCase("y")) {
                purchase(totalPrice, timer);
            }
        }
    }

    private void purchase(int totalPrice, TimeOutThread timer) {
        timer.start();
        System.out.print("지불하실 금액을 입력해주세요. (숫자만 입력)> ");
        int payment = scanner.nextInt();
        timer.stopTimer();

        if(payment >= totalPrice) {
            for(CartItem cartItem: cart) {
                cartItem.purchase();
            }
            displayReceipt(totalPrice, payment);
            cart.clear();
        }
        else {
            System.out.println("[오류]결제에 실패하였습니다.\n");
        }
    }

    private void displayReceipt(int totalPrice, int payment) {
        int change = payment - totalPrice;

        System.out.println("\n결제가 완료되었습니다. 영수증을 출력합니다.");
        System.out.println("====================================");
        System.out.println("구매내역");
        for(CartItem cartItem: cart) {
            cartItem.printCartItem();
        }
        System.out.println("------------------------------------");
        System.out.println("결제내역");
        System.out.println("총 금액:                  " + totalPrice + "원");
        System.out.println("지불금액:                  " + payment + "원");
        System.out.println("거스름돈:                  " + change + "원");
        System.out.println("====================================");
        System.out.println("감사합니다! 주문하신 상품은 3일 이내에 발송됩니다.\n");
    }

    private void exit() {
        System.out.println("\n감사합니다. 또 오세요!");
        System.out.println("-"+ this.name +"-");
    }

    public void start(){
        Thread thisThread = Thread.currentThread();
        TimeOutThread timer = new TimeOutThread(timeout, thisThread);

        // 메인화면 접속
        while (true) {
            int choice = displayMainMenu(timer);
            if(choice == 1){
                displayProductMenu(timer);
            }
            else if(choice == 2){
                displayCart(timer);
            }
            else if(choice == 3){
                exit();
                break;
            }
        }
    }
}
