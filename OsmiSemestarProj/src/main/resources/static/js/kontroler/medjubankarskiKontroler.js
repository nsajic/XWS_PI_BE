var medjubankarskiKontroler = angular.module('xws_pi_bezb.medjubankarskiKontroler', []);

medjubankarskiKontroler.controller('medjubankarskiKontroler',function($scope, klijentServis, $window, $location, medjubankarskiServis) {
	
	$scope.medjubankarskiBanke = [];
	
	var controller = this;
	$scope.setTab = function(newTab) {
		$scope.tab = newTab;
	};
	
	$scope.isSet = function(tabNum) {
		return $scope.tab === tabNum;
	};

	//INIT START
	$scope.setTab(0);

	
	medjubankarskiServis.izlistajMedjubankarskePrenose().success(function(data) {
		$scope.medjubankarskiBanke = data;
	}).error(function(data) {
		alert("Nemoguce ucitati privilegije.");
	});
	
});