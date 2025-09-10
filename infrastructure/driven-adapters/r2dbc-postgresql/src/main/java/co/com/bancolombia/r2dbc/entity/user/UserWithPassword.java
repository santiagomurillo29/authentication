package co.com.bancolombia.r2dbc.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPassword {
    private UserEntity userEntity;
    private String passwordHash;
    private String roleName;
}
