// get message layer
var btn = document.querySelector('#signin');
var message = document.querySelector('.message');
var text = message.querySelector('.text');
var closeButton = message.querySelector('.button');

var err = false;

btn.addEventListener("click", function(e){
    e.preventDefault();
    let us_name = signinForm.username.value;
    let cpf = signinForm.cpf.value;
    let realname = signinForm.realname.value;
    let password = signinForm.user_password.value;
    let group = signinForm.group.value;

    // default values
    if(us_name == "" || password == "" || realname == "" || group == "" || cpf == ""){
        err == false ? message.classList.toggle("error") : "";
        text.innerHTML = "All files are required!";
        err = true;
        return false;
    }
    //invalid email
    else if(password.length < 4){
        err == false ? message.classList.toggle("error") : "";
        text.innerHTML = "Password must have 4 characters at least!";
        err = true;
        return false;
    }
    // success
    else{
        setTimeout(function(){
            signinForm.submit();
        }, 3000);
    }
});

