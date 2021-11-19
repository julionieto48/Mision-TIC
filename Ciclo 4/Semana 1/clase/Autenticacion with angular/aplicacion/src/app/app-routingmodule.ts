import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { SignupComponent } from "./signup/signup.component";
import { SinginComponent } from "./singin/singin.component";

const routes: Routes = [
    { path: '/inicio', component: HomeComponent },
    { path: '/signin', component: SinginComponent},
    { path: '', component: SignupComponent},

  ];

  

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }