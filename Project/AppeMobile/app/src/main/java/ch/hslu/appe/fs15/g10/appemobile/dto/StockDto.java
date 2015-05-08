package ch.hslu.appe.fs15.g10.appemobile.dto;

public class StockDto {

    private int id;
    private ArticleDto article;
    private long stockQuantity;
    private long minStockQuantity;

    public StockDto() {
    }

    public StockDto(final int id, final ArticleDto article, final long stockQuantity, final long minStockQuantity) {
        this.id = id;
        this.article = article;
        this.stockQuantity = stockQuantity;
        this.minStockQuantity = minStockQuantity;
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

    public final long getStockQuantity() {
        return stockQuantity;
    }

    public final void setStockQuantity(final long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public final long getMinStockQuantity() {
        return minStockQuantity;
    }

    public final void setMinStockQuantity(final long minStockQuantity) {
        this.minStockQuantity = minStockQuantity;
    }

}
