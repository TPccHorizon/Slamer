import {Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {SidebarComponent} from "./core/sidebar/sidebar.component";
import {HeaderComponent} from "./core/header/header.component";


export const AppRoutes: Routes = [
  {
    path: '',
    component: SidebarComponent
  },
  {
    path: 'sidebar',
    component: SidebarComponent
  },
  {
    path: 'header',
    component: HeaderComponent
  }
];
