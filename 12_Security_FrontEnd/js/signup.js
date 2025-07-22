$('#sign-up-btn').on('click', function() {
    console.log('Sign up button clicked!');
    var name = $('#name').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var role = $('#role').val();

    if (password !== confirmPassword) {
        alert('Error: Passwords do not match');
        return;
    }

    if (!role) {
        alert('Error: Please select a role');
        return;
    }

    // Create request data matching User entity
    var userData = {
        username: username,
        password: password,
        role: role
    };

    $.ajax({
        method: 'POST',
        url: '/12_Security_FrontEnd/signup',
        contentType: 'application/json',
        data: JSON.stringify(userData),
        success: function(response) {
            if(response.code === '200') {
                alert('Sign Up Successful!');
                window.location.href = 'signin.html';
            } else {
                alert('Error: ' + response.message);
            }
        },
        error: function(xhr, status, error) {
            console.error('Signup error:', error);
            alert('Signup failed. Please try again.');
        }
    });
})
