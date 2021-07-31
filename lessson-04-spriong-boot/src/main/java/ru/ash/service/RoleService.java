package ru.ash.service;
import org.springframework.stereotype.Component;
import ru.ash.controller.RoleDto;

import java.util.List;
import java.util.Optional;

@Component
public interface RoleService {

    List<RoleDto> findAll();

    Optional<RoleDto> findById(Long id);

    RoleDto getDefaultRole();

}
