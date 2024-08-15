const wrapper = document.querySelector('.wrapper');
const registerLink = document.querySelector('.register-link');
const loginLink = document.querySelector('.login-link');
const recuperar = document.querySelector('.recover');
const recuperado = document.querySelector('.recovered');
const botonIniciarSesion = document.getElementById('btnI');


registerLink.onclick = () => {
    wrapper.classList.add('active');
}
loginLink.onclick = () => {
    wrapper.classList.remove('active');
}
recuperar.onclick = () => {
    wrapper.classList.add('recuperar');
}
recuperado.onclick = () => {
    wrapper.classList.remove('recuperar');
}
// botonIniciarSesion.addEventListener("click", function() {
//     window.location.href = "../Dashboard/dashboard.html";
//   });
// document.getElementById("loginForm").addEventListener("submit", function (event) {
//     event.preventDefault(); // Evita el envío del formulario por defecto para manejarlo con JavaScript.

//     // Aquí puedes realizar la validación del nombre de usuario y la contraseña.
//     var username = document.getElementById("username").value;
//     var password = document.getElementById("password").value;

//     // Supongamos que aquí realizas la validación de nombre de usuario y contraseña.
//     if (username !== "" && password !== "") {
//         // Si las credenciales son válidas, redirige al usuario a la página deseada.
//         window.location.href = "../Dashboard/dashboard.php";
//     } else {
//         alert("Credenciales incorrectas. Inténtelo de nuevo.");
//     }
// });