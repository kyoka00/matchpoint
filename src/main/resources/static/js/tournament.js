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
            // {
            //     matchId: 1,
            //     teamIdA: 1,
            //     teamAPlayer1: "A",
            //     teamAPlayer2: "B",
            //     teamIdB: 2,
            //     teamBPlayer1: "C",
            //     teamBPlayer2: "D",
            // },
            // {
            //     matchId: 2,
            //     teamIdA: 3,
            //     teamAPlayer1: "E",
            //     teamAPlayer2: "F",
            //     teamIdB: 4,
            //     teamBPlayer1: "G",
            //     teamBPlayer2: "H",
            // },
            // {
            //     matchId: 3,
            //     teamIdA: 5,
            //     teamAPlayer1: "I",
            //     teamAPlayer2: "J",
            //     teamIdB: 6,
            //     teamBPlayer1: "K",
            //     teamBPlayer2: "L",
            // },
            // {
            //     matchId: 4,
            //     teamIdA: 7,
            //     teamAPlayer1: "M",
            //     teamAPlayer2: "N",
            //     teamIdB: 8,
            //     teamBPlayer1: "O",
            //     teamBPlayer2: "P",
            // },
            // {
            //     matchId: 5,
            //     teamIdA: 9,
            //     teamAPlayer1: "Q",
            //     teamAPlayer2: "R",
            //     teamIdB: 10,
            //     teamBPlayer1: "S",
            //     teamBPlayer2: "T",
            // },
            // {
            //     matchId: 6,
            //     teamIdA: 11,
            //     teamAPlayer1: "U",
            //     teamAPlayer2: "V",
            //     teamIdB: 12,
            //     teamBPlayer1: "W",
            //     teamBPlayer2: "X",
            // },
            // {
            //     matchId: 7,
            //     teamIdA: 13,
            //     teamAPlayer1: "Y",
            //     teamAPlayer2: "Z",
            //     teamIdB: 14,
            //     teamBPlayer1: "AA",
            //     teamBPlayer2: "AB",
            // },
            // {
            //     matchId: 8,
            //     teamIdA: 15,
            //     teamAPlayer1: "AC",
            //     teamAPlayer2: "AD",
            //     teamIdB: 16,
            //     teamBPlayer1: "AE",
            //     teamBPlayer2: "AF",
            // },
            // {
            //     matchId: 9,
            //     teamIdA: 17,
            //     teamAPlayer1: "AG",
            //     teamAPlayer2: "AH",
            //     teamIdB: 18,
            //     teamBPlayer1: "AI",
            //     teamBPlayer2: "AJ",
            // },
            // {
            //     matchId: 10,
            //     teamIdA: 19,
            //     teamAPlayer1: "AK",
            //     teamAPlayer2: "AL",
            //     teamIdB: -1,
            //     teamBPlayer1: "empty",
            //     teamBPlayer2: "",
            // },
            // {
            //     matchId: 22,
            //     teamIdA: 21,
            //     teamAPlayer1: "BA",
            //     teamAPlayer2: "BB",
            //     teamIdB: 22,
            //     teamBPlayer1: "BC",
            //     teamBPlayer2: "BD",
            // },
            // {
            //     matchId: 23,
            //     teamIdA: 23,
            //     teamAPlayer1: "BE",
            //     teamAPlayer2: "BF",
            //     teamIdB: 24,
            //     teamBPlayer1: "BG",
            //     teamBPlayer2: "BH",
            // },
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
                                    name: "・" + matchFromDb.teamAPlayer1 + matchFromDb.teamAPlayer2,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name: "・" + matchFromDb.teamBPlayer1 + matchFromDb.teamBPlayer2,
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