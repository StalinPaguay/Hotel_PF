import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HabitacionesComponent } from './components/habitaciones/habitaciones.component';
import { ReservaComponent } from './components/reserva/reserva.component';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'habitaciones', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'habitaciones', component: HabitacionesComponent },
  { path: 'reserva/:id', component: ReservaComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'habitaciones' }
];
