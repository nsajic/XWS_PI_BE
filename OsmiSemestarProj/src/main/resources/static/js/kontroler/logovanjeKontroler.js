var logovanjeKontroler = angular.module('xws_pi_bezb.logovanjeKontroler', []);

logovanjeKontroler.controller('logovanjeKontroler', function($window,
		$location, $scope, logovanjeServis, klijentServis) {

	klijentServis.koJeNaSesiji().success(function(data) {
		if (data.message == "NekoNaSesiji") {
			$location.path('/welcome');
		} else {

		}
	});

	$scope.changeRoute = function(url, forceReload) {
		$scope = $scope || angular.element(document).scope();
		if (forceReload || $scope.$$phase) { // that's right TWO dollar
												// signs: $$phase
			window.location = url;
		} else {
			$location.path(url);
			$scope.$apply();
		}
	};

	$scope.logovanje = function() {
		var korisnik = {
			email : $scope.email,
			sifra : $scope.sifra
		}

		var str = JSON.stringify(korisnik);

		logovanjeServis.ulogujKorisnika(str).success(function(data) {
			if (data.message != "NePostoji") {
				console.log(data);
				$location.path('/welcome');

			}
		});

	}

})