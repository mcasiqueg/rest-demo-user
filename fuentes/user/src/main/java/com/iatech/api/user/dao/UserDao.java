package com.iatech.api.user.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iatech.api.user.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

	@Query("SELECT c FROM User c " +
            "WHERE UPPER(c.documentNumber) LIKE CONCAT('%',UPPER(:query),'%')" +
            "OR UPPER(c.name) LIKE CONCAT('%',UPPER(:query),'%')" +
            "OR UPPER(c.surname) LIKE CONCAT('%',UPPER(:query),'%')")
    List<User> searchQuery(@Param("query") String query);
	
}