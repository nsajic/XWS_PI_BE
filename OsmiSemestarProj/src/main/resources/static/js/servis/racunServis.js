var racunServis = angular.module('xws_pi_bezb.racunServis', []);

racunServis.factory('racunServis', function($http) {

	var temp = {};

	temp.dodajRacun = function(data) {
		return $http.post('/racunKontroler/dodajRacun', data);
	}

	temp.izbrisiRacun = function(data) {
		return $http.post('/racunKontroler/izbrisiRacun', data);
	}

	temp.izmeniRacun = function(data) {
		return $http.post('/racunKontroler/izmeniRacun', data);
	}

	temp.izlistajRacune = function(data) {
		return $http.get('/racunKontroler/izlistajRacune');
	}

	temp.pretraziRacune = function(data) {
		return $http.post('/racunKontroler/pretraziRacune', data);
	}

	temp.ucitajRacun = function(data) {
		return $http.post('/racunKontroler/ucitajRacun', data);
	}
	
	temp.ucitajBanke = function() {
		return $http.get('/racunKontroler/ucitajBanke');
	}
	
	temp.ucitajValute = function() {
		return $http.get('/racunKontroler/ucitajValute');
	}
	
	temp.ucitajKlijente = function() {
		return $http.get('/racunKontroler/ucitajKlijente');
	}
	

	temp.ucitajDnevnaStanjaOdabranogRacuna = function() {
		return $http.get('/racunKontroler/ucitajDnevnaStanjaOdabranogRacuna');
	}
	
	


	return temp;

})