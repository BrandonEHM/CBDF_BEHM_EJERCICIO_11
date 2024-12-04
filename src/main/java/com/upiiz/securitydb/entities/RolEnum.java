package com.upiiz.securitydb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum RolEnum {
    ADMIN,
    GUEST,
    USER,
    DEVELOPER
}

