package ru.ash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ash.controller.NotFoundException;
import ru.ash.controller.RoleDto;

import ru.ash.persist.RoleRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(Long id) {
        return roleRepository.findById(id)
                .map(role -> new RoleDto(role.getId(), role.getName()));
    }

    @Override
    public RoleDto getDefaultRole() {
        return findById(2L).orElseThrow(() -> new NotFoundException("User default not found"));
    }
}
