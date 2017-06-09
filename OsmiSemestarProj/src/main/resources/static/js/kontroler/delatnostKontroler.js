var delatnostKontroler = angular.module('xws_pi_bezb.delatnostKontroler', []);

delatnostKontroler.controller('delatnostCtrl', function($scope, delatnostServis, $window, $location) {

	$scope.resetujPoljaPretraga = function(){
		$scope.nazivDelatnostiPretraga = null;

	}

	$scope.resetujPoljaDodavanje = function() {
		$scope.nazivDelatnosti = null;
	}
	
	$scope.resetujPoljaIzmena = function() {
		controller.nazivDelatnostiIzmena = null;
	}
	
	var controller = this;
	$scope.setTab = function(newTab) {
		if(newTab == 0){
			$scope.resetujPoljaPretraga();
			delatnostServis.izlistajDelatnosti().success(function(data) {
				$scope.delatnosti = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje delatnosti!");
			});
		}
		$scope.tab = newTab;
	};

	//INIT START
	$scope.setTab(0);

	$scope.idDelatnostiZaIzmenu = -1;
	$scope.idDelatnostiZaNext = -1;
	
	//INIT END

	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};
	
	$scope.obrisiDelatnost = function(id) {
		delatnostServis.izbrisiDelatnost(id).success(function(data) {
			$scope.delatnosti = data;
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
			$scope.delatnosti = data;
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
			$scope.delatnosti = data;
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
		$scope.idDelatnostiZaNext = -1;
	}
	
	
	
	$scope.setDelatnostZaNext = function(delatnost) {
		if ($scope.idDelatnostiZaNext == delatnost.id){			
			$scope.idDelatnostiZaNext = -1;
		}
		else 
		{
			$scope.idDelatnostiZaNext = delatnost.id;
			
			
			/*drzavaServis.izlistajNaseljenaNext(delatnost).success(function(data) {
				$scope.naseljenaMestaZaNext = data;
			}).error(function(data) {
				alert("Neuspesno izlistavanje naseljenih mesta!");
			});*/
		}
		
		$scope.idDelatnostiZaIzmenu = -1;
		controller.nazivDelatnostiIzmena = null;

	}
	
	$scope.pretragaPoPoljima = function() {
		var delatnost = {
			nazivDelatnosti : $scope.nazivDelatnostiPretraga
		}
		console.log(delatnost);
		delatnostServis.pretraziDelatnosti(delatnost).success(function(data) {
			$scope.delatnosti = data;
			$scope.idDelatnostiZaIzmenu = -1;
		}).error(function(data) {
			alert("Greska prilikom pretrage");
		});
	}
	
	
	$scope.ponistiPretragu = function(){
		delatnostServis.izlistajDelatnosti().success(function(data) {
			$scope.delatnosti = data;
		}).error(function(data) {
			alert("Neuspesno izlistavanje delatnosti!");
		});
	}
});