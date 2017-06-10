var valutaServis = angular.module('xws_pi_bezb.valutaServis', []);

valutaServis.factory('valutaServis', function($http) {

	var temp = {};
	
	temp.dodajValutu = function(data) {
		return $http.post('/valutaKontroler/dodajValutu', data);
	}	
	
	temp.izbrisiValutu = function(data) {
		return $http.post('/valutaKontroler/izbrisiValutu', data);
	}	
	
	temp.izmeniValutu = function(data) {
		return $http.post('/valutaKontroler/izmeniValutu', data);
	}	
	
	temp.izlistajValute = function(data) {
		return $http.get('/valutaKontroler/izlistajValute');
	}
	
	temp.pretraziValute = function(data) {
		return $http.post('/valutaKontroler/pretraziValute', data);
	}
	return temp;

})