$(function() {

	$('#create-flat-fees-form').submit(function(e) {
		// alert("ALERT!")
		e.preventDefault();

		let flatfee = {
			amount : $('#flat-fees-amount').val(),
			description : $('#flat-fees-description').val(),
			client : {
				id : $('#flat-fees-clientId').val()
			}
		};

		let headers = {
			'X-CSRF-TOKEN' : $('#flat-fees-csrf').val()
		};

		let settings = {
			url : '/api/flatfees',
			headers : headers,
			data : JSON.stringify(flatfee),
			contentType : 'application/json'
		};
		
		$.post(settings)
			.done(function(data){
				console.log(data)
				$('#record-row')
					.append(`<tr>
							<td>${data.createdBy.username}</td>
							<td>${data.description}</td>
							<td>${data.client.name}</td>
							<td>$${data.amount}</td>
							<td>$</td>
							<td></td>
							<td>$${data.total}</td>
							</tr>`);
				$('#flat-fees-amount').val('');
				$('#flat-fees-clientId').val('');
				$('#flat-fees-description').val('');
			});

	});
});


$(function() {

	$('#create-ratebased-form').submit(function(e) {
		// alert("ALERT!")
		e.preventDefault();

		let ratebased = {
			rate : $('#ratebased-rate').val(),
			quantity : $('#ratebased-quantity').val(),
			description : $('#ratebased-description').val(),
			client : {
				id : $('#ratebased-name').val()
			}
			
		};

		let headers = {
			'X-CSRF-TOKEN' : $('#ratebased-csrf').val()
		};

		let settings = {
			url : '/api/ratefees',
			headers : headers,
			data : JSON.stringify(ratebased),
			contentType : 'application/json'
		};
		
		$.post(settings)
			.done(function(data){
				console.log(data)
				$('#record-row')
					.append(`<tr>
							<td>${data.createdBy.username}</td>
							<td>${data.description}</td>
							<td>${data.client.name}</td>
							<td>$</td>
							<td>$${data.rate}</td>
							<td>${data.quantity}</td>
							<td>$${data.total}</td>
							</tr>`);
				$('#ratebased-rate').val('');
				$('#ratebased-quantity').val('');
				$('#ratebased-description').val('');
				$('#ratebased-name').val('');
			});

	});
});

