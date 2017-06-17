var welcomeKontroler = angular.module('xws_pi_bezb.welcomeKontroler', []);

welcomeKontroler.controller('welcomeCtrl', function($scope, $location, $window, welcomeServis, klijentServis) {

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
			

			if($scope.rolaUlogovanog == "FizickoLice"){
				if($scope.ulogovanKorisnik.logovaoSe == true){
					$scope.zabraniPrikaz = false;
				} else {
					$location.path('/promenaLozinke');
				}
			}
		}).error(function(data) {
			alert("Neuspesno ucitavanje ulogovanog korisnika!");
		});	
		
		






});