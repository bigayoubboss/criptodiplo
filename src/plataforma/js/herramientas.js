$(document).ready(function() {
    $('#cargarHerramienta').click(function() {
        $.post('../admin/herramientas.php', {
            'op': 'cargar',
            'herramienta': $('#herramientasOpciones').val()
        }, function(data) {
            $('#herramientas').fadeOut("slow");
            setTimeout(function() {
                $('#herramientas').html(data);
                $('#herramientas').fadeIn("slow");
                $('tr:odd').addClass('zebra');
            }, 1500);
        });
        return false;
    });
});