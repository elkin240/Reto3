function traerClient(){
    $.ajax({
        url:"http://localhost:8080/api/Client/all",
        type:"GET",
        dataType:'json',
        success: function(respuesta, xhr){
            pintarClients(respuesta);
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

function pintarClients(datos){
 let html = "";
  html += "<thead>";
  html +="<tr>"
  html +="<th>Nombre</th>"
  html +="<th>Email</th>"
  html +="<th>Edad</th>"
  html +="<th>Eliminar</th>"
    html +="</tr>"
  html += "</thead>";

  html += "<tbody>"
  for (dato of datos){
    html += "<tr>"

    html += "<td>"+dato.name+"</td>";
    html += "<td>"+dato.email+"</td>";
    html += "<td>"+dato.age+"</td>";
     
    html += "<td><button  class='btn btn-danger' onclick='eliminarClient("+dato.idClient+")'>Eliminar </button> </td";
    html += "</tr>"

  }
  

  $("#tablaClients").html(html);
}

function guardarClient(){
    let datos={
            name:$("#name").val(),
            email:$("#email").val(),
            password:$("#password").val(),
            age:$("#age").val()
        };
    let dataToSend=JSON.stringify(datos);
   
    $.ajax({
        url:"http://localhost:8080/api/Client/save",
        type:"POST",
        dataType: 'json',
        contentType : 'application/json',
        data: dataToSend,
        success: function(respuesta){
            $("#name").val(""),
            $("#email").val(""),
            $("#password").val(""),
            $("#age").val(""),
            traerClient();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });

}


function eliminarClient(idClient){
    $.ajax({
        url:"http://localhost:8080/api/Client/"+idClient,
        type:"DELETE",
        success: function(respuesta){
          traerClient();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

