var promenaLozinkeServis = angular.module('xws_pi_bezb.promenaLozinkeServis', []);

promenaLozinkeServis.factory('promenaLozinkeServis', function($http) {
	

	var temp = {};

	temp.promeniLozinku = function(data) {
		return $http.post('/contr/resetPassword', data);
	}

	return temp;

});