var medjubankarskiServis = angular.module('xws_pi_bezb.medjubankarskiServis', []);

medjubankarskiServis.factory('medjubankarskiServis', function($http) {
	
	
	

	var temp = {};

	temp.izlistajMedjubankarskePrenose = function(data) {
		return $http.get('/medjubankarskiPrenosKontroler/izlistajMedjubankarskePrenose');
	}
	
	temp.ucitajMedjubankarskeOdabraneAnalitike = function(data) {
		console.log('sssss');
		console.log(data);
		console.log('sssss');
		return $http.post('/medjubankarskiPrenosKontroler/ucitajMedjubankarskeOdabraneAnalitike', data);
	}

	return temp;

});