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
	
	temp.izlistajBanke = function() {
		return $http.get('/bankaKontroler/izlistajBanke');
	}
	
	temp.pretraziBanke = function(data) {
		return $http.post('/bankaKontroler/pretraziBanke', data);
	}
	return temp;

})