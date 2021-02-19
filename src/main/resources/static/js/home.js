function onLoad()
{
	
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