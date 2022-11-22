$(document).ready(function () {
    // select active page in navbar
    let page = document.querySelector('meta[name="page"]').content;
    $(".nav" + page).addClass("active");

    let elems = $('select');
    M.FormSelect.init(elems, {});

    // enable side navbar
    elems = $('.sidenav');
    M.Sidenav.init(elems, {});
});