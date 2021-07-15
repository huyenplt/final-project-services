package vn.co.vis.restful.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.co.vis.common.dao.entity.User;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
