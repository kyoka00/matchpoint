'use strict';

const Bracket = window['vue-tournament-bracket'];

const vue = new Vue({
    el: "#app",
    components: {
        bracket: Bracket
    },
    data: {
        tournaments: [],
        teamLists: [],
        existingMatchLists: [
           
        ],
        matchNum: 0,
    },
    methods:{
        // トーナメント表のひな型を作る
        createTournament(tournamentNo, teamLists) {
            let teamNum = teamLists.length;
            this.tournaments.push(
                {
                    tournamentNo: tournamentNo,
                    rounds: [],
                }
            );
            this.createRounds(teamNum);
        },
        // ひな型を作るための再帰関数
        createRounds(teamNum) {
            teamNum = Math.ceil(teamNum / 2);
            this.tournaments[this.tournaments.length - 1].rounds.push(
                {
                    games: []
                }
            );
            for(let i = 0; i < teamNum; i++) {
                this.matchNum++;
                this.tournaments[this.tournaments.length - 1].rounds[this.tournaments[this.tournaments.length -1].rounds.length - 1].games.push(
                    {
                        matchId: this.matchNum,
                        player1: {},
                        player2: {},
                    }
                );
            }
            if(teamNum > 1) {
                this.createRounds(teamNum);
            }
        },
        // 対戦組み合わせを参照
        allotTeam() {
            this.tournaments.forEach(tournament => {
                this.$set(
                    tournament.rounds[0],
                    'games',
                    tournament.rounds[0].games.map(game => {
                        const matchFromDb = this.existingMatchLists.find(existingMatch => game.matchId === existingMatch.matchId);
                            return {
                                matchId: matchFromDb.matchId,
                                player1: {
                                    id: matchFromDb.teamIdA,
                                    name:  matchFromDb.teamAPlayer1 + "・" + matchFromDb.teamAPlayer2,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name:  matchFromDb.teamBPlayer1 + "・" + matchFromDb.teamBPlayer2,
                                },
                            };
                    })
                );
            });
        },
        // 試合番号ボタン押下時、画面遷移
        viewResultOrStartGame(matchId) {
            // 条件式：　試合番号で受信ボックスTBLに検索を掛けても、登録済みのレコードが無い && 選手が一人しかいない試合（シード）ではない
            if(matchId) {
                // 試合設定画面に遷移
            } else {
                // 試合結果画面に遷移
            }
        },
    },
    created: function() {
        // チーム一覧取得
		fetch('getTeamList')
		.then(res => res.json().then(data => {
            console.log(data);
			this.teamLists = data;
			console.log(this.teamLists);
            this.createTournament(1, this.teamLists1);
            this.createTournament(2, this.teamLists2);
	        // 対戦組み合わせ一覧取得
            fetch('getMatchList')
            .then(res => res.json().then(data => {
                console.log(data);
                this.existingMatchLists = data;
                console.log(this.existingMatchLists);
                this.allotTeam();
            }))
            .catch(error => console.log("対戦組み合わせ: " + error));
		}))
		.catch(error => console.log("チーム:" + error));
		
    },
    computed: {
        teamLists1() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 1);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -1,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 1
                    }
                );
            }
            return lists;
        },
        teamLists2() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 2);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -2,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 2
                    }
                );
            }
            return lists;
        },
    },
})