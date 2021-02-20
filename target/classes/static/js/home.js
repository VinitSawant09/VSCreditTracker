function onLoad()
{
	var addForm = document.getElementById("addForm");
	addForm.style.display ="none";
	var  editForm = document.getElementById("editForm");
    editForm.style.display ="none";
	var $tableId = $("#dtBasicExample");
	
	$.ajax(
       {
        url  : "./getSelfCreditDetails",
       headers: 
		{
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        type:"GET",
        contentType: 'application/json',
        cache: false,
        processData: false,
        success: function(response)
		{
			var creditCardList = response.creditCardList;
	         if(response.statusCode == "0")
	        { 
		
		        for (var i=0;i < creditCardList.length; i++)
        		{
	              
	                      $tableId.append(
	                      $("<tr>").append($("<td>").html(creditCardList[i].creditCardNumber))
	                      .append($("<td>").html(creditCardList[i].cardMerchant))
	                      .append($("<td>").html(creditCardList[i].expiryDate))
	                      .append($("<td>").html(creditCardList[i].maxLimit))
	                      .append($("<td>").html(creditCardList[i].creditCardNumber))
	                     );
	              
        		 }

 	    $(document).ready(function () 
		{
			 
              var table = $('#dtBasicExample').DataTable({
                  "order": [[ 0, "asc" ]],
                  "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull)
                            {

                                     $('td', nRow).css('background-color', 'Black').css('color','White');
                             },
                   'columnDefs': [
								 {
                              "targets": 0,
                               
                              "className": 'dt-body-right',
                                    },
                                {
                              "targets": 1,
                              "className": 'dt-body-right',
                                    },
                                 {
                              "targets": 2,
                              "className": 'dt-body-right',
                                     },
                                     {
                              "targets": 3,
                              "className": 'dt-body-right',
                                    },
							                                {
							            "targets": -1,
							            "data": null,
							            "defaultContent": "<button>Edit!</button>"
							        } 
                                    


                                     ],
                               

                   });
              $('.dataTables_length').addClass('bs-select');
		
		      $('#dtBasicExample tbody').on( 'click', 'button', function () {
			        var data = table.row( $(this).parents('tr') ).data();
			        console.log(data[0])
                        var dataTable = document.getElementById("allCreditCardTable");
					    dataTable.style.display ="none";
						
						var  editForm = document.getElementById("editForm");
						editForm.style.display ="block";
						document.getElementById("creditCardNumberEdit").value=data[0];
						document.getElementById("creditCardMerchantEdit").value=data[1];
						document.getElementById("creditCardLimitEdit").value=data[3];
						
                      
			    } );
		
		    });
        }
	    }
	
	
	});
	
}

function displayAddCreditForm()
{
	
	var dataTable = document.getElementById("allCreditCardTable");
	dataTable.style.display ="none";
	var addForm = document.getElementById("addForm");
	addForm.style.display ="block";
	var  editForm = document.getElementById("editForm");
	dataTable.style.display ="none";
		
}

function addCreditCard()
{
	if(!validateCreditCard())
	{
		
		document.getElementById("creditError").innerHTML="Please fill all the fields.";
		return false;
	}
	
	document.getElementById("creditError").innerHTML="";
	var creditCardNumber = document.getElementById("creditCardNumber").value;
	var merchantName = document.getElementById("creditCardMerchant").value;
	var maxLimit =  document.getElementById("creditCardLimit").value;
	var expiry =  document.getElementById("start").value;
	
	 var creditCardData = {
			"creditCardNumber": creditCardNumber, 
	        "cardMerchant": merchantName,
            "maxLimit":maxLimit,
            "expiryDate":expiry
  		   }
       

     $.ajax(
       {
        url  : "./add",
       headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
			
        },
        type:"POST",
        data : JSON.stringify(creditCardData),
        contentType: 'application/json',
        cache: false,
        processData: false,
        success: function(response){
	         if(response.statusCode == "0")
	        { 
		     document.getElementById("creditError").innerHTML="";
             document.getElementById("creditCardNumber").value="";
			 document.getElementById("creditCardMerchant").value="";
			 document.getElementById("creditCardLimit").value="";
		     document.getElementById("start").value="";
			 alert("Successfully added Card.");
             console.log(response);
			
             }
            else if(response.statusCode == "1")
            {
	       
	         document.getElementById("creditError").innerHTML="Card Number Already Exists.";
            }
            else 
            {
	          document.getElementById("creditError").innerHTML="Please try again later";
            }
	     }
});
	
	return true;
	
}

function validateCreditCard()
{
	
	var creditCardNumber = document.getElementById("creditCardNumber").value;
	var merchantName = document.getElementById("creditCardMerchant").value;
	var maxLimit =  document.getElementById("creditCardLimit").value;
	var expiry =  document.getElementById("start").value;
	console.log(expiry);
	if(creditCardNumber.length!="" && merchantName!="" && maxLimit!="" && expiry!="")
	{
		return true;
	}
	
	return false;
	
	
	
}

function editCreditCard()
{
	
	var creditCardNumber = document.getElementById("creditCardNumberEdit").value;
	var expiryDate = 	document.getElementById("editstart").value;
	
	var creditCardData = {
			"creditCardNumber": creditCardNumber,  
            "expiryDate":expiryDate
  		   }
	 $.ajax(
       {
        url  : "./update",
       headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
			
        },
        type:"POST",
        data : JSON.stringify(creditCardData),
        contentType: 'application/json',
        cache: false,
        processData: false,
        success: function(response){
	         if(response.statusCode == "0")
	        { 
		        document.getElementById("creditEditError").innerHTML="";
		       alert("Credit Card Edited successfully!");
		       window.location.replace("./home");
		
		    }
            else if(response.statusCode == "1")
            {
	       
	         document.getElementById("creditEditError").innerHTML="Failed Edit of Card";
            }
            else 
            {
	          document.getElementById("creditEditError").innerHTML="Please try again later";
            }
        }
	});
	
}


