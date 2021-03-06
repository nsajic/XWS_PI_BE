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
	
	temp.izlistajPravnaLicaBanke = function(data) {
		return $http.get('/klijentKontroler/izlistajPravnaLicaBanke');
	}


	temp.pretragaPravnihLica = function(data) {
		return $http.post('/klijentKontroler/pretragaPravnihLica', data);
	}

	temp.ucitajPravnoLice = function(data) {
		return $http.post('/klijentKontroler/ucitajPravnoLice', data);
	}
	
	temp.ucitajDelatnosti = function() {
		return $http.get('/klijentKontroler/ucitajDelatnosti');
	}
	
// FIZICKA ISPOD
	
	temp.dodajFizickoLice = function(data) {
		return $http.post('/klijentKontroler/dodajFizickoLice', data);
	}

	temp.izbrisiFizickoLice = function(data) {
		return $http.post('/klijentKontroler/izbrisiFizickoLice', data);
	}

	temp.izmeniFizickoLice = function(data) {
		return $http.post('/klijentKontroler/izmeniFizickoLice', data);
	}

	temp.izlistajFizickaLica = function(data) {
		return $http.get('/klijentKontroler/izlistajFizickaLica');
	}
	
	temp.izlistajFizickaLicaBanke = function(data) {
		return $http.get('/klijentKontroler/izlistajFizickaLicaBanke');
	}

	temp.pretraziFizickaLica = function(data) {
		return $http.post('/klijentKontroler/pretraziFizickaLica', data);
	}
	
	temp.ucitajUlogovanogKorisnika = function(data) {
		return $http.get('/klijentKontroler/ucitajUlogovanogKorisnika');
	}
	
	temp.ucitajRoluUlogovanogKorisnika = function(data) {
		return $http.get('/klijentKontroler/ucitajRoluUlogovanogKorisnika');
	}
	
	
	

	temp.ucitajFizickoLice = function(data) {
		return $http.post('/klijentKontroler/ucitajFizickoLice', data);
	}

	// ZA DELATNOST MI TREBALO

	temp.izlistajPravnaLicaDelatnosti = function (data){
		return $http.post('/klijentKontroler/izlistajPravnaLicaDelatnosti', data)
	}
	
	temp.pretragaPravnihLicaDelatnosti = function (data){
		return $http.post('/klijentKontroler/pretragaPravnihLicaDelatnosti', data)
	}
	
	temp.ucitajPrivilegije = function() {
		return $http.get('/klijentKontroler/ucitajPrivilegije');
	}
	
	temp.koJeNaSesiji = function() {
		return $http.post('/contr/check');
	}
	
	return temp;

})