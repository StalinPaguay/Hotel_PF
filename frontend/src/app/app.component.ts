import { Component, inject } from '@angular/core';
import { RouterOutlet, RouterModule, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  router = inject(Router);

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout(event?: Event) {
    if(event) event.preventDefault();
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
