/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    showAdvanced(false);
});

function showAdvanced(changeMode) {

    $.post(
            "requestAdvancedMode",
            {
                changeMode : changeMode
            },
            function(response) {
                if (response === "true") {
                    $("#advancedMenu").show();
                    //document.getElementById('advancedMenu').style.visibility = 'visible';
                } else {
                    $("#advancedMenu").hide();
                    //document.getElementById('advancedMenu').style.visibility = 'hidden';
                }
            });

}