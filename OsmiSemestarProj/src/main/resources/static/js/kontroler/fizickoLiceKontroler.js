var fizickoLiceKontroler = angular.module('xws_pi_bezb.fizickoLiceKontroler', []);

fizickoLiceKontroler.controller('fizickoLiceCtrl', function($scope, klijentServis, $window, $location) {

	$scope.resetujPoljaPretragaFizickaLica = function(){
		$scope.imeFizickogLicaPretraga = null;
		$scope.prezimeFizickogLicaPretraga = null;
		$scope.usernameFizickogLicaPretraga = null;
		$scope.emailFizickogLicaPretraga = null;
		$scope.brojLicneKarteFizickogLicaPretraga = null;
		$scope.datumIstekaLicneKarteFizickogLicaPretraga = null;
		$scope.telefonFizickogLicaPretraga = null;
		$scope.adresaFizickogLicaPretraga = null;
		$scope.jmbgFizickogLicaPretraga = null;
		$scope.imeRoditeljaFizickogLicaPretraga = null;
	}

	$scope.resetujPoljaDodavanjeFizickaLica = function() {
		$scope.imeFizickogLica = null;
		$scope.prezimeFizickogLica = null;
		$scope.usernameFizickogLica = null;
		$scope.emailFizickogLica = null;
		$scope.sifraFizickogLica = null;
		$scope.brojLicneKarteFizickogLica = null;
		$scope.datumIstekaLicneKarteFizickogLica = null;
		$scope.telefonFizickogLica = null;
		$scope.adresaFizickogLica = null;
		$scope.jmbgFizickogLica = null;
		$scope.imeRoditeljaFizickogLica = null;
	}
	
	$scope.resetujPoljaIzmenaFizickaLica = function() {
		$scope.imeFizickogLicaIzmena = null;
		$scope.prezimeFizickogLicaIzmena = null;
		$scope.brojLicneKarteFizickogLicaIzmena = null;
		$scope.datumIstekaLicneKarteFizickogLicaIzmena = null;
		$scope.telefonFizickogLicaIzmena = null;
		$scope.adresaFizickogLicaIzmena = null;
		$scope.jmbgFizickogLicaIzmena = null;
		$scope.imeRoditeljaFizickogLicaIzmena = null;
	}
	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretragaFizickaLica();
			klijentServis.izlistajFizickaLica().success(function(data) {
				$scope.fizickaLica = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje fizickih lica!");
			});
		}

		if(newTab == 2){
			if($scope.idFizickogLicaZaIzmenu == -1){
				alert("Prvo odaberite klijenta kog menjate.");
				newTab = 0;
			}
		}
		$scope.tab = newTab;
	};
	

	//INIT START
	$scope.setTab(0);
	$scope.idFizickogLicaZaIzmenu = -1;
	$scope.idFizickogLicaZaNext = -1;
	$scope.idFizickogLicaZaDetalje = -1;
	$scope.fizickoLiceZaDetalje = null;
	$scope.prikaziDrzaveBool = false;
	$scope.prikaziDrzaveBoolIzmena = false;
	

	
	//INIT END
	
	
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiFizickoLice = function(id) {
		klijentServis.izbrisiFizickoLice(id).success(function(data) {
			$scope.fizickaLica = data;
			$location.path('/fizickoLice');
		}).error(function(data) {
			alert("Nemoguce obrisati klijenta");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajFizickoLice = function() {

		var fizickoLice = {
				ime : $scope.imeFizickogLica,
				prezime : $scope.prezimeFizickogLica,
				username: $scope.usernameFizickogLica,
				email: $scope.emailFizickogLica,
				sifra: $scope.sifraFizickogLica,
				brojLicneKarte : $scope.brojLicneKarteFizickogLica,
				telefon : $scope.telefonFizickogLica,
				adresa : $scope.adresaFizickogLica,
				jmbg : $scope.jmbgFizickogLica,
				imeRoditelja : $scope.imeRoditeljaFizickogLica,
		}
		klijentServis.dodajFizickoLice(fizickoLice).success(function(data) {
			$scope.fizickaLica = data;
			$location.path('/fizickoLice');
			$scope.resetujPoljaDodavanjeFizickaLica();
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati klijenta");
		});
	}
	
	$scope.izmeniFizickoLice = function() {
		var fizickoLice = {
			id : $scope.idFizickogLicaZaIzmenu,
			ime : controller.imeFizickogLicaIzmena,
			prezime : controller.prezimeFizickogLicaIzmena,
			brojLicneKarte : controller.brojLicneKarteFizickogLicaIzmena,
			telefon : controller.telefonFizickogLicaIzmena,
			adresa : controller.adresaFizickogLicaIzmena,
			jmbg : controller.jmbgFizickogLicaIzmena,
			imeRoditelja : controller.imeRoditeljaFizickogLicaIzmena,
		}
		klijentServis.izmeniFizickoLice(fizickoLice).success(function(data) {
			$scope.fizickaLica = data;
			$location.path('/fizickoLice');
			$scope.idFizickogLicaZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti klijenta");
		});
	}
	
	$scope.setFizickoLiceZaIzmenu = function(fizickoLice) {
//			$scope.resetujPoljaIzmenaFizickaLica();
		$scope.idFizickogLicaZaIzmenu = fizickoLice.id;

		controller.imeFizickogLicaIzmena = fizickoLice.ime;
		controller.prezimeFizickogLicaIzmena = fizickoLice.prezime;
		controller.brojLicneKarteFizickogLicaIzmena = fizickoLice.brojLicneKarte;
		controller.telefonFizickogLicaIzmena = fizickoLice.telefon;
		controller.adresaFizickogLicaIzmena = fizickoLice.adresa;
		controller.jmbgFizickogLicaIzmena = fizickoLice.jmbg;
		controller.imeRoditeljaFizickogLicaIzmena = fizickoLice.imeRoditelja;
		$scope.setTab(2);
	}
	
	$scope.setFizickoLiceZaDetalje = function (fizickoLice){
		$scope.idFizickogLicaZaDetalje = fizickoLice.id;
		if($scope.idFizickogLicaZaDetalje != -1){
			klijentServis.ucitajFizickoLice(fizickoLice).success(function (data){
				$scope.fizickoLiceZaDetalje = data;
			}).error(function (data){
				$scope.fizickoLiceZaDetalje = null;
				alert("Neuspesno ucitavanje fizickog lica");
			});
		}
	}
	
	
	
	$scope.setFizickoLiceZaNext = function(fizickoLice) {
		if ($scope.idFizickogLicaZaNext == fizickoLice.id){			
			$scope.idFizickogLicaZaNext = -1;
		}
		else 
		{
			$scope.idFizickogLicaZaNext = fizickoLice.id;

		}
		
		$scope.idFizickogLicaZaIzmenu = -1;
		$scope.resetujPoljaIzmenaFizickaLica();
	}
	
	$scope.pretragaFizickihLicaPoPoljima = function() {
		var fizickoLice = {
				ime : $scope.imeFizickogLicaPretraga,
				prezime : $scope.prezimeFizickogLicaPretraga,
				username: $scope.usernameFizickogLicaPretraga,
				email: $scope.emailFizickogLicaPretraga,
				brojLicneKarte : $scope.brojLicneKarteFizickogLicaPretraga,
				telefon : $scope.telefonFizickogLicaPretraga,
				adresa : $scope.adresaFizickogLicaPretraga,
				jmbg : $scope.jmbgFizickogLicaPretraga,
				imeRoditelja : $scope.imeRoditeljaFizickogLicaPretraga
			}
		klijentServis.pretraziFizickaLica(fizickoLice).success(function(data) {
			$scope.fizickaLica = data;
			$scope.idFizickogLicaZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretraguFizickihLica = function(){
		klijentServis.izlistajFizickaLica().success(function(data) {
			$scope.fizickaLica = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje fizickih lica!");
		});
	}
});