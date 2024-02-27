import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-administrador-datos',
  templateUrl: './view-administrador-datos.component.html',
  styleUrls: ['./view-administrador-datos.component.css']
})
export class ViewAdministradorDatosComponent implements OnInit {

  displayedColumns = ['dato1', 'dato2', 'dato3', 'dato4', 'dato5', 'opciones'];

  constructor(private userServicio: UserService,
    public dialog: MatDialog,
    public login:LoginService) {
  }

  ngAfterViewInit(): void {
  }

  usuarioAdmin: any = null;
  ngOnInit(): void {
    this.usuarioAdmin = this.login.getUser();
    this.listarVigentes();
    this.listarEliminado();
  }

  listaUsuarios: any = []
  usuarios: any = []
  listaUsuariosEliminados: any = []

  listarVigentes() {
    this.userServicio.obtenerUsuarioRol(4).subscribe(
      res => {
        this.usuarios = res;
        this.listaUsuarios = this.usuarios.filter((usuario: any) => usuario.idUsuario !== 1);
        this.listaUsuarios = this.listaUsuarios.filter((usuario: any) => usuario.idUsuario !== this.usuarioAdmin.idUsuario)
        console.log(res)
      },
      err => console.log(err)
    )
  }

  listarEliminado() {
    this.userServicio.obtenerUsuarioRolEliminado(4).subscribe(
      res => {
        this.listaUsuariosEliminados = res;
      },
      err => console.log(err)
    )
  }

  eliminarUsuario(idUsuario: any) {
    Swal.fire({
      title: 'Eliminar información',
      text: '¿Estás seguro de eliminar al administrador de datos?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userServicio.eliminarUsuario(idUsuario).subscribe(
          (data) => {
            this.listaUsuarios = this.listaUsuarios.filter((usuario: any) => usuario.idUsuario != idUsuario);
            Swal.fire('Información eliminada', 'El administrador de datos ha sido eliminado', 'success');
            this.listarEliminado();
          },
          (error) => {
            Swal.fire('Error en el sistema', 'Error al eliminar el administrador de datos', 'error');
          }
        )
      }
    })
  }

  restaurarUsuario(idUsuario: any) {
    Swal.fire({
      title: 'Restaurar información',
      text: '¿Estás seguro de restaurar al administrador de datos?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Restaurar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userServicio.restaurarUsuario(idUsuario).subscribe(
          (data) => {
            this.listaUsuariosEliminados = this.listaUsuariosEliminados.filter((usuario: any) => usuario.idUsuario != idUsuario);
            Swal.fire('Información restaurada', 'El administrador de datos ha sido restaurado', 'success');
            this.listarVigentes();
          },
          (error) => {
            Swal.fire('Error en el sistema', 'Error al restaurar el administrador de datos', 'error');
          }
        )
      }
    })
  }



  //paginacion y busqueda
  page_size: number = 5
  page_size_options = [5, 10, 20, 50, 100]
  page_number: number = 1
  handlePage(e: PageEvent) {
    this.page_size = e.pageSize
    this.page_number = e.pageIndex + 1
  }

  page_number1: number = 1
  handlePage1(e: PageEvent) {
    this.page_size = e.pageSize
    this.page_number1 = e.pageIndex + 1
  }

  public search: string = '';

  onSearch(search: string) {
    this.search = search;
  }

  editar(id: any, dato1: any, dato2: any, dato3: any, dato4: any, dato5: any): void {
    const dialogRef = this.dialog.open(EditarAdministradorDatos, {
      data: { idUsuario: id, nombreUsuario: dato1, apellidoUsuario: dato2, cedula: dato3, telefono: dato4, email: dato5 },
    });
    dialogRef.afterClosed().subscribe(() => {
      this.listarVigentes();
    });
  }

  agregar(): void {
    const dialogRef = this.dialog.open(AgregarAdministradorDatos, {
    });
    dialogRef.afterClosed().subscribe(() => {
      this.listarVigentes();
    });
  }

}



