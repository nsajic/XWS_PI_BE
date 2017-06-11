var pravnoLiceKontroler = angular.module('xws_pi_bezb.pravnoLiceKontroler', []);

pravnoLiceKontroler.controller('pravnoLiceCtrl', function($scope, klijentServis, $window, $location) {

	$scope.resetujPoljaPretragaPravnaLica = function(){
		$scope.imePravnogLicaPretraga = null;
		$scope.prezimePravnogLicaPretraga = null;
		$scope.brojLicneKartePravnogLicaPretraga = null;
		$scope.datumIstekaLicneKartePravnogLicaPretraga = null;
		$scope.telefonPravnogLicaPretraga = null;
		$scope.adresaPravnogLicaPretraga = null;
		$scope.pibPravnogLicaPretraga = null;
		$scope.nazivPravnogLicaPretraga = null;
		$scope.emailPravnogLicaPretraga = null;
		$scope.webPravnogLicaPretraga = null;
		$scope.maticniBrojPravnogLicaPretraga = null;
		$scope.faxPravnogLicaPretraga = null;
		$scope.aprPravnogLicaPretraga = null;
		$scope.opPravnogLicaPretraga = null;
	}

	$scope.resetujPoljaDodavanjePravnaLica = function() {
		$scope.imePravnogLica = null;
		$scope.prezimePravnogLica = null;
		$scope.brojLicneKartePravnogLica = null;
		$scope.datumIstekaLicneKartePravnogLica = null;
		$scope.telefonPravnogLica = null;
		$scope.adresaPravnogLica = null;
		$scope.pibPravnogLica = null;
		$scope.nazivPravnogLica = null;
		$scope.emailPravnogLica = null;
		$scope.webPravnogLica = null;
		$scope.maticniBrojPravnogLica = null;
		$scope.faxPravnogLica = null;
		$scope.aprPravnogLica = null;
		$scope.opPravnogLica = null;	}
	
	$scope.resetujPoljaIzmenaPravnaLica = function() {
		$scope.imePravnogLicaIzmena = null;
		$scope.prezimePravnogLicaIzmena = null;
		$scope.brojLicneKartePravnogLicaIzmena = null;
		$scope.datumIstekaLicneKartePravnogLicaIzmena = null;
		$scope.telefonPravnogLicaIzmena = null;
		$scope.adresaPravnogLicaIzmena = null;
		$scope.pibPravnogLicaIzmena = null;
		$scope.nazivPravnogLicaIzmena = null;
		$scope.emailPravnogLicaIzmena = null;
		$scope.webPravnogLicaIzmena = null;
		$scope.maticniBrojPravnogLicaIzmena = null;
		$scope.faxPravnogLicaIzmena = null;
		$scope.aprPravnogLicaIzmena = null;
		$scope.opPravnogLicaIzmena = null;
	}
	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretragaPravnaLica();
			klijentServis.izlistajPravnaLica().success(function(data) {
				$scope.pravnaLica = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje pravnih lica!");
			});
		}
		
		if(newTab == 1){
			$scope.ucitajDelatnosti();
		}
		if(newTab == 2){
			if($scope.idPravnogLicaZaIzmenu == -1){
				alert("Prvo odaberite klijenta kog menjate.");
				newTab = 0;
			}
		}
		$scope.tab = newTab;
	};
	
	$scope.ucitajDelatnosti = function (){
		klijentServis.ucitajDelatnosti().success(function(data) {
			$scope.delatnosti = data;
		}).error(function(data) {
			alert("Nemoguce ucitati delatnosti");
		});
	}

	//INIT START
	$scope.setTab(0);
	$scope.delatnosti = [];
	$scope.idPravnogLicaZaIzmenu = -1;
	$scope.idPravnogLicaZaNext = -1;
	$scope.idPravnogLicaZaDetalje = -1;
	$scope.pravnoLiceZaDetalje = null;

	//INIT END
	
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiPravnoLice = function(id) {
		klijentServis.izbrisiPravnoLice(id).success(function(data) {
			$scope.pravnaLica = data;
			$location.path('/pravnoLice');
		}).error(function(data) {
			alert("Nemoguce obrisati klijenta");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajPravnoLice = function() {
		var pravnoLice = {
				ime : $scope.imePravnogLica,
				prezime : $scope.prezimePravnogLica,
				brojLicneKarte : $scope.brojLicneKartePravnogLica,
				datumIstekaLicneKarte : $scope.datumIstekaLicneKartePravnogLica,
				telefon : $scope.telefonPravnogLica,
				adresa : $scope.adresaPravnogLica,
				pib : $scope.pibPravnogLica,
				naziv : $scope.nazivPravnogLica,
				email : $scope.emailPravnogLica,
				web : $scope.webPravnogLica,
				maticniBroj : $scope.maticniBrojPravnogLica,
				fax : $scope.faxPravnogLica,
				apr : $scope.aprPravnogLica,
				op : $scope.opPravnogLica,
				delatnost : $scope.delatnostPravnogLica
		}
		klijentServis.dodajPravnoLice(pravnoLice).success(function(data) {
			$scope.pravnaLica = data;
			$location.path('/pravnoLice');
			$scope.resetujPoljaDodavanjePravnaLica();
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati klijenta");
		});
	}
	
	$scope.izmeniPravnoLice = function() {
		var pravnoLice = {
			id : $scope.idPravnogLicaZaIzmenu,
			ime : controller.imePravnogLicaIzmena,
			prezime : controller.prezimePravnogLicaIzmena,
			brojLicneKarte : controller.brojLicneKartePravnogLicaIzmena,
			datumIstekaLicneKarte : controller.datumIstekaLicneKartePravnogLicaIzmena,
			telefon : controller.telefonPravnogLicaIzmena,
			adresa : controller.adresaPravnogLicaIzmena,
			pib : controller.pibPravnogLicaIzmena,
			naziv : controller.nazivPravnogLicaIzmena,
			email : controller.emailPravnogLicaIzmena,
			web : controller.webPravnogLicaIzmena,
			maticniBroj : controller.maticniBrojPravnogLicaIzmena,
			fax : controller.faxPravnogLicaIzmena,
			apr : controller.aprPravnogLicaIzmena,
			op : controller.opPravnogLicaIzmena,
			delatnost : controller.delatnostPravnogLicaIzmena
		}
		klijentServis.izmeniPravnoLice(pravnoLice).success(function(data) {
			$scope.pravnaLica = data;
			$location.path('/pravnoLice');
			$scope.idPravnogLicaZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti klijenta");
		});
	}
	
	$scope.setPravnoLiceZaIzmenu = function(pravnoLice) {
//			$scope.resetujPoljaIzmenaPravnaLica();
		$scope.ucitajDelatnosti();
		$scope.idPravnogLicaZaIzmenu = pravnoLice.id;

		controller.imePravnogLicaIzmena = pravnoLice.ime;
		controller.prezimePravnogLicaIzmena = pravnoLice.prezime;
		controller.brojLicneKartePravnogLicaIzmena = pravnoLice.brojLicneKarte;
		controller.datumIstekaLicneKartePravnogLicaIzmena = pravnoLice.datumIstekaLicneKarte;
		controller.telefonPravnogLicaIzmena = pravnoLice.telefon;
		controller.adresaPravnogLicaIzmena = pravnoLice.adresa;
		controller.pibPravnogLicaIzmena = pravnoLice.pib;
		controller.nazivPravnogLicaIzmena = pravnoLice.naziv;
		controller.emailPravnogLicaIzmena = pravnoLice.email;
		controller.webPravnogLicaIzmena = pravnoLice.web;
		controller.maticniBrojPravnogLicaIzmena = pravnoLice.maticniBroj;
		controller.faxPravnogLicaIzmena = pravnoLice.fax;
		controller.aprPravnogLicaIzmena = pravnoLice.apr;
		controller.opPravnogLicaIzmena = pravnoLice.op;
		controller.delatnostPravnogLicaIzmena = pravnoLice.delatnost;

		$scope.setTab(2);
	}
	
	$scope.setPravnoLiceZaDetalje = function (pravnoLice){
		$scope.idPravnogLicaZaDetalje = pravnoLice.id;
		if($scope.idPravnogLicaZaDetalje != -1){
			klijentServis.ucitajPravnoLice(pravnoLice).success(function (data){
				$scope.pravnoLiceZaDetalje = data;
			}).error(function (data){
				$scope.pravnoLiceZaDetalje = null;
				alert("Neuspesno ucitavanje pravnog lica");
			});
		}
	}
	
	
	
	$scope.setPravnoLiceZaNext = function(pravnoLice) {
		if ($scope.idPravnogLicaZaNext == pravnoLice.id){			
			$scope.idPravnogLicaZaNext = -1;
		}
		else 
		{
			$scope.idPravnogLicaZaNext = pravnoLice.id;

		}
		
		$scope.idPravnogLicaZaIzmenu = -1;
		$scope.resetujPoljaIzmenaPravnaLica();

	}
	
	$scope.pretragaPravnihLicaPoPoljima = function() {
		var pravnoLice = {
				ime : $scope.imePravnogLicaPretraga,
				prezime : $scope.prezimePravnogLicaPretraga,
				brojLicneKarte : $scope.brojLicneKartePravnogLicaPretraga,
				datumIstekaLicneKarte : $scope.datumIstekaLicneKartePravnogLicaPretraga,
				telefon : $scope.telefonPravnogLicaPretraga,
				adresa : $scope.adresaPravnogLicaPretraga,
				pib : $scope.pibPravnogLicaPretraga,
				naziv : $scope.nazivPravnogLicaPretraga,
				email : $scope.emailPravnogLicaPretraga,
				web : $scope.webPravnogLicaPretraga,
				maticniBroj : $scope.maticniBrojPravnogLicaPretraga,
				fax : $scope.faxPravnogLicaPretraga,
				apr : $scope.aprPravnogLicaPretraga,
				op : $scope.opPravnogLicaPretraga
			}
			console.log(pravnoLice);
		klijentServis.pretraziPravnaLica(pravnoLice).success(function(data) {
			$scope.pravnaLica = data;
			$scope.idPravnogLicaZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretraguPravnihLica = function(){
		klijentServis.izlistajPravnaLica().success(function(data) {
			$scope.pravnaLica = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje pravnih lica!");
		});
	}
	
	
	// ZOOM

	
	$scope.odaberiDelatnost = function (delatnost){
		if (delatnost != null) {
			for (var i = 0; i < $scope.delatnosti.length; i++) {
				if ($scope.delatnosti[i].id == delatnost.id) {
					$scope.delatnostPravnogLica = $scope.delatnosti[i];
				}
			}
		} else {
			controller.delatnostPravnogLicaIzmena = null;
		}
		$scope.prikaziDelatnostiBool = false;
		
	}
	
	$scope.odaberiDelatnostIzmena = function (delatnost){
		$scope.prikaziDelatnostBoolIzmena = false;
		if (delatnost != null) {
			for (var i = 0; i < $scope.delatnosti.length; i++) {
				if ($scope.delatnosti[i].id == delatnost.id) {
					controller.delatnostPravnogLicaIzmena = $scope.delatnosti[i];
				}
			}
		} else {
			controller.delatnostPravnogLicaIzmena = null;
		}
		$scope.prikaziDelatnostiBoolIzmena = false;
	}
	
	
	$scope.skloni =function (){
		$scope.prikaziDelatnostiBool = false;
	}	

	$scope.skloniIzmena =function (){
		$scope.prikaziDelatnostiBoolIzmena = false;
	}	


	

});