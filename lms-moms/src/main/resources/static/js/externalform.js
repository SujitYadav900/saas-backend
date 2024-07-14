$(document).on("submit", "#quicktaskform", function (e) {
    e.preventDefault();
    var url = $('#taskformurl').val();
    var jsonTemp = JSON.parse($('#hidjson').val());
    var map = {};
    var json = $('#taskformjsonhidden').val();
    json = JSON.parse(json);
    if ($('#createbtntaskform').html() == "Create") {
        map["subject"] = $('#subjecttaskform').val();
        map["message"] = $('#messagetaskform').val().trim();
        map["txtMsgNotification"] = $('#txtmessagenotificatontaskform').prop("checked");
        map["dateTimeTask"] = $('#datetimetaskform').val();
        map["leadId"] = json.id;
    } else {
        map = json;
        map["subject"] = $('#subjecttaskform').val();
        map["message"] = $('#messagetaskform').val().trim();
        map["txtMsgNotification"] = $('#txtmessagenotificatontaskform').prop("checked");
        map["dateTimeTask"] = $('#datetimetaskform').val();
        map["leadId"] = json.leadId;
        map["id"] = json.id;
    }
    $.ajax({
        type: 'POST',
        data: JSON.stringify(map),
        contentType: 'application/json',
        url: url,
        success: function (data) {



            if ($('#createbtntaskform').html() == "Create") {
                checkAndUpdateStatus(map);
                swal({
                    title: "Success!",
                    text: "Successfully Created Task",
                    icon: "success",
                    button: "Ok!",
                });
            } else {
                loadleadlogtask(json.leadId);
                swal({
                    title: "Success!",
                    text: "Successfully Updated Task",
                    icon: "success",
                    button: "Ok!",
                });
            }
            $('#cancelbtntaskform').trigger("click")
            loadleadlogtask(jsonTemp.id);


        },
        error: function (err) {

            swal({
                title: 'Error',
                text: err.responseJSON.message,
                icon: 'error',
                button: 'Ok'
            });

        }
    });
});
function checkAndUpdateStatus(map) {
var json=$('#hidjsonstageandstatus').val()
    if(json==0)
    {
        return;
    }
    json=JSON.parse(json);
    if (json.type == "Action") // will check for actions
    {
        changeStatusOnly($('#id').val(), json.value, function (data) {
            triggerLeadNotification($('#id').val(),map,json)
        });

    }
    else  if(json.type == "ActionandChangeStage")
    {
        var inneraction=[];
        inneraction=json.innerAction.toString().split("|");
        changeStageandStatusOfLead($('#id').val(), inneraction[0], inneraction[1], function (data) {
            triggerLeadNotification($('#id').val(),map,json)
            if (data == "success") {
                loadLeadStatusByLeadStage(inneraction[0], inneraction[1], function (data) {

                })
            }
        })

    }
    $('#hidjsonstageandstatus').val(0)
}
function setTaskAfterTime(url,minstoincrease,id) {
    var map={};
    map["subject"] = "Automated Task";
    map["message"] = "This lead has a follow up as a part of automated task";

    map["leadId"] = id;
    $.ajax({
        type: 'POST',
        data: JSON.stringify(map),
        contentType: 'application/json',
        url: url+minstoincrease,
        success: function (data) {




        },
        error: function (err) {

            swal({
                title: 'Error',
                text: err.responseJSON.message,
                icon: 'error',
                button: 'Ok'
            });

        }
    });
}
$(document).on("submit", "#quicklogeventform", function (e) {
    e.preventDefault();
    var url = $('#logeventformurl').val();

    var map = {};

    var json = $('#logeventformjsonhidden').val();
    json = JSON.parse(json);
    var jsonTemp = JSON.parse($('#hidjson').val());

    if ($('#createbtnlogeventform').html() == "Create") {
        map["eventType"] = $('#subjectlogeventform').val();
        map["message"] = $('#messagelogeventform').val().trim();

        map["leadId"] = json.id;
    } else {
        map = json;
        map["eventType"] = $('#subjectlogeventform').val();
        map["message"] = $('#messagelogeventform').val().trim();
        map["leadId"] = json.leadId;
        map["id"] = json.id
    }

    $.ajax({
        type: 'POST',
        data: JSON.stringify(map),
        contentType: 'application/json',
        url: url,
        success: function (data) {
            if ($('#createbtnlogeventform').html() == "Create") {
                checkAndUpdateStatus(map);
                swal({
                    title: "Success!",
                    text: "Successfully Logged Event",
                    icon: "success",
                    button: "Ok!",
                });
            } else {
                loadleadevent(json.leadId);
                swal({
                    title: "Success!",
                    text: "Successfully Updated Event",
                    icon: "success",
                    button: "Ok!",
                });
            }
            $('#cancelbtnlogeventform').trigger("click")
            loadleadevent(jsonTemp.id);

        },
        error: function (err) {

            swal({
                title: 'Error',
                text: err.responseJSON.message,
                icon: 'error',
                button: 'Ok'
            });

        }
    });
});
$(document).on("submit", "#quickfileuploadform", function (e) {
    e.preventDefault();
    var form = $(this)[0];
    var jsonTemp = JSON.parse($('#hidjson').val());

    console.log(form);


    var data = new FormData(form);
    $('#preloader').css("display", "block");
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: $('#hiddenurlquickfile').val(),
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $('#preloader').fadeOut();
            $('#cancelbtnquickfileform').trigger("click")
            loadleadfileattchment(applicationPrefix + jsonTemp.id);


        },
        error: function (err) {
            $('#preloader').fadeOut();
            console.log(err.responseJSON);

            swal({
                title: 'Error',
                text: err.responseJSON.message,
                type: 'error',
                confirmButtonText: 'Ok'
            })

        }
    });
});
$(document).on("submit", "#leadtransferform", function (e) {
    e.preventDefault();

    let clientType = $("#clientTypeSelect").val();

    var arr = [];
    var json = JSON.parse($('#jsonhiddenttransferform').val());
    console.log(json);
    var jsonTemp = JSON.parse($('#hidjson').val());

    var url = $('#urlhiddentransferform').val();
    for (var i = 0; i < json.length; i++) {
        var map = {};
        map["leadId"] = json[i].id;
        map["toId"] = $('#usertransferform').val();
        map["tousername"] = $('#usertransferform :selected').text();
        map["fromusername"] = json[i].username;
        map["clientType"] = clientType
        arr.push(map)

    }
    if (arr.length == 0) {
        return;
    }


    $.ajax({
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json',
        url: url,
        success: function (data) {

            swal({
                title: "Success!",
                text: "Successfully Transfered " + data + " Lead",
                icon: "success",
                button: "Ok!",
            });


            $('#cancelbtntranferform').trigger("click")
            loadleadtransferhistory(jsonTemp.id);
            reloadTable();

            //IF THIS WAS A BULK TRANSFER
            transferList.clear();
            $("#bulkTransferDiv").fadeOut();
            $(".leadSelect").prop("checked",false);
            $("#selectAllLead").prop("checked",false);


        },
        error: function (err) {

            swal({
                title: 'Error',
                text: err.responseJSON.message,
                icon: 'error',
                button: 'Ok'
            });

        }
    });

});

