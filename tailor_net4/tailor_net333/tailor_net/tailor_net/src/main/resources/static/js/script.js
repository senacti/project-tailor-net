// Script for navigation bar
let lastScrollTop = 0;
navbar = document.getElementById("navbar");
window.addEventListener("scroll", function(){
    let scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    if(scrollTop > lastScrollTop){
        navbar.classList.add('icon');
    }else{
        navbar.classList.remove('icon');
    }
    lastScrollTop = scrollTop;
})

// const botonComprar = document.getElementById('pagar');

// botonComprar.addEventListener("click", function() {
//     window.location.href = "../error/Nube 404/nube.html";
//   });