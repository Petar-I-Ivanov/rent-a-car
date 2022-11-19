$('document').ready(function () {

	$('table #editButton').on('click', function (event) {

		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function (employee, status) {
			$('#txtUsernameEdit').val(employee.username);
			$('#txtAddressEdit').val(employee.address);
			$('#txtCityEdit').val(employee.city);
			$('#ddlNationalityEdit').val(employee.countryid);
			var dob = employee.dateOfBirth.substr(0, 10);
			$('#txtDateOfBirthEdit').val(dob);
			$('#txtEmailEdit').val(employee.email);
			$('#txtFirstnameEdit').val(employee.firstname);
			$('#ddlGenderEdit').val(employee.gender);
			$('#txtIdEdit').val(employee.id);
			$('#txtInitialsEdit').val(employee.initials);
			$('#txtLastnameEdit').val(employee.lastname);
			$('#ddlMaritalStatusEdit').val(employee.maritalStatus);
			$('#txtMobileEdit').val(employee.mobile);
			$('#txtOthernameEdit').val(employee.othername);
			$('#txtPhoneEdit').val(employee.phone);
			$('#fupPhotoEdit').val(employee.photo);
			$('#txtSSNEdit').val(employee.socialSecurityNumber);
			$('#ddlStateEdit').val(employee.stateid);
			$('#ddlTitleEdit').val(employee.title);
			$('#ddlEmployeeTypeEdit').val(employee.employeetypeid);
			var hireDate = employee.hireDate.substr(0, 10);
			$('#txtHireDateEdit').val(hireDate);
			$('#ddlJobTitleEdit').val(employee.jobtitleid);
		});

		$('#editModal').modal('show');
	});

	$('table #detailsButton').on('click', function (event) {

		event.preventDefault();

		var href = $(this).attr('href');

		$.get(href, function (employee, status) {

			var hireDate = employee.hireDate.substr(0, 10);

			$('#idDetails').val(employee.id);
			$('#employeeTypeDetails').val(employee.employeeType.description);
			$('#photoDetails').val(employee.photo);
			$('#usernameDetails').val(employee.username);
			$('#jobTitleDetails').val(employee.jobTitle.description);
			$('#hireDateDetails').val(hireDate);
		});

		$('#detailsModal').modal('show');
	});

	$('table #deleteButton').on('click', function (event) {

		event.preventDefault();

		var href = $(this).attr("href");

		$('#confirmDeleteButton').attr('href', href);

		$('#deleteModal').modal('show');
	});

	$('table #photoButton').on('click', function (event) {

		event.preventDefault();

		var href = $(this).attr("href");

		$('#photoModal #employeePhoto').attr('src', href);

		$('#photoModal').modal('show');
	});
});