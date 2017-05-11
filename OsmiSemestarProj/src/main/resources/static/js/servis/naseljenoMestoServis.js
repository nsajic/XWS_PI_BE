var naseljenoMestoServis = angular.module('xws_pi_bezb.naseljenoMestoServis', []);

naseljenoMestoServis.factory('naseljenoMestoServis', function($http) {

	var temp = {};
	
	temp.dodajNaseljenoMesto = function(data) {
		return $http.post('/naseljenoMestoKontroler/dodajNaseljenoMesto', data);
	}	
	
	temp.izbrisiNaseljenoMesto = function(data) {
		return $http.post('/naseljenoMestoKontroler/izbrisiNaseljenoMesto', data);
	}	
	
	temp.izmeniNaseljenoMesto = function(data) {
		return $http.post('/naseljenoMestoKontroler/izmeniNaseljenoMesto', data);
	}	
	
	temp.izlistajNaseljenaMesta = function(data) {
		return $http.get('/naseljenoMestoKontroler/izlistajNaseljenaMesta');
	}	
	
	temp.izlistajDrzave = function(data) {
		return $http.get('/drzavaKontroler/izlistajDrzave');
	}
	
	return temp;

})