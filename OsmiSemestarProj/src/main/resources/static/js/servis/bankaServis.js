var bankaServis = angular.module('xws_pi_bezb.bankaServis', []);

bankaServis.factory('bankaServis', function($http) {

	var temp = {};
	
	temp.dodajBanku = function(data) {
		return $http.post('/bankaKontroler/dodajBanku', data);
	}	
	
	temp.izbrisiBanku = function(data) {
		return $http.post('/bankaKontroler/izbrisiBanku', data);
	}	
	
	temp.izmeniBanku = function(data) {
		return $http.post('/bankaKontroler/izmeniBanku', data);
	}	
	
	temp.izlistajBanke = function(data) {
		return $http.get('/bankaKontroler/izlistajBanke');
	}
	
	return temp;

})