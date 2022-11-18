window.addEventListener('load', function () {

    const formulario = document.querySelector('#update');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const datosOdontologo = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        const url = '/odontologos/modificar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosOdontologo)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert("Odontologo modificado correctamente");
                console.log(data);
                resetForm();
            })
            .catch(error => {
                alert("No se pudo agregar el odontológo");
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }
})