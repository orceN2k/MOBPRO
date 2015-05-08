package ch.hslu.appe.fs15.g10.appemobile.dto;
/**
 * Customer Dto for the communication between the layers.
 */
public class CustomerDto {
    private int id;
    private String name;

    /**
     * Default Constructor.
     */
    public CustomerDto() {
    }

    /**
     * Overloaded Constructor.
     * @param id of the customer.
     * @param name of the customer.
     */
    public CustomerDto(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the current id.
     * @return id of the customer.
     */
    public final int getId() {
        return id;
    }

    /**
     * Sets the current id.
     * @param id to be set.
     */
    public final void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the current name.
     * @return name of the customer.
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the current name.
     * @param name to be set.
     */
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerDto other = (CustomerDto) obj;
        return (this.id == other.id);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public String toString() {
        return getId() + " " + getName();
    }
}
