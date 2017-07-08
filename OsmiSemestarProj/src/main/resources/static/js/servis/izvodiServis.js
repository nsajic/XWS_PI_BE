var izvodiServis = angular.module('xws_pi_bezb.izvodiServis', []);

izvodiServis.factory('izvodiServis', function($http) {
	
	var temp = {};
	
	temp.zatraziIzvode = function(data) {
		return $http.post('/contr/zatraziIzvode', data);
	}
	
	temp.izvodiUXML = function(data){
		return $http.post('/contr/izvodiUXML', data);
	}
	
	return temp;
	
});