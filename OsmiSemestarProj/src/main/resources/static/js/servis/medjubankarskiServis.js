var medjubankarskiServis = angular.module('xws_pi_bezb.medjubankarskiServis', []);

medjubankarskiServis.factory('medjubankarskiServis', function($http) {
	
	
	

	var temp = {};

	temp.izlistajMedjubankarskePrenose = function(data) {
		return $http.get('/medjubankarskiPrenosKontroler/izlistajMedjubankarskePrenose');
	}

	return temp;

});