function changeVerifyCode(img) {
    //alert("innner");
    img.src = "../Kaptcha?" + Math.floor(Math.random() * 100);
}