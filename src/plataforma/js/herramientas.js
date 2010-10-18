$(document).ready(function() {
    $('#cargarHerramienta').click(function() {
        $.post('../admin/herramientas.php', {
            'op': 'cargar',
            'herramienta': $('#herramientasOpciones').val()
        }, function(data) {
            setTimeout(function() {
                $('#herramientas').html(data);
                $('tr:odd').addClass('zebra');
            }, 1500);
        });
        return false;
    });
});