function autoComplete() {
    var text = document.getElementById("text-field").value;
    var search = {skill:text};
    document.getElementById("suggestion-list").innerHTML = '';

    $.ajax({
        type:'POST',
        url : '/autocomplete',
        data : JSON.stringify(search),
        contentType : 'application/json; charset=utf-8',
        dataType : 'json',
        success : function (data) {
            for(var i=0; i<data.length; i++){
                var node = document.createElement("li");
                var textNode = document.createTextNode(data[i]);
                node.appendChild(textNode);
                document.getElementById("suggestion-list").appendChild(node);
            }
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