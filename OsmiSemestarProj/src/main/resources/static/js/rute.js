var app = angular.module("xws_pi_bezb.rute", ["ngRoute"]);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "html/welcomePage.html"
    })
    .when("/banka", {
        templateUrl : "html/banka.html"
    })
    .when("/pravnoLice", {
        templateUrl : "html/pravnoLice.html"
    })
    .when("/delatnost", {
        templateUrl : "html/delatnost.html"
    })
    .when("/naseljenoMesto", {
        templateUrl : "html/naseljenoMesto.html"
    });

    $locationProvider.html5Mode(true);
});