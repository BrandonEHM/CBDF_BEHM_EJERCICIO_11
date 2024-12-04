package com.upiiz.securitydb.service;


import com.upiiz.securitydb.entities.UserEntity;
import com.upiiz.securitydb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Requerimos consutar el usuario en la base de datos por su nombre de usuario con todos sus detalles
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + ": not found"));

        // En userEnitiy = sus roles, permisos, etc.
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Iteramos sobre los roles del usuario
        userEntity.getRoles().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRolEnum().name()));
        });

        // Permisos
        userEntity.getRoles().stream()
                .flatMap(rol -> rol.getPermissions().stream())
                .forEach(permission -> {
                    authorities.add(new SimpleGrantedAuthority(permission.getName()));
                });

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnable(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities);
    }
}
