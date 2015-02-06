//var ctx = document.getElementById('canvas').getContext("2d");
			var ctx = $('#canvas')[0].getContext("2d");
			function drawCircle(x, y){
				temp = ctx.fillStyle;
				// rgba( red, green, blue, transparency)
				ctx.fillStyle = "rgba(0, 0, 0, 1)"
				ctx.beginPath();
				
				// x, y, radius, start angle, end angle, counterclockwise
				ctx.arc(x, y, 10, 0, Math.PI*2, true); 
				ctx.closePath();
				ctx.fill();
			}
			
			//function drawShit(){
			//get a reference to the canvas
			

			//draw a circle
			ctx.beginPath();
			ctx.arc(75, 75, 10, 0, Math.PI*2, true); 
			ctx.closePath();
			ctx.fill();
			
			
			drawCircle(15,150);
			drawCircle(50, 200);
			
			ctx.fillStyle = "rgba(255, 255, 0, .5)"
			ctx.beginPath();
			ctx.rect(15, 150, 120, 120);
			ctx.closePath();
			ctx.fill();
			
			drawCircle(75,250);
			
			//}
			//drawShit();