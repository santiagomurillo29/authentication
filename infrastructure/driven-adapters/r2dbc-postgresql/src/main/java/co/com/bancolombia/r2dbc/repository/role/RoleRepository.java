package co.com.bancolombia.r2dbc.repository.role;

import co.com.bancolombia.r2dbc.entity.role.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {
}
