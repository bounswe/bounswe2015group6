var hodgePodgeModule = angular.module('hodgePodgeGraph', ['mobile-angular-ui.gestures','mobile-angular-ui','angucomplete-alt','cgBusy','ngTouch']);

hodgePodgeModule.factory('hodgePodgeGraphServices', ['$http', hodgePodgeGraphServices]);

/***** Constants ******/
var NORMAL_LOAD_TIME_THRESHOLD = 2000;
var STYLE_CENTRALITY_1_FONT_SIZE = 30;
var STYLE_CENTRALITY_2_FONT_SIZE = 22;
var STYLE_CENTRALITY_3_FONT_SIZE = 15;
var STYLE_CENTRALITY_4_FONT_SIZE = 15;
var STYLE_CENTRALITY_5_FONT_SIZE = 15;
var STYLE_CENTRALITY_6_FONT_SIZE = 15;
var STYLE_CENTRALITY_1_NODE_SIZE = 30;
var STYLE_CENTRALITY_2_NODE_SIZE = 25;
var STYLE_CENTRALITY_3_NODE_SIZE = 20;
var STYLE_CENTRALITY_4_NODE_SIZE = 15;
var STYLE_CENTRALITY_5_NODE_SIZE = 15;
var STYLE_CENTRALITY_6_NODE_SIZE = 15;
var GROUP_COLOR_CENTRALITY = "3";

