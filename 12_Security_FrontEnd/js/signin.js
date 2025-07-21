$('#sign-in-btn').on('click', function() {
    var email = $('#email').val();
    var password = $('#password').val();

    $.ajax({
        method: 'POST',
        url: '/12_Security_FrontEnd/signin',
        contentType: 'application/json',
        data: JSON.stringify({
            uemail: email,
            upassword: password
        }),
        success: function(response) {
            if(response.code === '200') {
                localStorage.setItem('email', email);
                alert('Sign In Successfull!');
                window.location.href = 'dashboard.html';
            } else {
                alert('Error: ' +response.message);
            }
        }
    })
})
