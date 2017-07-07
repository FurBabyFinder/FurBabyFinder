/**
 * Created by frenchfryes on 7/7/17.
 */
$(document).ready(function() {
    $('.event-details').css('display','none');
    $('.event-details').css('height','auto');
    $('.event-details').css('margin-top','-17px');
    $('.event-details > .info').css('height','auto');
    $('.disabled').prev().css('cursor','default');


    $('.event-list > li').click(function() {
        if (!$(this).nextAll('.event-details').first().hasClass('disabled')) {
            //$(this).nextAll('.event-details').first().height('auto');
            $(this).nextAll('.event-details').first().nextAll('.info').first().height('auto');
            $(this).nextAll('.event-details').first().slideToggle();
        }
    });
})