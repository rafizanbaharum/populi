<!DOCTYPE html>
<html>
<head>
    <style>
        #map-canvas img {
            max-width: none;
        }
    </style>

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9PTc9RgylBuf0tSRrQsPI3SSPTjPXOJY&sensor=false">
    </script>
    <script type="text/javascript"
            src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <script type="text/javascript">
        var map;
        var poly;
        var center = new google.maps.LatLng(1.5243, 103.64988);
        var url = 'find';
        var districtId = 25;

        function initialize() {
            var mapOptions = {
                center: center,
                zoom: 12,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            var polyOptions = {
                strokeColor: '#000000',
                strokeOpacity: 1.0,
                strokeWeight: 3
            };
            poly = new google.maps.Polyline(polyOptions);
            poly.setMap(map);
            addDataPoints();
        }


        function addDataPoints() {
            $.getJSON('find?id=' + districtId, function(points) {
                for (var i = 0; i < points.length; i++) {
                    var latlng = new google.maps.LatLng(points[i].latitude, points[i].longitude);
                    poly.getPath().push(latlng);
                }
            });
        }
        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas" style="width:100%; height:59em"/>
</body>
</html>

