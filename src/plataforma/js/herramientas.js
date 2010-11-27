$(document).ready(function() {
    $('#cargarHerramienta').click(function() {
        $.post('../admin/herramientas.php', {
            'op': 'cargar',
            'herramienta': $('#herramientasOpciones').val()
        }, function(data) {
            $('#herramientas').fadeOut("fast");
            setTimeout(function() {
                $('#herramientas').html(data);
                $('#herramientas').fadeIn("fast");
                $('tr:odd').addClass('zebra');
            }, 500);
        });
        return false;
    });
});