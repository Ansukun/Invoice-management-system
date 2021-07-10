document.addEventListener("DOMContentLoaded", function() {
    loadData(0, 8);

});
var pagecount = 1;

var loadData = (start, limit) => {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8020/H2HBABBA1533/getactordata.do?start=" + start + "&limit=" + limit, false);
    xhttp.send();
    var jasonData = JSON.parse(xhttp.responseText);

    function amountToKFormat(num) {
        return Math.abs(num) > 999 ? Math.sign(num) * ((Math.abs(num) / 1000).toFixed(1)) + 'K' : Math.sign(num) * Math.abs(num)
    }

    console.log(jasonData);
    var tabledata = '<tr><th><img class="style1" alt="" src="images/check_box_outline_blank-black-18dp.svg"></th><th>customer Name</th><th>customer #</th> <th>invoice #</th><th>invoice amount</th><th>Due Date</th> <th>Predicted Payment date</th> <th>Notes</th></tr>';
    for (var i = 0; i < jasonData.length; i++) {
        tabledata += '<tr><td><INPUT type="checkbox" name="chk"/></td><td>' + jasonData[i].name + '</td><td>' + jasonData[i].cust_number + '</td><td>' + jasonData[i].invoice_id + '</td><td>' + amountToKFormat(jasonData[i].total_open_amount) + '</td><td>' + jasonData[i].due_in_date + '</td><td>' + jasonData[i].clear_date + '</td><td>' + jasonData[i].Delay_Grouped + '</td>'

    }
    document.getElementById('mytable').innerHTML = tabledata
}


function prevPageFuntion() {
    if (pagecount >= 2) {
        pagecount--;
    }
    loadData((pagecount - 1) * 8, 8);
}

function nextPageFuntion() {
    pagecount++;
    loadData((pagecount - 1) * 8, 8);
}

function addRow(tableID) {

    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var colCount = table.rows[0].cells.length;

    for (var i = 0; i < colCount; i++) {

        var newcell = row.insertCell(i);

        newcell.innerHTML = table.rows[0].cells[i].innerHTML;
        //alert(newcell.childNodes);
        switch (newcell.childNodes[0].type) {
            case "text":
                newcell.childNodes[0].value = "";
                break;
            case "checkbox":
                newcell.childNodes[0].checked = false;
                break;
            case "select-one":
                newcell.childNodes[0].selectedIndex = 0;
                break;
        }
    }
}

function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var span = document.getElementsByClassName("deleteButton")[0];
        span.onclick = function() {
            modal1.style.display = "none";
        }
        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if (null != chkbox && true == chkbox.checked) {
                if (rowCount <= 1) {
                    alert("Cannot delete all the rows.");
                    break;
                }
                table.deleteRow(i);
                rowCount--;
                i--;
            }


        }

    } catch (e) {
        alert(e);
    }
}
var modal1 = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var closeBtn1 = document.getElementsByClassName("close")[0];
btn.onclick = function() {
    modal1.style.display = "block";
}

closeBtn1.onclick = function() {
    modal1.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal1.style.display = "none";
    }
}

//     Edit Modal


var modal2 = document.getElementById("myModal2");
var btn = document.getElementById("myBtn2");
var closeBtn2 = document.getElementsByClassName("editClose")[0];
btn.onclick = function() {
    modal2.style.display = "block";
}

closeBtn2.onclick = function() {
    modal2.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
}

var span = document.getElementsByClassName("editButton")[0];
span.onclick = function() {
    modal2.style.display = "none";
}

closeBtn.onclick = function() {
    modal2.style.display = "none";
}