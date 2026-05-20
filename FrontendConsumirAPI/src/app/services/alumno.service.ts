import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AlumnoService {
  private apiUrl = `${environment.apiUrl}/alumno`;

  constructor(private http: HttpClient) { }

  getAlumnos(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
