import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-reserva',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './reserva.component.html',
  styleUrl: './reserva.component.css'
})
export class ReservaComponent implements OnInit {
  habitacionId: number | null = null;
  checkin: string = '';
  checkout: string = '';
  mensaje: string = '';

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private reservaService = inject(ReservaService);

  ngOnInit() {
    this.habitacionId = Number(this.route.snapshot.paramMap.get('id'));
    this.checkin = this.route.snapshot.queryParamMap.get('checkin') || '';
    this.checkout = this.route.snapshot.queryParamMap.get('checkout') || '';
  }

  confirmar() {
    if(!this.habitacionId || !this.checkin || !this.checkout) return;
    
    const req = {
      habitacionId: this.habitacionId,
      checkin: this.checkin,
      checkout: this.checkout
    };

    this.reservaService.crearReserva(req).subscribe({
      next: (res) => {
        this.mensaje = '¡Reserva completada con éxito!';
        setTimeout(() => this.router.navigate(['/habitaciones']), 3000);
      },
      error: (err) => {
        this.mensaje = 'Error al crear la reserva: ' + (err.error?.message || 'Revisa tus datos');
      }
    });
  }
}
