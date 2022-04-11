function loginPageRedirect(){
    window.location.href = "./login.html";
}

function signinPageRedirect(){
    window.location.href = "./signin.html";
}

function indexPageRedirect(){
    window.location.href = "./index.html";
}

function indexAccessRedirect(){
    window.location.href = "http://localhost:8080/Tris/";
}

function closeBtn(){
    err == true ? message.classList.toggle("error") : message.classList.toggle("success");
    err == false;
}

let cookies_btn = document.querySelector(".acpt_cookies");
cookies_btn.addEventListener("click", (e) => {
    let cookies_div = document.querySelector(".apt-cookies");
    cookies_div.style.display = "none";
})