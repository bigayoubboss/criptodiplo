$(document).ready(function() {
    $('#formLogin').validate({
        rules: {
            usuario: {
                required: true
            },
            contrasena: {
                required: true
            }
        },
        messages: {
            usuario: {
                required: "Ingrese un nombre de usuario"
            },
            contrasena: {
                required: "Ingrese una contrase&ntilde;a"
            }
        },
        submitHandler: function() {
            $.post('../uicontrolador/Login.php', {
                'op': 'login',
                'usuario': $('#usuario').val(),
                'contrasena': sha256_digest($('#contrasena').val())
            }, function(data) {
                if(data=="true"){
                    window.location = "../ui/inicio.php"
                } else {
                    $('#respuesta').html(data);
                    $('#respuesta').fadeIn("slow");
                    $('#respuesta').html(data);
                    setTimeout(function() {
                        $('#respuesta').fadeOut("slow");
                    }, 5000);
                }
            });
            return false;
        }
    });
});

