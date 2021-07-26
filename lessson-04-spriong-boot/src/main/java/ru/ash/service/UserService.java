package ru.ash.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.ash.controller.UserDto;
import ru.ash.controller.UserListParams;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserListParams userListParams);

    Optional<UserDto> findById(Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
