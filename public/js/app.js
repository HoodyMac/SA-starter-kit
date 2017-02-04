var sa = angular.module('saApp', []);

sa.config(function ($httpProvider) {
    $httpProvider.interceptors.push('httpRequestInterceptor');
});

sa.factory('httpRequestInterceptor', function () {
    return {
        request: function (config) {
            var token = localStorage.getItem('jwt');
            if(token === null) {
                token = '';
            }
            config.headers['x-auth-token'] = token;
            return config;
        }
    };
});

sa.controller('MainCtrl', function ($scope, $rootScope, $http) {
    $scope.userForm = {};
    $scope.user = {};

    $scope.getUser = function () {
        $http.get('api/users/me').then(function (response) {
            $scope.user = response.data;
            $rootScope.isSignedIn = true;
        }, function(response) {
            localStorage.removeItem('jwt');
            $rootScope.isSignedIn = false;
        })
    };

    $scope.signUp = function () {
        $http.post('api/users', $scope.userForm).then(function (response) {
            console.log('signed up');
        });
    };

    $scope.signIn = function () {
        $http.post('api/login', $scope.userForm).then(function (response) {
            localStorage.setItem('jwt', response.headers()['x-auth-token']);
            $scope.getUser();
            console.log('signed in');
        })
    };

    $scope.signOut = function () {
        $rootScope.isSignedIn = false;
        localStorage.removeItem('jwt');
        console.log('signed out');
    };

    $scope.getUser();
});