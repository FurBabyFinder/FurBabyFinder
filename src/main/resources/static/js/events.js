/**
 * Created by frenchfryes on 7/7/17.
 */
$(document).ready(function () {
    var map;
    $('.event-details').css('display', 'block');
    $('.event-details').css('height', 'auto');
    $('.event-details').css('margin-top', '-17px');
    $('.event-details > .info').css('height', 'auto');
    $('.disabled').prev().css('cursor', 'default');

    setTimeout(function() {
        $('.event-details').slideUp();
    }, 250);


    $('.event-list > li').click(function () {
        if (!$(this).nextAll('.event-details').first().hasClass('disabled')) {
            //$(this).nextAll('.event-details').first().height('auto');
            $(this).nextAll('.event-details').first().nextAll('.info').first().height('auto');
            $(this).nextAll('.event-details').first().slideToggle();
        }
    });

    function initMap(location, id) {

         map = new google.maps.Map(document.getElementById(id), {
            center: location,
            zoom: 15
        });
        var marker = new google.maps.Marker({
            position: location,
            map: map
        });
        marker.setMap(map);

    }

    function centerAddress(address, id) {
        var geocoder = new google.maps.Geocoder();
        var location = {};

// Geocode our address
        geocoder.geocode({"address": address}, function (results, status) {

            // Check for a successful result
            if (status == google.maps.GeocoderStatus.OK) {
                location.lat = parseFloat(results[0].geometry.location.lat());
                location.lng = parseFloat(results[0].geometry.location.lng());
                initMap(location, id);
                // Recenter the map over the address

            } else {

                // Show an error message with the status if our request fails
                alert("Geocoding was not successful - STATUS: " + status);
            }
        });

    }
    var addressElements = document.getElementsByClassName("address");
    var count=0;
     var intervalId = setInterval(function () {
         console.log(count);
         console.log(addressElements[count]);
         var targetElement = addressElements[count].getAttribute("data");
         var mapAddress = addressElements[count].innerHTML;
         centerAddress(mapAddress, targetElement);

         if(count < addressElements.length - 1){
             count++;
         }else{
             clearInterval(intervalId);
         }
     }, 400);

    var defaults = {
        calendarWeeks: true,
        showClear: true,
        showClose: true,
        allowInputToggle: true,
        useCurrent: true,
        ignoreReadonly: true,
        minDate: new Date(),
        toolbarPlacement: 'top',
        locale: 'en',
        icons: {
            time: 'fa fa-clock-o',
            date: 'fa fa-calendar',
            up: 'fa fa-angle-up',
            down: 'fa fa-angle-down',
            previous: 'fa fa-angle-left',
            next: 'fa fa-angle-right',
            today: 'fa fa-dot-circle-o',
            clear: 'fa fa-trash',
            close: 'fa fa-times'
        }
    };

    $(function() {
        var optionsDatetime = $.extend({}, defaults, {format:'MM-DD-YYYY HH:mm'});
        var optionsDate = $.extend({}, defaults, {format:'MM-DD-YYYY'});
        var optionsTime = $.extend({}, defaults, {format:'HH:mm'});

        $('.datepicker').datetimepicker(optionsDate);
        $('.timepicker').datetimepicker(optionsTime);
        $('.datetimepicker').datetimepicker(optionsDatetime);
    });

});

