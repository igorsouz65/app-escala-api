package igor.escalaspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
