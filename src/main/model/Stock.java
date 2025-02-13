package model;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    // 의류
    private List<Top> tops;
    private List<Bottom> bottoms;
    // 잡화류
    private List<Bag> bags;
    private List<Hat> hats;
    private List<Shoes> shoes;

    public Stock(String path) {
        // TODO: path 경로에 있는 데이터에서 재고 업로드
        tops = new ArrayList<>();
        bottoms = new ArrayList<>();
        bags = new ArrayList<>();
        hats = new ArrayList<>();
        shoes = new ArrayList<>();

        tops.add(new Top("오버핏 폭삭 니트", 23000, 10, "M", "블랙", "니트, 울", "롱슬리브"));
        tops.add(new Top("큐티 곰돌이 티셔츠", 15000, 15, "S", "화이트", "면", "숏슬리브"));

        bottoms.add(new Bottom("와이드 빈티지 진스", 34500, 3, "M", "진청", "데님, 폴리에스터", 28));
        bottoms.add(new Bottom("와이드 빈티지 진스", 34500, 5, "L", "진청", "데님, 폴리에스터", 28));

        bags.add(new Bag("미니멀 포켓 숄더백", 37000, 10, "가방", "블랙", "조절가능", 3));
        hats.add(new Hat("빅로고 니트 비니", 20000, 5, "모자", "화이트", "가을, 겨울"));
        hats.add(new Hat("남여공용 심플 볼캡", 15000, 18, "모자", "블루", "사계절"));
    }

    public void printClothes() {
        int totalCount = tops.size() + bottoms.size();
        System.out.println(String.format("\n의류 제품 리스트 (총 %d건)", totalCount));
        System.out.println("-------------------------------------");
        System.out.println("제품번호    제품명    가격   사이즈  남은수량");

        for (Top top : tops) {
            String output = String.format("%d  %s  %d원  %s  %d개", top.id, top.name, top.price, top.size, top.stock);
            System.out.println(output);
        }
        for (Bottom bottom : bottoms) {
            String output = String.format("%d  %s  %d원  %s  %d개", bottom.id, bottom.name, bottom.price, bottom.size, bottom.stock);
            System.out.println(output);
        }
        System.out.println("-------------------------------------");
    }

    public void printAccessories() {
        int totalCount = bags.size() + hats.size() + shoes.size();
        System.out.println(String.format("\n잡화 제품 리스트 (총 %d건)", totalCount));
        System.out.println("-------------------------------------");
        System.out.println("제품번호    제품명    가격   유형   남은수량");
        for (Bag bag : bags) {
            String output = String.format("%d  %s  %d원  %s  %d개", bag.id, bag.name, bag.price, bag.type, bag.stock);
            System.out.println(output);
        }
        for (Hat hat : hats) {
            String output = String.format("%d  %s  %d원  %s  %d개", hat.id, hat.name, hat.price, hat.type, hat.stock);
            System.out.println(output);
        }
        for (Shoes shoe : shoes) {
            String output = String.format("%d  %s  %d원  %s  %d개", shoe.id, shoe.name, shoe.price, shoe.type, shoe.stock);
            System.out.println(output);
        }
        System.out.println("-------------------------------------");
    }

    public List<Integer> getAllClothesId() {
        List<Integer> clothesIds = new ArrayList<>();
        for (Top top : tops) {
            clothesIds.add(top.id);
        }
        for (Bottom bottom : bottoms) {
            clothesIds.add(bottom.id);
        }
        return clothesIds;
    }

    public List<Integer> getAllAccessoriesId() {
        List<Integer> accessoriesIds = new ArrayList<>();
        for (Bag bag : bags) {
            accessoriesIds.add(bag.id);
        }
        for (Hat hat : hats) {
            accessoriesIds.add(hat.id);
        }
        for (Shoes shoe : shoes) {
            accessoriesIds.add(shoe.id);
        }
        return accessoriesIds;
    }

    public void printProductDetails(int productId) {
        for (Top top : tops) {
            if (top.id == productId) {
                top.printDetails();
            }
        }
        for (Bottom bottom : bottoms) {
            if (bottom.id == productId) {
                bottom.printDetails();
            }
        }
        for (Bag bag : bags) {
            if (bag.id == productId) {
                bag.printDetails();
            }
        }
        for (Hat hat : hats) {
            if (hat.id == productId) {
                hat.printDetails();
            }
        }
        for (Shoes shoe : shoes) {
            if (shoe.id == productId) {
                shoe.printDetails();
            }
        }
    }

    public Product getProductById(int productId) {
        for (Top top : tops) {
            if (top.id == productId) {
                return top;
            }
        }
        for (Bottom bottom : bottoms) {
            if (bottom.id == productId) {
                return bottom;
            }
        }
        for (Bag bag : bags) {
            if (bag.id == productId) {
                return bag;
            }
        }
        for (Hat hat : hats) {
            if (hat.id == productId) {
                return hat;
            }
        }
        for (Shoes shoe : shoes) {
            if (shoe.id == productId) {
                return shoe;
            }
        }
        return null;
    }
}
