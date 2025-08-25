package co.com.bancolombia.r2dbc.mapper;

import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperR2dbc {

    UserEntity toEntityUser(UserModel userModel);
    UserModel toModelUser(UserEntity userEntity);
}
