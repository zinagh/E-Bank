package com.mfbank.repository;

import com.mfbank.model.Static;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticRepository extends JpaRepository<Static,String> {
}
