/**
 * 
 */

$(document).ready(function() {
	$('#enableTax').prop('checked', true);
	$('#taxGroup').show();
	$('#datetimepicker1').datetimepicker({
		minView: 2,
		pickTime: false,
		autoclose: true,
	});
	$('#enableTax').click(function() {
		if ($('#enableTax').prop('checked')) {
			$('#taxGroup').show();
		} else {
			$('#taxGroup').hide();
		}
	});
	$("#itemTable").on('click', '.removeRow', function() {
		$(this).parent().parent().remove();
		calculateTotalAmount();
		var table = document.getElementById('itemTable');
		var rowCount = table.rows.length;
		for (var i = 1; i < rowCount; i++) {
			var label = document.querySelectorAll('#itemTable tbody tr:nth-child(' + i + ') td:nth-child(1) label');
			label[0].innerHTML = i + ".";
		}
	});
	$('#customerCode').autocomplete({
		source: function(request, response) {
			$.ajax({
				url: 'getCusDetails',
				data: {
					'salesBaseBean.customerId.customerCode': request.term,
				},
				success: function(data) {
					if (data == null || !data.length) {
						var result = [{
							label: '--No Matches Found--',
							value: 0
						}];
						response(result);
					} else {
						response($.map(data, function(i) {
							return {
								label: i[0] + "-" + i[1] + "-" + i[2],
								value: i[1]
							};
						}));
					}
				},
			});
		},
		appendTo: '#addSales',
		autoFocus: true,
		delay: 100
	});

	//itemwise gross amount calculation for dynamically added rows
	$('#itemTable').on('keyup', '.dynamicElement', (element) => {
		var elementId = element.target.id;
		var rowCount = elementId.split("_");
		calculateItemwiseRate(rowCount[1]);
	});
});

function addNewRow(tableId) {
	var table = document.getElementById(tableId);
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);

	var cell1 = row.insertCell(0);
	var serialNumber = document.createElement('label');
	serialNumber.innerHTML = rowCount + '.';
	serialNumber.classList = "serialNumber" + rowCount;
	cell1.appendChild(serialNumber);

	var cell2 = row.insertCell(1);
	var itemId = document.createElement('input');
	itemId.type = 'hidden';
	itemId.classList = "itemId";
	itemId.name = "itemsDetails[" + (rowCount - 1) + "].productId.productId";
	cell2.appendChild(itemId);
	var itemName = document.createElement('input');
	itemName.type = 'text';
	itemName.classList = "items_table itemName ui-autocomplete-input";
	itemName.placeholder = "Item Name";
	itemName.id = "itemName" + rowCount;
	itemName.onkeypress = cell2.appendChild(itemName);

	var cell3 = row.insertCell(2);
	var hsnCode = document.createElement('input');
	hsnCode.type = 'text';
	hsnCode.classList = "items_table";
	hsnCode.placeholder = "HSN Code";
	hsnCode.name = "itemsDetails[" + (rowCount - 1) + "].hsnCode";
	cell3.appendChild(hsnCode);

	var cell4 = row.insertCell(3);
	var quantity = document.createElement('input');
	quantity.type = 'text';
	quantity.classList = "items_table dynamicElement";
	quantity.placeholder = "Quantity";
	quantity.name = "itemsDetails[" + (rowCount - 1) + "].quantity";
	quantity.id = "quantity_" + rowCount;
	cell4.appendChild(quantity);

	var cell5 = row.insertCell(4);
	var rate = document.createElement('input');
	rate.type = 'text';
	rate.classList = "items_table itemRate dynamicElement";
	rate.placeholder = "Rate";
	rate.name = "itemsDetails[" + (rowCount - 1) + "].rate";
	rate.id = "rate_" + rowCount;
	cell5.appendChild(rate);

	var cell6 = row.insertCell(5);
	var grossAmount = document.createElement('input');
	grossAmount.type = 'text';
	grossAmount.classList = "items_table gross_amount";
	grossAmount.placeholder = "Gross Amount";
	grossAmount.name = "itemsDetails[" + (rowCount - 1) + "].totalamount";
	grossAmount.id = "grossAmount_" + rowCount;
	cell6.appendChild(grossAmount);

	var cell7 = row.insertCell(6);
	var remove = document.createElement('button');
	remove.classList = "removeRow btn btn-default btn-xs form-btn-danger pull-left";
	remove.innerHTML = "<span class='glyphicon glyphicon-remove'></span>";
	remove.id = "remove_" + rowCount;
	cell7.appendChild(remove);

}

