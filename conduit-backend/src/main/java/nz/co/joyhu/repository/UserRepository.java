package nz.co.joyhu.repository;

import nz.co.joyhu.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
