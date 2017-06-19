var racunKontroler = angular.module('xws_pi_bezb.racunKontroler', []);

racunKontroler.controller('racunCtrl', function($scope, racunServis, $window, $location, klijentServis) {

	$scope.resetujPoljaPretragaRacuni = function(){
		$scope.brojRacunaPretraga = null;
		$scope.statusRacunaPretraga = null;
		$scope.klijentRacunaPretraga = null;
		$scope.bankaRacunaPretraga = null;
		$scope.valutaRacunaPretraga = null;
	}
	
	$scope.resetujPoljaDodavanjeRacuni = function() {
		$scope.brojRacuna = null;
		$scope.statusRacuna = null;
		$scope.klijentRacuna = null;
		$scope.bankaRacuna = null;
		$scope.valutaRacuna = null;
	}
	
	$scope.resetujPoljaIzmenaRacuni = function() {
		$scope.brojRacunaIzmena = null;
		$scope.statusRacunaIzmena = null;
		$scope.klijentRacunaIzmena = null;
		$scope.bankaRacunaIzmena = null;
		$scope.valutaRacunaIzmena = null;
	}
	
	$scope.izlistajRacune = function (){
		racunServis.izlistajRacune().success(function(data) {
			$scope.racuni = data;
			console.log($scope.racuni);
			
		}).error(function(data) {
			alert("Neuspesno izlistavanje racuna!");
		});
	}
	
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
	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretragaRacuni();
			$scope.izlistajRacune();
		}
		
		if(newTab == 1){
			$scope.ucitajValute();
			$scope.ucitajBanke();
			$scope.ucitajKlijente();
		}
		if(newTab == 2){
			if($scope.idRacunaZaIzmenu == -1){
				alert("Prvo odaberite racun kog menjate.");
				newTab = 0;
			}
		}
		$scope.tab = newTab;
	};
	
	$scope.ucitajValute = function (){
		racunServis.ucitajValute().success(function(data) {
			$scope.valute = data;
		}).error(function(data) {
			alert("Nemoguce ucitati valute");
		});
	}
	
	$scope.ucitajBanke = function (){
		racunServis.ucitajBanke().success(function(data) {
			$scope.banke = data;
		}).error(function(data) {
			alert("Nemoguce ucitati banke");
		});
	}
	
	$scope.ucitajKlijente = function (){
		racunServis.ucitajKlijente().success(function(data) {
			$scope.klijenti = data;
		}).error(function(data) {
			alert("Nemoguce ucitati klijente");
		});
	}

	//INIT START
	$scope.setTab(0);
	$scope.valute = [];
	$scope.banke = [];
	$scope.klijenti = [];
	$scope.idRacunaZaIzmenu = -1;
	$scope.idRacunaZaDnevnaStanja = -1;
	$scope.idRacunaZaZatvaranje = -1;
	$scope.dnevnaStanjaOdabranogRacuna = [];
	

	
	//INIT END
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiRacun = function(id) {
		racunServis.izbrisiRacun(id).success(function(data) {
			$scope.izlistajRacune();
			$location.path('/racun');
		}).error(function(data) {
			alert("Nemoguce obrisati racun");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajRacun = function() {

		var racun = {
				brojRacuna : $scope.brojRacuna,
				statusRacuna : $scope.statusRacuna,
				klijent : $scope.klijentRacuna,
				banka : $scope.bankaRacuna,
				valuta : $scope.valutaRacuna
		}
		racunServis.dodajRacun(racun).success(function(data) {
			$scope.izlistajRacune();
			alert("Racun uspesno dodat");
			$location.path('/racun');
			$scope.resetujPoljaDodavanjeRacuni();
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati racun");
		});
	}
	
	$scope.izmeniRacun = function() {
		var racun = {
			id : $scope.idRacunaZaIzmenu,
			brojRacuna : controller.brojRacunaIzmena,
			statusRacuna : controller.statusRacunaIzmena,
			klijent : controller.klijentRacunaIzmena,
			banka : controller.bankaRacunaIzmena,
			valuta : controller.valutaRacunaIzmena,
		}
		racunServis.izmeniRacun(racun).success(function(data) {
			$scope.izlistajRacune();
			$location.path('/racun');
			$scope.idRacunaZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti klijenta");
		});
	}
	
	$scope.getIdRacunaZaZatvaranje = function (){
		return $scope.idRacunaZaZatvaranje;
	}
	
	$scope.getIdRacuna = function (){
		return $scope.idRacunaZaDnevnaStanja;
	}
	
	$scope.setRacunZaZatvaranje = function (racun){
		$scope.idRacunaZaZatvaranje = racun.id;
		$scope.idRacunaZaDnevnaStanja = -1;
		$scope.idRacunaZaIzmenu = -1;
	}
	
	$scope.setRacunZaIzmenu = function(racun) {
		$scope.ucitajBanke();
		$scope.ucitajValute();
		$scope.ucitajKlijente();
		$scope.idRacunaZaIzmenu = racun.id;
		$scope.idRacunaZaDnevnaStanja = -1;
		$scope.idRacunaZaZatvaranje = -1;

		//TODO: Treba da se odradi combo ko covek za klijente
		controller.brojRacunaIzmena = racun.brojRacuna;
		controller.statusRacunaIzmena = racun.statusRacuna;

		controller.klijentRacunaIzmena = racun.klijent;
		controller.bankaRacunaIzmena = racun.banka;
		controller.valutaRacunaIzmena = racun.valuta;
		
		$scope.setTab(2);
	}

	
	$scope.setRacunZaDnevnaStanja = function (racun){
		$scope.idRacunaZaDnevnaStanja = racun.id;

	
		racunServis.ucitajDnevnaStanjaOdabranogRacuna(racun).success(function(data) {
			$scope.dnevnaStanjaOdabranogRacuna = data;
		}).error(function(data) {
			alert("Nemoguce ucitati dnevna stanja racuna");
		});
		
		
		
		
	}
	
	$scope.prebaciSredstvaNaRacun = function(){
		// TODO: Implementirati prebacivanje sredstava i zatvaranje racuna
	}
	
	$scope.nazadNaRacune = function (){
		$scope.idRacunaZaDnevnaStanja = -1;
		$scope.idRacunaZaZatvaranje = -1;
		$scope.racunGdePrebacujem = "";
	}


	$scope.pretragaRacunaPoPoljima = function() {
		var racun = {
				brojRacuna : $scope.brojRacunaPretraga,
				statusRacuna : $scope.statusRacunaPretraga,
				klijent : $scope.statusRacunaPretraga,
				banka : $scope.statusRacunaPretraga,
				valuta : $scope.statusRacunaPretraga

			}
		racunServis.pretraziRacune(racun).success(function(data) {
			$scope.racun = data;
			$scope.idRacunaZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretraguRacuna = function(){
		$scope.izlistajRacune();
	}
	
	//KRECE ZUM
	// ZOOM KLIJENT

	$scope.odaberiKlijenta = function (klijent){
		if (klijent != null) {
			for (var i = 0; i < $scope.klijenti.length; i++) {
				if ($scope.klijenti[i].id == klijent.id) {
					$scope.klijentRacuna = $scope.klijenti[i];
				}
			}
		} else {
			controller.klijentRacunaIzmena = null;
		}
		$scope.prikaziKlijenteBool = false;
		
	}
	
	$scope.odaberiKlijentaIzmena = function (klijent){
		$scope.prikaziKlijentaBoolIzmena = false;
		if (klijent != null) {
			for (var i = 0; i < $scope.klijenti.length; i++) {
				if ($scope.klijenti[i].id == klijent.id) {
					controller.klijentRacunaIzmena = $scope.klijenti[i];
				}
			}
		} else {
			controller.klijentRacunaIzmena = null;
		}
		$scope.prikaziKlijenteBoolIzmena = false;
	}
	
	
	$scope.skloniKlijente =function (){
		$scope.prikaziklijenteBool = false;
	}	

	$scope.skloniKlijenteIzmena =function (){
		$scope.prikaziKlijenteBoolIzmena = false;
	}	


	

	
	
	// ZOOM BANKA

	
	$scope.odaberiBanku = function (banka){
		if (banka != null) {
			for (var i = 0; i < $scope.banke.length; i++) {
				if ($scope.banke[i].id == banka.id) {
					$scope.bankaRacuna = $scope.banke[i];
				}
			}
		} else {
			controller.bankaRacunaIzmena = null;
		}
		$scope.prikaziBankeBool = false;
		
	}
	
	$scope.odaberiBankuIzmena = function (banka){
		$scope.prikaziBankeBoolIzmena = false;
		if (banka != null) {
			for (var i = 0; i < $scope.banke.length; i++) {
				if ($scope.banke[i].id == banka.id) {
					controller.bankaRacunaIzmena = $scope.banke[i];
				}
			}
		} else {
			controller.bankaRacunaIzmena = null;
		}
		$scope.prikaziBankeBoolIzmena = false;
	}
	
	
	$scope.skloniBanke =function (){
		$scope.prikaziBankeBool = false;
	}	

	$scope.skloniBankeIzmena =function (){
		$scope.prikaziBankeBoolIzmena = false;
	}	



	

	
	
	// ZOOM VALUTA

	
	$scope.odaberiValutu = function (valuta){
		if (valuta != null) {
			for (var i = 0; i < $scope.valute.length; i++) {
				if ($scope.valute[i].id == valuta.id) {
					$scope.valutaRacuna = $scope.valute[i];
				}
			}
		} else {
			controller.valutaRacunaIzmena = null;
		}
		$scope.prikaziValuteBool = false;
		
	}
	
	$scope.odaberiValutuIzmena = function (valuta){
		$scope.prikaziValuteBoolIzmena = false;
		if (valuta != null) {
			for (var i = 0; i < $scope.valute.length; i++) {
				if ($scope.valute[i].id == valuta.id) {
					controller.valutaRacunaIzmena = $scope.valute[i];
				}
			}
		} else {
			controller.valutaRacunaIzmena = null;
		}
		$scope.prikaziValuteBoolIzmena = false;
	}
	
	
	$scope.skloniValute = function (){
		$scope.prikaziValuteBool = false;
	}	

	$scope.skloniValuteIzmena =function (){
		$scope.prikaziValuteBoolIzmena = false;
	}	

});