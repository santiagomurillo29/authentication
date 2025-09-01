package co.com.bancolombia.model.user.model;

public class RoleModel {

    private Long idRole;
    private String nameRole;
    private String descriptionRole;

    public RoleModel() {
    }

    public RoleModel(Long idRole, String nameRole, String descriptionRole) {
        this.idRole = idRole;
        this.nameRole = nameRole;
        this.descriptionRole = descriptionRole;
    }

    public RoleModel(Long idRole) {
        this.idRole = idRole;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescriptionRole() {
        return descriptionRole;
    }

    public void setDescriptionRole(String descriptionRole) {
        this.descriptionRole = descriptionRole;
    }
}
