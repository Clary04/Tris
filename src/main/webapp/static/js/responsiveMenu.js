// responsive menu
let show = true;

const menuSection = document.querySelector(".menu_section");
const menuToggle = menuSection.querySelector(".menu_toggle");
const menuClose = menuSection.querySelector(".menu_close");

menuToggle.addEventListener("click", () =>{
    menuSection.classList.toggle("on", show);
});

menuClose.addEventListener("click", () =>{
    menuSection.classList.toggle("on");
});