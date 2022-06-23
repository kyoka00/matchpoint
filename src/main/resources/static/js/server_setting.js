window.addEventListener("load",function(){
	document.getElementById('playerAtext').innerHTML = document.getElementById('playerA').value
	document.getElementById('playerBtext').innerHTML = document.getElementById('playerB').value
	document.getElementById('playerCtext').innerHTML = document.getElementById('playerC').value
	document.getElementById('playerDtext').innerHTML = document.getElementById('playerD').value
})

function changeLeft(){
	let playerA = document.getElementById('playerA')
	let playerB = document.getElementById('playerB')
	let tmp = playerA.value
	playerA.value = playerB.value
	playerB.value = tmp
	document.getElementById('playerAtext').innerHTML = playerA.value
	document.getElementById('playerBtext').innerHTML = playerB.value
}

function changeRight(){
	let playerA = document.getElementById('playerA')
	let playerB = document.getElementById('playerB')
	let playerC = document.getElementById('playerC')
	let playerD = document.getElementById('playerD')
	let tmp = playerC.value
	playerC.value = playerD.value
	playerD.value = tmp
	document.getElementById('playerCtext').innerHTML = playerC.value
	document.getElementById('playerDtext').innerHTML = playerD.value
}

function changeCoat(){
	let playerA = document.getElementById('playerA')
	let playerB = document.getElementById('playerB')
	let playerC = document.getElementById('playerC')
	let playerD = document.getElementById('playerD')
	let tmp1 = playerA.value
	let tmp2 = playerB.value
	playerA.value = playerC.value
	playerC.value = tmp1
	playerB.value = playerD.value
	playerD.value = tmp2
	document.getElementById('playerAtext').innerHTML = playerA.value
	document.getElementById('playerBtext').innerHTML = playerB.value
	document.getElementById('playerCtext').innerHTML = playerC.value
	document.getElementById('playerDtext').innerHTML = playerD.value
}

function serverSet(team){
	if(team == 1){
		document.getElementById('playerBtext').setAttribute('style', 'color: red;')
		document.getElementById('playerCtext').setAttribute('style', '')
	}else if(team == 2){
		document.getElementById('playerBtext').setAttribute('style', '')
		document.getElementById('playerCtext').setAttribute('style', 'color: red;')
	}
}