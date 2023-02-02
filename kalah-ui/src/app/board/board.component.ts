import { Component, OnInit } from '@angular/core';
import { KalahService } from '../services/kalah.service';
import { Board, Pit } from './board';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  gameBoard: Board | undefined;
  error: string | undefined;
  loading: boolean = false;

  constructor(private service: KalahService) {
  }

  ngOnInit() {
    //TODO read from URL
    let id = this.gameBoard?.id;
    if (id != null) {
      this.service.getBoard(id)
        .subscribe((board) => {
          this.gameBoard = board;
        });
    } else
      this.createGame();
  }

  createGame() {
    this.loading = true;
    this.error = undefined;

    this.service.createGame().subscribe({
        next: (board) => {
          this.gameBoard = board;
        this.loading = false;
        },
        error: (err) => {
          console.log(err);
          this.error = err.message;
          this.loading = false;
        }
      });
  }

  play(selectedPit: number, player: string, pits: Pit[]) {
    this.loading = true;
    if (this.gameBoard?.playerTurn !== player || pits[selectedPit].stones === 0){
      this.loading = false;
      return;
    }

    this.error = undefined;
    if (this.gameBoard == null) {
      this.loading = false;
      return;
    }

    this.service.play(selectedPit, player, this.gameBoard.id).subscribe({
      next: (board) => {
        this.gameBoard = board;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}
