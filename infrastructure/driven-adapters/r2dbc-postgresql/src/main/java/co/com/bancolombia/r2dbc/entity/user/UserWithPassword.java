package co.com.bancolombia.r2dbc.entity.login;

import co.com.bancolombia.r2dbc.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithPassword {
    private UserEntity userEntity;
    private String passwordHash;
    private String roleName;
}
