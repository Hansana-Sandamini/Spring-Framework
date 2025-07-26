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
                // Store the JWT token and username
                localStorage.setItem('jwtToken', response.data.accessToken);
                localStorage.setItem('username', username);

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
