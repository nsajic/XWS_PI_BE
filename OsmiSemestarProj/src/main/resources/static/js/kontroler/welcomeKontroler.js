var welcomeKontroler = angular.module('xws_pi_bezb.welcomeKontroler', []);

welcomeKontroler.controller('welcomeCtrl', function($scope, $location, $window, welcomeServis) {

	$scope.logOut = function(){

		welcomeServis.logOut().success(function(data) {
			if(data.message == "Izlogovan"){
				$window.location.href = '/';
			}else{
			}
		});
	}

});