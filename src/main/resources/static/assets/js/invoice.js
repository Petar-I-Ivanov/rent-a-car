$('document').ready(function() {

    $('table #editButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');
//      FIX DATE
        $.get(href, function(invoice, status) {
            $('#idEdit').val(invoice.id);
            var invoiceDate = invoice.invoiceDate.substr(0,10);
            $('#invoiceDateEdit').val(invoiceDate);
            $('#ddlInvoiceStatusEdit').val(invoice.invoicestatusid).change();
            $('#ddlClientEdit').val(invoice.clientid).change();
            $('#remarksEdit').val(invoice.remarks);
        });
        
        $('#editModal').modal('show');
    });

    $('table #detailsButton').on('click', function(event) {
        
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(invoice, status) {
            $('#idDetails').val(invoice.id);
            var invoiceDate = invoice.invoiceDate.substr(0,10);
            $('#invoiceDateDetails').val(invoiceDate);
            $('#ddlInvoiceStatusDetails').val(invoice.invoicestatusid).change();
            $('#ddlClientDetails').val(invoice.clientid).change();
            $('#remarksDetails').val(invoice.remarks);
        });
        
        $('#detailsModal').modal('show');
    });

	$('table #deleteButton').on('click', function(event) {

		event.preventDefault();
		
        var href = $(this).attr("href");
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal('show');
	});
});