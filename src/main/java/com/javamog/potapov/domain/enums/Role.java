package com.javamog.potapov.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum  Role implements GrantedAuthority {
    EMPLOYEE, MANAGER, ENGINEER;

    @Override
    public String getAuthority() {
        return name();
    }
}