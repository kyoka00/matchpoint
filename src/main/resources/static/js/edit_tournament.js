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
        matchNum: 0,
        dragTeamId: null,
        dragGameNo: null,
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
                        gameNo: this.matchNum,
                        player1: {},
                        player2: {},
                    }
                );
            }
            if(teamNum > 1) {
                this.createRounds(teamNum);
            }
        },
        // 適当にチームリストからひな型にぶち込む
        allotTeamFirst(tournamentNo, teamLists) {
            this.tournaments[tournamentNo - 1].rounds[0].games.find(obj => {
                // そのトーナメントの1回戦の試合番号を1にするための変数
                let currentGameNo = this.tournaments[tournamentNo - 1].rounds[0].games[0].gameNo - 1;
                // トーナメントごとの試合番号
                let gameNoEveryTournament = obj.gameNo - currentGameNo;
                // 試合番号から、適当に2チームを選ぶ
                let team1 = teamLists[(gameNoEveryTournament * 2) - 2];
                let team2 = teamLists[(gameNoEveryTournament * 2) - 1];
                let teamEmpty = teamLists[0];
                // 試合にチームを挿入
                obj.player1 = {
                    id: team1.teamId,
                    name: '・' + team1.playerAName + team1.playerBName,
                };
                // 二つ目のチームは、空チームにも対応
                if(teamLists.length >= gameNoEveryTournament * 2) {
                    obj.player2 = {
                        id: team2.teamId,
                        name: '・' + team2.playerAName + team2.playerBName,
                    };
                } else {
                    obj.player2 = {
                        id: teamEmpty.teamId,
                        name: '・' + teamEmpty.playerAName + teamEmpty.playerBName,
                    };
                }
            })
        },
        // 前回までの組み合わせを参照して、きれいにぶち込む
        allotTeam() {
            this.tournaments.forEach(tournament => {
                this.$set(
                    tournament.rounds[0],
                    'games',
                    tournament.rounds[0].games.map(game => {
                        const matchFromDb = this.existingMatchLists.find(existingMatch => game.gameNo === existingMatch.gameNo);
                            console.log("試合:" , {
                                gameNo: matchFromDb.gameNo,
                                player1: {
                                    id: matchFromDb.teamIdA,
                                    name: matchFromDb.teamAPlayer1name + "・" + matchFromDb.teamAPlayer2,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name: matchFromDb.teamBPlayer1 + "・" + matchFromDb.teamBPlayer2,
                                },
                            });
                            return {
                                gameNo: matchFromDb.gameNo,
                                player1: {
                                    id: matchFromDb.teamIdA,
                                    name: matchFromDb.teamAPlayer1 + "・" + matchFromDb.teamAPlayer2,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name: matchFromDb.teamBPlayer1 + "・" + matchFromDb.teamBPlayer2,
                                },
                            };
                    })
                );
            });
        },
        // チームをドラッグした時の処理
        dragList(event, dragTeamId) {
            this.dragTeamId = dragTeamId;
            this.dragGameNo = event.target.parentNode.parentNode.parentNode.parentNode.children[1].value;
        },
        // チームをドロップしたときの処理
        dropList(event, dropTeamId) {
            const dropGameNo = event.target.parentNode.parentNode.parentNode.parentNode.parentNode.children[1].value;

            // ドラッグ情報の取得・生成
            let dragTeam = null;
            if(this.dragTeamId >= 0) {
                dragTeam = this.teamLists.find(team => team.teamId === this.dragTeamId);
            } else {
                dragTeam = {
                    teamId: this.dragTeamId,
                    playerAName: 'empty',
                    playerBName: '',
                    tournamentNo: this.dragTeamId * -1
                };
            }
            const dragMatch = this.tournaments[dragTeam.tournamentNo - 1].rounds[0].games.find(match => match.gameNo == this.dragGameNo);

            // ドロップ情報の取得・生成
            let dropTeam = null;
            if(dropTeamId >= 0) {
                dropTeam = this.teamLists.find(team => team.teamId === dropTeamId);
            } else {
                dropTeam = {
                    teamId: dropTeamId,
                    playerAName: 'empty',
                    playerBName: '',
                    tournamentNo: dropTeamId * -1
                };
            }
            const dropMatch = this.tournaments[dropTeam.tournamentNo - 1].rounds[0].games.find(match => match.gameNo == dropGameNo);

            // 入れ替え処理
            let changedPlayer;
            if(dragTeam.tournamentNo === dropTeam.tournamentNo) {
                // 同じトーナメント内で入れ替えた時の処理
                if(dragTeam.teamId === dragMatch.player1.id) {
                    dragMatch.player1 = {
                        id: dropTeam.teamId,
                        name: dropTeam.playerAName + "・" + dropTeam.playerBName,
                    };
                    changedPlayer = 1;
                } else if(dragTeam.teamId === dragMatch.player2.id) {
                    dragMatch.player2 = {
                        id: dropTeam.teamId,
                        name: dropTeam.playerAName + "・" + dropTeam.playerBName,
                    };
                    changedPlayer = 2;
                }
                if(dragMatch.gameNo === dropMatch.gameNo && dragTeam === dropTeam) {
                    // 同じものを同じ場所に置いた時の処理
                } else if(dragMatch.gameNo === dropMatch.gameNo) {
                    // 同じチーム内で入れ替えた時の処理
                    switch(changedPlayer) {
                        case 1:
                            dropMatch.player2 = {
                                id: dragTeam.teamId,
                                name: dragTeam.playerAName + "・" + dragTeam.playerBName,
                            };
                            break;
                        case 2:
                            dropMatch.player1 = {
                                id: dragTeam.teamId,
                                name: dragTeam.playerAName + "・" + dragTeam.playerBName,
                            };
                            break;
                    }
                } else {
                    // 別のチームで入れ替えた時の処理
                    if(dropTeam.teamId === dropMatch.player1.id) {
                        dropMatch.player1 = {
                            id: dragTeam.teamId,
                            name: dragTeam.playerAName + "・" + dragTeam.playerBName,
                        };
                    } else if(dropTeam.teamId === dropMatch.player2.id) {
                        dropMatch.player2 = {
                            id: dragTeam.teamId,
                            name: dragTeam.playerAName + "・" + dragTeam.playerBName,
                        };
                    }
                }
            }
        },
    },
    created: function() {
        // チーム一覧取得
        fetch('getTeamList')
            .then(res => res.json()
            .then(data => {
                this.teamLists = data
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
                // 分岐: トーナメント作成済みか否か
                let tournamentStatus;
                // fetch('getTournamentStatus')
                //     .then(res => tournamentStatus = res)
                //     .catch(error => console.log(error));
                tournamentStatus = 0;
                if(tournamentStatus === 0) {
                    this.allotTeamFirst(1, this.teamLists1);
                    this.allotTeamFirst(2, this.teamLists2);
                    this.allotTeamFirst(3, this.teamLists3);
                    this.allotTeamFirst(4, this.teamLists4);
                    this.allotTeamFirst(5, this.teamLists5);
                    this.allotTeamFirst(6, this.teamLists6);
                    this.allotTeamFirst(7, this.teamLists7);
                    this.allotTeamFirst(8, this.teamLists8);
                    this.allotTeamFirst(9, this.teamLists9);
                    this.allotTeamFirst(10, this.teamLists10);
                } else {
                    fetch('getMatchList')
                    .then(res => res.json().then(data => this.existingMatchLists = data))
                    .catch(error => console.log(error));
                    this.allotTeam();
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