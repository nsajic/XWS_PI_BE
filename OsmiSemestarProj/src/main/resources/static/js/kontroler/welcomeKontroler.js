var welcomeKontroler = angular.module('xws_pi_bezb.welcomeKontroler', []);

welcomeKontroler.controller('welcomeCtrl', function($scope, $location, $window, welcomeServis, klijentServis) {

	$scope.privileges = [];

	klijentServis.ucitajPrivilegije().success(function(data) {
		$scope.privileges = data;
	}).error(function(data) {
		alert("Korisnik nema nikakvih privilegija.");
	});

	$scope.hasPrivilege = function(privilege) {
		if ($scope.privileges.indexOf(privilege) > -1)
			return true;
		else
			return false;
	};
	
	$scope.logOut = function(){

		welcomeServis.logOut().success(function(data) {
			if(data.message == "Izlogovan"){
				$window.location.href = '/';
			}else{
			}
		});
	}
	
	
	$scope.ulogovanKorisnik = new Object();
	$scope.zabraniPrikaz = false;
	$scope.rolaUlogovanog = "";
	
	
		klijentServis.ucitajUlogovanogKorisnika().success(function(data) {
			$scope.ulogovanKorisnik = data.korisnik
			$scope.rolaUlogovanog = data.rola.naziv;
			
			/*
			if($scope.rolaUlogovanog == "FizickoLice"){
				if($scope.ulogovanKorisnik.logovaoSe == true){
					$scope.zabraniPrikaz = false;
				} else {
					$location.path('/promenaLozinke');
				}
			}
			*/
		}).error(function(data) {
			alert("Neuspesno ucitavanje ulogovanog korisnika!");
		});	
		
		






});