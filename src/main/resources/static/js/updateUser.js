/**
 * Created by melodytempleton on 7/10/17.
 */
$(document).ready(function(){

    $('form').submit(function(ev) {
        ev.preventDefault();
        var rolesToDelete = "";

$('.roleIds').each(function () {
var self = this;
    if ($(self).prop('checked')) {

       var id = $(this).val();

       rolesToDelete = rolesToDelete + id + ",";
    }

});
$("#deleteRolesIds").val(rolesToDelete);
console.log("deleteRoles value is " + $("#deleteRolesIds").val())
        this.submit();
});
});