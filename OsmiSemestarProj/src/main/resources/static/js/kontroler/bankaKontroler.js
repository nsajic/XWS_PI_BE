var bankaKontroler = angular.module('xws_pi_bezb.bankaKontroler', []);

bankaKontroler.controller('bankaCtrl', function($scope, bankaServis, $window, $location) {

	var controller = this;
	$scope.setTab = function(newTab) {
		$scope.tab = newTab;
	};

	//INIT START
	$scope.setTab(0);

	$scope.idBankeZaIzmenu = -1;
	$scope.idBankeZaNext = -1;
	
	bankaServis.izlistajBanke().success(function(data) {
		$scope.banke = data;
	}).error(function(data) {
		alert("Neuspesno izlistavanje banaka!");
	});
	//INIT END
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiBanku = function(id) {
		bankaServis.izbrisiBanku(id).success(function(data) {
			$scope.banke = data;
			$location.path('/banka');
		}).error(function(data) {
			alert("Nemoguce obrisati banku");
		});
	}
	
	$scope.isVisible = function() {
		return $scope.prikaz;
	}
	
	$scope.dodajBanku = function() {
		var banka = {
			nazivBanke : $scope.nazivBanke,
			sifraBanke : $scope.sifraBanke,
			swiftKod : $scope.swiftKodBanke,
			obracunskiRacun : $scope.obracunskiRacunBanke
		}
		bankaServis.dodajBanku(banka).success(function(data) {
			$scope.banke = data;
			$location.path('/banka');
			$scope.nazivBanke = null;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce dodati banku");
		});
	}
	
	$scope.izmeniBanku = function(id) {
		var banka = {
			id : id,
			nazivBanke : controller.nazivBankeIzmena,
			sifraBanke : controller.sifraBankeIzmena,
			swiftKod : controller.swiftKodIzmena,
			obracunskiRacun : controller.obracunskiRacunIzmena
		}
		bankaServis.izmeniBanku(banka).success(function(data) {
			$scope.banke = data;
			$location.path('/banka');
			$scope.idBankeZaIzmenu = -1;
			$scope.setTab(0);
		}).error(function(data) {
			alert("Nemoguce izmeniti banke");
		});
	}
	
	$scope.setBankaZaIzmenu = function(banka) {
		if ($scope.idBankeZaIzmenu == banka.id){			
			$scope.idBankeZaIzmenu = -1;
			controller.nazivBankeIzmena = null;
			controller.sifraBankeIzmena = null;
			controller.swiftKodIzmena = null;
			controller.obracunskiRacunIzmena = null;
		}
		else {
			$scope.idBankeZaIzmenu = banka.id;
			controller.nazivBankeIzmena = banka.nazivBanke;
			controller.sifraBankeIzmena = banka.sifraBanke;
			controller.swiftKodIzmena = banka.swiftKod;
			controller.obracunskiRacunIzmena = banka.obracunskiRacun;
		}
		$scope.idBankeZaNext = -1;
	}
	/*
	$scope.setBankaZaNext = function(banka) {
		if ($scope.idBankeZaNext == banka.id){			
			$scope.idBankeZaNext = -1;
			//$scope.naseljenaMestaZaNext = null;
		}
		else 
		{
			$scope.idBankeZaNext = banka.id;
		
			drzavaServis.izlistajNaseljenaNext(banka).success(function(data) {
				$scope.naseljenaMestaZaNext = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje naseljenih mesta!");
			});
		}
		
		$scope.idBankeZaIzmenu = -1;
		controller.nazivBankeIzmena = null;
		controller.sifraBankeIzmena = null;
		controller.swiftKodIzmena = null;
		controller.obracunskiRacunIzmena = null;

	}*/

});