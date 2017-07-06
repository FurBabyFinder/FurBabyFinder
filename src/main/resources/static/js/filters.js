/**
 * Created by melodytempleton on 7/6/17.
 */
(function ($) {

    var x="";

    $.ajax({
        url: "/filterSearch.json",
        data: {filterList: x}
    }).done(function (list) {
// filters is the JSON response produce by our controller
        var filters = filterList.content;
// $filters is the div element where we want to add the next page of posts
        var $filters = $("[data-filters]");

    });

})(jQuery);
