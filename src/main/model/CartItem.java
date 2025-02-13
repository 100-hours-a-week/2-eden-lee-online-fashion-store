package model;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void printCartItem() {
        int productId = product.getId();
        String productName = product.getName();
        int productPrice = product.getPrice();

        String output = String.format("%d  %s  %d X %d  %d", productId, productName, productPrice, quantity, getTotalPrice());
        System.out.println(output);
    }

    public void purchase() {
        product.purchase(quantity);
    }
}
