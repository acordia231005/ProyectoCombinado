import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MatriculaService {
  private apiUrl = `${environment.apiUrl}/matricula`;

  constructor(private http: HttpClient) { }

  getMatriculas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getMatriculaById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
