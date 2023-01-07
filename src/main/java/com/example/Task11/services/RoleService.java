package com.example.Task11.services;

import com.example.Task11.models.Role;
import com.example.Task11.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> showRoles() {
        return roleRepository.findAll();
    }
}
