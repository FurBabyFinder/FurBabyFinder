/**
 * Created by melodytempleton on 7/10/17.
 */
$(document).ready(function(){

// =========================TURN LIST OF POSSIBLE ROLES INTO ARRAY AND COMPARE TO ROLES USER ALREADY HAS - ONLY SHOW CHECKS FOR NEW ROLES TO USER =========
    $('.addRoles').each(function () {
        var idToSet = $(this).text();

        $(this).closest(".hideAddRoles").prop("id", idToSet);

    });
    var allroles = $("#allRolesList").val();
    allroles = allroles.substr(1);
    allroles = allroles.slice(0, -1);
    allroles = allroles.split(",");

    $('.hasRoles').each(function () {
        var alreadyHas = $(this).text();

        for(var i = 0; i < allroles.length; i++){
            if ($.trim(allroles[i]) == alreadyHas){
                var idDontShow = "#" + $.trim(allroles[i]);
                $(idDontShow).css("display", "none");
            }
        }
    });


// ==============================CHECK TO SEE IF ROLES SHOULD BE DELETED AND ADD TO LIST TO SEND TO CONTROLLER =====================
    $('form').submit(function(ev) {
        ev.preventDefault();
        var rolesToDelete = "";
        var rolesToAdd = "";

$('.roleIds').each(function () {
var self = this;
    if ($(self).prop('checked')) {

       var id = $(this).val();

       rolesToDelete = rolesToDelete + id + ",";
    }

});

$('.addRoleChecks').each(function () {
var self = this;
    if ($(self).prop('checked')) {

       var id = $(this).val();

       rolesToAdd = rolesToAdd + id + ",";
    }

});


$("#deleteRolesIds").val(rolesToDelete);
$("#addRolesNames").val(rolesToAdd);

        this.submit();
});
});