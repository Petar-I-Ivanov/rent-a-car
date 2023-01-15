package com.rent.car.business.logic.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rent.car.business.logic.models.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

	Optional<Budget> findByActionAndActionId(String action, int actionId);
}
