$(document).ready(function() {
	$('#cambiarTextoBoton').click(function() {
		$.post('../admin/cambiarTexto.php', {
			'idUsuario' : $('#idUsuario').val(),
			'idActividad' : $('#idActividad').val()
		}, function(data) {
			$('#respuestaCambiarTexto').fadeIn("slow");
			$('#respuestaCambiarTexto').html(data);
			setTimeout(function() {
				$('#respuestaCambiarTexto').fadeOut("slow");
			}, 5500);
		});
		return false;
	});
});
