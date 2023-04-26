const pokemonContainer = document.querySelector('.pokemon-container');
const anterior = document.querySelector('#anterior');
const siguiente = document.querySelector('#siguiente');
const oculto = document.querySelector('.oculto');

let offset = 1;
let limit = 23;

anterior.addEventListener('click', () => {
    if (offset != 1) {
        offset -= 24;
        removePokemons(pokemonContainer);
        fetchAllPokemons(offset, limit);
    }
});

siguiente.addEventListener('click', () => {
    if (offset <= 1008) {
        offset += 24;
        removePokemons(pokemonContainer);
        fetchAllPokemons(offset, limit);
    }
});

function fetchPokemon(id) {
    fetch(`https://pokeapi.co/api/v2/pokemon/${id}/`)
        .then((res) => res.json())
        .then((data) => {
            crearPokemon(data);
        });
}

function fetchAllPokemons(offset, limit) {
    for (let i = offset; i <= offset + limit; i++) {
        fetchPokemon(i);
    }
}

function crearPokemon(pokemon) {

    const url = pokemon.species.url;

    const carta = document.createElement('div');
    carta.classList.add('pokemon');

    const spriteContainer = document.createElement('div');
    spriteContainer.classList.add('sprite');

    const imagen = document.createElement('img');
    imagen.src = pokemon.sprites.front_default;

    const number = document.createElement('p');
    number.textContent = `#${pokemon.id.toString().padStart(3, 0)}`;

    const name = document.createElement('p');
    name.classList.add('name');
    name.textContent = pokemon.name;

    const tipos = document.createElement('div');
    tipos.classList.add('tipos');
    const tipo1 = document.createElement('p');
    const tipo2 = document.createElement('p');
    if (pokemon.types.length == 1) {
        tipo1.textContent = pokemon.types[0].type.name;
        tipo1.classList.add('tipo1', pokemon.types[0].type.name);
    } else if (pokemon.types.length == 2) {
        tipo1.textContent = pokemon.types[0].type.name;
        tipo1.classList.add('tipo1', pokemon.types[0].type.name);
        tipo2.textContent = pokemon.types[1].type.name;
        tipo2.classList.add('tipo2', pokemon.types[1].type.name);
    }

    spriteContainer.appendChild(imagen);
    carta.appendChild(spriteContainer);
    carta.appendChild(number);
    carta.appendChild(name);

    tipos.appendChild(tipo1);
    tipos.appendChild(tipo2);
    carta.appendChild(tipos);

    pokemonContainer.appendChild(carta);

    /*fetch(url)
    .then((res) => res.json())
    .then((data) => {

        const desc = data.flavor_text_entries.find(entry => entry.language.name === 'es').flavor_text;
        const descripcion=document.createElement('p');
        descripcion.classList.add("descripcion");
        descripcion.textContent = desc;
        descripcion.style.display = "none";

        carta.appendChild(descripcion);
    });

    $(".pokemon").click(function () {
        $(".descripcion").show(200);
        $(".pokemon").style.position="fixed";
        $(".pokemon").style.width= "500px";


    });
    
    $(".pokemon").click(function () {
        $(".descripcion").hide(500);
        $(".pokemon").style.position= "initial";
    });*/

    const descripcion=document.createElement('p');
    var clicked = false;

    fetch(url)
    .then((res) => res.json())
    .then((data) =>{
        const desc = data.flavor_text_entries.find(entry => entry.language.name === 'es').flavor_text;
        descripcion.textContent=desc;
        descripcion.classList.add("descripcion");
        descripcion.style.display = "none";
        carta.appendChild(descripcion);
    });


    carta.addEventListener("click", () => {
        if (clicked == false){
            carta.style.width="90%";
            carta.style.height="80%";
            carta.style.position="fixed";
            imagen.style.width="20%";
            imagen.src = pokemon.sprites.other.dream_world.front_default;
            descripcion.style.display = "block";
            clicked = true;
        }else{
            carta.style.width="200px";
            carta.style.height="280px";
            carta.style.position="initial";
            imagen.style.width="70%";
            imagen.src=pokemon.sprites.front_default;
            descripcion.style.display = "none";
            clicked = false;
        }
    });
}



function removePokemons(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function removePokemonOculto(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

fetchAllPokemons(offset, limit);