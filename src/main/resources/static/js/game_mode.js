const vue = new Vue({
	el: "#app",
	data:{
		gameNo: document.getElementById('gameNo').value,
		team: [],
		team1: '',
		team2: '',
	},
	methods:{
		teamSet() {
			fetch('searchMember?gameNo=' + this.gameNo)
			.then(res => res.json()
			.then(data => {
				this.team = data
				if(this.team != null){
					this.team1 = this.team[0].playerAName + '・' + this.team[0].playerBName
					this.team2 = this.team[1].playerAName + '・' + this.team[1].playerBName
				}else{
					this.team1 = 'メンバーがいません'
					this.team2 = 'メンバーがいません'
				}
				
				}))
		},
	},
	created:
		function() {
			fetch('searchMember?gameNo=' + this.gameNo)
			.then(res => res.json()
			.then(data => {
				this.team = data
				if(this.team != null){
					this.team1 = this.team[0].playerAName + '・' + this.team[0].playerBName
					this.team2 = this.team[1].playerAName + '・' + this.team[1].playerBName
				}else{
					this.team1 = 'メンバーがいません'
					this.team2 = 'メンバーがいません'
				}
				
				}))
		},
})