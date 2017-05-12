var drzavaServis = angular.module('xws_pi_bezb.drzavaServis', []);

drzavaServis.factory('drzavaServis', function($http) {

	var temp = {};
	
	temp.dodajDrzavu = function(data) {
		return $http.post('/drzavaKontroler/dodajDrzavu', data);
	}	
	
	temp.izbrisiDrzavu = function(data) {
		return $http.post('/drzavaKontroler/izbrisiDrzavu', data);
	}	
	
	temp.izmeniDrzavu = function(data) {
		return $http.post('/drzavaKontroler/izmeniDrzavu', data);
	}	
	
	temp.izlistajDrzave = function(data) {
		return $http.get('/drzavaKontroler/izlistajDrzave');
	}

	temp.pretraziDrzave = function(data) {
		return $http.post('/drzavaKontroler/pretraziDrzave', data);
	}	
	
	return temp;

})