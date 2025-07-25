// $('#sign-up-btn').on('click', function() {
//     console.log('Sign up button clicked!');
//     var username = $('#username').val();
//     var password = $('#password').val();
//     var role = $('#role').val();
//
//     if (!role) {
//         alert('Error: Please select a role');
//         return;
//     }
//
//     // Create request data matching User entity
//     var userData = {
//         username: username,
//         password: password,
//         role: role
//     };
//
//     $.ajax({
//         method: 'POST',
//         url: '/12_Security_FrontEnd/signup',
//         contentType: 'application/json',
//         data: JSON.stringify(userData),
//         success: function(response) {
//             if(response.code === '200') {
//                 alert('Sign Up Successful!');
//                 window.location.href = 'signin.html';
//             } else {
//                 alert('Error: ' + response.message);
//             }
//         },
//         error: function(xhr, status, error) {
//             console.error('Signup error:', error);
//             alert('Signup failed. Please try again.');
//         }
//     });
// })


$('#sign-up-btn').on('click', function() {
    const username = $('#username').val();
    const password = $('#password').val();
    const role = $('#role').val();

    // Basic validation
    if (!username || !password || !role) {
        alert('Please fill in all fields');
        return;
    }

    const registerData = {
        username: username,
        password: password,
        role: role
    };

    $.ajax({
        method: 'POST',
        url: 'http://localhost:9090/auth/register',
        contentType: 'application/json',
        data: JSON.stringify(registerData),
        success: function(response) {
            if (response.code === 200) {
                alert('Registration successful! Please sign in.');
                window.location.href = 'signin.html';
            } else {
                alert('Error: ' + response.data);
            }
        },
        error: function(xhr) {
            const errorMsg = xhr.responseJSON ? xhr.responseJSON.data : 'Registration failed. Please try again.';
            alert(errorMsg);
        }
    });
});
