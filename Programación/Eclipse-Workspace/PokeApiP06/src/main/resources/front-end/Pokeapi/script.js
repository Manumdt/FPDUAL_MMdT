const pokemonContainer = document.querySelector('.pokemon-container');
const anterior = document.querySelector('#anterior');
const siguiente = document.querySelector('#siguiente');
const oculto = document.querySelector('.oculto');
const body = document.querySelector('.body');
const nav = document.querySelector('.nav');

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
    fetch(`http://localhost:8080/api/find/${id}`)
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

buscar.addEventListener('keyup', ()=>{
    
    var buscar = document.querySelector('#buscar').value;
    removePokemons(pokemonContainer);

    console.log(buscar);

    if(buscar.length < 1){
        fetchAllPokemons(offset, limit);
    }else{   
        fetch(`http://localhost:8080/api/find/${buscar}`)
        .then((res) => res.json())
        .then((data) => {
            crearPokemon(data);
        });
    }
});

document.querySelector('#eliminarFormulario').addEventListener('submit', buscarPokemon);

function buscarPokemon(event){
    event.preventDefault();

    var eliminar = document.querySelector('#eliminar').value;
    const mensajeEliminar = document.querySelector('.mensajeEliminar');
    const mensaje = document.querySelector('.mensaje');

    fetch(`http://localhost:8080/api/find/${eliminar}`)
    .then((res) => res.json())
    .then((data) => {
        if(data == null){
            mensaje.textContent = "Ha ocurrido un error";
            mensajeEliminar.classList.remove('correcto');
            mensajeEliminar.classList.add('error');
            mensajeEliminar.style.display = "block";
            setTimeout(function() {
                window.location.reload();
            }, 2000);
        }else{
            eliminarPokemonPorId(eliminar);
        }
    })
    .catch(error => {
        console.log(error);
        mensaje.textContent = "Ha ocurrido un error";
        mensajeEliminar.classList.remove('correcto');
        mensajeEliminar.classList.add('error');
        mensajeEliminar.style.display = "block";
        setTimeout(function() {
            window.location.reload();
        }, 2000);
    });
}

