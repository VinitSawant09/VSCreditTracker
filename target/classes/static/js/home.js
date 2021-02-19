function onLoad()
{
	var addForm = document.getElementById("addForm");
	addForm.style.display ="none";
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
	                      
	                     );
	              
        		 }

 	    $(document).ready(function () 
		{
              $('#dtBasicExample').DataTable({
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
                                    }
                                    


                                     ]

                   });
              $('.dataTables_length').addClass('bs-select');
		
		
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


