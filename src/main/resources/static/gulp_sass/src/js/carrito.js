document.addEventListener('DOMContentLoaded',function(){
    carrito_btn();
});

function carrito_btn(){
    const btn_mas = document.querySelector('.btn-mas');
    const btn_menos = document.querySelector('.btn-menos');
    const cantidad = btn_mas.previousElementSibling;
    let value = parseInt(cantidad.value);

    btn_mas.onclick = () => {
    cantidad.value =  ++value; 
    }
    btn_menos.onclick = () => {
    cantidad.value =  --value; 
    }

}
