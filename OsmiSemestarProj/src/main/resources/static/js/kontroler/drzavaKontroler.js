var drzavaKontroler = angular.module('xws_pi_bezb.drzavaKontroler', []);

drzavaKontroler.controller('drzavaCtrl', function($scope, drzavaServis, $window, $location) {

	var controller = this;
	$scope.setTab = function(newTab) {
		$scope.tab = newTab;
	};
	
	//INIT START
	$scope.setTab(0);

	$scope.idDrzaveZaIzmenu = -1;
	
	drzavaServis.izlistajDrzave().success(function(data) {
		$scope.drzave = data;
	}).error(function(data) {
		alert("Neuspesno izlistavanje drzava!");
	});
	//INIT END
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};

	$scope.obrisiDrzavu = function(id) {
		drzavaServis.izbrisiDrzavu(id).success(function(data) {
			$scope.drzave = data;
			$location.path('/drzava');
		}).error(function(data) {
			alert("Nemoguce obrisati drzavu");
		});
	}

	$scope.isVisible = function() {
		return $scope.prikaz;
	}

	$scope.dodajDrzavu = function() {
		var drzava = {
			nazivDrzave : $scope.nazivDrzave
		}
		drzavaServis.dodajDrzavu(drzava).success(function(data) {
			$scope.drzave = data;
			$location.path('/drzava');
			$scope.nazivDrzave = null;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati drzavu");
		});
	}
	
	$scope.izmeniDrzavu = function(id) {
		var drzava = {
			id : id,
			nazivDrzave : controller.nazivDrzaveIzmena
		}
		//TODO Ispraviti jer uzima samo setovano na pocetku 
		console.log(drzava);
		drzavaServis.izmeniDrzavu(drzava).success(function(data) {
			$scope.drzave = data;
			$location.path('/drzava');
			$scope.idDrzaveZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti drzavu");
		});
	}

	$scope.setDrzavaZaIzmenu = function(drzava) {
		if ($scope.idDrzaveZaIzmenu == drzava.id){			
			$scope.idDrzaveZaIzmenu = -1;
			controller.nazivDrzaveIzmena = null;
		}
		else {
			$scope.idDrzaveZaIzmenu = drzava.id;
			controller.nazivDrzaveIzmena = drzava.nazivDrzave;
		}

	}
	

});