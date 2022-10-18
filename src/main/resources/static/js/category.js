function traerCategory(){
    $.ajax({
        url:"http://localhost:8080/api/Category/all",
        type:"GET",
        dataType:'json',
        success: function(respuesta, xhr){
            pintarCategorys(respuesta);
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

function pintarCategorys(datos){
 let html = "";
  html += "<thead>";
  html +="<tr>"
  html +="<th>Nombre</th>"
  html +="<th>Descrpcion</th>"
  html +="<th>Eliminar</th>"
   html +="</tr>"
  html += "</thead>";

  html += "<tbody>"
  for (dato of datos){
    html += "<tr>"

    html += "<td>"+dato.name+"</td>";
    html += "<td>"+dato.description+"</td>";
       html += "<td><button  class='btn btn-danger' onclick='eliminarCategory("+dato.id+")'>Eliminar </button> </td";
    html += "</tr>"

  }
  

  $("#tablaCategory").html(html);
}

function guardarCategory(){
    let datos={
            name:$("#name").val(),
            description:$("#description").val(),
            };
    let dataToSend=JSON.stringify(datos);
   
    $.ajax({
        url:"http://localhost:8080/api/Category/save",
        type:"POST",
        dataType: 'json',
        contentType : 'application/json',
        data: dataToSend,
        success: function(respuesta){
            $("#name").val(""),
            $("#description").val(""),
            traerCategory();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });

}


function eliminarCategory(id){
    $.ajax({
        url:"http://localhost:8080/api/Category/"+id,
        type:"DELETE",
        success: function(respuesta){
            traerCategory();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

