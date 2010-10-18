$(document).ready(function() {
    $('#logout').click(function() {
        $.post('../uicontrolador/Login.php', {
            'op': 'logout'
        }, function(data) {
            if(data=="true"){
                window.location = "../index.php"
            } 
        });
        return false;
    });
});
