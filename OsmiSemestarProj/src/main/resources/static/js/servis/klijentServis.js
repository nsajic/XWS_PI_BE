var klijentServis = angular.module('xws_pi_bezb.klijentServis', []);

klijentServis.factory('klijentServis', function($http) {

	var temp = {};

	temp.dodajPravnoLice = function(data) {
		return $http.post('/klijentKontroler/dodajPravnoLice', data);
	}

	temp.izbrisiPravnoLice = function(data) {
		return $http.post('/klijentKontroler/izbrisiPravnoLice', data);
	}

	temp.izmeniPravnoLice = function(data) {
		return $http.post('/klijentKontroler/izmeniPravnoLice', data);
	}

	temp.izlistajPravnaLica = function(data) {
		return $http.get('/klijentKontroler/izlistajPravnaLica');
	}

	temp.pretraziPravnaLica = function(data) {
		return $http.post('/klijentKontroler/pretraziPravnaLica', data);
	}

	temp.ucitajPravnoLice = function(data) {
		return $http.post('/klijentKontroler/ucitajPravnoLice', data);
	}
	
	temp.ucitajDelatnosti = function() {
		return $http.get('/klijentKontroler/ucitajDelatnosti');
	}
	
	return temp;

})