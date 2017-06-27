var medjubankarskiKontroler = angular.module('xws_pi_bezb.medjubankarskiKontroler', []);






medjubankarskiKontroler
.controller(
		'medjubankarskiKontroler',
		function($scope, klijentServis, $window, $location,
				medjubankarskiServis) {
			
			medjubankarskiServis.izlistaj().success(function(data) {
				$scope.privileges = data;
			}).error(function(data) {
				alert("Korisnik nema nikakvih privilegija.");
			});
			
			
		});