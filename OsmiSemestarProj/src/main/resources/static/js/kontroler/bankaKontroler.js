var bankaKontroler = angular.module('xws_pi_bezb.bankaKontroler', []);

bankaKontroler.controller('bankaCtrl', function($scope, bankaServis, $window, $location, klijentServis) {
	
	$scope.resetujPoljaPretraga = function(){
		$scope.nazivBankePretraga = null;
		$scope.sifraBankePretraga = null;
		$scope.swiftKodPretraga = null;
		$scope.obracunskiRacunPretraga = null;
	}

	$scope.resetujPoljaDodavanje = function() {
		$scope.nazivBanke = null;
		$scope.sifraBanke = null;
		$scope.swiftKodBanke = null;
		$scope.obracunskiRacunBanke = null;
	}
	
	$scope.resetujPoljaIzmena = function() {
		controller.nazivBankeIzmena = null;
		controller.sifraBankeIzmena = null;
		controller.swiftKodIzmena = null;
		controller.obracunskiRacunIzmena = null;
	}
	
	var controller = this;
	$scope.setTab = function(newTab) {
		klijentServis.ucitajPrivilegije().success(function(data){
			$scope.privileges = data;	
		})
		.error(function(data){
			alert("Korisnik nema nikakvih privilegija.");
		});
		if(newTab == 0){
			$scope.resetujPoljaPretraga();
			bankaServis.izlistajBanke().success(function(data) {
				$scope.banke = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje banaka!");
			});
		}
		$scope.tab = newTab;
	};

	//INIT START
	
	$scope.privileges = [];

	$scope.idBankeZaIzmenu = -1;
	$scope.idBankeZaNext = -1;
	
	//INIT END
	
	
	
	$scope.hasPrivilege = function(privilege){
		if($scope.privileges.indexOf(privilege) > -1)
			return true;
		else
			return false;
	};
	
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
			//$scope.banke = data;	
			bankaServis.izlistajBanke().success(function(data1) {
				$scope.banke = data1;
			}).error(function(data1) {
				alert("Neuspesno izlistavanje banaka!");
			});
			
			$location.path('/banka');
			$scope.resetujPoljaDodavanje();
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
			//$scope.banke = data;
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
			$scope.resetujPoljaIzmena();
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
	
	
	
	$scope.setBankaZaNext = function(banka) {
		if ($scope.idBankeZaNext == banka.id){			
			$scope.idBankeZaNext = -1;
		}
		else 
		{
			$scope.idBankeZaNext = banka.id;
			
			
			/*drzavaServis.izlistajNaseljenaNext(banka).success(function(data) {
				$scope.naseljenaMestaZaNext = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje naseljenih mesta!");
			});*/
		}
		
		$scope.idBankeZaIzmenu = -1;
		controller.nazivBankeIzmena = null;
		controller.sifraBankeIzmena = null;
		controller.swiftKodIzmena = null;
		controller.obracunskiRacunIzmena = null;

	}
	
	$scope.pretragaPoPoljima = function() {
		var banka = {
			nazivBanke : $scope.nazivBankePretraga,
			sifraBanke : $scope.sifraBankePretraga,
			swiftKod : $scope.swiftKodBankePretraga,
			obracunskiRacun : $scope.obracunskiRacunBankePretraga
		}
		bankaServis.pretraziBanke(banka).success(function(data) {
			$scope.banke = data;
			$scope.idBankeZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretragu = function(){
		bankaServis.izlistajBanke().success(function(data) {
			$scope.banke = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje banaka!");
		});
	}
	
	$scope.setTab(0);
});