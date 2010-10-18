$(document).ready(function() {
    $('#cambiarEstadoUsuarioBoton').click(function() {
        $.post('../admin/cambiarEstadoUsuario.php', {
            'usuario': $('#usuario').val(),
            'estadoUsuario': $('#estadoUsuario').val()
        }, function(data) {
            $('#respuestaEstadoUsuario').fadeIn("slow");
            $('#respuestaEstadoUsuario').html(data);
            setTimeout(function() {
                $('#respuestaEstadoUsuario').fadeOut("slow");
            }, 3500);
        });
        return false;
    });
});
