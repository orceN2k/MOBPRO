package ch.hslu.appe.fs15.g10.appemobile.dto;

import java.util.Date;
import java.util.List;


public final class OrderDto {

    private int id;
    private CustomerDto customer;
    private List<OrderItemDto> orderItems;
    private Date deliveryDate;
    private int status;
    private String salesPerson;
    private double totalAmount;

    public OrderDto() {
    }

    public OrderDto(int id, CustomerDto customer) {
        this.id = id;
        this.customer = customer;
    }

    public OrderDto(int id, CustomerDto customer, List<OrderItemDto> orderItems) {
        this(id, customer);
        this.orderItems = orderItems;
    }

    public OrderDto(int id, CustomerDto customer, Date deliveryDate, int status) {
        this.id = id;
        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public OrderDto(int id, CustomerDto customer, List<OrderItemDto> orderItems, Date deliveryDate, int status) {
        this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public OrderDto(int id, CustomerDto customer, Date deliveryDate, int status, String salesPerson, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.salesPerson = salesPerson;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDto customer) {
        this.customer = customer;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(final double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(final String salesPerson) {
        this.salesPerson = salesPerson;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(final Date deliveryDate){
        this.deliveryDate=deliveryDate;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(final List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderDto other = (OrderDto) obj;
        return (this.id == other.id);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
