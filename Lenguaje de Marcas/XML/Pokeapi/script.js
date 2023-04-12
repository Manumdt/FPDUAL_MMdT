const pokemonContainer= document.querySelector('.pokemon-container');

function fetchPokemon(id){
    fetch(`https://pokeapi.co/api/v2/pokemon/${id}/`)
    .then((res)=> res.json())
    .then((data)=> {
        crearPokemon(data);
    });
}

function fetchAllPokemons(number){
    for(let i=1; i<=number; i++){
        fetchPokemon(i);
    }
}

function crearPokemon(pokemon){
    const carta= document.createElement('div');
    carta.classList.add('pokemon');

    const spriteContainer=document.createElement('div');
    spriteContainer.classList.add('sprite');

    const imagen=document.createElement('img');
    imagen.src= pokemon.sprites.front_default;

    spriteContainer.appendChild(imagen);
    
    const number= document.createElement('p');
    number.textContent= `#${pokemon.id.toString().padStart(3, 0)}`;

    const name= document.createElement('p');
    name.classList.add('name');
    name.textContent=pokemon.name;

    const tipos= document.createElement('p');
    if(pokemon.types.length==1){
        tipos.textContent= pokemon.types[0].type.name;
        tipos.classList.add('tipos', pokemon.types[0].type.name);
    }else if(pokemon.types.length==2){
        tipos.textContent= pokemon.types[0].type.name + " " + pokemon.types[1].type.name;
        tipos.classList.add('tipos', pokemon.types[0].type.name, pokemon.types[1].type.name);
    }


    carta.appendChild(spriteContainer);
    carta.appendChild(number);
    carta.appendChild(name);
    carta.appendChild(tipos);

    pokemonContainer.appendChild(carta);
}

fetchAllPokemons(12);