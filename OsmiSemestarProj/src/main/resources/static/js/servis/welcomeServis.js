var welcomeServis = angular.module('xws_pi_bezb.welcomeServis', []);

welcomeServis.factory('welcomeServis', function($http) {
	
	var temp = {};
	
	temp.logOut = function(){
		return $http.post('/contr/logout');
	}
	
	return temp;
})