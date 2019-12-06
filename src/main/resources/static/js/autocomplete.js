function autoComplete() {
    var text = document.getElementById("text-field").value;
    document.getElementById("suggestion-list").innerHTML = '';

    $.ajax({
        type:'GET',
        url : '/autocomplete?query='+text,
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
        type:'GET',
        url : '/search?query=Java',
        success : function (data) {
            console.log(data[0]);
        }
    });
}