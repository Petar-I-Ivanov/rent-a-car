package com.rent.car.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rent.car.services.ClientService;
import com.rent.car.services.ContactService;
import com.rent.car.services.CountryService;
import com.rent.car.services.EmployeeService;
import com.rent.car.services.InvoiceService;
import com.rent.car.services.InvoiceStatusService;
import com.rent.car.services.JobTitleService;
import com.rent.car.services.LocationService;
import com.rent.car.services.StateService;
import com.rent.car.services.SupplierService;
import com.rent.car.services.VehicleHireService;
import com.rent.car.services.VehicleMaintenanceService;
import com.rent.car.services.VehicleMakeService;
import com.rent.car.services.VehicleModelService;
import com.rent.car.services.VehicleMovementService;
import com.rent.car.services.VehicleService;
import com.rent.car.services.VehicleStatusService;
import com.rent.car.services.VehicleTypeService;

@Controller
public class SearchBarController {
	
	@Autowired private ClientService clientService;
	@Autowired private ContactService contactService;
	@Autowired private CountryService countryService;
	@Autowired private EmployeeService employeeService;
	@Autowired private InvoiceService invoiceService;
	@Autowired private InvoiceStatusService invoiceStatusService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private LocationService locationService;
	@Autowired private StateService stateService;
	@Autowired private SupplierService supplierService;
	@Autowired private VehicleHireService vehicleHireService;
	@Autowired private VehicleMaintenanceService vehicleMaintenanceService;
	@Autowired private VehicleMakeService vehicleMakeService;
	@Autowired private VehicleModelService vehicleModelService;
	@Autowired private VehicleMovementService vehicleMovementService;
	@Autowired private VehicleService vehicleService;
	@Autowired private VehicleStatusService vehicleStatusService;
	@Autowired private VehicleTypeService vehicleTypeService;

	@PreAuthorize("hasAuthority('Client')")
	@PostMapping("/client/search")
	public String clientSearchAction(String search, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("keyword", search);
		
		String modelName = (vehicleService.getVehicles(search).size() != 0)
				? "vehicles"
				: (invoiceService.getInvoices(search).size() != 0)
				? "invoices"
				: "error";
		
		return "redirect:/" + modelName;
	}
	
	@PreAuthorize("hasAuthority('Human Resource')")
	@PostMapping("/hr/search")
	public String humanResourceSearchAction(String search, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("keyword", search);
		
		String modelName =
				(vehicleService.getVehicles(search).size() != 0) ? "vehicles"
			  : (invoiceService.getInvoices(search).size() != 0) ? "invoices"
			  : (clientService.getClients(search).size() != 0) ? "clients"
			  : (contactService.getContacts(search).size() != 0) ? "contacts"
			  : (employeeService.getEmployees(search).size() != 0) ? "employees"
			  : (supplierService.getSuppliers(search).size() != 0) ? "suppliers"
			  : (vehicleHireService.getVehicleHires(search).size() != 0) ? "vehicleHires"
			  : "error";
		
		return "redirect:/" + modelName;
	}
	
	@PreAuthorize("hasAnyAuthority('Manager', 'Admin', 'Super Admin')")
	@PostMapping("/search")
	public String searchAction(String search, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("keyword", search);
		
		String modelName =
				(vehicleService.getVehicles(search).size() != 0) ? "vehicles"
			  : (invoiceService.getInvoices(search).size() != 0) ? "invoices"
			  : (clientService.getClients(search).size() != 0) ? "clients"
			  : (contactService.getContacts(search).size() != 0) ? "contacts"
			  : (countryService.getCountries(search).size() != 0) ? "countries"
			  : (employeeService.getEmployees(search).size() != 0) ? "employees"
			  : (invoiceStatusService.getInvoiceStatuses(search).size() != 0) ? "invoiceStatuses"
			  : (jobTitleService.getJobTitles(search).size() != 0) ? "jobTitles"
			  : (locationService.getLocations(search).size() != 0) ? "locations"
			  : (stateService.getStates(search).size() != 0) ? "states"
			  : (supplierService.getSuppliers(search).size() != 0) ? "suppliers"
			  : (vehicleHireService.getVehicleHires(search).size() != 0) ? "vehicleHires"
			  : (vehicleMaintenanceService.getVehicleMaintenances(search).size() != 0) ? "vehicleMaintenances"
			  : (vehicleMakeService.getVehicleMake(search).size() != 0) ? "vehicleMakes"
			  : (vehicleModelService.getVehicleModels(search).size() != 0) ? "vehicleModels"
			  : (vehicleMovementService.getVehicleMovements(search).size() != 0) ? "vehicleMovements"
			  : (vehicleStatusService.getVehicleStatuses(search).size() != 0) ? "vehicleStatuses"
			  : (vehicleTypeService.getVehicleTypes(search).size() != 0) ? "vehicleTypes"
			  : "error";
		
		return "redirect:/" + modelName;
	}
}
