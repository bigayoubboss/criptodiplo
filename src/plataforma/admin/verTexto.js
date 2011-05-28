$(document).ready(function() {
    $('#verTextoBoton').click(function() {
        $.post('../admin/verTexto.php', {
            'idTexto' : $('#idTexto').val()
        }, function(data) {
            $('#respuestaVerTexto').fadeIn("slow");
            $('#respuestaVerTexto').html(data);
        });
        return false;
    });
});
