$(document).ready(function() {
    $('#cambiarEstadoActividadBoton').click(function() {
            $.post('../admin/cambiarEstadoActividad.php', {
                'actividad': $('#actividad').val(),
                'estadoActividad': $('#estadoActividad').val()
            }, function(data) {
                $('#respuestaEstadoActividad').fadeIn("slow");
                $('#respuestaEstadoActividad').html(data);
                setTimeout(function() { $('#respuestaEstadoActividad').fadeOut("slow"); }, 3500);
            });
        return false;
    });
});
