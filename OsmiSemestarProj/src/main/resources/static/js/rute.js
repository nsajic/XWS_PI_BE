var app = angular.module("xws_pi_bezb.rute", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/logovanje.html"
    })
    .when("/banka", {
        templateUrl : "html/banka.html"
    })
    .when("/welcome", {
    	templateUrl : "html/welcomePage.html"
    })
    .when("/naseljenoMesto", {
        templateUrl : "html/naseljenoMesto.html"
    });

    $locationProvider.html5Mode(true);
});