var request= new XMLHttpRequest();

function processRequest(){
    if(request.readyState==4){
        switch(request.status){
            case 200:

                processRequest(request);
                break;

            case 400:

                console.error(`Error ${request.status}: No se ha podido procesar la petición`);
                break;

        }
    }
}

function processRequest(){
    console.log(`Respuesta: ${request.responseText}`);
}

request.onreadystatechange= (() => processRequest(request));

request.open("Get" , "https://pokeapi.co/api/v2/pokemon/ditto" , true);
request.send();