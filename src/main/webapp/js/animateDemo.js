

function draw(){
	ctx.clearRect(0,0,300,300);
	//console.log(arKeys[37]);
	//console.log(isLeft());
	if(isRight()){
		x += dx;
	}
	if(isLeft()){
		x -= dx;
	}
	if(isDown()){
		y += dy;
	}
	if(isUp()){
		y -= dy;
	}
	
	drawCircle(x,y);
}


function draw1() {
  ctx.clearRect(0,0,300,300);
  
  x += dx;
  y += dy;
  if(x < 0 || x > 300){
	dx = -dx;
  }
  if(y < 0 || y > 300){
	dy = -dy;
  }
}

init();