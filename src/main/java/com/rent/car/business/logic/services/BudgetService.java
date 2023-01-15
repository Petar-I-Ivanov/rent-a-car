package com.rent.car.business.logic.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.car.business.logic.models.Budget;
import com.rent.car.business.logic.repositories.BudgetRepository;

@Service
public class BudgetService {

	@Autowired private BudgetRepository budgetRepository;
	
	public List<Budget> getBudgets() {
		return budgetRepository.findAll();
	}
	
	public Optional<Budget> findById(int id) {
		return budgetRepository.findById(id);
	}
	
	public void delete (int id) {
		budgetRepository.deleteById(id);
	}
	
	public void save (Budget budget) {
		budgetRepository.save(budget);
	}
	
	public Budget findByActionAndActionId(String action, int actionId) {
		return budgetRepository.findByActionAndActionId(action, actionId).orElse(null);
	}
	
	public double getOverall(boolean isIncome) {
		
		String action = isIncome ? "hire" : "maintenance";
		double sum = 0.0;
		
		for (Budget budget : budgetRepository.findAll()) {
			if (budget.getAction().equals(action)) {
				sum += budget.getPrice();
			}
		}
		
		return sum;
	}
	
	public double getLastPeriod(boolean isIncome, String period) {
		
		String action = isIncome ? "hire" : "maintenance";
		
		int periodEnum = (period.equals("year"))
						? Calendar.YEAR
						: (period.equals("month"))
						? Calendar.MONTH
						: Calendar.DAY_OF_MONTH;
		
		double sum = 0.0;
		
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(periodEnum, -1);
        
        Date onePeriodAgo = cal.getTime();
		
		for (Budget budget : budgetRepository.findAll()) {
			if (budget.getAction().equals(action) &&
				budget.getDate().after(onePeriodAgo)) {
				sum += budget.getPrice();
			}
		}
		
		return sum;
	}
}
