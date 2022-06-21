import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { LandingPageComponent } from "./views/landing-page/landing-page.component";
import { LoginPageComponent } from "./views/login-page/login-page.component";
import { RegisterPageComponent } from "./views/register-page/register-page.component";
import { LoginGuard } from "./guards/login.guard";

const routes: Routes = [
    { path: 'landing', component: LandingPageComponent},
    { path: 'register', component: RegisterPageComponent, canActivate:[LoginGuard]},
    { path: 'login', component: LoginPageComponent, canActivate:[LoginGuard]},
    { path: '', redirectTo:"landing", pathMatch: 'full'},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LandingPageComponent, LoginPageComponent];