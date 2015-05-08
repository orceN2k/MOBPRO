package ch.hslu.appe.fs15.g10.appemobile.dto;


public class OrderItemDto {

    private int id;
    private ArticleDto article;
    private int quantity;
    private double price;

    public OrderItemDto() {
    }

    public OrderItemDto(int id, ArticleDto article, int quantity, double price) {
        this.id = id;
        this.article = article;
        this.quantity = quantity;
        this.price = price;
    }


    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final ArticleDto getArticle() {
        return article;
    }

    public final void setArticle(final ArticleDto article) {
        this.article = article;
    }

    public final int getQuantity() {
        return quantity;
    }

    public final void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public final double getPrice() {
        return price;
    }

    public final void setPrice(final double price) {
        this.price = price;
    }

}
