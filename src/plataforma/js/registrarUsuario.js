$(document).ready(function() {
    $('#formNuevoUsuario').validate({
        rules: {
            usuario: {
                required: true,
                minlength: 4,
                remote: "../uicontrolador/Registro.php"
            },
            contrasena: {
                required: true,
                minlength: 6
            },
            contrasena2: {
                required: true,
                minlength: 6,
                equalTo: "#contrasena"
            },
            nombres: {
                required: true,
                minlength: 3
            },
            apellidos: {
                required: true,
                minlength: 3
            },
            correo_electronico: {
                required: true,
                email: true
            }
        },
        messages: {
            usuario: {
                required: "Ingrese un nombre de usuario",
                minlength: jQuery.format("Debe ser m&iacute;nimo de {0} caracteres"),
                remote: "Nombre de usuario ya registrado"
            },
            contrasena: {
                required: "Ingrese una contrase&ntilde;a",
                minlength: jQuery.format("Debe ser m&iacute;nimo de {0} caracteres")
            },
            contrasena2: {
                required: "Ingrese de nuevo su contrase&ntilde;a",
                minlength: jQuery.format("Debe ser m&iacute;nimo de {0} caracteres"),
                equalTo: "Ingrese la misma contrase&ntilde;a"
            },
            nombres: {
                required: "Ingrese sus nombres",
                minlength: jQuery.format("Debe ser m&iacute;nimo de {0} caracteres")
            },
            apellidos: {
                required: "Ingrese sus apellidos",
                minlength: jQuery.format("Debe ser m&iacute;nimo de {0} caracteres")
            },
            correo_electronico: {
                required: "Ingrese un correo electr&oacute;nico",
                email: "Ingrese un correo electr&oacute;nico v&aacute;lido"
            }
        },
        submitHandler: function() {
            $.post('../uicontrolador/Registro.php', {
                'op': 'registrarUsuario',
                'usuario': $('#usuario').val(),
                'contrasena': sha256_digest($('#contrasena').val()),
                'nombres': $('#nombres').val(),
                'apellidos': $('#apellidos').val(),
                'correo_electronico': $('#correo_electronico').val()
            }, function(data) {
                if(data=="true"){
                    window.location = "../ui/inicio.php"
                } else {
                    $('#respuesta').html(data);
                }
            });
            return false;
        }
    });
});

