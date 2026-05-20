import { Component, signal, computed, OnInit, inject, OnDestroy, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlumnoService } from './services/alumno.service';
import { AsignaturaService } from './services/asignatura.service';
import { ProfesorService } from './services/profesor.service';
import { MatriculaService } from './services/matricula.service';
import { finalize, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit, OnDestroy {
  private alumnoService = inject(AlumnoService);
  private asignaturaService = inject(AsignaturaService);
  private profesorService = inject(ProfesorService);
  private matriculaService = inject(MatriculaService);
  private platformId = inject(PLATFORM_ID);

  title = signal('NexusHome Dashboard');
  
  // RAW Data
  alumnos = signal<any[]>([]);
  asignaturas = signal<any[]>([]);
  profesores = signal<any[]>([]);
  matriculas = signal<any[]>([]);
  
  // Search state
  searchTerm = signal('');

  // COMPUTED Data (Filtered based on what the user wants to see)
  filteredAlumnos = computed(() => {
    const term = this.searchTerm().toLowerCase();
    if (!term) return this.alumnos();
    return this.alumnos().filter(a => 
      a.nombre?.toLowerCase().includes(term) || 
      a.apellidos?.toLowerCase().includes(term) ||
      a.dni?.toLowerCase().includes(term) ||
      a.id?.toString().includes(term)
    );
  });

  filteredProfesores = computed(() => {
    const term = this.searchTerm().toLowerCase();
    if (!term) return this.profesores();
    return this.profesores().filter(p => 
      p.nombre?.toLowerCase().includes(term) || 
      p.apellidos?.toLowerCase().includes(term) ||
      p.especialidad?.toLowerCase().includes(term) ||
      p.id?.toString().includes(term)
    );
  });

  filteredAsignaturas = computed(() => {
    const term = this.searchTerm().toLowerCase();
    if (!term) return this.asignaturas();
    return this.asignaturas().filter(a => 
      a.nombre?.toLowerCase().includes(term) ||
      a.id?.toString().includes(term)
    );
  });

  filteredMatriculas = computed(() => {
    const term = this.searchTerm().toLowerCase();
    if (!term) return this.matriculas();
    return this.matriculas().filter(m => 
      m.id?.toString().includes(term) ||
      m.alumno?.nombre?.toLowerCase().includes(term) ||
      m.asignatura?.nombre?.toLowerCase().includes(term)
    );
  });
  
  isLoading = signal<boolean>(false);
  lastUpdate = signal<Date | null>(null);
  
  private autoRefreshInterval: any;

  ngOnInit() {
    this.refreshData();
    // Auto-refresh every 30 seconds ONLY in the browser
    if (isPlatformBrowser(this.platformId)) {
      this.autoRefreshInterval = setInterval(() => {
        this.refreshData();
      }, 30000);
    }
  }

  ngOnDestroy() {
    if (this.autoRefreshInterval) {
      clearInterval(this.autoRefreshInterval);
    }
  }

  refreshData() {
    this.isLoading.set(true);
    
    // Solo hacer el retraso y fetch en el navegador, para que el SSR no se quede colgado
    if (isPlatformBrowser(this.platformId)) {
      setTimeout(() => {
        this.fetchAlumnos();
        this.fetchAsignaturas();
        this.fetchProfesores();
        this.fetchMatriculas();
      }, 800);
    } else {
      this.isLoading.set(false);
    }
  }

  private fetchAlumnos() {
    this.alumnoService.getAlumnos()
      .pipe(catchError(() => of([])), finalize(() => this.checkLoadingComplete()))
      .subscribe(data => this.alumnos.set(data));
  }

  private fetchAsignaturas() {
    this.asignaturaService.getAsignaturas()
      .pipe(catchError(() => of([])), finalize(() => this.checkLoadingComplete()))
      .subscribe(data => this.asignaturas.set(data));
  }

  private fetchProfesores() {
    this.profesorService.getProfesores()
      .pipe(catchError(() => of([])), finalize(() => this.checkLoadingComplete()))
      .subscribe(data => this.profesores.set(data));
  }

  private fetchMatriculas() {
    this.matriculaService.getMatriculas()
      .pipe(catchError(() => of([])), finalize(() => this.checkLoadingComplete()))
      .subscribe(data => this.matriculas.set(data));
  }

  private checkLoadingComplete() {
    setTimeout(() => {
      this.isLoading.set(false);
      this.lastUpdate.set(new Date());
    }, 500);
  }
}
