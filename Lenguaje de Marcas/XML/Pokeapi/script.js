const pokemonContainer = document.querySelector('.pokemon-container');
const anterior = document.querySelector('#anterior');
const siguiente = document.querySelector('#siguiente');
const oculto = document.querySelector('.oculto');
const body = document.querySelector('.body');

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
    carta.classList.add('noHover');

    const col33=document.createElement('div');
    col33.classList.add('col33');
    
    const col66=document.createElement('div');
    col66.classList.add('col66');

    const spriteContainer = document.createElement('div');
    spriteContainer.classList.add('sprite');

    const imagen = document.createElement('img');
    imagen.src = pokemon.sprites.front_default;

    const nombreNumero=document.createElement('div');
    nombreNumero.classList.add('nombreNumero');

    const number = document.createElement('p');
    number.classList.add('number');
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

    const descripcion=document.createElement('p');
    const peso=document.createElement('p');
    const altura=document.createElement('p');
    const alturaPeso=document.createElement('div');
    alturaPeso.classList.add('alturaPeso');
    const stats=document.createElement('div');
    stats.classList.add('stats');
    const statHp=document.createElement('div');
    statHp.classList.add('stat');
    const statAttack=document.createElement('div');
    statAttack.classList.add('stat');
    const statDefense=document.createElement('div');
    statDefense.classList.add('stat');
    const statSpecialAttack=document.createElement('div');
    statSpecialAttack.classList.add('stat');
    const statSpecialDefense=document.createElement('div');
    statSpecialDefense.classList.add('stat');
    const statSpeed=document.createElement('div');
    statSpeed.classList.add('stat');
    const hp=document.createElement('meter');
    const attack=document.createElement('meter');
    const defense=document.createElement('meter');
    const specialAttack=document.createElement('meter');
    const specialDefense=document.createElement('meter');
    const speed=document.createElement('meter');
    const hpName=document.createElement('p');
    const attackName=document.createElement('p');
    const defenseName=document.createElement('p');
    const specialAttackName=document.createElement('p');
    const specialDefenseName=document.createElement('p');
    const speedName=document.createElement('p');
    var clicked = false;

    fetch(url)
    .then((res) => res.json())
    .then((data) =>{
        const desc = data.flavor_text_entries.find(entry => entry.language.name === 'es').flavor_text;
        descripcion.textContent=desc;
        descripcion.classList.add("descripcion");
        descripcion.style.display = "none";

        peso.textContent="Peso: " + (pokemon.weight*0.1).toFixed(2) + " Kg";
        peso.classList.add('peso');
        carta.appendChild(peso);

        altura.textContent="Altura: " + (pokemon.height*0.1).toFixed(2) + " m";
        altura.classList.add('altura');

        hp.setAttribute('min','1');
        hp.setAttribute('max','255');
        hp.setAttribute('low','80');
        hp.setAttribute('high','180');
        hp.setAttribute('optium','200');
        hp.setAttribute('value', pokemon.stats[0].base_stat);
        hpName.textContent=(pokemon.stats[0].stat.name).toUpperCase();

        attack.setAttribute('min','1');
        attack.setAttribute('max','255');
        attack.setAttribute('low','80');
        attack.setAttribute('high','180');
        attack.setAttribute('optium','200');
        attack.setAttribute('value', pokemon.stats[1].base_stat);
        attackName.textContent=(pokemon.stats[1].stat.name).toUpperCase();

        defense.setAttribute('min','1');
        defense.setAttribute('max','255');
        defense.setAttribute('low','80');
        defense.setAttribute('high','180');
        defense.setAttribute('optium','200');
        defense.setAttribute('value', pokemon.stats[2].base_stat);
        defenseName.textContent=(pokemon.stats[2].stat.name).toUpperCase();

        specialAttack.setAttribute('min','1');
        specialAttack.setAttribute('max','255');
        specialAttack.setAttribute('low','80');
        specialAttack.setAttribute('high','180');
        specialAttack.setAttribute('optium','200');
        specialAttack.setAttribute('value', pokemon.stats[3].base_stat);
        specialAttackName.textContent=(pokemon.stats[3].stat.name).toUpperCase();

        specialDefense.setAttribute('min','1');
        specialDefense.setAttribute('max','255');
        specialDefense.setAttribute('low','80');
        specialDefense.setAttribute('high','180');
        specialDefense.setAttribute('optium','200');
        specialDefense.setAttribute('value', pokemon.stats[4].base_stat);
        specialDefenseName.textContent=(pokemon.stats[4].stat.name).toUpperCase();

        speed.setAttribute('min','1');
        speed.setAttribute('max','255');
        speed.setAttribute('low','80');
        speed.setAttribute('high','180');
        speed.setAttribute('optium','200');
        speed.setAttribute('value', pokemon.stats[5].base_stat);
        speedName.textContent=(pokemon.stats[5].stat.name).toUpperCase();

        statHp.appendChild(hpName);
        statAttack.appendChild(attackName);
        statDefense.appendChild(defenseName);
        statSpecialAttack.appendChild(specialAttackName);
        statSpecialDefense.appendChild(specialDefenseName);
        statSpeed.appendChild(speedName);

        statHp.appendChild(hp);
        statAttack.appendChild(attack);
        statDefense.appendChild(defense);
        statSpecialAttack.appendChild(specialAttack);
        statSpecialDefense.appendChild(specialDefense);
        statSpeed.appendChild(speed);

        stats.appendChild(statHp);
        stats.appendChild(statAttack);
        stats.appendChild(statDefense);
        stats.appendChild(statSpecialAttack);
        stats.appendChild(statSpecialDefense);
        stats.appendChild(statSpeed);
        
        alturaPeso.appendChild(altura);
        alturaPeso.appendChild(peso);

    });

    spriteContainer.appendChild(imagen);
    col33.appendChild(spriteContainer);
    
    col66.appendChild(descripcion);
    
    nombreNumero.appendChild(number);
    nombreNumero.appendChild(name);
    col33.appendChild(nombreNumero);

    tipos.appendChild(tipo1);
    tipos.appendChild(tipo2);
    col33.appendChild(tipos);

    col33.appendChild(alturaPeso);

    col66.appendChild(stats);

    carta.appendChild(col33);
    carta.appendChild(col66);

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

    carta.addEventListener("click", () => {
        if (clicked == false){
            carta.style.width="90%";
            carta.style.height="70%";
            carta.style.position="fixed";
            carta.style.paddingTop="50px";
            carta.style.paddingBottom="50px";
            carta.style.paddingLeft="20px";
            carta.style.transition="200ms";
            carta.style.boxShadow="1px 1px 20px white";
            carta.classList.remove('noHover');
            col33.style.width="33%";
            col66.style.display="block";
            imagen.style.width="100%";
            imagen.style.maxHeight="200px";
            imagen.style.paddingBottom="20px";
            imagen.src = pokemon.sprites.other.dream_world.front_default;
            tipos.style.width="100%";
            tipos.style.display="flex";
            tipos.style.flexDirection="column";
            tipos.style.justifyContent="left";
            tipos.style.margin="0";
            tipo1.style.width="100%";
            tipo1.style.paddingTop="10px";
            tipo2.style.width="100%";
            tipo2.style.paddingTop="10px";
            spriteContainer.style.textAlign="left";
            spriteContainer.style.width="100%";
            spriteContainer.style.display="flex";
            number.style.width="100%";
            name.style.width="100%";
            nombreNumero.style.width="100%";
            nombreNumero.style.marginBottom="1%";
            nombreNumero.style.display="flex";
            nombreNumero.style.flexDirection="column";
            altura.style.display="block";
            peso.style.display="block";
            alturaPeso.style.display="flex";
            alturaPeso.style.flexDirection="column";
            stats.style.display="block";
            descripcion.style.display="block";
            clicked = true;
        }else{
            carta.style.width="200px";
            carta.style.height="280px";
            carta.style.position="initial";
            carta.style.paddingTop="10px";
            carta.style.paddingBottom="20px";
            carta.style.paddingLeft="0";
            carta.style.boxShadow="none";  
            carta.classList.add('noHover');
            col33.style.width="100%";
            col66.style.display="none";
            imagen.style.width="70%";
            imagen.style.maxHeight="none";
            imagen.style.paddingBottom="0";
            imagen.src=pokemon.sprites.front_default;
            tipos.style.width="60%";
            tipos.style.float="initial";
            tipos.style.display="block";
            tipos.style.flexDirection="none";
            tipos.style.margin="0 auto";
            tipo1.style.paddingTop="5px";
            tipo1.style.width="initial";
            tipo2.style.paddingTop="5px";
            tipo2.style.width="initial";
            spriteContainer.style.textAlign="center";
            spriteContainer.style.width="initial";
            spriteContainer.style.display="initial";
            number.style.width="100%";
            name.style.width="100%";
            nombreNumero.style.width="100%";
            nombreNumero.style.display="initial";
            nombreNumero.style.flexDirection="initial";
            altura.style.display="none";
            peso.style.display="none";
            alturaPeso.style.display="none";
            alturaPeso.style.flexDirection="initial";
            stats.style.display="none";
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