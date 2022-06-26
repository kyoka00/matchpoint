'use strict';

const Bracket = window['vue-tournament-bracket'];

const vue = new Vue({
    el: "#app",
    components: {
        bracket: Bracket
    },
    data: {
        tournaments: [],
        teamLists: [
            {
                teamId: 1,
                playerAName: 'A',
                playerBName: 'B',
                tournamentNo: 1
            },
            {
                teamId: 2,
                playerAName: 'C',
                playerBName: 'D',
                tournamentNo: 1
            },
            {
                teamId: 3,
                playerAName: 'E',
                playerBName: 'F',
                tournamentNo: 1
            },
            {
                teamId: 4,
                playerAName: 'G',
                playerBName: 'H',
                tournamentNo: 1
            },
            {
                teamId: 5,
                playerAName: 'I',
                playerBName: 'J',
                tournamentNo: 1
            },
            {
                teamId: 6,
                playerAName: 'K',
                playerBName: 'L',
                tournamentNo: 1
            },
            {
                teamId: 7,
                playerAName: 'M',
                playerBName: 'N',
                tournamentNo: 1
            },
            {
                teamId: 8,
                playerAName: 'O',
                playerBName: 'P',
                tournamentNo: 1
            },
            {
                teamId: 9,
                playerAName: 'Q',
                playerBName: 'R',
                tournamentNo: 1
            },
            {
                teamId: 10,
                playerAName: 'S',
                playerBName: 'T',
                tournamentNo: 1
            },
            {
                teamId: 11,
                playerAName: 'U',
                playerBName: 'V',
                tournamentNo: 1
            },
            {
                teamId: 12,
                playerAName: 'W',
                playerBName: 'X',
                tournamentNo: 1
            },
            {
                teamId: 13,
                playerAName: 'Y',
                playerBName: 'Z',
                tournamentNo: 1
            },
            {
                teamId: 14,
                playerAName: 'AA',
                playerBName: 'AB',
                tournamentNo: 1
            },
            {
                teamId: 15,
                playerAName: 'AC',
                playerBName: 'AD',
                tournamentNo: 1
            },
            {
                teamId: 16,
                playerAName: 'AE',
                playerBName: 'AF',
                tournamentNo: 1
            },
            {
                teamId: 17,
                playerAName: 'AG',
                playerBName: 'AH',
                tournamentNo: 1
            },
            {
                teamId: 18,
                playerAName: 'AI',
                playerBName: 'AJ',
                tournamentNo: 1
            },
            {
                teamId: 19,
                playerAName: 'AK',
                playerBName: 'AL',
                tournamentNo: 1
            },
            {
                teamId: 20,
                playerAName: 'BA',
                playerBName: 'BB',
                tournamentNo: 2
            },
            {
                teamId: 22,
                playerAName: 'BC',
                playerBName: 'BD',
                tournamentNo: 2
            },
            {
                teamId: 23,
                playerAName: 'BE',
                playerBName: 'BF',
                tournamentNo: 2
            },
            {
                teamId: 24,
                playerAName: 'BG',
                playerBName: 'BH',
                tournamentNo: 2
            },
        ],
        existingMatchLists: [
            {
                matchId: 1,
                teamIdA: 1,
                teamAPlayer1: "A",
                teamAPlayer2: "B",
                teamIdB: 2,
                teamBPlayer1: "C",
                teamBPlayer2: "D",
            },
            {
                matchId: 2,
                teamIdA: 3,
                teamAPlayer1: "E",
                teamAPlayer2: "F",
                teamIdB: 4,
                teamBPlayer1: "G",
                teamBPlayer2: "H",
            },
            {
                matchId: 3,
                teamIdA: 5,
                teamAPlayer1: "I",
                teamAPlayer2: "J",
                teamIdB: 6,
                teamBPlayer1: "K",
                teamBPlayer2: "L",
            },
            {
                matchId: 4,
                teamIdA: 7,
                teamAPlayer1: "M",
                teamAPlayer2: "N",
                teamIdB: 8,
                teamBPlayer1: "O",
                teamBPlayer2: "P",
            },
            {
                matchId: 5,
                teamIdA: 9,
                teamAPlayer1: "Q",
                teamAPlayer2: "R",
                teamIdB: 10,
                teamBPlayer1: "S",
                teamBPlayer2: "T",
            },
            {
                matchId: 6,
                teamIdA: 11,
                teamAPlayer1: "U",
                teamAPlayer2: "V",
                teamIdB: 12,
                teamBPlayer1: "W",
                teamBPlayer2: "X",
            },
            {
                matchId: 7,
                teamIdA: 13,
                teamAPlayer1: "Y",
                teamAPlayer2: "Z",
                teamIdB: 14,
                teamBPlayer1: "AA",
                teamBPlayer2: "AB",
            },
            {
                matchId: 8,
                teamIdA: 15,
                teamAPlayer1: "AC",
                teamAPlayer2: "AD",
                teamIdB: 16,
                teamBPlayer1: "AE",
                teamBPlayer2: "AF",
            },
            {
                matchId: 9,
                teamIdA: 17,
                teamAPlayer1: "AG",
                teamAPlayer2: "AH",
                teamIdB: 18,
                teamBPlayer1: "AI",
                teamBPlayer2: "AJ",
            },
            {
                matchId: 10,
                teamIdA: 19,
                teamAPlayer1: "AK",
                teamAPlayer2: "AL",
                teamIdB: -1,
                teamBPlayer1: "empty",
                teamBPlayer2: "",
            },
            {
                matchId: 22,
                teamIdA: 21,
                teamAPlayer1: "BA",
                teamAPlayer2: "BB",
                teamIdB: 22,
                teamBPlayer1: "BC",
                teamBPlayer2: "BD",
            },
            {
                matchId: 23,
                teamIdA: 23,
                teamAPlayer1: "BE",
                teamAPlayer2: "BF",
                teamIdB: 24,
                teamBPlayer1: "BG",
                teamBPlayer2: "BH",
            },
        ],
        matchNum: 0,
        dragTeamId: null,
        dragMatchId: null,
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
        // 適当にチームリストからひな型にぶち込む
        allotTeamFirst(tournamentNo, teamLists) {
            this.tournaments[tournamentNo - 1].rounds[0].games.find(obj => {
                // そのトーナメントの1回戦の試合番号を1にするための変数
                let currentMatchId = this.tournaments[tournamentNo - 1].rounds[0].games[0].matchId - 1;
                // トーナメントごとの試合番号
                let matchIdEveryTournament = obj.matchId - currentMatchId;
                // 試合番号から、適当に2チームを選ぶ
                let team1 = teamLists[(matchIdEveryTournament * 2) - 2];
                let team2 = teamLists[(matchIdEveryTournament * 2) - 1];
                let teamEmpty = teamLists[0];
                // 試合にチームを挿入
                obj.player1 = {
                    id: team1.teamId,
                    name: '・' + team1.playerAName + team1.playerBName,
                };
                // 二つ目のチームは、空チームにも対応
                if(teamLists.length >= matchIdEveryTournament * 2) {
                    obj.player2 = {
                        id: team2.playerAName,
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
        // 編集途中なら前回までの組み合わせを参照
        allotTeam() {
            this.tournaments.forEach(tournament => {
                this.$set(
                    tournament.rounds[0],
                    'games',
                    tournament.rounds[0].games.map(game => {
                        const matchFromDb = this.existingMatchLists.find(existingMatch => game.matchId === existingMatch.matchId);
                            console.log("試合:" , {
                                matchId: matchFromDb.matchId,
                                player1: {
                                    id: matchFromDb.teamIdA,
                                    name: "・" + matchFromDb.teamAPlayer1name + matchFromDb.teamAPlayer2,
                                },
                                player2: {
                                    id: matchFromDb.teamIdB,
                                    name: "・" + matchFromDb.teamBPlayer1 + matchFromDb.teamBPlayer2,
                                },
                            });
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
        viewResultOrPlayStart(matchId) {
            // 条件式：　試合番号で受信ボックスTBLに検索を掛けても、登録済みのレコードが無い && 選手が一人しかいない試合（シード）ではない
            if(matchId) {
                // 試合設定画面に遷移
            } else {
                // 試合結果画面に遷移
            }
        },
        // チームをドラッグした時の処理
        dragList(event, dragTeamId) {
            this.dragTeamId = dragTeamId;
            this.dragMatchId = event.target.parentNode.parentNode.parentNode.parentNode.children[1].value;
        },
        // チームをドロップしたときの処理
        dropList(event, dropTeamId) {
            const dropMatchId = event.target.parentNode.parentNode.parentNode.parentNode.parentNode.children[1].value;

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
            const dragMatch = this.tournaments[dragTeam.tournamentNo - 1].rounds[0].games.find(match => match.matchId == this.dragMatchId);

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
            const dropMatch = this.tournaments[dropTeam.tournamentNo - 1].rounds[0].games.find(match => match.matchId == dropMatchId);

            // 入れ替え処理
            let changedPlayer;
            if(dragTeam.tournamentNo === dropTeam.tournamentNo) {
                // 同じトーナメント内で入れ替えた時の処理
                if(dragTeam.teamId === dragMatch.player1.id) {
                    dragMatch.player1 = {
                        id: dropTeam.teamId,
                        name: "・" + dropTeam.playerAName + dropTeam.playerBName,
                    };
                    changedPlayer = 1;
                } else if(dragTeam.teamId === dragMatch.player2.id) {
                    dragMatch.player2 = {
                        id: dropTeam.teamId,
                        name: "・" + dropTeam.playerAName + dropTeam.playerBName,
                    };
                    changedPlayer = 2;
                }
                if(dragMatch.matchId === dropMatch.matchId && dragTeam === dropTeam) {
                    // 同じものを同じ場所に置いた時の処理
                } else if(dragMatch.matchId === dropMatch.matchId) {
                    // 同じチーム内で入れ替えた時の処理
                    switch(changedPlayer) {
                        case 1:
                            dropMatch.player2 = {
                                id: dragTeam.teamId,
                                name: "・" + dragTeam.playerAName + dragTeam.playerBName,
                            };
                            break;
                        case 2:
                            dropMatch.player1 = {
                                id: dragTeam.teamId,
                                name: "・" + dragTeam.playerAName + dragTeam.playerBName,
                            };
                            break;
                    }
                } else {
                    // 別のチームで入れ替えた時の処理
                    if(dropTeam.teamId === dropMatch.player1.id) {
                        dropMatch.player1 = {
                            id: dragTeam.teamId,
                            name: "・" + dragTeam.playerAName + dragTeam.playerBName,
                        };
                    } else if(dropTeam.teamId === dropMatch.player2.id) {
                        dropMatch.player2 = {
                            id: dragTeam.teamId,
                            name: "・" + dragTeam.playerAName + dragTeam.playerBName,
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
            .then(data => this.teamLists = data))
            .catch(error => console.log(error));
        this.createTournament(1, this.teamLists1);
        this.createTournament(2, this.teamLists2);
        // 分岐：トーナメント作成済み ? トーナメント未作成
        let tournamentStatus;
        // fetch('getTournamentStatus')
        //     .then(res => tournamentStatus = res)
        //     .catch(error => console.log(error));
        tournamentStatus = 0;
        if(tournamentStatus === 0) {
            this.allotTeamFirst(1, this.teamLists1);
            this.allotTeamFirst(2, this.teamLists2);
            
        } else {
            // 対戦組み合わせ一覧取得
            // fetch('getMatchList')
            // .then(res => res.json().then(data => this.existingMatchLists = data))
            // .catch(error => console.log(error));
            this.allotTeam();
        }
    },
    computed: {
        teamlists1() {
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
        teamlists2() {
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