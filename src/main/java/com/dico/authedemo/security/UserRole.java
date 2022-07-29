package com.dico.authedemo.security;


import com.google.common.collect.Sets;

import java.util.Set;

import static com.dico.authedemo.security.UserPermission.*;

public enum UserRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE));

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
