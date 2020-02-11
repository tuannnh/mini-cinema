//Filter News
$('.nav-link').click(function () {
    var filter = $(this).text();
    filter = $.trim(filter);
    filterList(filter);
});

//News filter function
function filterList(value) {
    var list = $(".movie-list .movie-item");
    $(list).fadeOut("fast");
    console.log('Value =' + value);
    if (value == 'All') {
        $(".movie-list").find("article").each(function (i) {
            $(this).delay(200).slideDown("fast");
        });
    } else {
        //Notice this *=" <- This means that if the data-category contains multiple options, it will find them
        //Ex: data-category="Cat1, Cat2"
        $(".movie-list").find("article[data-category*=" + value + "]").each(function (i) {
            $(this).delay(200).slideDown("fast");
        });
    }
}