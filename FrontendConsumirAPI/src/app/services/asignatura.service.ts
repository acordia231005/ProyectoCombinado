import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AsignaturaService {
  private apiUrl = `${environment.apiUrl}/asignatura`;

  constructor(private http: HttpClient) { }

  getAsignaturas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
