import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-singin',
  templateUrl: './singin.component.html',
  styleUrls: ['./singin.component.css']
})
export class SinginComponent implements OnInit {

  constructor(
    private fb: FormBuilder,
    private auth: AuthService
  ) { }

  // se crea el formulario

  form!: FormGroup;

  ngOnInit(): void {

    console.log("token =>", this.auth.token);

    this.form = this.fb.group( {
      username : '',
      password : ''
    } )
  }

  async onSubmit(){
    console.log(this.form.value);
    this.auth.signin(this.form.value);// this.form.value esta la info de inicio de sesion y contrasena

    try{
      const resp: any = await this.auth.signin(this.form.value);
      console.log("respuesta =>", resp);
      this.auth.setToken(resp.token)

    }catch(error){ console.log(error);}
  }

}
// hacer la peticion para guardar el token