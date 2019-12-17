/*var typingTimer;                //timer identifier
var doneTypingInterval = 2000;  //time in ms (5 seconds)

$('#text-field').keyup(function () {
    clearTimeout(typingTimer);
    if($('#text-field').val()){
        typingTimer = setTimeout(autoComplete, doneTypingInterval);
    }
});*/

function autoComplete(version) {
    var text = document.getElementById("text-field").value;
    var search = {skill:text};
    document.getElementById("suggestion-table").innerHTML = '';
    if(version === 'first')
        url_ = '/autocomplete-first';
    else
        url_ = '/autocomplete-second';

    $.ajax({
        type:'POST',
        url : url_,
        data : JSON.stringify(search),
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        success : function (data) {
            var tr = document.createElement("TR");
            for(var i=0; i<data.length; i++){
                var node = document.createElement("TD");
                var textNode = document.createTextNode((i+1) + ") " + data[i]);
                node.appendChild(textNode);
                if(i % 3 === 0){
                    document.getElementById("suggestion-table").appendChild(tr);
                    tr = document.createElement("TR");
                    tr.appendChild(node);
                }
                else
                    tr.appendChild(node);
            }
            document.getElementById("suggestion-table").appendChild(tr);
        }
    })
}

function onLoadBody() {
    $.ajax({
        type:'POST',
        url : '/search?query=Java',
        success : function (data) {
            console.log(data[0]);
        }
    });
}