'use strict';

const Bracket = window['vue-tournament-bracket'];

const vue = new Vue({
    el: "#app",
    components: {
        bracket: Bracket
    },
    data: {
        tournaments: [],
        team_lists: [
            {
                team_id: 1,
                player_a_name: 'A',
                player_b_name: 'B',
                tournament_no: 1
            },
            {
                team_id: 2,
                player_a_name: 'C',
                player_b_name: 'D',
                tournament_no: 1
            },
            {
                team_id: 3,
                player_a_name: 'E',
                player_b_name: 'F',
                tournament_no: 1
            },
            {
                team_id: 4,
                player_a_name: 'G',
                player_b_name: 'H',
                tournament_no: 1
            },
            {
                team_id: 5,
                player_a_name: 'I',
                player_b_name: 'J',
                tournament_no: 1
            },
            {
                team_id: 6,
                player_a_name: 'K',
                player_b_name: 'L',
                tournament_no: 1
            },
            {
                team_id: 7,
                player_a_name: 'M',
                player_b_name: 'N',
                tournament_no: 1
            },
            {
                team_id: 8,
                player_a_name: 'O',
                player_b_name: 'P',
                tournament_no: 1
            },
            {
                team_id: 9,
                player_a_name: 'Q',
                player_b_name: 'R',
                tournament_no: 1
            },
            {
                team_id: 10,
                player_a_name: 'S',
                player_b_name: 'T',
                tournament_no: 1
            },
            {
                team_id: 11,
                player_a_name: 'U',
                player_b_name: 'V',
                tournament_no: 1
            },
            {
                team_id: 12,
                player_a_name: 'W',
                player_b_name: 'X',
                tournament_no: 1
            },
            {
                team_id: 13,
                player_a_name: 'Y',
                player_b_name: 'Z',
                tournament_no: 1
            },
            {
                team_id: 14,
                player_a_name: 'AA',
                player_b_name: 'AB',
                tournament_no: 1
            },
            {
                team_id: 15,
                player_a_name: 'AC',
                player_b_name: 'AD',
                tournament_no: 1
            },
            {
                team_id: 16,
                player_a_name: 'AE',
                player_b_name: 'AF',
                tournament_no: 1
            },
            {
                team_id: 17,
                player_a_name: 'AG',
                player_b_name: 'AH',
                tournament_no: 1
            },
            {
                team_id: 18,
                player_a_name: 'AI',
                player_b_name: 'AJ',
                tournament_no: 1
            },
            {
                team_id: 19,
                player_a_name: 'AK',
                player_b_name: 'AL',
                tournament_no: 1
            },
            {
                team_id: 20,
                player_a_name: 'BA',
                player_b_name: 'BB',
                tournament_no: 2
            },
            {
                team_id: 22,
                player_a_name: 'BC',
                player_b_name: 'BD',
                tournament_no: 2
            },
            {
                team_id: 23,
                player_a_name: 'BE',
                player_b_name: 'BF',
                tournament_no: 2
            },
            {
                team_id: 24,
                player_a_name: 'BG',
                player_b_name: 'BH',
                tournament_no: 2
            },
        ],
        existing_match_lists: [
            {
                match_id: 1,
                team_id_a: 1,
                team_a_player_1_name: "A",
                team_a_player_2_name: "B",
                team_id_b: 2,
                team_b_player_1_name: "C",
                team_b_player_2_name: "D",
            },
            {
                match_id: 2,
                team_id_a: 3,
                team_a_player_1_name: "E",
                team_a_player_2_name: "F",
                team_id_b: 4,
                team_b_player_1_name: "G",
                team_b_player_2_name: "H",
            },
            {
                match_id: 3,
                team_id_a: 5,
                team_a_player_1_name: "I",
                team_a_player_2_name: "J",
                team_id_b: 6,
                team_b_player_1_name: "K",
                team_b_player_2_name: "L",
            },
            {
                match_id: 4,
                team_id_a: 7,
                team_a_player_1_name: "M",
                team_a_player_2_name: "N",
                team_id_b: 8,
                team_b_player_1_name: "O",
                team_b_player_2_name: "P",
            },
            {
                match_id: 5,
                team_id_a: 9,
                team_a_player_1_name: "Q",
                team_a_player_2_name: "R",
                team_id_b: 10,
                team_b_player_1_name: "S",
                team_b_player_2_name: "T",
            },
            {
                match_id: 6,
                team_id_a: 11,
                team_a_player_1_name: "U",
                team_a_player_2_name: "V",
                team_id_b: 12,
                team_b_player_1_name: "W",
                team_b_player_2_name: "X",
            },
            {
                match_id: 7,
                team_id_a: 13,
                team_a_player_1_name: "Y",
                team_a_player_2_name: "Z",
                team_id_b: 14,
                team_b_player_1_name: "AA",
                team_b_player_2_name: "AB",
            },
            {
                match_id: 8,
                team_id_a: 15,
                team_a_player_1_name: "AC",
                team_a_player_2_name: "AD",
                team_id_b: 16,
                team_b_player_1_name: "AE",
                team_b_player_2_name: "AF",
            },
            {
                match_id: 9,
                team_id_a: 17,
                team_a_player_1_name: "AG",
                team_a_player_2_name: "AH",
                team_id_b: 18,
                team_b_player_1_name: "AI",
                team_b_player_2_name: "AJ",
            },
            {
                match_id: 10,
                team_id_a: 19,
                team_a_player_1_name: "AK",
                team_a_player_2_name: "AL",
                team_id_b: -1,
                team_b_player_1_name: "empty",
                team_b_player_2_name: "",
            },
            {
                match_id: 22,
                team_id_a: 21,
                team_a_player_1_name: "BA",
                team_a_player_2_name: "BB",
                team_id_b: 22,
                team_b_player_1_name: "BC",
                team_b_player_2_name: "BD",
            },
            {
                match_id: 23,
                team_id_a: 23,
                team_a_player_1_name: "BE",
                team_a_player_2_name: "BF",
                team_id_b: 24,
                team_b_player_1_name: "BG",
                team_b_player_2_name: "BH",
            },
        ],
        match_num: 0,
        drag_team_id: null,
        drag_match_id: null,
    },
    methods:{
        // トーナメント表のひな型を作る
        create_tournament(tournament_no, team_lists) {
            let team_num = team_lists.length;
            this.tournaments.push(
                {
                    tournament_no: tournament_no,
                    rounds: [],
                }
            );
            this.create_rounds(team_num);
        },
        // ひな型を作るための再帰関数
        create_rounds(team_num) {
            team_num = Math.ceil(team_num / 2);
            this.tournaments[this.tournaments.length - 1].rounds.push(
                {
                    games: []
                }
            );
            for(let i = 0; i < team_num; i++) {
                this.match_num++;
                this.tournaments[this.tournaments.length - 1].rounds[this.tournaments[this.tournaments.length -1].rounds.length - 1].games.push(
                    {
                        match_id: this.match_num,
                        player1: {},
                        player2: {},
                    }
                );
            }
            if(team_num > 1) {
                this.create_rounds(team_num);
            }
        },
        // 適当にチームリストからひな型にぶち込む
        allot_team_first(tournament_no, team_lists) {
            this.tournaments[tournament_no - 1].rounds[0].games.find(obj => {
                // そのトーナメントの1回戦の試合番号を1にするための変数
                let current_match_id = this.tournaments[tournament_no - 1].rounds[0].games[0].match_id - 1;
                // トーナメントごとの試合番号
                let match_id_every_tournament = obj.match_id - current_match_id;
                // 試合番号から、適当に2チームを選ぶ
                let team_1 = team_lists[(match_id_every_tournament * 2) - 2];
                let team_2 = team_lists[(match_id_every_tournament * 2) - 1];
                let team_empty = team_lists[0];
                // 試合にチームを挿入
                obj.player1 = {
                    id: team_1.team_id,
                    name: '・' + team_1.player_a_name + team_1.player_b_name,
                };
                // 二つ目のチームは、空チームにも対応
                if(team_lists.length >= match_id_every_tournament * 2) {
                    obj.player2 = {
                        id: team_2.team_id,
                        name: '・' + team_2.player_a_name + team_2.player_b_name,
                    };
                } else {
                    obj.player2 = {
                        id: team_empty.team_id,
                        name: '・' + team_empty.player_a_name + team_empty.player_b_name,
                    };
                }
            })
        },
        // 編集途中なら前回までの組み合わせを参照
        allot_team() {
            this.tournaments.forEach(tournament => {
                this.$set(
                    tournament.rounds[0],
                    'games',
                    tournament.rounds[0].games.map(game => {
                        const match_from_db = this.existing_match_lists.find(existing_match => game.match_id === existing_match.match_id);
                            console.log("試合:" , {
                                match_id: match_from_db.match_id,
                                player1: {
                                    id: match_from_db.team_id_a,
                                    name: "・" + match_from_db.team_a_player_1_name + match_from_db.team_a_player_2_name,
                                },
                                player2: {
                                    id: match_from_db.team_id_b,
                                    name: "・" + match_from_db.team_b_player_1_name + match_from_db.team_b_player_2_name,
                                },
                            });
                            return {
                                match_id: match_from_db.match_id,
                                player1: {
                                    id: match_from_db.team_id_a,
                                    name: "・" + match_from_db.team_a_player_1_name + match_from_db.team_a_player_2_name,
                                },
                                player2: {
                                    id: match_from_db.team_id_b,
                                    name: "・" + match_from_db.team_b_player_1_name + match_from_db.team_b_player_2_name,
                                },
                            };
                    })
                );
            });
        },
        // 試合番号ボタン押下時、画面遷移
        view_result_or_play_start(match_id) {
            // 条件式：　試合番号で受信ボックスTBLに検索を掛けても、登録済みのレコードが無い && 選手が一人しかいない試合（シード）ではない
            if(match_id) {
                // 試合設定画面に遷移
            } else {
                // 試合結果画面に遷移
            }
        },
        // チームをドラッグした時の処理
        dragList(event, drag_team_id) {
            this.drag_team_id = drag_team_id;
            this.drag_match_id = event.target.parentNode.parentNode.parentNode.parentNode.children[1].value;
        },
        // チームをドロップしたときの処理
        dropList(event, drop_team_id) {
            const drop_match_id = event.target.parentNode.parentNode.parentNode.parentNode.parentNode.children[1].value;

            // ドラッグ情報の取得・生成
            let drag_team = null;
            if(this.drag_team_id >= 0) {
                drag_team = this.team_lists.find(team => team.team_id === this.drag_team_id);
            } else {
                drag_team = {
                    team_id: this.drag_team_id,
                    player_a_name: 'empty',
                    player_b_name: '',
                    tournament_no: this.drag_team_id * -1
                };
            }
            const drag_match = this.tournaments[drag_team.tournament_no - 1].rounds[0].games.find(match => match.match_id == this.drag_match_id);

            // ドロップ情報の取得・生成
            let drop_team = null;
            if(drop_team_id >= 0) {
                drop_team = this.team_lists.find(team => team.team_id === drop_team_id);
            } else {
                drop_team = {
                    team_id: drop_team_id,
                    player_a_name: 'empty',
                    player_b_name: '',
                    tournament_no: drop_team_id * -1
                };
            }
            const drop_match = this.tournaments[drop_team.tournament_no - 1].rounds[0].games.find(match => match.match_id == drop_match_id);

            // 入れ替え処理
            let changed_player;
            if(drag_team.tournament_no === drop_team.tournament_no) {
                // 同じトーナメント内で入れ替えた時の処理
                if(drag_team.team_id === drag_match.player1.id) {
                    drag_match.player1 = {
                        id: drop_team.team_id,
                        name: "・" + drop_team.player_a_name + drop_team.player_b_name,
                    };
                    changed_player = 1;
                } else if(drag_team.team_id === drag_match.player2.id) {
                    drag_match.player2 = {
                        id: drop_team.team_id,
                        name: "・" + drop_team.player_a_name + drop_team.player_b_name,
                    };
                    changed_player = 2;
                }
                if(drag_match.match_id === drop_match.match_id && drag_team === drop_team) {
                    // 同じものを同じ場所に置いた時の処理
                } else if(drag_match.match_id === drop_match.match_id) {
                    // 同じチーム内で入れ替えた時の処理
                    switch(changed_player) {
                        case 1:
                            drop_match.player2 = {
                                id: drag_team.team_id,
                                name: "・" + drag_team.player_a_name + drag_team.player_b_name,
                            };
                            break;
                        case 2:
                            drop_match.player1 = {
                                id: drag_team.team_id,
                                name: "・" + drag_team.player_a_name + drag_team.player_b_name,
                            };
                            break;
                    }
                } else {
                    // 別のチームで入れ替えた時の処理
                    if(drop_team.team_id === drop_match.player1.id) {
                        drop_match.player1 = {
                            id: drag_team.team_id,
                            name: "・" + drag_team.player_a_name + drag_team.player_b_name,
                        };
                    } else if(drop_team.team_id === drop_match.player2.id) {
                        drop_match.player2 = {
                            id: drag_team.team_id,
                            name: "・" + drag_team.player_a_name + drag_team.player_b_name,
                        };
                    }
                }
            }
        },
    },
    created: function() {
        // チーム一覧取得
        // fetch('/getTeamList')
        // .then(res => res.json().then(data => this.team_lists = data))
        // .catch(error => console.log(error));
        this.create_tournament(1, this.team_lists_1);
        this.create_tournament(2, this.team_lists_2);
        // 分岐：トーナメント作成済み ? トーナメント未作成
        let tournament_status;
        // fetch('/getTournamentStatus')
        //     .then(res => tournament_status = res)
        //     .catch(error => console.log(error));
        tournament_status = 1;
        if(tournament_status === 0) {
            this.allot_team_first(1, this.team_lists_1);
            this.allot_team_first(2, this.team_lists_2);
            
        } else {
            // 対戦組み合わせ一覧取得
            // fetch('/getMatchList')
            // .then(res => res.json().then(data => this.existing_match_lists = data))
            // .catch(error => console.log(error));
            this.allot_team();
        }
    },
    computed: {
        team_lists_1() {
            let lists = this.team_lists.filter(team => team.tournament_no === 1);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        team_id: -1,
                        player_a_name: 'empty',
                        player_b_name: '',
                        tournament_no: 1
                    }
                );
            }
            return lists;
        },
        team_lists_2() {
            let lists = this.team_lists.filter(team => team.tournament_no === 2);
            if(lists.length % 2 === 1) {
                lists.push(
                    {
                        team_id: -2,
                        player_a_name: 'empty',
                        player_b_name: '',
                        tournament_no: 2
                    }
                );
            }
            return lists;
        },
    },
})