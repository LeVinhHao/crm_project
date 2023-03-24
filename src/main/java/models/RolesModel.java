package models;

public class RolesModel {
    private int id;
    private String roleName;
    private String roleDecription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDecription() {
        return roleDecription;
    }

    public void setRoleDecription(String roleDecription) {
        this.roleDecription = roleDecription;
    }
}
