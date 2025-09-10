package co.com.bancolombia.authsecurity.model.auth;

import java.util.Collection;

public interface UserAuth {
    String getUsername();
    String getPassword();
    Collection<String> getRoles();
}
