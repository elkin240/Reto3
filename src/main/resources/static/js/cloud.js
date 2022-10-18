function traerCloud(){
    $.ajax({
        url:"http://141.147.38.210:8080/api/Cloud/all",
        type:"GET",
        dataType:'json',
        success: function(respuesta, xhr){
            pintarClouds(respuesta);
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

function pintarClouds(datos){
 let html = "";
  html += "<thead>";
  html +="<tr>"
  html +="<th>Nombre</th>"
  html +="<th>Marca</th>"
  html +="<th> A&ntilde;o</th>"
  html +="<th>Descripcion</th>"
  html +="<th>Eliminar</th>"
   html +="</tr>"
  html += "</thead>";

  html += "<tbody>"
  for (dato of datos){
    html += "<tr>"

    html += "<td>"+dato.name+"</td>";
    html += "<td>"+dato.brand+"</td>";
    html += "<td>"+dato.year+"</td>";
    html += "<td>"+dato.description+"</td>";
  
    html += "<td><button  class='btn btn-danger' onclick='eliminarCloud("+dato.id+")'>Eliminar </button> </td";
    html += "</tr>"

  }
  

  $("#tablaClouds").html(html);
}

function guardarCloud(){
    let datos={
            name:$("#name").val(),
            brand:$("#brand").val(),
            year:$("#years").val(),   
            description:$("#description").val(),
        };
    let dataToSend=JSON.stringify(datos);
   
    $.ajax({
        url:"http://141.147.38.210:8080/api/Cloud/save",
        type:"POST",
        dataType: 'json',
        contentType : 'application/json',
        data: dataToSend,
        success: function(respuesta){
            $("#name").val(""),
            $("#brand").val(""),
            $("#years").val(""),
            $("#description").val(""),
            traerCloud();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });

}


function eliminarCloud(id){
    $.ajax({
        url:"http://141.147.38.210:8080/api/Cloud/"+id,
        type:"DELETE",
        success: function(respuesta){
          traerCloud();
        },
        error:function (respuesta,xhr){
            alert("Error de peticion!");
    }
    });
}

