$(document).ready(function() {
    $('#cambiarEstadoSeccionBoton').click(function() {
        $.post('../admin/cambiarEstadoSeccion.php', {
            'seccion' : $('#seccion').val(),
            'estadoSeccion' : $('#estadoSeccion').val()
        }, function(data) {
            $('#respuestaEstadoSeccion').fadeIn("slow");
            $('#respuestaEstadoSeccion').html(data);
            setTimeout(function() {
                $('#respuestaEstadoSeccion').fadeOut("slow");
            }, 3500);
        });
        return false;
    });
});
