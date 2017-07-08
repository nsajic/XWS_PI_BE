var izvodiKontroler = angular.module('xws_pi_bezb.izvodiKontroler', []);

izvodiKontroler.controller('izvodiKontroler', function($scope, $window,
		$location, izvodiServis) {

	$scope.zatraziIzvode = function() {

		var izvodiVM = {
			iDrac : $scope.iDrac,
			datumOd : $scope.datumOd,
			datumDo : $scope.datumDo
		}

		izvodiServis.zatraziIzvode(izvodiVM).success(function(data) {
			alert(data.message);
		}).error(function(data) {
			alert("Nemoguce dodati racun");
		});

		
	}
	
	$scope.izvodiUXML = function() {
		var izvodiVM = {
			iDrac : $scope.iDrac,
			datumOd : $scope.datumOd,
			datumDo : $scope.datumDo
		}
		
		izvodiServis.izvodiUXML(izvodiVM).success(function(data) {
			alert(data.message);
		}).error(function(data) {
			alert("Nemoguce izlistati izvode");
		});
	}

});