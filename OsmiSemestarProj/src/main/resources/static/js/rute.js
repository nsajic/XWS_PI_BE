var app = angular.module("xws_pi_bezb.rute", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/welcomePage.html"
    })
    .when("/drzava", {
        templateUrl : "html/drzava.html"
    })
    .when("/naseljenoMesto", {
        templateUrl : "html/naseljenoMesto.html"
    });

    $locationProvider.html5Mode(true);
});