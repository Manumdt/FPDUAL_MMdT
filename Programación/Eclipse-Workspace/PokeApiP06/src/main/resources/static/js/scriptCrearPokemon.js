document.getElementById('formularioCrear').addEventListener('submit', function(event) { event.preventDefault();

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
            location.href = "../html/index.html";
        }, 2000);
    })
        .catch(error => {
        console.error('Ocurrió un error en la solicitud:', error);
        setTimeout(function() {
            window.location.reload();
        }, 2000);
    });
    });