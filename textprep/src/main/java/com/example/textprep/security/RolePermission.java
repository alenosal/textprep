package com.example.textprep.security;

public enum RolePermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    RolePermission( String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
