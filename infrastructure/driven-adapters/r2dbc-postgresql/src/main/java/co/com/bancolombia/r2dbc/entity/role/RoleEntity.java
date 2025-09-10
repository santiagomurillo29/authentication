package co.com.bancolombia.r2dbc.entity.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("role")
public class RoleEntity {
    @Id
    @Column("id_role")
    private Long idRole;

    @Column("name_role")
    private String nameRole;

    @Column("description_role")
    private String descriptionRole;
}
