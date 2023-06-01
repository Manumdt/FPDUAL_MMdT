document.querySelector('#seleccionarPokemon').addEventListener('submit', function (event) {
    event.preventDefault();

    const numeroPokedex = document.getElementById('numeroPokedex');

    fetch(`http://localhost:8080/api/find/${numeroPokedex.value}`)
        .then((res) => res.json())
        .then((data) => {           
            mostarPokemon(data, numeroPokedex.value);
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

    numero_Pokedex = document.getElementById('numero_Pokedex');
    nombre = document.getElementById('nombre');
    altura = document.getElementById('altura');
    peso = document.getElementById('peso');
    imagen = document.getElementById('imagen');
    descripcion = document.getElementById('descripcion');
    
    numero_Pokedex.value = numeroPokedex;
    nombre.value = pokemon.nombre;
    altura.value = pokemon.altura;
    peso.value = pokemon.peso;
    imagen.value = pokemon.imagen;
    descripcion.value = pokemon.descripcion;
}

document.getElementById('formularioModificar').addEventListener('submit', function(event) { event.preventDefault();

    const formData = new FormData(event.target);
    const pokemonData = Object.fromEntries(formData.entries());
    const mensajeCorrecto = document.querySelector('.mensajeCorrecto');
    
    fetch('http://localhost:8080/api/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    body: JSON.stringify(pokemonData)
    })
        .then(response => response.json())
        .then(data => {
        console.log(data);
        mensajeCorrecto.style.display="block";
        setTimeout(function() {
            location.href = "pokeapi03.html";
        }, 2000);
    })
        .catch(error => {
        console.error('Ocurrió un error en la solicitud:', error);
        setTimeout(function() {
            window.location.reload();
        }, 2000);
    });
    });