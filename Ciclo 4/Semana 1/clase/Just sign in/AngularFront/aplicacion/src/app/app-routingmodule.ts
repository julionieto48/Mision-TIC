import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { SinginComponent } from "./singin/singin.component";

const routes: Routes = [
    { path: '/inicio', component: HomeComponent },
    { path: '', component: SinginComponent},
  ];

  

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }