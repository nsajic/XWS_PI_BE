var delatnostKontroler = angular.module('xws_pi_bezb.delatnostKontroler', []);

delatnostKontroler.controller('delatnostCtrl', function($scope, klijentServis, delatnostServis, $window, $location, klijentServis) {

	$scope.resetujPoljaPretraga = function(){
		$scope.nazivDelatnostiPretraga = null;
	}

	$scope.resetujPoljaDodavanje = function() {
		$scope.nazivDelatnosti = null;
	}
	
	$scope.resetujPoljaIzmena = function() {
		controller.nazivDelatnostiIzmena = null;
	}
	
	$scope.izlistajDelatnosti = function(){
		delatnostServis.izlistajDelatnosti().success(function(data) {
			$scope.delatnosti = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje delatnosti!");
		});
	}

	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretraga();
			$scope.izlistajDelatnosti();
		}
		$scope.tab = newTab;
	};

	//INIT START
	$scope.setTab(0);

	$scope.idDelatnostiZaIzmenu = -1;
	$scope.idDelatnostiZaNext = -1;
	$scope.delatnostZaNext = null;
	$scope.pravnaLicaDelatnosti = [];
	$scope.setovanDiv = -1;
	
	$scope.idPravnogLicaZaIzmenu = -1;
	$scope.idPravnogLicaZaDetalje = -1;
	$scope.pravnoLiceZaDetalje = null;
	
	//INIT END
	
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

	$scope.setujDiv = function (divNum){
		$scope.setovanDiv = divNum;
	}
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiDelatnost = function(id) {
		delatnostServis.izbrisiDelatnost(id).success(function(data) {
			$scope.izlistajDelatnosti();
			$location.path('/delatnost');
		}).error(function(data) {
			alert("Nemoguce obrisati delatnost");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajDelatnost = function() {
		var delatnost = {
			nazivDelatnosti : $scope.nazivDelatnosti
		}
		
		delatnostServis.dodajDelatnost(delatnost).success(function(data) {
			$scope.izlistajDelatnosti();
			$location.path('/delatnost');
			$scope.resetujPoljaDodavanje();
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati delatnost");
		});
	}
	
	$scope.izmeniDelatnost = function(id) {
		var delatnost = {
			id : id,
			nazivDelatnosti : controller.nazivDelatnostiIzmena
		}
		delatnostServis.izmeniDelatnost(delatnost).success(function(data) {
			$scope.izlistajDelatnosti();
			$location.path('/delatnost');
			$scope.idDelatnostiZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti delatnosti");
		});
	}
	
	$scope.setDelatnostZaIzmenu = function(delatnost) {
		if ($scope.idDelatnostiZaIzmenu == delatnost.id){			
			$scope.idDelatnostiZaIzmenu = -1;
			$scope.resetujPoljaIzmena();
		}
		else {
			$scope.idDelatnostiZaIzmenu = delatnost.id;
			controller.nazivDelatnostiIzmena = delatnost.nazivDelatnosti;
		}
	}
	
	$scope.ucitajPravnaLicaDelatnosti = function(){
		klijentServis.izlistajPravnaLicaDelatnosti($scope.idDelatnostiZaNext).success(function(data) {

			console.log("Success izlistavanja");
			$scope.pravnaLicaDelatnosti = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje pravnih lica!");
		});		
		
	}
	
	$scope.setDelatnostZaNext = function(delatnost) {
		$scope.idDelatnostiZaNext = delatnost.id;	
		$scope.delatnostZaNext = delatnost;
		$scope.ucitajPravnaLicaDelatnosti();
		$scope.setovanDiv = 0
	}
	
	$scope.pretragaPoPoljima = function() {
		var delatnost = {
			nazivDelatnosti : $scope.nazivDelatnostiPretraga
		}
		delatnostServis.pretraziDelatnosti(delatnost).success(function(data) {
			$scope.delatnosti = data;
			$scope.idDelatnostiZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretragu = function(){
		$scope.izlistajDelatnosti();

	}
	
	
	
	
	// OD KLIJENTA ZBOG NEXT-A

	
	$scope.obrisiPravnoLice = function(id) {
		klijentServis.izbrisiPravnoLice(id).success(function(data) {
			$scope.ucitajPravnaLicaDelatnosti();
			$location.path('/delatnost');
		}).error(function(data) {
			alert("Nemoguce obrisati klijenta");
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
			web : controller.webPravnogLicaIzmena,
			maticniBroj : controller.maticniBrojPravnogLicaIzmena,
			fax : controller.faxPravnogLicaIzmena,
			apr : controller.aprPravnogLicaIzmena,
			op : controller.opPravnogLicaIzmena,
			delatnost : $scope.delatnostZaNext
		}
		klijentServis.izmeniPravnoLice(pravnoLice).success(function(data) {
			$scope.ucitajPravnaLicaDelatnosti();
			//$location.path('/delatnost');
			$scope.idPravnogLicaZaIzmenu = -1;
			$scope.setovanDiv = 0;
		}).error(function(data) {
			alert("Nemoguce izmeniti klijenta");
		});
	}
	
	$scope.setPravnoLiceZaIzmenu = function(pravnoLice) {
		
//		$scope.resetujPoljaIzmenaPravnaLica();
		$scope.idPravnogLicaZaIzmenu = pravnoLice.id;
	
		controller.imePravnogLicaIzmena = pravnoLice.ime;
		controller.prezimePravnogLicaIzmena = pravnoLice.prezime;
		controller.brojLicneKartePravnogLicaIzmena = pravnoLice.brojLicneKarte;
		controller.datumIstekaLicneKartePravnogLicaIzmena = pravnoLice.datumIstekaLicneKarte;
		controller.telefonPravnogLicaIzmena = pravnoLice.telefon;
		controller.adresaPravnogLicaIzmena = pravnoLice.adresa;
		controller.pibPravnogLicaIzmena = pravnoLice.pib;
		controller.nazivPravnogLicaIzmena = pravnoLice.naziv;
		controller.webPravnogLicaIzmena = pravnoLice.web;
		controller.maticniBrojPravnogLicaIzmena = pravnoLice.maticniBroj;
		controller.faxPravnogLicaIzmena = pravnoLice.fax;
		controller.aprPravnogLicaIzmena = pravnoLice.apr;
		controller.opPravnogLicaIzmena = pravnoLice.op;
	
		$scope.setovanDiv = 2;
	}
	
	$scope.setPravnoLiceZaDetalje = function (pravnoLice){
		$scope.idPravnogLicaZaDetalje = pravnoLice.id;
		if($scope.idPravnogLicaZaDetalje != -1){
			klijentServis.ucitajPravnoLice(pravnoLice).success(function (data){
				$scope.pravnoLiceZaDetalje = data;
				$scope.setovanDiv = 1;
			}).error(function (data){
				$scope.pravnoLiceZaDetalje = null;
				alert("Neuspesno ucitavanje pravnog lica");
			});
		}
	}
	
	
	$scope.pretragaPravnihLicaPoPoljima = function() {
		var pravnoLice = {
				ime : $scope.imePravnogLicaPretraga,
				prezime : $scope.prezimePravnogLicaPretraga,
				username: $scope.usernamePravnogLicaPretraga,
				email: $scope.emailPravnogLicaPretraga,
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
		var zaPretragu  = {
				pravnoLice : pravnoLice,
				delatnost : $scope.delatnostZaNext
		}
		
		klijentServis.pretragaPravnihLicaDelatnosti(zaPretragu).success(function(data) {
			$scope.pravnaLicaDelatnosti = data;
			$scope.idPravnogLicaZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretraguPravnihLica = function(){
		$scope.ucitajPravnaLicaDelatnosti();
	}
	
	
	
});