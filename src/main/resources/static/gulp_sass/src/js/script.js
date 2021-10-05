document.addEventListener('DOMContentLoaded',function(){
    iniciarApp();
});

function iniciarApp(){
    const form_entrega = document.querySelector('.form-entrega');
    const input_container = document.querySelector('.seleccion');
    const despacho = document.querySelector('#despacho');
    const retiro = document.querySelector('#retiro');
    hideForm();

    despacho.onclick = () => {
        hideForm();
    }
    retiro.onclick = () => {
        hideForm();
    }

    function hideForm(checked){
        if(!despacho.checked){
            console.log('anadiendo clase hide');
            form_entrega.classList.add('hide-form');
            input_container.classList.add('mg-2');
        }else{
            form_entrega.classList.remove('hide-form');
            input_container.classList.remove('mg-2');
        }
    }
}
