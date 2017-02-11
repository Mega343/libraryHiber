package com.nixsolutions.dao;


import com.nixsolutions.bean.Role;

import java.util.List;

public interface RoleDAO {

    public boolean add(Role role);

    public boolean edit(Role role);

    public boolean delete(Integer id);

    public Role getRole(Integer id);

    public List<Role> getRoleByName(String name);

    public List<Role> getAllRoles();
}
