// DOM: Obtener elementos
let tablero = document.getElementById("tablero");
let boton_play = document.getElementById("boton_play");
let boton_pause = document.getElementById("boton_pause");
let boton_reset = document.getElementById("boton_reset");

let clickplay = document.getElementById("boton_play").onclick = changeColor ;

/* 
cuatro elementos principales, que son el tablero, en el cual se
muestra el tiempo y los tres botones 

*/

// Estados Cronometro
let esta_activo = false;
let time = {
decimas : 0,
segundos : 0,
minutos : 0
}

/* 
variable booleana llamada esta_activo que permitirá determinar si el cronómetro está pausado

objeto que almacenerá el tiempo transcurrido, si bien podrían
haberse guardado el tiempo en variables independientes, la opción de usar un objeto permite mantener
la información ordenada

*/

// Funcion Actualizar
function formato(numero){ // transforma el 8 al 08
    if(numero<10){
        return "0"+numero;
    }
    else{
        return numero;
    }
}

function actualizar(){  
    time.decimas++; // cada 100 milisegundos aumenta 


    if(time.decimas == 10){ 
        time.decimas = 0;
        time.segundos++; // aumenta un segundo
    }
    if(time.segundos == 60){ // alcanz ael minuto
        time.segundos = 0;
        time.minutos++;
    }

    tablero.innerHTML = `${formato(time.minutos)}:${formato(time.segundos)}:${time.decimas} ` // concatena en string
    if(esta_activo == true){
        setTimeout(actualizar,100);
    }
}


// Funciones Botones
function play(){
    if(esta_activo == false){
    esta_activo = true;
    actualizar();
    }
}

function pause(){
    esta_activo = false;
}

function reset(){
    time.decimas = 0;
    time.segundos = 0;
    time.minutos = 0;
    tablero.innerHTML = `${formato(time.minutos)}:${formato(time.segundos)}:${time.decimas}`
}

// Escuchar Eventos
boton_play.addEventListener('click', play);
boton_pause.addEventListener('click', pause);
boton_reset.addEventListener('click', reset);


//______________
function changeColor() {
    document.body.style.color = "gray";
    return false;
}   