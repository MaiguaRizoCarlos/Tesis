import { AfterViewInit, Component, ViewChild, Inject, EventEmitter,ChangeDetectorRef  } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { ViewAlturaDataSource, ViewAlturaItem } from './view-altura-datasource';
import { AlturaService } from 'src/app/services/altura.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MedidaService } from 'src/app/services/medida.service';




@Component({
  selector: 'app-view-altura',
  templateUrl: './view-altura.component.html',
  styleUrls: ['./view-altura.component.css']
})
export class ViewAlturaComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<ViewAlturaItem>;
  dataSource: ViewAlturaDataSource;

  constructor(
    private alturaService:AlturaService,
    public matDialog: MatDialog,
    private cdRef: ChangeDetectorRef,
    private changeDetectorRef: ChangeDetectorRef,
    private medidaService:MedidaService
  ) {
    this.dataSource = new ViewAlturaDataSource();
  }
  
  displayedColumns = ['dato1', 'dato2', 'dato3', 'opciones'];
  
  ngAfterViewInit(): void {
  }
  
  ngOnInit(): void {
    this.listar();
    this.listarMedidas();
    this.listarEliminados();
  }

  medida : any = []
  listarMedidas()
    {
      this.medidaService.listar().subscribe(
          res=>{
            this.medida=res;
            console.log(this.medida);
          },
          err=>console.log(err)
        )
    }

    listaDatos : any = []

    listar()
    {
      this.alturaService.actualizarEditable().subscribe((data: any) => { 
      this.alturaService.listar().subscribe(
          res=>{
            this.listaDatos=res;
          },
          err=>console.log(err)
        )
      })
    }
  
    //paginacion y busqueda
    page_size:number=5
    page_number:number=1
    page_size_options=[5,10,20,50,100]
  
    handlePage(e: PageEvent){
      this.page_size=e.pageSize
      this.page_number=e.pageIndex + 1
    }
    
    public search: string = '';
  
    onSearch( search: string ) {
      this.search = search;
    }

    //eliminar

    eliminar(id:any){
      Swal.fire({
        title:'Eliminar información',
        text:'¿Estás seguro de eliminar la altura?',
        icon:'warning',
        showCancelButton:true,
        confirmButtonColor:'#3085d6',
        cancelButtonColor:'#d33',
        confirmButtonText:'Eliminar',
        cancelButtonText:'Cancelar'
      }).then((result) => {
        if(result.isConfirmed){
          this.alturaService.eliminar(id).subscribe(
            (data) => {
              Swal.fire('Información eliminada','La altura ha sido eliminada','success');
              this.listarEliminados();
              this.listar();
            },
            (error) => {
              Swal.fire('Error','Error al eliminar la altura','error');
            }
          )
        }
      })
    }

    page_number1: number = 1
    handlePage1(e: PageEvent) {
      this.page_size = e.pageSize
      this.page_number1 = e.pageIndex + 1
    }

    listaDatosEliminados : any = []
    listarEliminados()
    {
      this.alturaService.listarEliminads().subscribe(
          res=>{
            this.listaDatosEliminados=res;
          },
          err=>console.log(err)
        )
    }

    restaurar(id: any) {
      Swal.fire({
        title: 'Restaurar información',
        text: '¿Estás seguro de restaurar al altura?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Restaurar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.alturaService.restaurar(id).subscribe(
            (data) => {
              Swal.fire('Información restaurada', 'La altura ha sido restaurado', 'success');
              this.listarEliminados();
              this.listar();
            },
            (error) => {
              Swal.fire('Error en el sistema', 'Error al restaurar la altura', 'error');
            }
          )
        }
      })
    }

    //agregar
    agregar(): void {
      const dialogRef = this.matDialog.open(AgregarAltura, {
        data: { medida: this.medida},
      });
      dialogRef.afterClosed().subscribe(() => {
          this.listar();
      });
    }

    //editar

  

    editar(id:any, dato1:any, dato2:any): void {
      const dialogRef = this.matDialog.open(EditarAltura, {
        data: { idAltura: id, altura:dato1,idUnidadMedida:dato2,medida: this.medida},
      });
      dialogRef.afterClosed().subscribe(() => {
        this.listar();
      });
    }
  }
  



  
export interface dataEditar {
  idAltura: 0,
  altura: null,
  idUnidadMedida:0,
  medida:[]
}

@Component({
  selector: 'editar-altura',
  templateUrl: 'editar-altura.html',
  styleUrls: ['./view-altura.component.css']
})

export class EditarAltura {
  constructor(
    public dialogRef: MatDialogRef<EditarAltura>,
    @Inject(MAT_DIALOG_DATA) public data1: dataEditar,
    private alturaService:AlturaService,
    private snack: MatSnackBar,
    private medidaService:MedidaService,
    private cdRef: ChangeDetectorRef
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.medida = this.data1.medida;
    this.data.idAltura=this.data1.idAltura;
    this.data.altura=this.data1.altura;
    this.data.unidadMedida.idUnidadMedida=this.data1.idUnidadMedida;
    this.cdRef.detectChanges(); 
  }

  

  medida : any = []
  listarMedidas()
    {
      this.medidaService.listar().subscribe(
          res=>{
            this.medida=res;
            console.log(this.medida);
          },
          err=>console.log(err)
        )
    }

  public data = {
    idAltura: 0,
    altura: null,
    unidadMedida:{
      idUnidadMedida:-1
    }
  }

  

  public editar() {

    if (this.data.altura == null) {
      this.snack.open('La altura es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    
    if (this.data.unidadMedida.idUnidadMedida == 0) {
      this.snack.open('La unidad de medida es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    

    this.alturaService.actualizar(this.data).subscribe(
      (data) => {
        Swal.fire('Información actualizada', 'La altura se ha acutalizado con éxito', 'success').then(
          (e) => {
            this.cdRef.detectChanges(); 
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error en el sistema', 'No se ha acutalizado la altura', 'error');
        console.log(error);
      }
    );
  }

}



@Component({
  selector: 'agregar-altura',
  templateUrl: 'agregar-altura.html',
  styleUrls: ['./view-altura.component.css']
})

export class AgregarAltura {
  constructor(
    public dialogRef: MatDialogRef<AgregarAltura>,
    @Inject(MAT_DIALOG_DATA) public data1: dataEditar,
    private alturaService:AlturaService,
    private snack: MatSnackBar,
    private medidaService:MedidaService
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  public data = {
    altura: '',
    unidadMedida:{
      idUnidadMedida:-1
    }
  }

  ngOnInit(): void {
    this.medida = this.data1.medida;
  }

  medida : any = []
  listarMedidas()
    {
      this.medidaService.listar().subscribe(
          res=>{
            this.medida=res;
            console.log(this.medida);
          },
          err=>console.log(err)
        )
    }

  

  public agregar() {

    if (this.data.altura.trim() == '' || this.data.altura.trim() == null) {
      this.snack.open('La altura es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    
    if (this.data.unidadMedida.idUnidadMedida == 0) {
      this.snack.open('La unidad de medida es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    

    this.alturaService.guardar(this.data).subscribe(
      (data) => {
        Swal.fire('Información guardada', 'La altura se agrego con éxito', 'success').then(
          (e) => {
            
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error en el sistema', 'No se registro la altura', 'error');
        console.log(error);
      }
    );
  }

}



  