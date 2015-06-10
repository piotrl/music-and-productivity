(function() {
    'use strict';

    angular
        .module('music')
        .controller('MusicDetailController', MusicDetailController);

    function MusicDetailController($mdBottomSheet, track) {
        var vm = this;

        vm.track = track;
        var productivity = track.productivity;
        var maxValue = Math.max(productivity.productive, productivity.neutral, productivity.unproductive);

        vm.productivity = [
            {
                name: 'productive',
                procentage: Math.floor(productivity.productive / maxValue),
                minutes: toSpentMinutes(productivity.productive)
            },
            {
                name: 'neutral',
                procentage: Math.floor(productivity.neutral / maxValue),
                minutes: toSpentMinutes(productivity.neutral)
            },
            {
                name: 'unproductive',
                procentage: Math.floor(productivity.unproductive / maxValue),
                minutes: toSpentMinutes(productivity.unproductive)
            }];

        console.log(vm.productivity);

        this.hide = $mdBottomSheet.hide;
    }

    function toSpentMinutes(seconds) {
        return Math.floor(seconds / 60);
    }


})();