package nevt.repositories.account;

import java.util.Optional;

import nevt.models.account.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
