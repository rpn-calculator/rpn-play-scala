angular.module('components', []);

angular.module('lookUp', ['base64']);

angular.module('rpn-calc', [
    'ngRoute',
    'base64',
    'components',
    'lookUp'
])

.config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'app/js/lookUp/lookUp.html',
            controller: 'LookUpController'
        })
        .otherwise({
            redirectTo: '/'
        });

}]);
