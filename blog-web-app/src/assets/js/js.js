function lgn(){
    let U = document.getElementById("User").value;
    let P = document.getElementById("Pass").value;
    if(!U){
        alert('用户名不能为空');
    }else if(!P){
        alert('密码不能为空');
    }else if(U=="admin" && P=="admin"){
        alert('登陆成功');
    }else alert('登陆失败，账号或密码错误');
}

var x =document.getElementById("login")
var y =document.getElementById("register")
var z =document.getElementById("btn")
function register() {
    x.style.left = "-400px"
    y.style.left = "50px"
    z.style.left = "110px"
}
function login() {
    x.style.left = "50px"
    y.style.left = "450px"
    z.style.left = "0"
}