hodgePodgeModule.controller('hodgePodgeController', function ($scope,$log,$swipe,hodgePodgeGraphServices  ) {
    $scope.nodes = new vis.DataSet();
    $scope.edges = new vis.DataSet();
    $scope.topics = [];
    $scope.maxGroupNumber = 1;
    $scope.network;
    $scope.loaded = false;
    hodgePodgeGraphServices.getGraph().success( function(graph){

        convertBeersToNodes(graph.nodes);

        $scope.nodes.add(graph.nodes);
        $scope.edges.add(graph.edges);

        var data = {
            nodes : $scope.nodes,
            edges : $scope.edges
        }
        var container = document.getElementById('graphContainer');
        $scope.network = new vis.Network(container, data, options);

        $scope.network.on("click",neighbourhoodHighlight);

        $scope.network.on("stabilizationProgress", function(params) {
            doStabilizationStep(params);
        });

        $scope.network.once("stabilizationIterationsDone", function() {

            // var positionsSize = 0, key;
            // for (key in graph.positions) {
            //     if (graph.positions.hasOwnProperty(key)) positionsSize++;
            // }

            // if (graph.styles.length > positionsSize){
            //     // new positions must be calculated and send to server
            //     $scope.network.storePositions();
            //     updateRemotePositions(data.nodes._data,hodgePodgeGraphServices);
            // }
            doStabilizationDone();
        });
    });

    var options = {
            autoResize: true,
            height: '100%',
            nodes: {
                shape: 'dot'
            },
            interaction:{
                hover: true,
                navigationButtons: true
            },
            layout:{
                randomSeed:2
            }
        };

    updateRemotePositions = function(positions,hodgePodgeGraphServices){
        var positionsOnly = {};
        angular.forEach(positions,function(axis,beerStyle){
            positionsOnly[beerStyle] = {};
            positionsOnly[beerStyle].x = axis.x;
            positionsOnly[beerStyle].y = axis.y;
        })
        hodgePodgeGraphServices.updatePositions(positionsOnly);
    }

    doStabilizationStep = function(params) {
        var maxWidth = $('#border').width();
        var minWidth = 20;
        var widthFactor = params.iterations/params.total;
        var width = Math.max(minWidth,maxWidth * widthFactor);

        document.getElementById('bar').style.width = width + 'px';
        document.getElementById('text').innerHTML = Math.round(widthFactor*100) + '%';
    }

    doStabilizationDone = function() {
        document.getElementById('text').innerHTML = '100%';
        document.getElementById('bar').style.width = $('#border').width();
        document.getElementById('loadingBar').style.opacity = 0;
        $scope.loaded = true;
        $scope.$apply();
        // really clean the dom element
        setTimeout(function () {
            document.getElementById('loadingBar').style.display = 'none'
        }, 500);
    }

    neighbourhoodHighlight = function(params) {
        // get a JSON object
        var allNodes = $scope.nodes.get({returnType:"Object"});

        // if something is selected:
        if (params.nodes.length > 0) {
            highlightActive = true;
            var i,j;
            var selectedNode = params.nodes[0];
            var degrees = 2;

            // mark all nodes as hard to read.
            for (var nodeId in allNodes) {
                allNodes[nodeId].color = 'rgba(200,200,200,0.5)';
                if (allNodes[nodeId].hiddenLabel === undefined) {
                    allNodes[nodeId].hiddenLabel = allNodes[nodeId].label;
                    allNodes[nodeId].label = undefined;
                }
            }
            var connectedNodes = $scope.network.getConnectedNodes(selectedNode);
            var allConnectedNodes = [];

            // get the second degree nodes
            for (i = 1; i < degrees; i++) {
                for (j = 0; j < connectedNodes.length; j++) {
                    allConnectedNodes = allConnectedNodes.concat($scope.network.getConnectedNodes(connectedNodes[j]));
                }
            }

            // all second degree nodes get a different color and their label back
            for (i = 0; i < allConnectedNodes.length; i++) {
                allNodes[allConnectedNodes[i]].color = 'rgba(150,150,150,0.75)';
                if (allNodes[allConnectedNodes[i]].hiddenLabel !== undefined) {
                    allNodes[allConnectedNodes[i]].label = allNodes[allConnectedNodes[i]].hiddenLabel;
                    allNodes[allConnectedNodes[i]].hiddenLabel = undefined;
                }
            }

            // all first degree nodes get their own color and their label back
            for (i = 0; i < connectedNodes.length; i++) {
                allNodes[connectedNodes[i]].color = undefined;
                if (allNodes[connectedNodes[i]].hiddenLabel !== undefined) {
                    allNodes[connectedNodes[i]].label = allNodes[connectedNodes[i]].hiddenLabel;
                    allNodes[connectedNodes[i]].hiddenLabel = undefined;
                }
            }

            // the main node gets its own color and its label back.
            allNodes[selectedNode].color = undefined;
            if (allNodes[selectedNode].hiddenLabel !== undefined) {
                allNodes[selectedNode].label = allNodes[selectedNode].hiddenLabel;
                allNodes[selectedNode].hiddenLabel = undefined;
            }
            handleDisplayStyleInfo(params.nodes[0],connectedNodes);
            $scope.network.fit({
                nodes: connectedNodes
              });
        }
        else {
            // reset all nodes
            for (var nodeId in allNodes) {
                allNodes[nodeId].color = undefined;
                if (allNodes[nodeId].hiddenLabel !== undefined) {
                    allNodes[nodeId].label = allNodes[nodeId].hiddenLabel;
                    allNodes[nodeId].hiddenLabel = undefined;
                }
            }
            highlightActive = false
            handleNoDisplayStyleInfo();
        }

        // transform the object into an array
        var updateArray = [];
        for (nodeId in allNodes) {
            if (allNodes.hasOwnProperty(nodeId)) {
                updateArray.push(allNodes[nodeId]);
            }
        }

        removeAxisBeforeUpdate(updateArray);
        $scope.nodes.update(updateArray);
    }

    removeAxisBeforeUpdate = function(styleArray){
        angular.forEach(styleArray,function(tempStyle){
            delete tempStyle.x;
            delete tempStyle.y;
        })
    }

    handleDisplayStyleInfo = function(style,allConnectedNodes) {

        var allNodes = $scope.nodes.get({returnType:"Object"});

        var StyleObject = null;
        angular.forEach($scope.topics,function(tempStyle){
            if (style == tempStyle.id){
                StyleObject = tempStyle;
            }
        })
        $scope.sideBarContent = StyleObject;
        $scope.sideBarConnectionsArray = [];
        for (i = 0; i < allConnectedNodes.length; i++) {
            $scope.sideBarConnectionsArray.push({name:allNodes[allConnectedNodes[i]].label,id:allConnectedNodes[i]});
            }
        $scope.sideBarContentArray = [];
        // $scope.sideBarContentArray.push({name:'Aroma',value:StyleObject.label});
        // $scope.sideBarContentArray.push({name:'Appearance',value:StyleObject.Appearance});
        // $scope.sideBarContentArray.push({name:'Comments',value:StyleObject.Comments});
        // $scope.sideBarContentArray.push({name:'Flavor',value:StyleObject.Flavor});
        // $scope.sideBarContentArray.push({name:'History',value:StyleObject.History});
        // $scope.sideBarContentArray.push({name:'Ingredients',value:StyleObject.Ingredients});
        // $scope.sideBarContentArray.push({name:'Mouthfeel',value:StyleObject.Mouthfeel});
        setTimeout(function () {
            $scope.Ui.turnOn("uiSidebarLeft");
            if(!$scope.$$phase) {
                $scope.$apply();
            }
        }, 10);
    }

    handleNoDisplayStyleInfo = function(style){
        setTimeout(function () {
            $scope.Ui.turnOff("uiSidebarLeft");
            $scope.Ui.turnOff("uiSidebarRight");
            if(!$scope.$$phase) {
                $scope.$apply();
            }
        }, 10)
    }

    convertBeersToNodes = function(graph){
        angular.forEach(graph,function(beerStyle){
            $scope.topics.push(beerStyle);            
        })
    }

    getGroupByStyle = function(style){
        var returnGroup = null;
        angular.forEach($scope.nodes._data,function(node){
            if (node.id == style) {
                returnGroup = angular.copy(node.group);
                return;
            }
        })
        return returnGroup;
    }

    getNodeTextSize = function(beerStyle) {
        switch (beerStyle.centrality){
            case "1": {
                return STYLE_CENTRALITY_1_FONT_SIZE;
            }
            case "2": {
                return STYLE_CENTRALITY_2_FONT_SIZE;
            }
            case "3": {
                return STYLE_CENTRALITY_3_FONT_SIZE;
            }
            case "4": {
                return STYLE_CENTRALITY_4_FONT_SIZE;
            }
            case "5": {
                return STYLE_CENTRALITY_5_FONT_SIZE;
            }
            case "6": {
                return STYLE_CENTRALITY_6_FONT_SIZE;
            }
        }
    }

    getNodeSize = function(beerStyle) {
        switch (beerStyle.centrality){
            case "1": {
                return STYLE_CENTRALITY_1_NODE_SIZE;
            }
            case "2": {
                return STYLE_CENTRALITY_2_NODE_SIZE;
            }
            case "3": {
                return STYLE_CENTRALITY_3_NODE_SIZE;
            }
            case "4": {
                return STYLE_CENTRALITY_4_NODE_SIZE;
            }
            case "5": {
                return STYLE_CENTRALITY_5_NODE_SIZE;
            }
            case "6": {
                return STYLE_CENTRALITY_6_NODE_SIZE;
            }
        }
    }

    $scope.focusToNode = function(nodeId) {
        // $scope.network.focus(nodeId);
        neighbourhoodHighlight({nodes:[nodeId]});
    }

    $scope.submitAddBeer = function(formData) {
        handleAddBeer(formData, hodgePodgeGraphServices);
    }

    $scope.submitAddStyle = function(formData) {
        handleAddStyle(formData, hodgePodgeGraphServices);
    }

    $scope.$watch('searchText', function() {
        if ($scope.searchText){
            var nodesToFilter = $scope.nodes.get({
                fields: ['id'],
                filter: function (item) {
                    return (item.id.toUpperCase().indexOf($scope.searchText.toUpperCase())> -1);
                }})

            if (nodesToFilter.length >= 1){
                nodesToFilter = nodesToFilter.splice(0,1)[0].id;
                neighbourhoodHighlight({nodes:[nodesToFilter]});
            }else {
                neighbourhoodHighlight({nodes:[]});
            }
        } else {
            neighbourhoodHighlight({nodes:[]});
        }
    }, true);

    $scope.swipeCloseLeft = function(){
        $scope.Ui.turnOff("uiSidebarLeft");
    }

    $scope.swipeCloseRight = function(){
        $scope.Ui.turnOff("uiSidebarRight");
    }

    var handleAddBeer = function(formData,hodgePodgeGraphServices) {
        var newBeer = {email:formData.email,name:formData.name,style:formData.style.title};
        $scope.promise = hodgePodgeGraphServices.addBeer(newBeer);
        $scope.promise.then(
            function(){
                //addBeerToTypes(scope.styles,newBeer);
                $scope.Ui.turnOff("addBeerModal");
                $scope.Ui.turnOn("confirmationModal");
            },
            function(errorPayload) {
                formData.error = errorPayload.data;
            });
    }

    var handleAddStyle = function(formData,beerGraphServices) {
        var newStyle = {email:formData.email,styleName:formData.name,parentStyle:formData.style.title,
            ABV:formData.ABV,
            Appearance:formData.Appearance,
            Comments:formData.Comments,
            Aroma:formData.Aroma,
            FG:formData.FG,
            History:formData.History,
            Flavor:formData.Flavor,
            IBUs:formData.IBUs,
            Ingredients:formData.Ingredients,
            Mouthfeel:formData.Mouthfeel,
            OG:formData.OG,
            SRM:formData.SRM
        };
        $scope.promise = hodgePodgeGraphServices.addStyle(newStyle);
        $scope.promise.then(
            function(){
                //addBeerToTypes(scope.styles,newBeer);
                $scope.Ui.turnOff("addStyleModal");
                $scope.Ui.turnOn("confirmationModal");
            },
            function(errorPayload) {
                formData.error = errorPayload.data;
            });
    }
})

var addBeerToTypes = function(topics,newBeer) {
    angular.forEach(topics,function(style,iterator){
        if (style == newBeer.style){
            topics[iterator].Beers.push(style);
            return;
        }
    })
}

var addStyleToStyles = function(styles,newStyle){
    topics.push(newStyle);
}

var addStyleToGraph = function(scope,nodes,edges,newStyle){
    nodes.update({id:newStyle.style,label:newStyle.style,group:getGroupByStyle(newStyle.parentStyle),font:{size:getNodeSize(newStyle)},size:getNodeSize(newStyle)});
    edges.update({from: newStyle.parentStyle, to: newStyle.style });
}