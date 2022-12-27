package springwithmysql.com.springmysql.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springwithmysql.com.springmysql.Models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
