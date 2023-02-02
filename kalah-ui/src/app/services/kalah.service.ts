import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Board } from '../board/board';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class KalahService {

  baseURL = environment.backendUrl;

  constructor(private http: HttpClient) { }

  public getBoard(id: any): Observable<Board> {
    return this.http.get<Board>(`${this.baseURL}${id}`);
  }

  public createGame(): Observable<Board> {
    return this.http.post<Board>(this.baseURL, null);
  }

  public play(selectedPit: number, player: string, id: number): Observable<any> {
    let httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    };

    return this.http
      .put(
        `${this.baseURL}${id}`,
        `{"player":"${player}",
          "pitIndex":${selectedPit}}`,
        httpOptions
      )
      .pipe(catchError(this.handleError));
  }

  handleError(error: any) {
    if (error.error instanceof ErrorEvent) {
      error = { message: `Error: ${error.error.message}` };
    } else {
      error = error.error;
    }
    return throwError(() => {
      return error;
    });
  }
}
