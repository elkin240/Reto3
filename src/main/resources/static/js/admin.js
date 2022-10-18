function traerAdmin(){
    $.ajax({
        url:"http://localhost:8080/api/Admin/all",
        type:"GET",
        dataType:'json',
        success: function(respuesta, xhr){
            pintarAdmins(respuesta);
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

function pintarAdmins(datos){
 let html = "";
  html += "<thead>";
  html +="<tr>"
  html +="<th>Nombre</th>"
  html +="<th>Correo</th>"
  html +="<th>Eliminar</th>"
   html +="</tr>"
  html += "</thead>";

  html += "<tbody>"
  for (dato of datos){
    html += "<tr>"

    html += "<td>"+dato.name+"</td>";
    html += "<td>"+dato.email+"</td>";
   html += "<td><button  class='btn btn-danger' onclick='eliminarAdmin("+dato.idAdmin+")'>Eliminar </button> </td";
    html += "</tr>"

  }
  

  $("#tablaAdmins").html(html);
}

function guardarAdmin(){
    let datos={
            name:$("#name").val(),
            email:$("#email").val(),
           password:$("#password").val(),   
        };
    let dataToSend=JSON.stringify(datos);
   
    $.ajax({
        url:"http://localhost:8080/api/Admin/save",
        type:"POST",
        dataType: 'json',
        contentType : 'application/json',
        data: dataToSend,
        success: function(respuesta){
            $("#name").val(""),
            $("#email").val(""),
            $("#password").val(""),
            traerAdmin();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });

}


function eliminarAdmin(idAdmin){
    $.ajax({
        url:"http://localhost:8080/api/Admin/"+idAdmin,
        type:"DELETE",
        success: function(respuesta){
          traerAdmin();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

