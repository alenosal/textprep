package com.example.textprep.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.textprep.security.RolePermission.*;

public enum Role {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, USER_READ, USER_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,  USER_READ));

    private final Set<RolePermission> permission;

    Role(Set<RolePermission> permission) {
        this.permission = permission;
    }

    public Set<RolePermission> getPermissions() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities()
    {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permissions;
    }
}
