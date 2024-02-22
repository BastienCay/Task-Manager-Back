package com.bastien.TaskManager.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum Role {
    EMPLOYE,
    MANAGER,
    ADMINISTRATEUR
}
