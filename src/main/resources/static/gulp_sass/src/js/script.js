document.addEventListener('DOMContentLoaded',function(){
    iniciarApp();
    filtrarCiudades();
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
            form_entrega.classList.add('hide-form');
            input_container.classList.add('mg-2');
        }else{
            form_entrega.classList.remove('hide-form');
            input_container.classList.remove('mg-2');
        }
    }

}

function filtrarCiudades(){
    const select_region = document.querySelector('#select-region');
    const ciudades = document.querySelectorAll('.option-ciudad');

    setCiudades();

    select_region.addEventListener('change', () => setCiudades() );

    function setCiudades(){
        const select_ciudad = document.querySelector('#select-ciudad');
        let cadena = '';

        ciudades.forEach( ciudad => {
            if(ciudad.classList.contains(`${select_region.value}`)){
            cadena+=`<option value="${ciudad.value}" class="option-ciudad" >${ciudad.text}</option> \n`
            }
        } );
        select_ciudad.innerHTML = cadena;
    }

}



    