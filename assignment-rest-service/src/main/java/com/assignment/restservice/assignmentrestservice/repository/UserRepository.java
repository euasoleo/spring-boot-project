package com.assignment.restservice.assignmentrestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.restservice.assignmentrestservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findBySalaryGreaterThanEqualAndSalaryLessThanEqual(double lowerBound,double upperBound);
}
