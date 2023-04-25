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

/*function fetchOculto(id){
    fetch(`https://pokeapi.co/api/v2/pokemon/${id}/`)
    .then((res)=> res.json())
    .then((data)=> {
        crearOculto(data);
    });
}*/

function fetchAllPokemons(offset, limit) {
    for (let i = offset; i <= offset + limit; i++) {
        fetchPokemon(i);
    }
}

function crearPokemon(pokemon) {

    let url = pokemon.species.url;

    const carta = document.createElement('div');
    carta.classList.add('pokemon');

    // carta.addEventListener('click', ()=>{
    //     oculto.style.display="block";
    //     oculto.style.opacity="100%";
    //     crearOculto(pokemon.id);
    // });

    const desc = pokemon.flavor_text_entries.find(entry => entry.language.name === 'es').flavor_text; /** Filtramos el contenido por idioma y sólo cogemos la descripción el español ('es') */
    const pokemon_oculto = document.createElement('div');
    const descripcion = document.createElement('p')
    pokemon_oculto.classList.add('pokemon_oculto');
    descripcion.classList.add("descripcion");
    descripcion.textContent = desc;
    //descripcion.style.display = "none"; /* Dejamos la descripción oculta hasta que hagamos click */

    pokemon_oculto.appendChild(descripcion);
    oculto.appendChild(pokemon_oculto);


    $(".pokemon").click(function () {
        $(".oculto").show(100);
        $(".fondo_oculto").show(200);
        // crearOculto(pokemon.id);

        // fetch(url)
        //     .then((res) => res.json())
        //     .then((data) => {
        //         const desc = data.flavor_text_entries.find(entry => entry.language.name === 'es').flavor_text; /** Filtramos el contenido por idioma y sólo cogemos la descripción el español ('es') */
        //         const pokemon_oculto = document.createElement('div');
        //         pokemon_oculto.classList.add('pokemon_oculto');
        //         descripcion.classList.add("descripcion");
        //         descripcion.textContent = desc;
        //         //descripcion.style.display = "none"; /* Dejamos la descripción oculta hasta que hagamos click */

        //         pokemon_oculto.appendChild(descripcion);
        //         oculto.appendChild(pokemon_oculto);
        //     }); 
        



        /*
        const pokemonOculto=document.createElement('div');
        pokemonOculto.classList.add('pokemon_oculto');
    
        const sprite_number=document.createElement('div');
        sprite_number.classList.add('sprite_number');
    
        const sprite=document.createElement('img');
        sprite.classList.add('img_oculto');
        sprite.src= pokemon.sprites.front_default;
    
        const numero_oculto=document.createElement('p');
        numero_oculto.classList.add('numero_oculto');
        numero_oculto.textContent= `#${pokemon.id.toString().padStart(3, 0)}`;
    
        sprite_number.appendChild(sprite);
        sprite_number.appendChild(numero_oculto);
    
        oculto.appendChild(sprite_number);
    
        oculto.appendChild(pokemonOculto);*/
    });

    $(".oculto").click(function () {
        $(".oculto").hide(500);
        $(".fondo_oculto").hide(500);
        //  removePokemons(pokemon_oculto);
    });

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
}

// function  crearOculto(id, pokemon_oculto){

//     // fetchOculto();

//     id -= 1;

//         fetch(`https://pokeapi.co/api/v2/pokemon/${id}/`)
//         .then((res) => res.json())
//         .then((data) => {
//             crearOculto(id, data);
//         });

//     const div_oculto = document.createElement('div');
//     div_oculto.classList.add('pokemon_oculto');

//     const sprite_number = document.createElement('div');
//     sprite_number.classList.add('sprite_number');

//     const sprite = document.createElement('img');
//     sprite.classList.add('img_oculto');
//     sprite.src = pokemon_oculto.sprites.front_default;

//     const number = document.createElement('p');
//     number.classList.add('number_oculto');
//     number.textContent = `#${pokemon_oculto.id.toString().padStart(3, 0)}`;

//     sprite_number.appendChild(sprite);
//     sprite_number.appendChild(number);

//     oculto.appendChild(sprite_number);
// }

function removePokemons(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

fetchAllPokemons(offset, limit);