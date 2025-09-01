package co.com.bancolombia.api.router;

import co.com.bancolombia.api.dto.request.UserRequestDto;
import co.com.bancolombia.api.dto.response.UserResponseDto;
import co.com.bancolombia.api.handler.HandlerUser;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestUser {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    method = RequestMethod.GET,
                    beanClass = HandlerUser.class,
                    beanMethod = "GetUser",
                    operation = @Operation(
                            summary = "Get all users",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "OK")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/usuario/correo",
                    method = RequestMethod.GET,
                    beanClass = HandlerUser.class,
                    beanMethod = "GetUserByEmail",
                    operation = @Operation(
                            operationId = "getUserByEmail",
                            summary = "Get user by email",
                            parameters = {
                                    @io.swagger.v3.oas.annotations.Parameter(
                                            name = "emailUser",
                                            description = "User email",
                                            required = true,
                                            example = "user@mail.com"
                                    )
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "OK")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    method = RequestMethod.POST,
                    beanClass = HandlerUser.class,
                    beanMethod = "CreateUser",
                    consumes = "application/json",
                    produces = "application/json",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Create a new user",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    description = "User to create",
                                    required = true,
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "201",
                                            description = "User created",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserResponseDto.class))
                                    ),
                                    @ApiResponse(responseCode = "409", description = "User already exists")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(HandlerUser handlerUser) {
        return route(GET("/api/v1/usuarios"), handlerUser::GetUser)
                .andRoute(POST("/api/v1/usuarios"), handlerUser::CreateUser)
                .andRoute(GET("/api/v1/usuario/correo"), handlerUser::GetUserByEmail);
    }
}
