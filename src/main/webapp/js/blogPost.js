/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#submitButton').click(function(ev) {
        $.post(
                "submitComment",
                {
                    id: $("#postId").val(),
                    content: $("#commentContent").val()
                },
        function(response) {
            response = JSON.parse(response);
            var date = response["date"];
            var content = response["content"];
            addComment(date, content);
        });
    });
});



function addComment(date, content) {
    var div = document.getElementById('commentsDiv');
    div.innerHTML += "<p><span class=\"glyphicon glyphicon-time\"></span> Posted on" + date + "</p>";
    div.innerHTML += "<p>" + content + "</p>";
    div.innerHTML += "<hr>";
    $("#commentContent").val("");
}

