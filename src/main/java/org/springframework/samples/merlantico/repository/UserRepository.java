package org.springframework.samples.merlantico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.samples.merlantico.model.User;


public interface UserRepository extends  JpaRepository<User, String>{
	
	@Query("select u from User u where u.username like %?1")
	User findByUsernameLike(String username);
	
}
