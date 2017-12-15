$(function() {
	// from the html
	// after you click submit it calls function
	// e - event object
	$('#create-companies-form').submit(function(e) {
		// stops the default behavior of the browser
		e.preventDefault();

		// create data
		let company = {
			// get value from input
			// added id on the html company
			name : $('#company-name').val()
		};
		let headers = {
			'X-CSRF-TOKEN': $('#company-csrf').val()	
		};
		let settings = {
			url: '/api/admin/companies',
			headers: headers,
			data: JSON.stringify(company),
			contentType: 'application/json'
		};
		//when the post is done.. call this function
		$.post(settings)
		 .done(function (data){
			$('#company-list')
				.append(`<li>${data.name}</li>`);
			$('#company-name').val('');
			//console.log('data received:', data); 
		 });
	});

});