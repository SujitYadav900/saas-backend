
function loadChartSettingsModal() {

    $.ajax({
        url: contextPathUrl+"/html/chartsettings.html",
        type: 'GET',
        success: function (html) {
            $('#graphSettingDiv').html(html);
        },
        error: function (err) {
        }
    })

}

loadChartSettingsModal()

//===== SET CHART WIDTH ON CLICK

$(document).on("click",".chartWidthBtn",function(){

    let width = $(this).attr("chartWidth");
    let currentWidth = localStorage.getItem("chartWidth");

    if(width == "full" && currentWidth != "full"){

        $(".chartPanelClass").addClass("col-md-12")
        $(".chartPanelClass").removeClass("col-md-6")
        localStorage.setItem("chartWidth", "full");
        $(document).find("#formCloseBtn").trigger("click");

    }
    else if(width == "half"  && currentWidth != "half"){

        $(".chartPanelClass").addClass("col-md-6")
        $(".chartPanelClass").removeClass("col-md-12")
        localStorage.setItem("chartWidth", "half");
        $(document).find("#formCloseBtn").trigger("click");
    }
});

// ============= SET CHART TYPE

$(document).on("click",".chartTypeBtn",function(){

    let type = $(this).attr("chartType");
    if(type == "bar"){
        localStorage.setItem("chartType", type);
        var chartOptions = barOptions;
        localStorage.setItem("chartOptions", JSON.stringify(chartOptions));
        location.reload();


    }
    else{
        localStorage.setItem("chartType", type);
        var chartOptions = otherOptions;
        localStorage.setItem("chartOptions", JSON.stringify(chartOptions));
        location.reload();

    }
});

// ===== SET WIDTH OF THE CHARTS ON LOAD

let currentWidthGlobal = localStorage.getItem("chartWidth");
if(currentWidthGlobal == "full" ){
    $(".chartPanelClass").addClass("col-md-12")
    $(".chartPanelClass").removeClass("col-md-6")
    }
else if(currentWidthGlobal == "half"){
    $(".chartPanelClass").addClass("col-md-6")
    $(".chartPanelClass").removeClass("col-md-12")


}

$(document).on("change","#datarender",function () {
    localStorage.setItem("datarender",$("#datarender").val())
    var chartType = localStorage.getItem("chartType");
    var chartOptions = (chartType == "bar")?barOptions:otherOptions;
    chartOptions.plugins.labels.render = $("#datarender").val();
    localStorage.setItem("chartOptions", JSON.stringify(chartOptions));
    location.reload();
})

$(document).on("change","#position",function () {
    localStorage.setItem("position",$("#position").val())
    var chartType = localStorage.getItem("chartType");
    var chartOptions = (chartType == "bar")?barOptions:otherOptions;
    chartOptions.plugins.labels.position = $("#position").val()
    localStorage.setItem("chartOptions", JSON.stringify(chartOptions));
    location.reload();
})


var barOptions = {scales: {
    xAxes: [{
        barThickness: 40,  // number (pixels) or 'flex'
        maxBarThickness: 40 ,// number (pixels),
    }],
    yAxes: [{
        ticks: {
            beginAtZero: true,

        }
    }]
},
    tooltips: {
        enabled: false
    },
    plugins: {
        labels :{
            // render 'label', 'value', 'percentage', 'image' or custom function, default is 'percentage'
            render: localStorage.getItem("datarender") || "percentage",

            // identifies whether or not labels of value 0 are displayed, default is false
            showZero: true,

            // font size, default is defaultFontSize
            fontSize: 12,

            // font color, can be color array for each data or function for dynamic color, default is defaultFontColor
            fontColor: "#111111",

            // draw text shadows under labels, default is false
            textShadow: true,

            // draw label in arc, default is false
            // bar chart ignores this
            arc: false,

            // position to draw label, available value is 'default', 'border' and 'outside'
            // bar chart ignores this
            // default is 'default'
            position: localStorage.getItem("position") || "default",
        }
    }

}

var otherOptions = {
    tooltips: {
        enabled: false
    },
    plugins: {
        labels :{
            // render 'label', 'value', 'percentage', 'image' or custom function, default is 'percentage'
            render: localStorage.getItem("datarender") || "percentage",

            // identifies whether or not labels of value 0 are displayed, default is false
            showZero: true,

            // font size, default is defaultFontSize
            fontSize: 12,

            // font color, can be color array for each data or function for dynamic color, default is defaultFontColor
            fontColor: "#111111",

            // draw text shadows under labels, default is false
            textShadow: true,

            // draw label in arc, default is false
            // bar chart ignores this
            arc: false,

            // position to draw label, available value is 'default', 'border' and 'outside'
            // bar chart ignores this
            // default is 'default'
            position: localStorage.getItem("position") || "default",
        }
    }
};

function onSettingFromLoad(){

    var position = localStorage.getItem("position");
    var datarender = localStorage.getItem("datarender");

    $("#position").val(position);
    $("#datarender").val(datarender);

}