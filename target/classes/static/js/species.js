/**
 * Created by melodytempleton on 7/5/17.
 */
(function ($) {

    var x="";

    $.ajax({
        url: "/petTypes.json",
        data: {list: x}
    }).done(function (list) {
// types is the JSON response produce by our controller
        var types = list.content;
// $species is the div element where we want to add the next page of posts
        var $species = $("[data-types]");

    });

})(jQuery);
