package srs.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import srs.appliance.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {}
