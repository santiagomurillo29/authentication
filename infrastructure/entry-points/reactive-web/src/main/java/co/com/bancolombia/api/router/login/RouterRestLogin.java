package co.com.bancolombia.api.router.login;

import co.com.bancolombia.api.dto.request.login.LoginRequestDto;
import co.com.bancolombia.api.dto.response.login.LoginResponseDto;
import co.com.bancolombia.api.handler.login.HandlerLogin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestLogin {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/login",
                    method = RequestMethod.POST,
                    beanClass = HandlerLogin.class,
                    beanMethod = "Login",
                    consumes = "application/json",
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "login",
                            summary = "Login",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = "Login",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "Login",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LoginResponseDto.class))
                                    ),
                                    @ApiResponse(responseCode = "409", description = "User already exists")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunctionLogin(HandlerLogin handlerLogin) {
        return route(POST("/api/v1/login"), handlerLogin::Login);
    }
}
