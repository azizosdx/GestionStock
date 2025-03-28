package meddeb.aziz.itbs.repository;

import meddeb.aziz.itbs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
