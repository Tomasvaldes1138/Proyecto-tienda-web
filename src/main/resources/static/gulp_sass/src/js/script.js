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

    carrito_btn();
}

function carrito_btn(){
    const btn_mas = document.querySelector('.btn-mas');
    const btn_menos = document.querySelector('.btn-menos');
    console.log("funciooopn");
    console.log(btn_mas);

    btn_mas.onclick = () => {
        console.log("clickckckck");
        console.log(btn_mas.previousSibling);
    }

}