function getStateList(element) {
	$('#' + element.id).autocomplete({
		source: function(request, response) {
			$.ajax({
				url: 'getStateSetupList',
				data: {
					'autoCompleteSTR': request.term,
				},
				success: function(data) {
					if (data == null || !data.length) {
						var result = [{
							label: '--No Matches Found--',
							value: 0
						}];
						response(result);
					} else {
						response($.map(data, function(i) {
							return {
								label: i[0] + "-" + i[1],
								value: i[0] + "-" + i[1]
							};
						}));
					}
				},
			});
		},
		appendTo: '#addSales',
		autoFocus: true,
		delay: 100
	});
}

$(document).on("keypress", ".itemName", function() {
	$('.itemName').autocomplete({
		source: function(request, response) {
			$.ajax({
				url: 'getItemAutoCompleteForSales',
				data: {
					'autoCompleteSTR': request.term,
				},
				success: function(data) {
					if (data == null || !data.length) {
						var result = [{
							label: '--No Matches Found--',
							value: 0
						}];
						response(result);
					} else {
						response($.map(data, function(i) {
							return {
								label: i[0],
								value: i[1]
							};
						}));
					}
				},
			});
		},
		select: function(event, ui) {
			event.preventDefault();
			$(this).val(ui.item.label);
			$(this).parent().find("input:hidden:first").val(ui.item.value);
		},
		appendTo: '#addSales',
		autoFocus: true,
		delay: 100,
	});
});

function enableGstSplit(element) {
	if (element.value != "" && element.value != undefined) {
		var gstSplit = element.value / 2;
		$('#cgst').attr('hidden', false);
		$('#label_cgst').attr('hidden', false);
		$('#cgst').val(gstSplit);
		$('#sgst').attr('hidden', false);
		$('#label_sgst').attr('hidden', false);
		$('#sgst').val(gstSplit);
	}
}

function addSalesBill(type) {
	if (type == "S") {
		$.ajax({
			url: 'saveAndGenerateSalesInvoice',
			data: $('#addSales').serialize(),
			success: function(data) {
				alert(data);
			},
			error: function() {
				alert('Error occured');
			}
		});
	}
}

function calculateItemwiseRate(rowCount) {
	var rate = $('#rate_' + rowCount).val();
	var quantity = $('#quantity_' + rowCount).val();
	var grossAmount = parseFloat(rate) * parseFloat(quantity);
	if (grossAmount != undefined && grossAmount != NaN && grossAmount > 0) {
		$('#grossAmount_' + rowCount).val(grossAmount.toFixed(3));
	} else {
		grossAmount = 0;
		$('#grossAmount_' + rowCount).val(grossAmount.toFixed(3));
	}
	calculateTotalAmount();
}

function calculateTotalAmount() {
	var totalAmount = 0;
	$('.gross_amount').each(function() {
		var grossAmount = $(this).val();
		if (grossAmount != undefined && grossAmount != NaN && grossAmount != "") {
			grossAmount = parseFloat(grossAmount);
			totalAmount = totalAmount + grossAmount;
			$('#totalAmount').text(totalAmount.toFixed(3));
			$('#totalAmountHidden').val(totalAmount.toFixed(3));
			$('#netAmount').val(totalAmount.toFixed(3));
		} else if (grossAmount != "") {
			$('#totalAmount').text(totalAmount.toFixed(3));
			$('#totalAmountHidden').val(totalAmount.toFixed(3));
			$('#netAmount').val(totalAmount.toFixed(3));
		}
	});
}

