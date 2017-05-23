package ru.kolaer.permit.dto;

import java.util.Map;

/**
 * Created by Danilov on 22.05.2017.
 */
public class RoleNameDto {
    private Map<String, String> roleNameMap;

    public Map<String, String> getRoleNameMap() {
        return roleNameMap;
    }

    public void setRoleNameMap(Map<String, String> roleNameMap) {
        this.roleNameMap = roleNameMap;
    }
}