$(document).on("submit", "#dynamicfilterform", function (e) {
    e.preventDefault();
    var leadsource = $('#leadsourcefilterform').val() || 0;
    var leadstatus = $('#leadstatusfilterform').val() || 0;
    var product = $('#leadproductfilterform').val() || 0;
    var leadtype = $('#leadtypefilterform').val() || 0;
    var userfilter = $('#leaduserfilterform').val() || 0;
    var startdate = $('#leadstartdatefilterform').val() || 0;
    var enddate = $('#leadendfilterform').val() || 0;
    var searchstring = $('#leadsearchfilterform').val() || 0;
    var leadstage = $('#leadstagefilterform').val() || 0;
    var innersource = $('#leadsourceinnerfilterform').val() || 0;
    var leadpriority = $('#leadpriorityfilterform').val() || 0;
    var datefilter = true;
    var customdate = startdate + "@" + enddate;
    var datefiltertype = $("input[name='datefiltertype']:checked").val();
    let dateTypeFlag = 0;

    if(datefiltertype == "convertdate"){
        dateTypeFlag = 2
    }else if(datefiltertype == "updatedate"){
        dateTypeFlag = 3
    }else if(datefiltertype == "createdate"){
        dateTypeFlag = 4
    }else if(datefiltertype == "assessmentdate"){
        dateTypeFlag = 8
    }

    if (startdate == 0 || enddate == 0) {
        customdate = 0;
        datefilter = false;
    }
   // var searchStringJson={};
    var fieldArray="";
    var input=$('#checkboxDiv').find("input");
    for(var i=0;i<input.length;i++)
    {
        if(input.eq(i).prop("checked"))
        {
           fieldArray+=input.eq(i).attr("hidname")+",";
        }
    }
    //searchStringJson["parameters"]=parameters;
    //searchStringJson["searchValue"]= $('#leadsearchfilterform').val();
    //searchstring=JSON.stringify(searchStringJson)
    //searchstring = encodeURIComponent(JSON.stringify(searchStringJson));
    var urlparams = "dateTypeFlag="+dateTypeFlag+"&datefiltertype=" + datefiltertype + "&innersource=" + innersource + "&statusValue=" + leadstatus + "&leadSource=" + leadsource + "&product=" + product + "&leadType=" + leadtype + "&userFilter=" + userfilter + "&datefilter=" + datefilter + "&datevalue=" + customdate + "&searchvalue=" + searchstring + "&leadstage=" + leadstage +"&leadpriority="+leadpriority+"&fieldArray="+fieldArray;
    console.log("URL >> ",urlparams)
    var url = window.location.toString().split("?")[0];
    url = url + "?" + urlparams;
    window.history.pushState({}, null, url);
    loaddataintotable()



});

