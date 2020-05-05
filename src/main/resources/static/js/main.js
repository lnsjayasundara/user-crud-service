$(document).ready(function(){

	loadUserList();
	
	 $("form").submit(function(event) {
	
		event.preventDefault();
		var action = $("#action").val();
		if(action == "update"){
			updateUser();
		}else{
			saveUser();
		}
	});





});

function saveUser(){
var data = {
name : $("#name").val(),
age : $("#age").val(),
salary : $("#salary").val(),
};

$.ajax({
      type : "POST",
      contentType : "application/json",
      url : window.location + "/customer/save",
      data : JSON.stringify(data),
      dataType: 'json',
      success : function(result) {
        
        if(result.status == "Success"){
			resetData();
			$("#body-content").empty();
			loadUserList();
			alert("Successfully Saved !");          
        }else{
        	alert("Error occurred While Saving data!");
        }
      },
      error : function(e) {
        alert("Error!");
      }
    });
}

function updateUser(){
	var data = {
		id : $("#id").val(),
		name : $("#name").val(),
		age : $("#age").val(),
		salary : $("#salary").val()
	};
	
	$.ajax({
	      type : "POST",
	      contentType : "application/json",
	      url : window.location + "/customer/update",
	      data : JSON.stringify(data),
	      dataType: 'json',
	      success : function(result) {
	        
	        if(result.status == "Success"){
				resetData();
				$("#body-content").empty();
				loadUserList();
				alert("Successfully Saved !");          
	        }else{
	        	alert("Error occurred While Saving data!");
	        }
	      },
	      error : function(e) {
	        alert("Error!");
	      }
	    });
}

function loadUserList(){
	$.ajax({
      type : "GET",
      contentType : "application/json",
      url : window.location + "/cutomer/filter/0",
      dataType: 'json',
      success : function(result) {
      	var trHTML = '<tbody id="body-content">';
        $.each(result, function (i, item) {
        var user = {
	        id: item.id ,
			name : item.name ,
			age : item.age ,
			salary : item.salary 
		};
            trHTML += '<tr><td>' + item.id 
            + '</td><td>' + item.name 
            + '</td><td>' + item.age 
            + '</td><td>' + item.salary 
            + '</td><td>' + '  <button type="button" id="'+item.id+'" class="edit-btn btn btn-success" onclick="editUser('+item.id+',\''+item.name+'\','+item.age+','+item.salary+')">Edit</button>' 
            + '</td><td>'+ '  <button type="button" id="'+item.id+'" class="remove-btn btn btn-danger" onclick="removeUser('+item.id+',\''+item.name+'\','+item.age+','+item.salary+')">Remove</button>' 
            + '</td></tr>';
        });
		trHTML += '</tbody>';
        $('#user-list').append(trHTML);
		
      },
      error : function(e) {
        alert("Error!");
      }
    });
}

function editUser(id,name,age,salary){
	$("#name").val(name);
	$("#age").val(age);
	$("#salary").val(salary);
	$("#id").val(id);
	$("#action").val("update");
}

function removeUser(id,name,age,salary){
	var data = {
		id : id,
		name : name,
		age : age,
		salary : salary,
		status : 'N'
	};
	$.ajax({
	      type : "POST",
	      contentType : "application/json",
	      url : window.location + "/customer/remove",
	      data : JSON.stringify(data),
	      dataType: 'json',
	      success : function(result) {
	        
	        if(result.status == "Success"){
				alert("Successfully Removed !"); 
				loadUserList();       
	        }else{
	        	alert("Error occurred While Removing !");  
	        }
	      },
	      error : function(e) {
	        alert("Error!");
	      }
    });
}

function resetData(){
	$("#name").val("");
	$("#age").val("");
	$("#salary").val(""); 
	$("#id").val("");
	$("#action").val("add");
}


