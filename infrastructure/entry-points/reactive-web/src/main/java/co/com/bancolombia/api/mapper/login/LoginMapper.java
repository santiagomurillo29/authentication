package co.com.bancolombia.api.mapper.login;

import co.com.bancolombia.api.dto.request.login.LoginRequestDto;
import co.com.bancolombia.api.dto.response.login.LoginResponseDto;
import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginRequest toModelLogin(LoginRequestDto loginRequestDto);
    LoginResponseDto toDtoLogin (LoginResponse loginResponse);
}
