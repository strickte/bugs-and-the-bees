package org.launchcode.bugs_and_the_bees.data;

import org.launchcode.bugs_and_the_bees.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsByUsername(String username);

}