function eliminarPokemonPorId(eliminar) {
    
    const mensajeEliminar = document.querySelector('.mensajeEliminar');
    const mensaje = document.querySelector('.mensaje');

    fetch(`http://localhost:8080/api/delete/${eliminar}`, {
        method: 'DELETE',
        mode: 'cors',
        headers: {
            'Access-Control-Allow-Origin': 'http://127.0.0.1:5500'
        }
    })
        .then((data) => {
            console.log(data);
            mensaje.textContent = "El pokemon se ha eliminado correctamente";
            mensajeEliminar.classList.remove('error');
            mensajeEliminar.classList.add('correcto');
            mensajeEliminar.style.display = "block";
            setTimeout(function() {
                window.location.reload();
            }, 2000);
        })
        .catch(error => {
            console.log(error);
            mensaje.textContent = "Ha ocurrido un error";
            mensajeEliminar.classList.remove('correcto');
            mensajeEliminar.classList.add('error');
            mensajeEliminar.style.display = "block";
            setTimeout(function() {
                window.location.reload();
            }, 2000);
        });
}      

    function crearPokemon(pokemon) {

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
    if(pokemon.url.length < 1){
        imagen.src = "img/interrogacion.png"
    }else{
        imagen.src = pokemon.url;
    }
 
    const nombreNumero=document.createElement('div');
    nombreNumero.classList.add('nombreNumero');

    const number = document.createElement('p');
    number.classList.add('number');
    number.textContent = `#${pokemon.numero_pokedex.toString().padStart(3, 0)}`;

    const name = document.createElement('p');
    name.classList.add('name');
    name.textContent = pokemon.nombre;

    const tipos = document.createElement('div');
    tipos.classList.add('tipos');
    const tipo1 = document.createElement('p');
    const tipo2 = document.createElement('p');
    if (pokemon.tipos.length == 1) {
        tipo1.textContent = pokemon.tipos[0].nombre;
        tipo1.classList.add('tipo1', pokemon.tipos[0].nombre);
    } else if (pokemon.tipos.length == 2) {
        tipo1.textContent = pokemon.tipos[0].nombre;
        tipo1.classList.add('tipo1', pokemon.tipos[0].nombre);
        tipo2.textContent = pokemon.tipos[1].nombre;
        tipo2.classList.add('tipo2', pokemon.tipos[1].nombre);
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
    const statSpecial=document.createElement('div');
    statSpecial.classList.add('stat');
    const statSpeed=document.createElement('div');
    statSpeed.classList.add('stat');
    const hp=document.createElement('meter');
    const attack=document.createElement('meter');
    const defense=document.createElement('meter');
    const special=document.createElement('meter');
    const speed=document.createElement('meter');
    const hpName=document.createElement('p');
    const attackName=document.createElement('p');
    const defenseName=document.createElement('p');
    const specialName=document.createElement('p');
    const speedName=document.createElement('p');
    var clicked = false;

    fetch(`http://localhost:8080/api/find/${pokemon.numero_pokedex}`)
    .then((res) => res.json())
    .then((data) =>{

        descripcion.textContent= pokemon.descripcion;
        descripcion.classList.add("descripcion");
        descripcion.style.display = "none";

        peso.textContent="Peso: " + (pokemon.peso).toFixed(2) + " Kg";
        peso.classList.add('peso');
        carta.appendChild(peso);

        altura.textContent="Altura: " + (pokemon.altura).toFixed(2) + " m";
        altura.classList.add('altura');

        hp.setAttribute('min','1');
        hp.setAttribute('max','255');
        hp.setAttribute('low','80');
        hp.setAttribute('high','180');
        hp.setAttribute('optium','200');
        hp.setAttribute('value', pokemon.stats.ps);
        hpName.textContent = "HP";

        attack.setAttribute('min','1');
        attack.setAttribute('max','255');
        attack.setAttribute('low','80');
        attack.setAttribute('high','180');
        attack.setAttribute('optium','200');
        attack.setAttribute('value', pokemon.stats.ataque);
        attackName.textContent= "ATAQUE"

        defense.setAttribute('min','1');
        defense.setAttribute('max','255');
        defense.setAttribute('low','80');
        defense.setAttribute('high','180');
        defense.setAttribute('optium','200');
        defense.setAttribute('value', pokemon.stats.defensa);
        defenseName.textContent= "DEFENSA";

        special.setAttribute('min','1');
        special.setAttribute('max','255');
        special.setAttribute('low','80');
        special.setAttribute('high','180');
        special.setAttribute('optium','200');
        special.setAttribute('value', pokemon.stats.especial);
        specialName.textContent= "ESPECIAL";

        speed.setAttribute('min','1');
        speed.setAttribute('max','255');
        speed.setAttribute('low','80');
        speed.setAttribute('high','180');
        speed.setAttribute('optium','200');
        speed.setAttribute('value', pokemon.stats.velocidad);
        speedName.textContent= "VELOCIDAD";

        statHp.appendChild(hpName);
        statAttack.appendChild(attackName);
        statDefense.appendChild(defenseName);
        statSpecial.appendChild(specialName);
        statSpeed.appendChild(speedName);

        statHp.appendChild(hp);
        statAttack.appendChild(attack);
        statDefense.appendChild(defense);
        statSpecial.appendChild(special);
        statSpeed.appendChild(speed);

        stats.appendChild(statHp);
        stats.appendChild(statAttack);
        stats.appendChild(statDefense);
        stats.appendChild(statSpecial);
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
            imagen.style.width="50%";
            imagen.style.paddingBottom="20px";
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
            spriteContainer.style.justifyContent="center";
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
            nav.style.display="none";
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
            nav.style.display="flex";
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