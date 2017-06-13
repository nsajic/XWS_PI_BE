var delatnostServis = angular.module('xws_pi_bezb.delatnostServis', []);

delatnostServis.factory('delatnostServis', function($http) {

	var temp = {};
	
	temp.dodajDelatnost = function(data) {
		return $http.post('/delatnostKontroler/dodajDelatnost', data);
	}	
	
	temp.izbrisiDelatnost = function(data) {
		return $http.post('/delatnostKontroler/izbrisiDelatnost', data);
	}	
	
	temp.izmeniDelatnost = function(data) {
		return $http.put('/delatnostKontroler/izmeniDelatnost', data);
	}	
	
	temp.izlistajDelatnosti = function(data) {
		return $http.get('/delatnostKontroler/izlistajDelatnosti');
	}
	
	temp.pretraziDelatnosti = function(data) {
		return $http.post('/delatnostKontroler/pretraziDelatnosti', data);
	}
	return temp;

})