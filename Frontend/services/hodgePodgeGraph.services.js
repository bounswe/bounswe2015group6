function hodgePodgeGraphServices($http){
    {
        var urlBase = 'api';
        var hodgePodgeServices = {};
        hodgePodgeServices.getGraph = function () {
            return $http({
                method: 'GET',
                url: urlBase + "/getGraph",
            })
        }

        hodgePodgeServices.addBeer = function (data) {
            return $http({
                method: 'POST',
                url: urlBase + "/addBeer",
                data: data,
                headers: {'Content-Type': 'application/json'}
            })
        }

        hodgePodgeServices.addStyle = function (data) {
            return $http({
                method: 'POST',
                url: urlBase + "/addStyle",
                data: data,
                headers: {'Content-Type': 'application/json'}
            })
        }

        hodgePodgeServices.updatePositions = function (data) {
            return $http({
                method: 'POST',
                url: urlBase + "/updatePositions",
                data: data,
                headers: {'Content-Type': 'application/json'}
            })
        }

        return hodgePodgeServices;
    }
}