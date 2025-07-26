$('#sign-up-btn').on('click', function() {
    const username = $('#username').val();
    const password = $('#password').val();
    const role = $('#role').val();

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
