import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HabitacionService {
  private http = inject(HttpClient);
  private apiUrl = '/habitaciones';

  getDisponibles(checkin: string, checkout: string): Observable<any[]> {
    let params = new HttpParams()
      .set('checkin', checkin)
      .set('checkout', checkout);
    return this.http.get<any[]>(`${this.apiUrl}/disponibles`, { params });
  }
}
