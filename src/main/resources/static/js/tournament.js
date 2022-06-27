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
        existingMatchLists: [],
        registeredMatchLists: [],
        matchNum: 0,
    },
    methods:{
        // トーナメント表のひな型を作る
        createTournament(tournamentNo, teamLists) {
            let teamNum = teamLists.length;
            let firstRoundNum = Math.ceil(teamNum / 2);
            this.tournaments.push(
                {
                    tournamentNo: tournamentNo,
                    rounds: [],
                }
            );
            this.createRounds(teamNum, firstRoundNum);
        },
        // ひな型を作るための再帰関数
        createRounds(teamNum, firstRoundNum) {
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
                        gameNo: this.matchNum,
                        next: Math.ceil(this.matchNum) + firstRoundNum,
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
                        const matchFromDb = this.existingMatchLists.find(existingMatch => game.gameNo === existingMatch.gameNo);
                            return {
                                gameNo: matchFromDb.gameNo,
                                player1: {
                                    id: matchFromDb.teamIdA,
                                    name:  matchFromDb.teamAPlayer1 + "・" + matchFromDb.teamAPlayer2,
                                    winner: false,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name:  matchFromDb.teamBPlayer1 + "・" + matchFromDb.teamBPlayer2,
                                    winner: false,
                                },
                            };
                    })
                );
            });
        },
        allotMatch() {
            this.registeredMatchLists
        },
        // 試合番号ボタン押下時、画面遷移
        viewResultOrStartGame(event) {
            const gameNo = event.target.value;            
            // 試合番号でReceivedResultに検索
            fetch(`searchMatchByGameNo?gameNo=${gameNo}`)
            .then(res => {
                // 条件式?　その試合が登録済みなら、試合結果画面に。 : でなければ、試合設定画面に。
                if(res === null) {
                    // 試合設定画面に遷移
                    location.href =  `match_from_tournament?gameNo=${gameNo}`;
                } else {
                    res.json().then(data => {
                        console.log(data);
                        if(data.length === 1 && isNotEmptyMatch(gameNo)) {
                            // 試合結果画面に遷移
                            location.href = `registered_game_result?gameNo=${gameNo}`;
                        } else {
                            // 空試合です
                        }
                    })
                }
            })
            .catch(error => {
                console.log(error);
            });
        },
        // 空試合か否か
        isNotEmptyMatch(gameNo) {
            const match = this.tournaments.forEach(tournament => tournament.rounds.forEach(round => round.games.filter(game => game.gameNo === gameNo)));
            return match.player2.teamId < 0 || match.player2.teamId < 0 ? false : true;
        }
    },
    created: function() {
        // トーナメント表作成済みか否か確認
        let tournamentEditStatus;
        fetch("getTournamentEditStatus")
        .then(res => res.json().then(data => {
            tournamentEditStatus = data;
            if(tournamentEditStatus === 0) {
                document.getElementById("app").innerHTML = "トーナメントは未作成です。選手登録を完了させてトーナメントを作成してください。";
            } else {
                // チーム一覧取得
                fetch('getTeamList')
                .then(res => res.json().then(data => {
                    this.teamLists = data;
                    this.createTournament(1, this.teamLists1);
                    this.createTournament(2, this.teamLists2);
                    this.createTournament(3, this.teamLists3);
                    this.createTournament(4, this.teamLists4);
                    this.createTournament(5, this.teamLists5);
                    this.createTournament(6, this.teamLists6);
                    this.createTournament(7, this.teamLists7);
                    this.createTournament(8, this.teamLists8);
                    this.createTournament(9, this.teamLists9);
                    this.createTournament(10, this.teamLists10);
                    // 対戦組み合わせ一覧取得
                    fetch('getMatchList')
                    .then(res => res.json().then(data => {
                        this.existingMatchLists = data;
                        this.allotTeam();
                        // 登録された試合一覧取得
                        fetch('getRegisteredMatchLists')
                        .then(res => {
                            if(res === null) {
                                // 登録された試合なし
                            } else {
                                res.json().then(data => {
                                    this.registeredMatchLists = data;
                                    this.allotMatch();
                                })
                                .catch(error => console.log(error));
                            }
                        })
                    }))
                    .catch(error => console.log(error));
                }))
                .catch(error => console.log(error));
            }
        }))
        .catch(error => console.log(error));
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
        teamLists3() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 3);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -3,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 3
                    }
                );
            }
            return lists;
        },
        teamLists4() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 4);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -4,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 4
                    }
                );
            }
            return lists;
        },
        teamLists5() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 5);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -5,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 5
                    }
                );
            }
            return lists;
        },
        teamLists6() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 6);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -6,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 6
                    }
                );
            }
            return lists;
        },
        teamLists7() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 7);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -7,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 7
                    }
                );
            }
            return lists;
        },
        teamLists8() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 8);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -8,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 8
                    }
                );
            }
            return lists;
        },
        teamLists9() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 9);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -9,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 9
                    }
                );
            }
            return lists;
        },
        teamLists10() {
            let lists = this.teamLists.filter(team => team.tournamentNo === 10);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        teamId: -10,
                        playerAName: 'empty',
                        playerBName: '',
                        tournamentNo: 10
                    }
                );
            }
            return lists;
        },
    },
})