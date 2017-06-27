var welcomeServis = angular.module('xws_pi_bezb.welcomeServis', []);

welcomeServis.factory('welcomeServis', function($http) {
	
	var temp = {};
	
	temp.logOut = function(){
		return $http.post('/contr/logout');
	}
	temp.posaljiKliring = function(){
		return $http.post('/contr/posaljiKliring');
	}
	
	return temp;
})