angular.module('main', [])
  .controller('MainCtrl', function($scope) {
    var jsonAsString = '[{"id":1,"name":"A","nodes":[{"id":2,"name":"AA","nodes":[{"id":3,"name":"AA1","nodes":[]},{"id":4,"name":"AA2","nodes":[]}]},{"id":5,"name":"AB","nodes":[]}]},{"id":6,"name":"B","nodes":[]},{"id":7,"name":"C","nodes":[{"id":8,"name":"CA","nodes":[{"id":9,"name":"CA1","nodes":[]},{"id":10,"name":"CA2","nodes":[]}]}]},{"id":11,"name":"D","nodes":[{"id":12,"name":"DA","nodes":[]}]}]';
    $scope.nodes = JSON.parse(jsonAsString);
    $scope.toggle = function(node) {
           node.show = !node.show;
    }
  });