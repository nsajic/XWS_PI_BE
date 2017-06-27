var medjubankarskiServis = angular.module('xws_pi_bezb.medjubankarskiServis', []);

medjubankarskiServis.factory('medjubankarskiServis', function($http) {
	
	
	

	var temp = {};

	temp.izlistaj = function(data) {
		return $http.get('/bankaKontroler/izlistajMedjubankarski');
	}

	return temp;

});