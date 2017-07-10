/**
 * Created by frenchfryes on 7/10/17.
 */

$(document).ready(function () {
    function isValidDate(txtDate) {
        var dateString = $(txtDate).val();
        var warningId = txtDate + “Warning”;
        var date_regex = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        var txtDatePresent = (function () {
            if (!($(txtDate).val() ==“”))
            {
                return true
            }
            else
            {
                return false
            }
        })();

        if ((!(date_regex.test(dateString))) && txtDatePresent) {
            /*$(warningId).css(“display”, “inline - block”);*/
            return false;
        }

        else {
            return true
        }
        ;
    }
}