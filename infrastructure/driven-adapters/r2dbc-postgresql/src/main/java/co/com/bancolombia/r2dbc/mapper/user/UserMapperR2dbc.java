package co.com.bancolombia.r2dbc.mapper;

import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",  uses = {RoleMapperR2dbc.class})
public interface UserMapperR2dbc {

    @Mapping(target = "idRole", source = "role.idRole")
    UserEntity toEntityUser(UserModel userModel);

    @Mapping(target = "role", source = "idRole")
    UserModel toModelUser(UserEntity userEntity);

    default RoleModel mapToRole(Long id) { return id == null ? null : new RoleModel(id); }
}
