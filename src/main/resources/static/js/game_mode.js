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
				if(this.team.matchId != null){
					this.team1 = this.team.teamAPlayer1
					if(this.team.teamAPlayer2){
						this.team1 = this.team1 + '・' + this.team.teamAPlayer2
					}
					this.team2 = this.team.teamBPlayer1
					if(this.team.teamBPlayer2){
						this.team2 = this.team2 +'・' + this.team.teamBPlayer2
					}
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
				if(this.team.matchId != null){
					this.team1 = this.team.teamAPlayer1
					if(this.team.teamAPlayer2){
						this.team1 = this.team1 + '・' + this.team.teamAPlayer2
					}
					this.team2 = this.team.teamBPlayer1
					if(this.team.teamBPlayer2){
						this.team2 = this.team2 +'・' + this.team.teamBPlayer2
					}
				}else{
					this.team1 = 'メンバーがいません'
					this.team2 = 'メンバーがいません'
				}
				
				}))
		},
})