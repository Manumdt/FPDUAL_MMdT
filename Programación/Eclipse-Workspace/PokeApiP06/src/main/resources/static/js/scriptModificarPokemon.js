document.querySelector('#seleccionarPokemon').addEventListener('submit', function (event) {
    event.preventDefault();

    const numeroPokedex = document.getElementById('numeroPokedex').value;

    fetch(`http://localhost:8080/api/find/${numeroPokedex}`)
        .then((res) => res.json())
        .then((data) => {           
            mostarPokemon(data, numeroPokedex);
        })
        .catch(error => {
            console.error('Ocurrió un error en la solicitud:', error);
            setTimeout(function() {
                window.location.reload();
            }, 2000);
        });
});

function mostarPokemon(pokemon, numeroPokedex){
            
    formularioModificar = document.getElementById('formularioModificar');
    seleccionarPokemon = document.getElementById('seleccionarPokemon');
    formularioModificar.style.display = "block";
    seleccionarPokemon.style.display="none";

    id = document.getElementById('id');
    nombre = document.getElementById('nombre');
    altura = document.getElementById('altura');
    peso = document.getElementById('peso');
    url = document.getElementById('url');
    descripcion = document.getElementById('descripcion');
    
    id.value = numeroPokedex;
    nombre.value = pokemon.nombre;
    altura.value = pokemon.altura;
    peso.value = pokemon.peso;
    url.value = pokemon.url;
    descripcion.value = pokemon.descripcion;
}

document.getElementById('formularioModificar').addEventListener('submit', function(event) { event.preventDefault();
    
    const mensajeCorrecto = document.querySelector('.mensajeCorrecto');
    const mensajeError = document.querySelector('.mensajeError');

    id = document.getElementById('id');
    nombre = document.getElementById('nombre');
    altura = document.getElementById('altura');
    peso = document.getElementById('peso');
    descripcion = document.getElementById('descripcion');
    url = document.getElementById('url');

    const bodyPut={
        "numero_pokedex": id.value,
        "nombre": nombre.value,
        "peso": peso.value,
        "altura": altura.value,
        "descripcion": descripcion.value,
        "url": url.value
    };

    fetch(`http://localhost:8080/api/update/${id.value}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': '*/*',
            'Accept-Encoding': 'gzip, deflate, br'
        },
    body: JSON.stringify(bodyPut)
    })
        .then(response => response.json())
        .then(data => {
        console.log(data);
        mensajeCorrecto.style.display="block";
        setTimeout(function() {
            location.href = "../html/index.html";
        }, 2000);
    })
        .catch(error => {
        console.error('Ocurrió un error en la solicitud:', error);
        mensajeError.style.display="block";
        setTimeout(function() {
            window.location.reload();
        }, 2000);
    });
    });