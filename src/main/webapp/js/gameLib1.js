var x = 150;
var y = 150;
var dx = 5;
var dy = 5;

var XMAX = 30;
var YMAX = 30;


var ctx;
var drawInterval;

var arKeys = new Array(222);

var isRight = false;
var isLeft = false;
var isUp = false;
var isDown = false;

function init() {
	var fps = 30;
	ctx = $('#canvas')[0].getContext("2d");
	for(var i =0; i < arKeys.length; ++i){
		arKeys[i] = false;
	}
	$(document).keydown(
		(function (keyEvent){
			arKeys[keyEvent.keyCode] = true;
		})
	);
	$(document).keyup(
		(function (keyEvent){
			arKeys[keyEvent.keyCode] = false;
		})
	);
	function getKeyFn(code){
		return (function () {return arKeys[code];});
	}
	isRight = getKeyFn(39);
	isLeft = getKeyFn(37);
	isUp = getKeyFn(38);
	isDown = getKeyFn(40);
	
	drawInterval = setInterval(draw, 1000/fps);

}


function drawSquare(x, y){
	temp = ctx.fillStyle;
	// rgba( red, green, blue, transparency)
	ctx.fillStyle = "rgba(0, 0, 0, 1)"
	ctx.beginPath();

	// x, y, radius, start angle, end angle, counterclockwise
	ctx.arc(x, y, 10, 0, Math.PI*2, true);
	ctx.closePath();
	ctx.fill();
	ctx.fillStyle = temp;
}

function drawCircle(x, y){
	temp = ctx.fillStyle;
	// rgba( red, green, blue, transparency)
	ctx.fillStyle = "rgba(0, 0, 0, 1)"
	ctx.beginPath();
	
	// x, y, radius, start angle, end angle, counterclockwise
	ctx.arc(x, y, 10, 0, Math.PI*2, true); 
	ctx.closePath();
	ctx.fill();
	ctx.fillStyle = temp;
}