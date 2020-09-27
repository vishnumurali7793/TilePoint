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
	quantity.classList = "items_table";
	quantity.placeholder = "Quantity";
	quantity.name = "itemsDetails[" + (rowCount - 1) + "].quantity";
	cell4.appendChild(quantity);

	var cell5 = row.insertCell(4);
	var rate = document.createElement('input');
	rate.type = 'text';
	rate.classList = "items_table";
	rate.placeholder = "Rate";
	rate.name = "itemsDetails[" + (rowCount - 1) + "].rate";
	cell5.appendChild(rate);

	var cell6 = row.insertCell(5);
	var grossAmount = document.createElement('input');
	grossAmount.type = 'text';
	grossAmount.classList = "items_table";
	grossAmount.placeholder = "Gross Amount";
	rate.name = "itemsDetails[" + (rowCount - 1) + "].totalamount";
	cell6.appendChild(grossAmount);

	var cell7 = row.insertCell(6);
	var remove = document.createElement('button');
	remove.classList = "removeRow btn btn-default btn-xs form-btn-danger pull-left";
	remove.innerHTML = "<span class='glyphicon glyphicon-remove'></span>";
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
//			$(".itemId").val(ui.item.value);
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
	if(type == "S") {
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