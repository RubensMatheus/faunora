const dom_first_name = document.getElementById('first-name');
const dom_last_name = document.getElementById('last-name');
const dom_email = document.getElementById('email');
const dom_password = document.getElementById('password');
const dom_confirm_password = document.getElementById('confirm-password');

document.querySelector('.login-boto').addEventListener('click', function(event) {
    event.preventDefault();
    cadastrar();
    //limpar();
});

function cadastrar() {
    const usuario = {
        nome: dom_first_name.value,
        sobrenome: dom_last_name.value,
        email: dom_email.value,
        senha: dom_password.value,
        confirmarSenha: dom_confirm_password,
        tipo: "TUTOR"
    };

    fetch('http://localhost:8080/users/registrar', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(usuario)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return response.json().then(data => {
                    throw new Error(data.message || 'erro ao cadastrar usuário!');
                });
            }
        })
        .then(data => {
            alert(`${data.message || 'usuário cadastrado com sucesso!'}`);
            limpar();
        })
        .catch(error => {
            alert(`${error.message}`);
        });
}

function limpar() {
    dom_last_name.value = "";
    dom_first_name.value = "";
    dom_email.value = "";
    dom_password.value = "";
    dom_confirm_password.value = "";
}
