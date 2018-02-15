angular.module('app.services', [])

.factory('BlankFactory', [function(){

}])

.service('BlankService', [function(){

}])
.service('GeoService', [function(){
		 
		 
	

}])

.service('LoginService',['$http', function($http){
	
		this.http = $http;

		
		this.serverUrl = 'http://localhost:8080';

        this.login = function (loginRequest) {
            console.debug("login request");
            var req = {
                method: 'POST',
                url: this.serverUrl +'/login',
                data: loginRequest
            };
            return this.http(req);
        };

}]);