const boton=document.getElementById('boton');
const APIdata=document.getElementById('APIdata');
const BaseExperience=document.getElementById('BaseExperiencie');

const llamarAPI=()=>{
    fetch('https://pokeapi.co/api/v2/pokemon/ditto')
    .then(res=>res.json())   
    .then(data=>{
        APIdata.innerText=JSON.stringify(data);
        BaseExperience.innerText=`Experiencia Base: ${JSON.stringify(data.base_experience)} `;
    })
    .cath(e=>console.error(new Error(e)));
}

boton.addEventListener('click',llamarAPI);