$(document).ready(function() {
	$('#agregarRecursoBoton').click(function() {
		$.post('../admin/agregarRecurso.php', {
			'nombre' : $('#nombre').val(),
			'enlace' : $('#enlace').val(),
			'prerequisitos' : $('#prerequisitos').val(),
			'idMetodo' : $('#idMetodo').val()
		}, function(data) {
			$('#respuestaAgregarRecurso').fadeIn("slow");
			$('#respuestaAgregarRecurso').html(data);
			setTimeout(function() {
				$('#respuestaAgregarRecurso').fadeOut("slow");
			}, 3500);
		});
		return false;
	});
});
