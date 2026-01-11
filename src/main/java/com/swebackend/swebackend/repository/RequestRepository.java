package com.swebackend.swebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.swebackend.swebackend.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {}
