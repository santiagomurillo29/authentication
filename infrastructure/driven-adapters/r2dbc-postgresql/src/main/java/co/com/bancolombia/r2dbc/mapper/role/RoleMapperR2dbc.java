package co.com.bancolombia.r2dbc.mapper;

import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.r2dbc.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapperR2dbc {
    RoleEntity toEntityRole(RoleModel roleModel);
    RoleModel toModelRole(RoleEntity roleEntity);
}
