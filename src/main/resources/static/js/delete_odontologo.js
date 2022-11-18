window.addEventListener('load', function () {

    const formulario = document.querySelector('#delete');

    formulario.addEventListener('submit', function (event) {

        event.preventDefault();

        const idOdontologo = document.querySelector('#id').value;

        const url = '/odontologos/eliminar/' + idOdontologo;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then()
            .then(() => {
                alert("Odontologo eliminado correctamente");
                resetForm();
            })
            .catch(error => {
                alert("El ID ingresado no existe");
                console.log(error);
                resetForm();
            })
    });

    function resetForm() {
        document.querySelector('#id').value = "";
    }
})