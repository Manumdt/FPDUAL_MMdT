let pokemons=[];
const poke_container=document.getElementById('poke_container');
const url="https://pokeapi.co/api/v2/pokemon";
const pokemons_number=151;
const search=document.getElementById("search");
const form= document.getElementById("form");

const fetchPokemons= async() => {
    for(i = 1; i <= pokemons_number; i++){
        await getAllPokemon(i);
    }
    pokemons.forEach((pokemon)=>createPokemonCard(pokemon));
};

const removePokemon = ()=>{
    const pokemonEls=document.getElementsByClassName("pokemon");
    let removablePokemons=[];
    for(i=0; i<pokemonEls.length; i++){
        const pokemonEl=pokemonEls[i];
        removablePokemons=[...removablePokemons, pokemonEl];
    }
    removablePokemons.forEach((remPoke)=>remPoke.remove());
};

const getPokemon=async (id) =>{
    const searchPokemons = pokemons.filter((poke) =>poke.name === id);
    removePokemon();
    searchPokemons.forEach((pokemon) => createPokemonCard(pokemon));
};

const getAllPokemon=async (id) =>{
    const res=await fetch(`$[url]/$[id]`);
    const pokemon=await res.json();
    pokemons=[...pokemons,pokemon];
};

// fetchPokemons();

function createPokemonCard(pokemon){
    const pokemonEl=document.createElement("div");
    pokemonEl.classList.add("pokemon");
    const poke_types=pokemon.types.map((el)=> el.type.name).slice(0,1);
    const name= pokemon.name[0].toUpperCase + pokemon.name.slice(1);
    const poke_stat = pokemon.stat.map((el)=> el.stat.name);
    const stats= poke_stat.slice(0,3);
    const base_value= pokemon.stats.map((el)=> el.base_stat);
    const base_stat=base.value.slice(0,3);
    const stat= stats.map((stat)=> {
        return `<li class="names">${stat}</li>`
    }).join("");
    const base=base_stat.map((base)=>{
        return `<li class="base">${base}</li>`
    }).join("");
    const pokeInnerHTML= `<div class="img-container">
    <img src="https://www.google.com/search?q=pokemon+interrogacion&rlz=1C1GCEU_esES1050ES1050&sxsrf=APwXEdckxlUL9R1IuPtandxQojdgtAevWQ:1681126266104&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiOmaavm5_-AhUeUqQEHQvKBDwQ_AUoAXoECAIQAw&biw=1600&bih=757&dpr=1#imgrc=Dz2cD8_ROYxkbM" alt="${name}/>"
    </div>
    <div class="info">
    <span class="number">#${pokemon.id.toString().padStart(3,"0")}</span>
    <h3 class="name">${name}</h3>
    <small class="type"><span>${poke_types}</span><small>
    </div>
    <div class="stats">
    <h2>Stats</h2>
    <div class="flex"
    <ul>${stat}</ul>
    <ul>${base}</ul>
    </div>
    </div>`;
    pokemonEl.innerHTML = pokeInnerHTML;
    poke_container.appendChild(pokemonEl);
}

form.addEventListener("submit", (e)=>{
    e.preventDefault();
    const searchItem = search.nodeValue;
    if(searchTerm){
        getPokemon(searchTerm);
        search.value="";
    }else if(searchTerm === ""){
        pokemon=[];
        removePokemon();
        fetchPokemons();
    }
});