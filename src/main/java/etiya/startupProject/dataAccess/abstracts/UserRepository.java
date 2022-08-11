package etiya.startupProject.dataAccess.abstracts;

import etiya.startupProject.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
