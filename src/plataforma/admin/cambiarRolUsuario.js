$(document).ready(function() {
    $('#cambiarRolUsuarioBoton').click(function() {
        $.post('../admin/cambiarRolUsuario.php', {
            'usuario': $('#usuario').val(),
            'rolUsuario': $('#rolUsuario').val()
        }, function(data) {
            $('#respuestaRolUsuario').fadeIn("slow");
            $('#respuestaRolUsuario').html(data);
            setTimeout(function() {
                $('#respuestaRolUsuario').fadeOut("slow");
            }, 3500);
        });
        return false;
    });
});
