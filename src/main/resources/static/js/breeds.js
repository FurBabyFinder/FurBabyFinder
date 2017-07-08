/**
 * Created by melodytempleton on 7/7/17.
 */

(function ($) {

    var x="";

    $.ajax({
        url: "/petBreeds.json",
        data: {breeds: x}
    }).done(function (list) {
// types is the JSON response produce by our controller
        var speciesBreeds = breeds.content;
// $species is the div element where we want to add the next page of posts
        var $breeds = $("[data-speciesBreeds]");

    });

})(jQuery);
