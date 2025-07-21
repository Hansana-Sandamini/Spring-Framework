$('#sign-up-btn').on('click', function() {
    console.log('Sign up button clicked!');
    var name = $('#name').val();
    var email = $('#email').val();
    var password = $('#password').val();

    $.ajax({
        method: 'POST',
        url: '/12_Security_FrontEnd/signup',
        contentType: 'application/json',
        data: JSON.stringify({
            uname: name,
            uemail: email,
            upassword: password
        }),
        success: function(response) {
            if(response.code === '200') {
                alert('Sign Up Successfull!');
                window.location.href = 'signin.html';
            } else {
                alert('Error: ' +response.message);
            }
        }
    })
})
