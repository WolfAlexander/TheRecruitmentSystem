angular.module("application", ['ngRoute', 'ngMessages', 'pascalprecht.translate']).config(function ($routeProvider, $translateProvider) {
    $routeProvider.when('/', {
        templateUrl: 'form.html',
        controller: 'registration',
        controllerAs: 'registration'
    });

    $translateProvider.useUrlLoader('/messageBundle');
    $translateProvider.useStorage('UrlLanguageStorage');
    $translateProvider.preferredLanguage('en');
    $translateProvider.fallbackLanguage('en');

}).controller('navigation', function ($scope, $translate, $location) {
    $scope.changeLanguage = function (lang) {
        $translate.use(lang);
        $location.search('lang', lang);
    }
}).controller('registration', function ($http) {
    var self = this;

    self.submitForm = function (registrationForm) {
        $http({
            method: 'POST',
            url: 'http://localhost:8080/api/test/registration',
            data: $.param({firstname: self.firstname, lastname: self.lastname}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function (response) {
            handlingRegistrationResponse(registrationForm, response);
        });
    }
}).factory('UrlLanguageStorage', ['$location', function($location) {
    return {
        put: function (name, value) {},
        get: function (name) {
            return $location.search()['lang']
        }
    };
}]);

/**
 * Taking action depending on status of registration response
 * @param registrationForm
 * @param response
 */
function handlingRegistrationResponse(registrationForm, response) {
    if(response.data.status == "BAD_REQUEST")
        handleFailedRegistration(registrationForm, response);
    else if(response.data.status == "CREATED")
        handleSuccessfulRegistration();
}

/**
 * Handling registration failure
 * @param registrationForm
 * @param response
 */
function handleFailedRegistration(registrationForm, response) {
    response.data.errorList.forEach(function (entry) {
        showErrorMessageForGiveField($scope, entry.field);
    });
}

/**
 * Showing error message for given field
 * @param registrationForm
 * @param field
 */
function showErrorMessageForGiveField(registrationForm, field) {
    registrationForm[field].$error.minlength = true;
}

function handleSuccessfulRegistration() {
    alert("User registered");
}