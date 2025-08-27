package co.com.bancolombia.api.handler;

import co.com.bancolombia.api.dto.request.UserRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.mapper.UserMapper;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerUser {

    private final UserServicePort userServicePort;
    private final UserMapper userMapper;
    private final RequestValidator validator;

    public Mono<ServerResponse> CreateUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequestDto.class)
                .flatMap(validator::validate)
                .map(userMapper::toModelUser)
                .flatMap(userServicePort::createUser)
                .map(userMapper::toDtoUser)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> GetUser(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("[]");
    }
}
