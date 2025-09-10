package co.com.bancolombia.r2dbc.adapter.user;

import co.com.bancolombia.authsecurity.model.auth.UserAuth;
import co.com.bancolombia.r2dbc.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserAuthAdapter implements UserAuth {

    @Getter
    private final UserEntity userEntity;
    private final String passwordHash;
    private final String roleName;

    @Override
    public String getUsername() {
        return userEntity.getEmailUser();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public Collection<String> getRoles() {
        return List.of(roleName);
    }
}
