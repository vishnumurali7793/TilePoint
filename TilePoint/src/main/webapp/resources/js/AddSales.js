/**
 * 
 */

$(document).ready(function() {
	$('#enableTax').prop('checked', true);
	$('#taxGroup').show();
	$('#datetimepicker1').datetimepicker({
		minView: 2,
		pickTime: true,
		autoclose: true,
		icons: {
			time: "fa fa-clock-o",
			date: "fa fa-calendar",
			up: "fa fa-arrow-up",
			down: "fa fa-arrow-down",
			previous: "fa fa-chevron-left",
			next: "fa fa-chevron-right",
			today: "fa fa-clock-o",
			clear: "fa fa-trash-o"
		}
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
	var itemName = document.createElement('input');
	itemName.type = 'text';
	itemName.classList = "items_table itemName ui-autocomplete-input";
	itemName.placeholder = "Item Name";
	itemName.id = "itemName" + rowCount;
	itemName.onkeypress =
		cell2.appendChild(itemName);

	var cell3 = row.insertCell(2);
	var hsnCode = document.createElement('input');
	hsnCode.type = 'text';
	hsnCode.classList = "items_table";
	hsnCode.placeholder = "HSN Code";
	cell3.appendChild(hsnCode);

	var cell4 = row.insertCell(3);
	var quantity = document.createElement('input');
	quantity.type = 'text';
	quantity.classList = "items_table";
	quantity.placeholder = "Quantity";
	cell4.appendChild(quantity);

	var cell5 = row.insertCell(4);
	var rate = document.createElement('input');
	rate.type = 'text';
	rate.classList = "items_table";
	rate.placeholder = "Rate";
	cell5.appendChild(rate);

	var cell6 = row.insertCell(5);
	var grossAmount = document.createElement('input');
	grossAmount.type = 'text';
	grossAmount.classList = "items_table";
	grossAmount.placeholder = "Gross Amount";
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
		appendTo: '#addSales',
		autoFocus: true,
		delay: 100,
		select: function(event, ui){
			debugger;
			$(this).val(ui.item.label);
			$(".itemId").val(ui.item.value);
		}
	});
});