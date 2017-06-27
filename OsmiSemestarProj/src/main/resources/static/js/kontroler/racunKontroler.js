var racunKontroler = angular.module('xws_pi_bezb.racunKontroler', []);

racunKontroler.controller('racunCtrl', function($scope, racunServis, $window, $location, klijentServis) {

	$scope.resetujPoljaPretragaRacuni = function(){
		$scope.brojRacunaPretraga = null;
		$scope.klijentRacunaPretraga = null;
		$scope.valutaRacunaPretraga = null;
	}
	
	$scope.resetujPoljaDodavanjeRacuni = function() {
		$scope.brojRacuna = null;
		$scope.klijentRacuna = null;
		$scope.valutaRacuna = null;
	}
	
	$scope.resetujPoljaIzmenaRacuni = function() {
		$scope.brojRacunaIzmena = null;
		$scope.klijentRacunaIzmena = null;
		$scope.valutaRacunaIzmena = null;
	}
	
	$scope.resetujZaView = function (){
		$scope.idRacunaZaIzmenu = -1;
		$scope.idRacunaZaZatvaranje = -1;
		$scope.idRacunaZaDnevnaStanja = -1;
		$scope.idDnevnogStanjaZaAnalitiku = -1;
	}
	
	$scope.izlistajRacune = function (){
		racunServis.izlistajRacune().success(function(data) {
			$scope.racuni = data;
			
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
			$scope.ucitajKlijente();
			$scope.izlistajFizickaLica();
			$scope.izlistajPravnaLica();
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
	
	$scope.ucitajKlijente = function (){
		racunServis.ucitajKlijente().success(function(data) {
			$scope.klijenti = data;
		}).error(function(data) {
			alert("Nemoguce ucitati klijente");
		});
	}	

	
	$scope.izlistajFizickaLica = function (){
		klijentServis.izlistajFizickaLica().success(function(data) {
			console.log(data + " FIzicka ");
			$scope.fizickaLica = data;
		}).error(function(data) {
			alert("Nemoguce ucitati klijente");
		});
	}	
	$scope.izlistajPravnaLica = function (){
		klijentServis.izlistajPravnaLica().success(function(data) {
			console.log(data + " Pravna ");
			$scope.pravnaLica = data;
		}).error(function(data) {
			alert("Nemoguce ucitati klijente");
		});
	}

	//INIT START
	$scope.setTab(0);
	$scope.valute = [];
	$scope.klijenti = [];
	$scope.pravnaLica = [];
	$scope.fizickaLica= [];
	$scope.idRacunaZaIzmenu = -1;
	$scope.idRacunaZaZatvaranje = -1;
	$scope.idRacunaZaDnevnaStanja = -1;
	$scope.idDnevnogStanjaZaAnalitiku = -1;
	$scope.kojaLicaDaPrikaze = "F";
	$scope.tipKlijentaModel = 1;
	$scope.dnevnaStanjaOdabranogRacuna = [];
	$scope.analitikeOdabranogDnevnogStanja = [];
	

	
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
		if($scope.kojaLicaDaPrikaze == "F"){
			var racun = {
					brojRacuna : $scope.brojRacuna,
					statusRacuna : $scope.statusRacuna,
					klijent : $scope.fizickoLiceRacuna,
					valuta : $scope.valutaRacuna
			}			
			
		} else if ($scope.kojaLicaDaPrikaze == "P"){
			var racun = {
					brojRacuna : $scope.brojRacuna,
					statusRacuna : $scope.statusRacuna,
					klijent : $scope.pravnoLiceRacuna,
					valuta : $scope.valutaRacuna
			}
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
	
	$scope.getPrikaziSveRacune = function (){
		return $scope.idRacunaZaIzmenu == -1 && $scope.idRacunaZaZatvaranje == -1 && $scope.idDnevnogStanjaZaAnalitiku == -1 && $scope.idRacunaZaDnevnaStanja == -1;
	}
	
	$scope.getIdRacunaZaZatvaranje = function (){
		return $scope.idRacunaZaZatvaranje;
	}
	
	$scope.getIdDnevnogStanjaZaAnalitiku = function (){
		return $scope.idDnevnogStanjaZaAnalitiku;
	}
	
	$scope.getIdRacuna = function (){
		return $scope.idRacunaZaDnevnaStanja;
	}
	
	$scope.setRacunZaZatvaranje = function (racun){
		$scope.resetujZaView();
		$scope.idRacunaZaZatvaranje = racun.id;

	}
	
	$scope.setRacunZaIzmenu = function(racun) {
		$scope.ucitajValute();
		$scope.ucitajKlijente();

		$scope.resetujZaView();
		$scope.idRacunaZaIzmenu = racun.id;


		//TODO: Treba da se odradi combo ko covek za klijente
		controller.brojRacunaIzmena = racun.brojRacuna;
		controller.statusRacunaIzmena = racun.statusRacuna;

		controller.klijentRacunaIzmena = racun.klijent;
		controller.valutaRacunaIzmena = racun.valuta;
		
		$scope.setTab(2);
	}

	$scope.promenjenCheck = function(tipLica){
		$scope.kojaLicaDaPrikaze = tipLica;
		$scope.izlistajFizickaLica();
		$scope.izlistajPravnaLica();
	}

	$scope.setRacunZaDnevnaStanja = function (racun){
		$scope.resetujZaView();
		$scope.idRacunaZaDnevnaStanja = racun.id;
		
		racunServis.ucitajDnevnaStanjaOdabranogRacuna(racun).success(function(data) {
			$scope.dnevnaStanjaOdabranogRacuna = data;
		}).error(function(data) {
			alert("Nemoguce ucitati dnevna stanja racuna");
		});
	}

	$scope.setDnevnoStanjeRacunaNext = function(dnevnoStanje){
		$scope.resetujZaView();
		$scope.idDnevnogStanjaZaAnalitiku = dnevnoStanje.id
		
		racunServis.ucitajAnalitikeOdabranogDnevnogStanja(dnevnoStanje).success(function(data) {
			$scope.analitikeOdabranogDnevnogStanja = data;
		}).error(function(data) {
			alert("Nemoguce ucitati analitike");
		});
	}
	
	
	$scope.prebaciSredstva = function(){
		var racun = {
			idRacunKojiGasim : $scope.idRacunaZaZatvaranje,
			brojRacunaNaKojiPrebacujem : $scope.racunGdePrebacujem
		}
		racunServis.zatvoriRacun(racun).success(function (data){
			$scope.izlistajRacune();
			$scope.nazadNaRacune();
		}).error(function (data){
			$scope.izlistajRacune();
			alert("Nemoguc prenos");
			
			$scope.nazadNaRacune();
		});
	}
	
	$scope.nazadNaRacune = function (){
		$scope.resetujZaView();
		$scope.racunGdePrebacujem = "";
	}
	$scope.nazadNaDnevnaStanja = function (){
		$scope.resetujZaView();
		$scope.idRacunaZaDnevnaStanja = 0;
	}


	
	// KRECE PRETRAGA
	
	$scope.pretragaRacunaPoPoljima = function() {
		var racun = {
				brojRacuna : $scope.brojRacunaPretraga,
				statusRacuna : $scope.statusRacunaPretraga,
				klijent : $scope.statusRacunaPretraga,
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
			
			if($scope.kojaLicaDaPrikaze == "F"){
				for (var i = 0; i < $scope.fizickaLica.length; i++) {
					if ($scope.fizickaLica[i].id == klijent.id) {
						$scope.fizickoLiceRacuna = $scope.fizickaLica[i];
					}
				}
			} else if ($scope.kojaLicaDaPrikaze == "P") {
				for (var i = 0; i < $scope.pravnaLica.length; i++) {
					if ($scope.pravnaLica[i].id == klijent.id) {
						$scope.pravnoLiceRacuna = $scope.pravnaLica[i];
					}
				}				
			}
		}
		controller.klijentRacunaIzmena = null;
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
		$scope.prikaziKlijenteBool = false;
	}	

	$scope.skloniKlijenteIzmena =function (){
		$scope.prikaziKlijenteBoolIzmena = false;
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