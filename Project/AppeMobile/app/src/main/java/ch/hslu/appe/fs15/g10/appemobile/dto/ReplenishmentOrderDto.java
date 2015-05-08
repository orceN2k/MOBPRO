package ch.hslu.appe.fs15.g10.appemobile.dto;


import java.util.Date;

/**
 * Replenishment Order Dto for the communication between the layers.
 */
public final class ReplenishmentOrderDto {
    private ArticleDto article;
    private OrderItemDto orderItem;
    private int id;
    private int centerReservationId;
    private long orderedQuantity;
    private Date deliveryDate;
    private int status;

    /**
     * Default constructor.
     */
    public ReplenishmentOrderDto() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id                  of the order.
     * @param centerReservationId provided by the center.
     * @param orderedQuantity     to be ordered.
     * @param article             to be ordered.
     * @param orderItem           causing the replenishment order.
     * @param deliveryDate        provided by the center.
     * @param status              of the order.
     */
    public ReplenishmentOrderDto(final int id, final int centerReservationId, final long orderedQuantity, final ArticleDto article, final OrderItemDto orderItem, final Date deliveryDate, final int status) {
        this.id = id;
        this.centerReservationId = centerReservationId;
        this.orderedQuantity = orderedQuantity;
        this.article = article;
        this.orderItem = orderItem;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    /**
     * Gets the id.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order id.
     *
     * @param id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the Center reservation id.
     *
     * @return id provided by the center.
     */
    public int getCenterReservationId() {
        return centerReservationId;
    }

    /**
     * Sets the id provided by the center.
     *
     * @param centerReservationId of the center.
     */
    public void setCenterReservationId(int centerReservationId) {
        this.centerReservationId = centerReservationId;
    }

    /**
     * Gets the ordered quantity.
     *
     * @return ordered quantity.
     */
    public long getOrderedQuantity() {
        return orderedQuantity;
    }

    /**
     * Sets the current ordered quantity.
     *
     * @param orderedQuantity to be set.
     */
    public void setOrderedQuantity(long orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    /**
     * Gets the current {@link ArticleDto}.
     *
     * @return instance of {@link ArticleDto}.
     */
    public ArticleDto getArticle() {
        return article;
    }

    /**
     * Sets the current {@link ArticleDto}.
     *
     * @param article to be set.
     */
    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    /**
     * Gets the current {@link OrderItemDto}.
     *
     * @return instance of {@link OrderItemDto}.
     */
    public OrderItemDto getOrderItem() {
        return orderItem;
    }

    /**
     * Sets the current {@link OrderItemDto}.
     *
     * @param orderItem to be set.
     */
    public void setOrderItem(OrderItemDto orderItem) {
        this.orderItem = orderItem;
    }

    /**
     * Gets the current delivery date of the order.
     *
     * @return the delivery date.
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the current delivery date.
     *
     * @param deliveryDate to be set.
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Gets the Status of the order.
     *
     * @return Status represented as an integer.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the current state of the order.
     *
     * @param status to be set.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReplenishmentOrderDto other = (ReplenishmentOrderDto) obj;
        return (this.id == other.id);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return this.id;
    }
}
