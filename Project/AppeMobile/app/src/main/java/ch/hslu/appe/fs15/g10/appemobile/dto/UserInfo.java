package ch.hslu.appe.fs15.g10.appemobile.dto;

/**
 * UserInfo is used as Dto.
 */
public class UserInfo {
    private String user;
    private String password;

    /**
     * Used for json serialization.
     */
    public UserInfo() {

    }

    /**
     * Creates userinfo obj.
     *
     * @param user     username.
     * @param password password.
     */
    public UserInfo(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    /**
     * Gets the username.
     *
     * @return username.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username.
     *
     * @param user username.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Returns the password.
     *
     * @return password password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
