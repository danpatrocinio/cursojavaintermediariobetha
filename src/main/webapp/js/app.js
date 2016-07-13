$(function () {

    // carregar o header
    $.get('views/partials/header-menu.html', function (data) {
        $("nav#menu").html(data);
    });

    // carregar o footer
    $.get('views/partials/footer.html', function (data) {
        $("nav#footer").html(data);
    });

});