function calculateTaxAmount() {
	var netAmount = 0;
	var taxAmount = 0;
	var taxableAmount = 0;
	var gstPercentage = $('#gstPercentage').val();
	var igstPercentage = $('#igstPercentage').val();
	gstPercentage = parseFloat(gstPercentage);
	igstPercentage = parseFloat(igstPercentage);

	if (gstPercentage != undefined && gstPercentage != NaN && gstPercentage > 0) {
		$('#cgst').attr('hidden', false);
		$('#label_cgst').attr('hidden', false);
		$('#cgst').val(gstPercentage / 2);
		$('#sgst').attr('hidden', false);
		$('#label_sgst').attr('hidden', false);
		$('#sgst').val(gstPercentage / 2);
		$('#igstPercentage').val(0);

		netAmount = $('#netAmount').val();
		netAmount = parseFloat(netAmount);
		igstPercentage = 0; //setting valu to zero to double check no previous data is stored in the variable
		taxAmount = (netAmount * gstPercentage) / 100;
		taxableAmount = netAmount - taxAmount

		$('#taxAmount').val(taxAmount.toFixed(3));
		$('#cgstAmount').val(taxAmount / 2);
		$('#sgstAmount').val(taxAmount / 2);
		$('#totalAmount').text(taxableAmount.toFixed(3));
		$('#totalAmountHidden').val(taxableAmount.toFixed(3));
	} else if (igstPercentage != undefined && igstPercentage != NaN && igstPercentage > 0) {
		$('#gstPercentage').val(0);
		$('#cgst').attr('hidden', true);
		$('#label_cgst').attr('hidden', true);
		$('#sgst').attr('hidden', true);
		$('#label_sgst').attr('hidden', true);

		netAmount = $('#netAmount').val();
		netAmount = parseFloat(netAmount);
		gstPercentage = 0; //setting valu to zero to double check no previous data is stored in the variable
		taxAmount = (netAmount * igstPercentage) / 100;
		taxableAmount = netAmount - taxAmount;

		$('#taxAmount').val(taxAmount.toFixed(3));
		$('#cgstAmount').val(0);
		$('#sgstAmount').val(0);
		$('#totalAmount').text(taxableAmount.toFixed(3));
		$('#totalAmountHidden').val(taxableAmount.toFixed(3));
	}
}

function addExtraCharge(element) {
	var extraCharge = 0;
	var netAmount = 0;
	extraCharge = element.value;
	extraCharge = extraCharge != "" ? parseFloat(extraCharge) : 0;

	if (extraCharge != undefined && extraCharge != NaN && extraCharge > 0) {
		netAmount = $('#netAmount').val();
		netAmount = netAmount != "" ? parseFloat(netAmount) : 0;
		netAmount += extraCharge;

		$('#netAmount').val(netAmount.toFixed(3));
	} else {
		var taxAmount = 0;
		var taxableAmount = 0;
		var vehicleCharge = 0;
		var loadingCharge = 0;

		vehicleCharge = $('#vehicleCharge').val();
		loadingCharge = $('#loadingCharge').val();
		vehicleCharge = vehicleCharge != "" ? parseFloat(vehicleCharge) : 0;
		loadingCharge = loadingCharge != "" ? parseFloat(loadingCharge) : 0;

		taxAmount = $('#taxAmount').val();
		taxAmount = parseFloat(taxAmount);
		taxableAmount = $('#totalAmount').text();
		taxableAmount = parseFloat(taxableAmount);
		netAmount = taxableAmount + taxAmount;
		netAmount = vehicleCharge > 0 ? netAmount + vehicleCharge : netAmount;
		netAmount = loadingCharge > 0 ? netAmount + loadingCharge : netAmount;
		$('#netAmount').val(netAmount.toFixed(3));
	}

}

function validateNullorEmpty(param) {
	var flag = false;
	$('.validateImportant').each(function() {
		debugger;
		var value = $(this).val();
		var label = $(this).attr('placeholder');
		if (value == undefined || value == "") {
			alert(label + ' is mandatory');
			flag = false;
			return false;
		} else {
			flag = true;
		}
	});
	if (flag) {
		addSalesBill(param);
	}
}