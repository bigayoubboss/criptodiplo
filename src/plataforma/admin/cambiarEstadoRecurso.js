$(document).ready(function() {
    $('#cambiarEstadoRecursoBoton').click(function() {
        $.post('../admin/cambiarEstadoRecurso.php', {
            'recurso' : $('#recurso').val(),
            'estadoRecurso' : $('#estadoRecurso').val()
        }, function(data) {
            $('#respuestaEstadoRecurso').fadeIn("slow");
            $('#respuestaEstadoRecurso').html(data);
            setTimeout(function() {
                $('#respuestaEstadoRecurso').fadeOut("slow");
            }, 3500);
        });
        return false;
    });
});
