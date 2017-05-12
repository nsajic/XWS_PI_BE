var naseljenoMestoKontroler = angular.module(
		'xws_pi_bezb.naseljenoMestoKontroler', []);

naseljenoMestoKontroler.controller('naseljenoMestoCtrl', function($scope,
		naseljenoMestoServis, $window, $location) {
	var vm = this;
	$scope.setTab = function(newTab) {
		$scope.tab = newTab;
	};

	// INIT START
	$scope.setTab(0);

	$scope.prikaziDrzaveBool = false;
	$scope.prikaziDrzaveIzmenaBool = false;
	
	$scope.idNaseljenogMestaZaIzmenu = -1;

	naseljenoMestoServis.izlistajNaseljenaMesta().success(function(data) {
		$scope.naseljenaMesta = data;
	}).error(function(data) {
		alert("Neuspesno izlistavanje naseljenih mesta!");
	});

	naseljenoMestoServis.izlistajDrzave().success(function(data) {
		$scope.drzave = data;
	}).error(function(data) {
		alert("Neuspesno izlistavanje drzava!");
	});
	// INIT END

	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};

	$scope.obrisiNaseljenoMesto = function(id) {
		naseljenoMestoServis.izbrisiNaseljenoMesto(id).success(function(data) {
			$scope.naseljenaMesta = data;
			$location.path('/naseljenoMesto');
		}).error(function(data) {
			alert("Nemoguce obrisati naseljeno mesto");
		});
	}

	$scope.isVisible = function() {
		return $scope.prikaz;
	}

	$scope.dodajNaseljenoMesto = function() {
		var naseljenoMesto = {
			naziv : $scope.nazivNaseljenogMesta,
			pttOznaka : $scope.pttOznakaNaseljenogMesta,
			drzava : $scope.drzavaNaseljenogMesta
		}
		naseljenoMestoServis.dodajNaseljenoMesto(naseljenoMesto).success(
				function(data) {
					$scope.naseljenaMesta = data;
					$location.path('/naseljenoMesto');
					$scope.nazivNaseljenogMesta = null;
					$scope.drzavaNaseljenogMesta = null;
					$scope.pttOznakaNaseljenogMesta = null;
					$scope.setTab(0);
				}).error(function(data) {
			alert("Nemoguce dodati naseljenoMesto");
		});
	}

	$scope.izmeniNaseljenoMesto = function(id) {
		var naseljenoMesto = {
			id : id,
			naziv : vm.nazivNaseljenogMestaIzmena,
			pttOznaka : vm.pttOznakaNaseljenogMestaIzmena,
			drzava : vm.drzavaNaseljenogMestaIzmena
		}
		// TODO Ispraviti jer uzima samo setovano na pocetku
		naseljenoMestoServis.izmeniNaseljenoMesto(naseljenoMesto).success(
				function(data) {
					$scope.naseljenaMesta = data;
					$location.path('/naseljenoMesto');
					$scope.idNaseljenogMestaZaIzmenu = -1;
					$scope.setTab(0);
				}).error(function(data) {
			alert("Nemoguce izmeniti naseljeno mesto");
		});
	}

	$scope.setNaseljenoMestoZaIzmenu = function(naseljenoMesto) {

		if ($scope.idNaseljenogMestaZaIzmenu == naseljenoMesto.id) {
			$scope.idNaseljenogMestaZaIzmenu = -1;
			vm.nazivIzmena = null;
		} else {
			$scope.idNaseljenogMestaZaIzmenu = naseljenoMesto.id;
			vm.nazivNaseljenogMestaIzmena = naseljenoMesto.naziv;
			vm.pttOznakaNaseljenogMestaIzmena = naseljenoMesto.pttOznaka;
			if (naseljenoMesto.drzava != null) {
				for (var i = 0; i < $scope.drzave.length; i++) {
					if ($scope.drzave[i].id == naseljenoMesto.drzava.id) {
						vm.drzavaNaseljenogMestaIzmena = $scope.drzave[i];
					}
				}
			} else {
				vm.drzavaNaseljenogMestaIzmena = null;
			}
		}
	}

	// PRETRAGA
	$scope.pretraziNaseljenaMesta = function() {
		if ($scope.naseljenaMestaSearch == null || $scope.naseljenaMestaSearch == "") {
			naseljenoMestoServis.izlistajNaseljenaMesta().success(function(data) {
				$scope.naseljenaMesta = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje naseljenih mesta!");
			});
		} else {
			naseljenoMestoServis.pretraziNaseljenaMesta($scope.naseljenaMestaSearch).success(function(data) {
				$scope.naseljenaMesta = data;
				$location.path('/naseljenoMesto');
				$scope.idNaseljenogMestaZaIzmenu = -1;
				$scope.setTab(0);
			}).error(function(data) {
				alert("Problem prilikom pretrage");
			});
		}
	}
	
	$scope.odaberiDrzavu = function (drzava){
		if (drzava != null) {
			for (var i = 0; i < $scope.drzave.length; i++) {
				if ($scope.drzave[i].id == drzava.id) {
					$scope.drzavaNaseljenogMesta = $scope.drzave[i];
				}
			}
		} else {
			vm.drzavaNaseljenogMesta = null;
		}
		$scope.prikaziDrzaveBool = false;
		
	}
	
	$scope.odaberiDrzavuIzmena = function (drzava){
		$scope.prikaziDrzaveIzmenaBool = false;
		if (drzava != null) {
			for (var i = 0; i < $scope.drzave.length; i++) {
				if ($scope.drzave[i].id == drzava.id) {
					vm.drzavaNaseljenogMestaIzmena = $scope.drzave[i];
				}
			}
		} else {
			vm.drzavaNaseljenogMestaIzmena = null;
		}
		$scope.prikaziDrzaveIzmenaBool = false;
	}
	
	
	
	$scope.skloni =function (){
		$scope.prikaziDrzaveBool = false;
	}	

	$scope.skloniIzmena =function (){
		$scope.prikaziDrzaveIzmenaBool = false;
	}	
	
	$scope.daLiDaPrikazeDrzaveIzmenaZoom = function(){
		return $scope.prikaziDrzaveIzmenaBool
	}
	
	$scope.prikaziDrzaveIzmena = function(){
		$scope.prikaziDrzaveIzmenaBool = true;
	}
	
	
});