console.log("hola")
let list = document.querySelectorAll(".navigation li");

function linkActivado(){
    list.forEach(elemento =>{
        elemento.classList.remove("hovered");
    })
    this.classList.add("hovered");
}
list.forEach(elemento => elemento.addEventListener("mouseover", linkActivado));

//Menu
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function() {
    //El método toggle se utiliza en JavaScript para agregar o quitar una clase CSS de un elemento del DOM, como un elemento HTML. Su funcionalidad es bastante simple:

    // Si el elemento no tiene la clase especificada, toggle la agregará, es decir, la añadirá.
    // Si el elemento tiene la clase especificada, toggle la quitará, es decir, la eliminará.
    navigation.classList.toggle("active");
    main.classList.toggle("active");
}