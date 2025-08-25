package co.com.bancolombia.api.mapper;

import co.com.bancolombia.api.dto.request.UserRequestDto;
import co.com.bancolombia.api.dto.response.UserResponseDto;
import co.com.bancolombia.model.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "idUser", ignore = true)
    UserModel toModelUser(UserRequestDto userRequestDto);
    UserResponseDto toDtoUser(UserModel userModel);
}
