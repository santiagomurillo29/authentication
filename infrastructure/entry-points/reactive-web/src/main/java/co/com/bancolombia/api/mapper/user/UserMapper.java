package co.com.bancolombia.api.mapper.user;

import co.com.bancolombia.api.dto.request.user.UserRequestDto;
import co.com.bancolombia.api.dto.response.user.UserResponseDto;
import co.com.bancolombia.model.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role.idRole", source = "idRole")
    @Mapping(target = "idUser", ignore = true)
    UserModel toModelUser(UserRequestDto userRequestDto);

    @Mapping(target = "nameRole", source = "role.nameRole")
    @Mapping(target = "idRole", source = "role.idRole")
    UserResponseDto toDtoUser(UserModel userModel);
}