$('#removefilter').click(function () {
    var url = window.location.toString().split("?")[0];
    window.history.pushState({}, null, url);
    loaddataintotable()
});

$(document).on("submit", "#quickticketform", function (e) {
    e.preventDefault();
    $('#preloader').css("display", "block");
    var json = JSON.parse($('#ticketformjsonhidden').val());
    var jsonTemp = JSON.parse($('#hidjson').val());

    var map = {};
    map["leadid"] = json.id;
    map["subject"] = $('#subjectticketform').val();
    map["type"] = $('#ticketformtype').val();
    map["department"] = $('#departmentform').val();
    map["lastforward"] = $('#userform').val();
    map["description"] = $('#ticketmessageform').val();

    var tr = $('#tbodyattachmentticket').find("tr");
    var ar = [];

    for (var i = 0; i < tr.length; i++) {
        var innermap = {};
        innermap["id"] = tr.eq(i).find("td").eq(1).attr("atrhid");
        innermap["name"] = tr.eq(i).find("td").eq(0).html();
        ar.push(innermap);
    }
    map["attachmentlist"] = JSON.stringify(ar);

    var url = $('#ticketformurl').val();
    url = url + "true";


    $.ajax({
        type: 'POST',
        data: JSON.stringify(map),
        contentType: 'application/json',
        url: url,
        success: function (data) {
            checkAndUpdateStatus(map);
            swal({
                title: "Success!",
                text: "Successfully Created Ticket",
                icon: "success",
                button: "Ok!",
            });
            $('#cancelbtntaskform').trigger("click");
            $('#preloader').css("display", "none");
            loadleadticket(jsonTemp.id, 10, 0);

        },
        error: function (err) {
            $('#preloader').css("display", "none");
            swal({
                title: 'Error',
                text: err.responseJSON.message,
                icon: 'error',
                button: 'Ok'
            });

        }
    });


});

$(document).on("submit", "#clicktocalldynamicform", function (e) {
    e.preventDefault();
    var json = JSON.parse($('#hiddenjsondynamicform').val());
    var map = {};
    map["eventType"] = "Click To Call";
    map["message"] = $('#messagedynamicform').val();
    map["leadId"] = json.id;
    var url = $('#dynamicformurl').val();
    var phonenumber = $('#phonenumberdynamicform').val();
    url = url + phonenumber;
    swal({
        title: "Are you sure?",
        text: "You Want To Place A Call??",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {


            if (willDelete) {
                $('#preloader').fadeIn();
                $.ajax({
                    type: 'POST',
                    data: JSON.stringify(map),
                    contentType: 'application/json',
                    url: url,
                    success: function (data) {
                        $('#preloader').css("display", "none");

                       if(data.includes("Login")){
                          // document.location.href = tempElem.value;
                       }
                        else{
                           swal({
                               title: "Success!!!!!!!!!",
                               text: data.trim(),
                               icon: "success",
                               button: "Ok!",
                           });


                           $('#cancelbtndynmaicform').trigger("click")
                       }

                    },
                    error: function (err) {
                        $('#preloader').css("display", "none");
                        swal({
                            title: 'Error',
                            text: err.responseJSON.message,
                            icon: 'error',
                            button: 'Ok'
                        });

                    }
                });


            }
        });


});








