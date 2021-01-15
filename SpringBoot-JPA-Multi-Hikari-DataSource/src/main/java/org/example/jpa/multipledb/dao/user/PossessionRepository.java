package org.example.jpa.multipledb.dao.user;


import org.example.jpa.multipledb.model.user.Possession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PossessionRepository extends JpaRepository<Possession, Long> {

}
