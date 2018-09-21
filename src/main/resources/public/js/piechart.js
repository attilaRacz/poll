function drawChart(piechart) {
    console.log("in hereasdasd")
    var data = google.visualization.arrayToDataTable(piechart);
    var chartwidth = $('#chartparent').width();
    var options = {'width':chartwidth, 'height':300, backgroundColor: '#0E76FB', is3D: true, legend: {position: 'none'}};
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(data, options);
}