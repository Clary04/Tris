// get message layer
var btn = document.querySelector('#login');
var message = document.querySelector('.message');
var text = message.querySelector('.text');
var closeButton = message.querySelector('.button');

var err = false;

btn.addEventListener("click", function(e){
    e.preventDefault();
    let name = loginForm.username.value;
    let password = loginForm.password.value;

    // default values
    if(name == "" || password == ""){
        err == false ? message.classList.toggle("error") : "";
        text.innerHTML = "All files are required!";
        err = true;
        return false;
    } else if(password.length < 4){
        err == false ? message.classList.toggle("error") : "";
        text.innerHTML = "Password must have 4 characters at least!";
        err = true;
        return false;
    }
    // success
    else{
        setTimeout(function(){
            loginForm.submit();
        }, 3000);
    }
});
