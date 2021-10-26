document.addEventListener('DOMContentLoaded',function(){
    validarRegistro();
});

function validarRegistro(){

    const formulario = document.querySelector('.f-registro');
    const clave1 = document.querySelector('#password1');
    const clave2 = document.querySelector('#password2');

    formulario.addEventListener('submit',evento=>{

        if(clave1.value !== clave2.value){
            alert("Las contraseñas no coinciden");
            evento.preventDefault();
            return;
        }


    });  


}