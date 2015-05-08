package ch.hslu.appe.fs15.g10.appemobile.dto;

//import org.codehaus.jackson.annotate.JsonIgnore;

import java.security.Principal;

/**
 * The Security Context implementation used for Authorization.
 */
public class RequestSecurityContext  {

    private static final String AUTHSCHEME = "BASIC";
    private UserPrincipal userPrincipal;
    private boolean secure;

    public RequestSecurityContext() {
    }

    public RequestSecurityContext(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    /**
     * Returns the User Principal.
     * @return Principal current User.
     */
    public Principal getUserPrincipal() {
        return userPrincipal;
    }


    public boolean isUserInRole(String s) {
        if (userPrincipal.getRoles() == null)
            return false;

        for(String role :userPrincipal.getRoles()) {
            if(role.toLowerCase().equals(s.toLowerCase())){
                return  true;
            }
        }
        return false;
    }

    public boolean isSecure() {
        // it is secure otherwise there is no security context !
        return secure;
    }



    public String getAuthenticationScheme() {
        return AUTHSCHEME;
    }
}
