$(document).ready(function() {
    $('#sign-in-btn').on('click', function() {
        const username = $('#username').val();
        const password = $('#password').val();

        if (!username || !password) {
            alert('Please enter both username and password');
            return;
        }

        const authData = {
            username: username,
            password: password
        };

        $.ajax({
            method: 'POST',
            url: 'http://localhost:9090/auth/login',
            contentType: 'application/json',
            data: JSON.stringify(authData),
            success: function(response) {
                if (response.code === 200) {
                    // Print token to console
                    console.log('JWT Token:', response.data.accessToken);

                    // Store token and username in cookies (10 days expiration)
                    document.cookie = `jwtToken=${response.data.accessToken}; path=/; max-age=864000`; // 10 days
                    document.cookie = `username=${username}; path=/; max-age=864000`;

                    alert('Sign in successful!');
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Error: ' + response.data);
                }
            },
            error: function(xhr) {
                const errorMsg = xhr.responseJSON ? xhr.responseJSON.data : 'Sign in failed. Please try again.';
                alert(errorMsg);
            }
        });
    });
});
