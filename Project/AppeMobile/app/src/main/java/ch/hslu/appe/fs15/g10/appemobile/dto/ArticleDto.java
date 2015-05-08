package ch.hslu.appe.fs15.g10.appemobile.dto;


/**
 * Created by Simon on 19.03.2015.
 */
public class ArticleDto {

    private int id;
    private String name;
    private double price;
    private long availableStock;
    private long minimalStock;

    public ArticleDto() {
    }

    public ArticleDto(final int id, final String name, final double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public final int getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return Value of price.
     */
    public final double getPrice() {
        return price;
    }

    /**
     * Sets new price.
     *
     * @param price New value of price.
     */
    public final void setPrice(final double price) {
        this.price = price;
    }

    public final long getAvailableStock() {
        return availableStock;
    }

    /**
     * Sets new availableStock.
     *
     * @param availableStock New value of availableStock.
     */
    public final void setAvailableStock(final long availableStock) {
        this.availableStock = availableStock;
    }

    /**
     * Gets availableStock.
     *
     * @return Value of availableStock.
     */
    public final long getMinimalStock() {
        return minimalStock;
    }

    /**
     * Sets new minimalStock.
     *
     * @param minimalStock New value of minimalStock.
     */
    public final void setMinimalStock(final long minimalStock) {
        this.minimalStock = minimalStock;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticleDto other = (ArticleDto) obj;
        return (this.id == other.id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getId() + " " + getName() + " " + getPrice() + " local stock: " + getAvailableStock();
    }
}
