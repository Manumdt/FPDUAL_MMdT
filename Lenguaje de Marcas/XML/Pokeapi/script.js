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

    const type= document.createElement('p');
    type.classList.add('type');
    type.textContent=pokemon.types.name;

    carta.appendChild(spriteContainer);
    carta.appendChild(number);
    carta.appendChild(name);
    carta.appendChild(type);

    pokemonContainer.appendChild(carta);
}

fetchAllPokemons(12);