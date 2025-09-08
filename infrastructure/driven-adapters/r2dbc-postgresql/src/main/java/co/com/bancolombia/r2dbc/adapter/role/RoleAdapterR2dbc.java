package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.user.gateways.RolePersistencePort;
import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.RoleMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class RoleAdapterR2dbc implements RolePersistencePort {

    private final RoleRepository roleRepository;
    private final RoleMapperR2dbc roleMapperR2dbc;
    private final R2dbcSafeExecutor r2dbcSafeExecutor;

    @Override
    public Mono<RoleModel> findRoleById(Long idRole) {
        return r2dbcSafeExecutor.executeMono(() ->
                roleRepository.findById(idRole)
                        .doOnSubscribe(sub -> log.info("Checking find role of id: {}", idRole))
                        .map(roleMapperR2dbc::toModelRole)
                        .doOnSuccess(found -> log.info("The role found: {}", found))
                        .doOnError(e -> log.error("Error finding role by id {}: {}", idRole, e.getMessage()))
        );
    }

}
