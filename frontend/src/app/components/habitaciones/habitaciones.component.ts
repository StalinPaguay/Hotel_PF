import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HabitacionService } from '../../services/habitacion.service';

@Component({
  selector: 'app-habitaciones',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './habitaciones.component.html',
  styleUrl: './habitaciones.component.css'
})
export class HabitacionesComponent {
  checkin = '';
  checkout = '';
  habitaciones: any[] = [];
  
  private habitacionService = inject(HabitacionService);
  private router = inject(Router);

  buscar() {
    if (!this.checkin || !this.checkout) return;
    this.habitacionService.getDisponibles(this.checkin, this.checkout).subscribe({
      next: (data) => this.habitaciones = data,
      error: (err) => console.error(err)
    });
  }

  reservar(id: number) {
    this.router.navigate(['/reserva', id], {
      queryParams: { checkin: this.checkin, checkout: this.checkout }
    });
  }
}
