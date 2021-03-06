package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "role", uniqueConstraints={@UniqueConstraint(columnNames={"user_role"})})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleID;
    @NotNull
    @Column(name = "user_role")
    private String userRole;

    public Role() {
    }

    public Role(String userRole) {
        this.userRole = userRole;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleID=" + roleID +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
