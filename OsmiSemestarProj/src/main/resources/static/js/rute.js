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
    .when("/pravnoLice", {
        templateUrl : "html/pravnoLice.html"
    })
    .when("/fizickoLice", {
        templateUrl : "html/fizickoLice.html"
    })
    .when("/delatnost", {
        templateUrl : "html/delatnost.html"
    })
    .when("/valuta", {
        templateUrl : "html/valuta.html"
    })
    .when("/racun", {
        templateUrl : "html/racun.html"
    })
    .when("/promenaLozinke", {
        templateUrl : "html/promenaLozinke.html"
    }).when("/medjubankarski", {
        templateUrl : "html/medjubankarski.html"
    });

    $locationProvider.html5Mode(true);
});