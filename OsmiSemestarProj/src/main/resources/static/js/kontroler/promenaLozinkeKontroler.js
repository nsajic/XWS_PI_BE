var promenaLozinkeKontroler = angular.module('xws_pi_bezb.promenaLozinkeKontroler', []);

promenaLozinkeKontroler.controller('promenaLozinkeKontroler', function($scope, promenaLozinkeServis, $window, $location) {
	
	$scope.promeniLozinku = function() {
		var banka = {
			staraLozinka : $scope.staraLozinka,
			novaLozinka : $scope.novaLozinka,
			novaLozinka2 : $scope.novaLozinka2
		}
		
		promenaLozinkeServis.promeniLozinku(banka).success(function(data) {
			if(data.message == "Promenjeno"){
				alert("Lozinka uspesno promenjena.");
			}else if(data.message == "RazliciteLozinke"){
				alert("Niste uneli iste lozinke.")
			}else{
				alert("Lozinka nije promenjena.");
			}
			
			$location.path('/welcome');
		}).error(function(data) {
			alert("Nemoguce promeniti lozinku!");
		});
	}
	
});