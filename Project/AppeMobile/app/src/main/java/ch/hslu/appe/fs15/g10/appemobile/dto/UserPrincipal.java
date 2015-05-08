package ch.hslu.appe.fs15.g10.appemobile.dto;
import java.security.Principal;
import java.util.List;

/**
 * Created by Simon on 28.03.2015.
 */
public class UserPrincipal implements Principal {

    private String name;
    private List<String> roles;

    /**
     * used for json serialization.
     */
    public UserPrincipal() {
    }

    public UserPrincipal(String name, List<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }
}
