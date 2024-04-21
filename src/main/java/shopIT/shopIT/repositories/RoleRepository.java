package shopIT.shopIT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shopIT.shopIT.enums.RoleName;
import shopIT.shopIT.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByRoleName(RoleName roleName);

}
