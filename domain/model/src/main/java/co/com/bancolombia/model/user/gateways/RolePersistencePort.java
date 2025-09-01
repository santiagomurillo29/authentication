package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.user.model.RoleModel;
import reactor.core.publisher.Mono;

public interface RolePersistencePort {
    Mono<RoleModel> findRoleById(Long idRole);
}
