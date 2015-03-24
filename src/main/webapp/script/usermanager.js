/*
	Useful functions
*/
function propertyRename(prop, src, dst){
	prop[dst] = prop[src];
	delete prop[src]; 
}

var login_success = function(loginName){
	// Update the dropdown list
	$("#account-dropdown").html("<strong>"+loginName+"</strong>");
	$("#login-dropdown").hide();
	$("#register-dropdown").hide();
	$("#logout-dropdown").show();

	// List the thunbnails
	$.get( "./list", function( data ) {
		data.numberPlates.forEach(function(entity){
				$.get( "./query/" + entity, function( data ) {
					if (data.result[0]==="success"){

						// Get file object from data
						var fileObject = data.numberPlate;

						// Rename the propertie names
						propertyRename(fileObject, "fileName", "name");
						propertyRename(fileObject, "contenType", "type");
						
						// Make the xhr properties to fit the response JSON
						fileObject["xhr"] = {"response": '{"id":' + fileObject.id + ',"result":["' + fileObject["number"] + '"]}' } ; 	
						
						// Delete useless properties
						delete fileObject["number"];

						// Fake filesize
						fileObject["size"] = '329023';

						// Now add the preview
						myDropzone.addPreview(fileObject);
					}
				})
			}
		)
	});
}

var logout_success = function(){
	// Update the dropdown list
	$("#account-dropdown").html("Account");
	$("#login-dropdown").show();
	$("#register-dropdown").show();
	$("#logout-dropdown").hide();
	$(".dz-preview").remove();
}

/*
	Events blinding
*/

// Attach handlers to login form
$( "#login-modeal #submit" ).on("click", function(){
	// Get the submit form in login form
	$form = $( "#login-form" );
	
	// Get some values from elements on the page:
	var loginName = $form.find( "input[name='loginName']" ).val(),
		password = $form.find( "input[name='password']" ).val(),
		rememberMe = $( "#login-form .switch #rememberMe" )[0].checked,
		url = $form.attr( "action" );
	
	// Send the data using post
	var posting = $.post( url, { loginName: loginName, password: password, rememberMe: rememberMe } );

	// Put the results in a div
	posting.done(function( data ) {
	var result = $("#login-modeal #result");
	if (data.result[0]!=="success"){
		//Login error
		result.html( data.result[0]).slideDown();
	} else {
		// Login success
		// Hide the error message 
		result.hide();
		// Close the login form
		$("#login-modeal a").click();
		// Update the dropdown
		login_success(loginName);
	}
	});
});
$( "#login-modeal #register" ).on("click", function(){ 
	$("#register-dropdown").click();
});

// Attach handlers to register form
$( "#register-modeal #submit" ).on("click", function(){
 	// Get the submit form in register form
	$form = $( "#register-form" );

	// Get some values from elements on the page:
	var loginName = $form.find( "input[name='loginName']" ).val(),
		password = $form.find( "input[name='password']" ).val(),
		confirmPassword = $form.find( "input[name='confirmPassword']" ).val(),
		url = $form.attr( "action" );

	// Send the data using post
	var posting = $.post( url, { loginName: loginName, password: password, confirmPassword: confirmPassword } );

	// Put the results in a div
	posting.done(function( data ) {
	var result = $("#register-modeal #result");
	if (data.result[0]!=="success"){
	  result.html( data.result[0]).slideDown();
	} else {
	  result.hide();
	  $("#register-modeal a").click();
	  login_success(loginName);
	}
	});
}); 
$( "#register-modeal #register" ).on("click", function(){ 
	$("#login-dropdown").click();
});

// Attach the Logout dropdown
$("#logout-dropdown").on("click",function(){
	$.get( "./logout", function( data ) {
	});
	logout_success()
});


/*
	Script runs onload
*/

// Check username with cookies login
$.get( "./checkUsername", function( data ) {
	if (data.result[0] === "success"){
		login_success(data.result[1]);
	}
});

