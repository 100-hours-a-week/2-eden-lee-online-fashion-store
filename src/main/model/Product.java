package model;

public class Product {
    private static int idCounter = 100;
    protected int id;
    protected String name;
    protected int price;  // 원
    protected int stock;

    public Product(String name, int price, int stock) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void purchase(int quantity) {
        this.stock -= quantity;
    }

    public boolean checkStock(int stock) {
        if(this.stock >= stock) {
            return true;
        }
        else if(stock < 0) {
            System.out.println("최소 1개 이상의 수량을 선택해야 합니다.\n");
            return false;
        }
        else {
            System.out.println("해당 상품은 최대 "+ stock +"개 구매 가능합니다.\n");
            return false;
        }
    }
}

// 제품 -> 의류
class Clothes extends Product {
    String size;
    String color;
    String material;

    public Clothes(String name, int price, int stock, String size, String color, String material) {
        super(name, price, stock);
        this.size = size;
        this.color = color;
        this.material = material;
    }
}

class Top extends Clothes {
    String sleeveLength;

    public Top(String name, int price, int stock, String size, String color, String material, String sleeveLength) {
        super(name, price, stock, size, color, material);
        this.sleeveLength = sleeveLength;
    }

    public void printDetails() {
        System.out.println("\n["+this.name+"]의 상세정보");
        System.out.println("-----------------------");
        System.out.println("색상: "+this.color);
        System.out.println("소재: "+this.material);
        System.out.println("소매: "+this.sleeveLength);
        System.out.println("-----------------------\n");
    }
}

class Bottom extends Clothes {
    int waistSize;

    public Bottom(String name, int price, int stock, String size, String color, String material, int waistSize) {
        super(name, price, stock, size, color, material);
        this.waistSize = waistSize;
    }

    public void printDetails() {
        System.out.println("\n["+this.name+"]의 상세정보");
        System.out.println("-----------------------");
        System.out.println("색상: "+this.color);
        System.out.println("소재: "+this.material);
        System.out.println("허리둘레: "+this.waistSize);
        System.out.println("-----------------------\n");
    }

}

// 제품 -> 잡화
class Accessories extends Product {
    String type;
    String color;

    public Accessories(String name, int price, int stock, String type, String color) {
        super(name, price, stock);
        this.type = type;
        this.color = color;
    }
}

// 2차 상속 클래스 - 잡화
class Bag extends Accessories {
    String strapAdjustable;
    int pocketCount;

    public Bag(String name, int price, int stock, String type, String color, String strapAdjustable, int pocketCount) {
        super(name, price, stock, type, color);
        this.strapAdjustable = strapAdjustable;
        this.pocketCount = pocketCount;
    }

    public void printDetails() {
        System.out.println("\n["+this.name+"]의 상세정보");
        System.out.println("-----------------------");
        System.out.println("색상: "+this.color);
        System.out.println("끈 조절 여부: "+this.strapAdjustable);
        System.out.println("포켓 갯수: "+this.pocketCount);
        System.out.println("-----------------------\n");
    }
}

class Hat extends Accessories {
    String season;

    public Hat(String name, int price, int stock, String type, String color, String season) {
        super(name, price, stock, type, color);
        this.season = season;
    }

    public void printDetails() {
        System.out.println("\n["+this.name+"]의 상세정보");
        System.out.println("-----------------------");
        System.out.println("색상: "+this.color);
        System.out.println("계절감: "+this.season);
        System.out.println("-----------------------\n");
    }
}

class Shoes extends Accessories {
    int size;

    public Shoes(String name, int price, int stock, String type, String color, int size) {
        super(name, price, stock, type, color);
        this.size = size;
    }

    public void printDetails() {
        System.out.println("\n["+this.name+"]의 상세정보");
        System.out.println("-----------------------");
        System.out.println("색상: "+this.color);
        System.out.println("사이즈: "+this.size);
        System.out.println("-----------------------\n");
    }
}

