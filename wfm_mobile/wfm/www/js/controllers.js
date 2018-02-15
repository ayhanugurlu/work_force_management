angular.module('app.controllers', [])
  
.controller('worklogCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
   
.controller('addlocationCtrl', ['$scope', '$stateParams','uiGmapIsReady', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams,uiGmapIsReady) {
	$scope.controller = this;
	this.addLocationRequest = new AddLocationRequest();
	
	this.uiGmapIsReady = uiGmapIsReady;

	uiGmapIsReady.promise(1).then(function(instances) {
        instances.forEach(function(inst) {
        var map = inst.map;
		
		map.addListener('click', function(e) {
			console.log("ayhan");
			$scope.controller.placeMarkerAndPanTo(e.latLng, map);
		});

	
		map.addListener('center_changed', function() {    
			console.log("ayhan");
        });

		
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position){
				console.log(position);	
				inst.map.setCenter(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
				$scope.controller.addLocationRequest.lat = position.coords.latitude;
				$scope.controller.addLocationRequest.lon = position.coords.longitude;
				$scope.controller.marker = new google.maps.Marker({
					position: new google.maps.LatLng(position.coords.latitude, position.coords.longitude),
					map: map,
					title: 'Current Position'
				});
			  
				$scope.controller.marker.addListener('click', function() {
					map.setZoom(8);
					map.setCenter(marker.getPosition());
				});											
			});
		}     
			
            var uuid = map.uiGmap_id;
            var mapInstanceNumber = inst.instance;
			
        });
    });
	
	 this.takePhoto = function () {		 
		 
		navigator.camera.getPicture(function(imageData) {
			var image = document.getElementById('myImage');
			image.src = "data:image/jpeg;base64," + imageData;
			}, function(message) {
			alert('Failed because: ' + message);
		}, { quality: 50,
		destinationType: Camera.DestinationType.DATA_URL
		}); 	 
	 }
	
	
	
	 this.addlocation = function () {
			if($scope.controller.marker == null){
				alert("please select location");
				return;
			}
	 }
	 
	 this.placeMarkerAndPanTo = function (latLng, map) {
		 if($scope.controller.marker){
			 $scope.controller.marker.setMap(null);
		}	
		
	  $scope.controller.addLocationRequest.lat = latLng.latitude;
	  $scope.controller.addLocationRequest.lon = latLng.longitude;
	  $scope.controller.marker = new google.maps.Marker({
		position: latLng,
		map: map
	  });
	  map.panTo(latLng);
	}
	
	
	 


}])
   
.controller('loginCtrl', ['$scope', '$stateParams','$state','LoginService', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams,$state, LoginService) {
	
	  
	$scope.controller = this;
	this.loginService = LoginService;
	  this.loginReq = new LoginRequest();
	  
	   this.login = function () {
            console.debug("call login service ");
            console.debug($scope.controller.loginReq.email);
            console.debug($scope.controller.loginReq.password);
            $scope.controller.loginService.login($scope.controller.loginReq).success(function (data) {
                console.debug("Got response data from server, response message: " + data);
                if (data.name !== null) {
                    $state.go('home', {"obj": data.name, "token": data.token});
                } else {
                    navigator.notification.alert('invalid password or invalid user name', null, 'ERROR', 'OK');
                }
            }).error(function (data, status) {
                var errorMessage = 'Unable to load login, data: ' + data + ' status: ' + status;
                console.debug(errorMessage);
            });

        };


}])
   
.controller('homeCtrl', ['$scope', '$stateParams', // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams) {


}])
 