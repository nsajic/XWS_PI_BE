var valutaKontroler = angular.module('xws_pi_bezb.valutaKontroler', []);

valutaKontroler.controller('valutaCtrl', function($scope, valutaServis, $window, $location, klijentServis) {


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
	
	$scope.resetujPoljaPretraga = function(){
		$scope.nazivValutePretraga = null;
		$scope.sifraValutePretraga = null;

	}

	$scope.resetujPoljaDodavanje = function() {
		$scope.nazivValute = null;
		$scope.sifraValute = null;
	}
	
	$scope.resetujPoljaIzmena = function() {
		controller.nazivValuteIzmena = null;
		controller.sifraValuteIzmena = null;
	}
	
	$scope.izlistajValute = function (){
		valutaServis.izlistajValute().success(function(data) {
			$scope.valute = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje valuta!");
		});
	}
	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretraga();
			$scope.izlistajValute();
		}
		$scope.tab = newTab;
	};

	//INIT START
	$scope.setTab(0);

	$scope.idValuteZaIzmenu = -1;
	
	//INIT END

	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiValutu = function(id) {
		valutaServis.izbrisiValutu(id).success(function(data) {
			$scope.izlistajValute();
			$location.path('/valuta');
		}).error(function(data) {
			alert("Nemoguce obrisati valutu");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajValutu = function() {
		var valuta = {
				nazivValute : $scope.nazivValute,
				sifraValute : $scope.sifraValute
		}
		
		valutaServis.dodajValutu(valuta).success(function(data) {
			$scope.izlistajValute();
			$location.path('/valuta');
			$scope.resetujPoljaDodavanje();
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati valutu");
		});
	}
	
	$scope.izmeniValutu = function(id) {
		var valuta = {
			id : id,
			nazivValute : controller.nazivValuteIzmena,
			sifraValute : controller.sifraValuteIzmena
		}
		valutaServis.izmeniValutu(valuta).success(function(data) {
			$scope.izlistajValute();
			$location.path('/valuta');
			$scope.idValuteZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti valutu");
		});
	}
	
	$scope.setValutuZaIzmenu = function(valuta) {
		if ($scope.idValuteZaIzmenu == valuta.id){			
			$scope.idValuteZaIzmenu = -1;
			$scope.resetujPoljaIzmena();
		}
		else {
			$scope.idValuteZaIzmenu = valuta.id;
			controller.nazivValuteIzmena = valuta.nazivValute;
			controller.sifraValuteIzmena = valuta.sifraValute;
		}
	}

	$scope.pretragaPoPoljima = function() {
		var valuta = {
				nazivValute : $scope.nazivValutePretraga,
				sifraValute : $scope.sifraValutePretraga
		}
		valutaServis.pretraziValute(valuta).success(function(data) {
			$scope.valute = data;
			$scope.idValuteZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretragu = function(){
		$scope.izlistajValute();
	}
});