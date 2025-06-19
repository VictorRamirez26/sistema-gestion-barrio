$(document).ready(function() {
    // Manejar clics en los elementos del panel izquierdo para mostrar los sub-menús
    $('#left-panel .menu-item-has-children > a').on('click', function(e) {
        var $submenu = $(this).next('.sub-menu');

        if ($submenu.is(':visible')) {
            $submenu.slideUp();
        } else {
            $('#left-panel .sub-menu').slideUp();  // Ocultar otros sub-menús
            $submenu.stop(true, true).slideDown();  // Mostrar el sub-menú actual
        }

        e.preventDefault();  // Evitar el comportamiento por defecto del enlace
    });
});
