package com.rent.car.business.logic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rent.car.business.logic.models.Budget;
import com.rent.car.business.logic.services.BudgetService;
import com.rent.car.models.VehicleHire;
import com.rent.car.models.VehicleMaintenance;

@Component
public class BudgetAction {
	
	@Autowired private BudgetService budgetService;
	
	public void makeHireAction(VehicleHire hire) {	
		budgetService.save(setBudget(hire, null));
	}
	
	public void makeMaintenanceAction (VehicleMaintenance maintenance) {
		budgetService.save(setBudget(null, maintenance));
	}

	//	first check with witch entity it is working with (hire or maintenance)
	//	checks if DB have record with that Action and Id
	//	if so it is returned, else it is created new record with filled data
	private Budget setBudget(VehicleHire hire, VehicleMaintenance maintenance) {
		
		if (hire != null) {
			
			Budget budget = budgetService.findByActionAndActionId("hire", hire.getId());
			
			return (budget == null)
					? new Budget(0, Double.parseDouble(hire.getPrice()), new Date(), "hire", hire.getId())
					: budget;
		}
		
		Budget budget = budgetService.findByActionAndActionId("maintenance", maintenance.getId());
		
		return (budget == null)
				? new Budget(0, Double.parseDouble(maintenance.getPrice()), new Date(), "maintenance", maintenance.getId())
				: budget;
	}
}