export interface dataEditar {
  idUsuario: 0,
  nombreUsuario: '',
  apellidoUsuario: '',
  cedula: '',
  telefono: '',
  email: '',
  vigencia: 1
}

@Component({
  selector: 'agregar-administrador-datos',
  templateUrl: 'agregar-administrador-datos.html',
  styleUrls: ['./view-administrador-datos.component.css']
})

export class AgregarAdministradorDatos {
  constructor(
    public dialogRef: MatDialogRef<AgregarAdministradorDatos>,
    private userService: UserService,
    private snack: MatSnackBar,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

  public data = {
    nombreUsuario: '',
    apellidoUsuario: '',
    cedula: '',
    telefono: '',
    email: '',
    vigencia: 1,
    rol: {
      idRol: 4
    }
  }

  public afterClosed: EventEmitter<void> = new EventEmitter<void>();

  public agregar() {
    if (this.data.nombreUsuario.trim() == '' || this.data.nombreUsuario.trim() == null) {
      this.snack.open('El nombre del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.apellidoUsuario.trim() == '' || this.data.apellidoUsuario.trim() == null) {
      this.snack.open('El apellido del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.cedula.trim() == '' || this.data.cedula.trim() == null) {
      this.snack.open('La cédula del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    if (this.data.telefono.trim() == '' || this.data.telefono.trim() == null) {
      this.snack.open('El teléfono del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.email.trim() == '' || this.data.email.trim() == null) {
      this.snack.open('El email del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }


    this.userService.aniadirUsuario(this.data).subscribe(
      (data) => {
        Swal.fire('Información agregada', 'El administrador de datos se agrego con éxito', 'success').then(
          (e) => {
            this.afterClosed.emit();
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error en el sistema', 'No se agrego el administrador de datos', 'error');
        console.log(error);
      }
    );

  }

}



@Component({
  selector: 'editar-administrador-datos',
  templateUrl: 'editar-administrador-datos.html',
  styleUrls: ['./view-administrador-datos.component.css']
})

export class EditarAdministradorDatos {
  constructor(
    public dialogRef: MatDialogRef<EditarAdministradorDatos>,
    @Inject(MAT_DIALOG_DATA) public data1: dataEditar,
    private userService: UserService,
    private snack: MatSnackBar,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.data.idUsuario = this.data1.idUsuario;
    this.data.nombreUsuario = this.data1.nombreUsuario;
    this.data.apellidoUsuario = this.data1.apellidoUsuario;
    this.data.cedula = this.data1.cedula;
    this.data.telefono = this.data1.telefono;
    this.data.email = this.data1.email;
  }

  public data = {
    idUsuario: 0,
    nombreUsuario: '',
    apellidoUsuario: '',
    cedula: '',
    telefono: '',
    email: '',
    vigencia: 1,
    rol: {
      idRol: 4
    }
  }

  public afterClosed: EventEmitter<void> = new EventEmitter<void>();

  public editar() {
    if (this.data.nombreUsuario.trim() == '' || this.data.nombreUsuario.trim() == null) {
      this.snack.open('El nombre del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.apellidoUsuario.trim() == '' || this.data.apellidoUsuario.trim() == null) {
      this.snack.open('El apellido del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.cedula.trim() == '' || this.data.cedula.trim() == null) {
      this.snack.open('La cédula del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    if (this.data.telefono.trim() == '' || this.data.telefono.trim() == null) {
      this.snack.open('El teléfono del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    if (this.data.email.trim() == '' || this.data.email.trim() == null) {
      this.snack.open('El email del administrador de datos es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }


    this.userService.actualizarUsuario(this.data).subscribe(
      (data) => {
        Swal.fire('Información actualizada', 'El administrador de datos se actualizo con éxito', 'success').then(
          (e) => {
            this.afterClosed.emit();
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error en el sistema', 'No se actualizo el administrador de datos', 'error');
        console.log(error);
      }
    );

  }

}