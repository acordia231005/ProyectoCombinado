import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {
  private apiUrl = `${environment.apiUrl}/profesor`;

  constructor(private http: HttpClient) { }

  getProfesores(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
