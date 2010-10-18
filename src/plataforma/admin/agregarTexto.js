$(document).ready(function() {
    $('#agregarTextoBoton').click(function() {
        $.post('../admin/agregarTexto.php', {
            'idiomaTexto': $('#idiomaTexto').val(),
            'metodoCifrado': $('#metodoCifrado').val(),
            'textoPlano': $('#textoPlano').val(),
            'textoCifrado': $('#textoCifrado').val(),
            'clave': $('#clave').val()
        }, function(data) {
            $('#respuestaAgregarTexto').fadeIn("slow");
            $('#respuestaAgregarTexto').html(data);
            setTimeout(function() {
                $('#respuestaAgregarTexto').fadeOut("slow");
            }, 3500);
        });
        return false;
    });
});
