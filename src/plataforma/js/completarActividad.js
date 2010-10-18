$(document).ready(function() {
    $('#actividadFormularioCompletar').validate({
        rules: {
            clave: {
                required: true
            }
        },
        messages: {
            clave: {
                required: "Ingrese una respuesta"
            }
        },
        submitHandler: function() {
            $.post('../uicontrolador/Actividad.php', {
                'op': 'completarActividad',
                'clave': $('#clave').val(),
                'idActividad': $('#idActividad').val()
            }, function(data) {
                if(data=="true"){
                    $('#respuestaCompletar').fadeIn("slow");
                    data = "<span class='mensaje'>Respuesta correcta. Aguarde mientras su actividad es finalizada</span>";
                    $('#respuestaCompletar').html(data);
                    setTimeout(function() {
                        window.location.reload(true);
                    }, 3500);
                } else {
                    $('#respuestaCompletar').fadeIn("slow");
                    $('#respuestaCompletar').html(data);
                    setTimeout(function() {
                        $('#respuestaCompletar').fadeOut("slow");
                    }, 3500);
                }
            });
            return false;
        }
    });
});

