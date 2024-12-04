package com.upiiz.securitydb;

import com.upiiz.securitydb.entities.PermissionEntity;
import com.upiiz.securitydb.entities.RolEntity;
import com.upiiz.securitydb.entities.RolEnum;
import com.upiiz.securitydb.entities.UserEntity;
import com.upiiz.securitydb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecuritydbApplication {

	public static void main(String[] args) {

		SpringApplication.run(SecuritydbApplication.class, args);
	}
	@Bean
	CommandLineRunner init( UserRepository userRepository){
		return args -> {
			//CEAR PERSMISOS

			PermissionEntity createPermision = PermissionEntity
					.builder()
					.name("CREATE")
					.build();  // Usar build() en lugar de buld()

			PermissionEntity deletePermision = PermissionEntity
					.builder()
					.name("DELETE")
					.build();  // Usar build() en lugar de buld()

			PermissionEntity updatePermision = PermissionEntity
					.builder()
					.name("UPDATE")
					.build();  // Usar build() en lugar de buld()

			PermissionEntity readPermision = PermissionEntity
					.builder()
					.name("READ")
					.build();  // Usar build() en lugar de buld()

			RolEntity addminRol= RolEntity
					.builder()
					.rolEnum(RolEnum.ADMIN)
					.permissions(Set.of(createPermision,deletePermision,updatePermision,readPermision))
					.build();


			RolEntity guestRol= RolEntity
					.builder()
					.rolEnum(RolEnum.GUEST)
					.permissions(Set.of(readPermision))
					.build();

			RolEntity userRol= RolEntity
					.builder()
					.rolEnum(RolEnum.USER)
					.permissions(Set.of(updatePermision,readPermision))
					.build();

			RolEntity developerRol= RolEntity
					.builder()
					.rolEnum(RolEnum.DEVELOPER)
					.permissions(Set.of(createPermision,deletePermision,updatePermision,readPermision))
					.build();


			UserEntity user1 = UserEntity
					.builder()
					.username("admin")
					.password("12345")
					.isEnable(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(addminRol))
					.build();

			UserEntity user2 = UserEntity
					.builder()
					.username("desarrollador")
					.password("12345")
					.isEnable(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(developerRol))
					.build();

			UserEntity user3 = UserEntity
					.builder()
					.username("invitado")
					.password("12345")
					.isEnable(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(guestRol))
					.build();

			UserEntity user4 = UserEntity
					.builder()
					.username("usuario")
					.password("12345")
					.isEnable(true)
					.accountNoExpired(true)
					.credentialNoExpired(true)
					.accountNoLocked(true)
					.roles(Set.of(userRol))
					.build();


			userRepository.saveAll(List.of(user1,user2,user3,user4));
		};
	}
}
