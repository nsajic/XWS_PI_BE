var welcomeKontroler = angular.module('xws_pi_bezb.welcomeKontroler', []);

welcomeKontroler.controller('welcomeCtrl', function($scope, $location, $window, welcomeServis, klijentServis) {

	$scope.logOut = function(){

		welcomeServis.logOut().success(function(data) {
			if(data.message == "Izlogovan"){
				//$window.location.href = '/';
				$location.path('/');
			}else{
			}
		});
	}
	
	$scope.ulogovanKorisnik = new Object();
	$scope.zabraniPrikaz = false;
	
	$scope.ucitajTrenutnogKorisnika = function(){
		klijentServis.ucitajUlogovanogKorisnika().success(function(data) {
			$scope.ulogovanKorisnik = data;
			//TODO JSON IGNORE ZEZA
			console.log(data);
		}).error(function(data) {
			alert("Neuspesno ucitavanje ulogovanog korisnika!");
		});	
	}
	
	$scope.ucitajTrenutnogKorisnika();
	
	alert($scope.ulogovanKorisnik.rola.naziv);
	if($scope.ulogovanKorisnik.rola.naziv == "FizickoLice"){
		if($scope.ulogovanKorisnik.logovaoSe == true){
			$scope.zabraniPrikaz = true;
		} else {
			$scope.zabraniPrikaz = false;
		}
	}
	


});