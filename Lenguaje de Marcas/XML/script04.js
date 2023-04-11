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

request.open("Get" , "https://pokeapi.co/api/v2/pokemon/" , true);
request.send();

const index=1;

$(document).ready(function() {
    $.get("https://pokeapi.co/api/v2/pokemon/", function(data) {
      data.results.forEach(function(pokemon, index) {
        // Make another request to get the full Pokemon object
        $.get(pokemon.url, function(pokemonData) {
          const types = pokemonData.types.map(function(type) {
            return type.type.name;
           }).join(", ");

          const pokemonNumber = index + 1;

          const listItem = `
            <li class="${types}">
              <span class="upper">${pokemonNumber}. ${pokemon.name}</span>
              <span>${types}</span>
              <img src="${pokemonData.sprites.front_default}" alt="${pokemon.name}">
            </li>
          `;
          $("#lista").append(listItem);
        });
      });
    });
  });