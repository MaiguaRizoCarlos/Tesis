import { AfterViewInit, Component, ViewChild, Inject,EventEmitter } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { ViewAreasDataSource, ViewAreasItem } from './view-areas-datasource';
import { AreaService } from 'src/app/services/area.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MedidaService } from 'src/app/services/medida.service';


@Component({
  selector: 'app-view-areas',
  templateUrl: './view-areas.component.html',
  styleUrls: ['./view-areas.component.css']
})
export class ViewAreasComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<ViewAreasItem>;
  dataSource: ViewAreasDataSource;

  constructor( private areaService:AreaService,
    public matDialog: MatDialog,
    private medidaService:MedidaService) {
    this.dataSource = new ViewAreasDataSource();
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
      this.areaService.actualizarEditable().subscribe((data: any) => { 
      this.areaService.listar().subscribe(
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
        title:'Eliminar área',
        text:'¿Estás seguro de eliminar el área?',
        icon:'warning',
        showCancelButton:true,
        confirmButtonColor:'#3085d6',
        cancelButtonColor:'#d33',
        confirmButtonText:'Eliminar',
        cancelButtonText:'Cancelar'
      }).then((result) => {
        if(result.isConfirmed){
          this.areaService.eliminar(id).subscribe(
            (data) => {
              this.listaDatos = this.listaDatos.filter((lista:any) => lista.idAltura != id);
              Swal.fire('Información eliminado','El área ha sido eliminado','success');
              this.listar();
              this.listarEliminados();
            },
            (error) => {
              Swal.fire('Error','Error al eliminar el área','error');
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
      this.areaService.listarEliminads().subscribe(
          res=>{
            this.listaDatosEliminados=res;
          },
          err=>console.log(err)
        )
    }

    restaurar(id: any) {
      Swal.fire({
        title: 'Restaurar información',
        text: '¿Estás seguro de restaurar el área?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Restaurar',
        cancelButtonText: 'Cancelar'
      }).then((result) => {
        if (result.isConfirmed) {
          this.areaService.restaurar(id).subscribe(
            (data) => {
              Swal.fire('Información restaurada', 'El área ha sido restaurado', 'success');
              this.listarEliminados();
              this.listar();
            },
            (error) => {
              Swal.fire('Error en el sistema', 'Error al restaurar el área', 'error');
            }
          )
        }
      })
    }

    //agregar
    agregar(): void {
      const dialogRef = this.matDialog.open(AgregarArea, {
        data: { medida: this.medida},
      });
      dialogRef.afterClosed().subscribe(() => {
        this.listar();
      });
      
    }

    //editar
    editar(id:any, dato1:any, dato2:any): void {
      const dialogRef = this.matDialog.open(EditarArea, {
        data: { idArea: id, area:dato1,idUnidadMedida:dato2, medida: this.medida},
      });
      dialogRef.afterClosed().subscribe(() => {
        this.listar();
      });
    }
  }
  



  
export interface dataEditar {
  idArea: 0,
  area: null,
  idUnidadMedida:0,
  medida:[]
}

@Component({
  selector: 'editar-area',
  templateUrl: 'editar-area.html',
  styleUrls: ['./view-areas.component.css']
})

export class EditarArea {
  constructor(
    public dialogRef: MatDialogRef<EditarArea>,
    @Inject(MAT_DIALOG_DATA) public data1: dataEditar,
    private areaService:AreaService,
    private snack: MatSnackBar,
    private medidaService:MedidaService
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.medida = this.data1.medida;
    this.data.idArea=this.data1.idArea;
    this.data.area=this.data1.area;
    this.data.unidadMedida.idUnidadMedida=this.data1.idUnidadMedida;
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
      idArea:-1,
      area: null,
      unidadMedida:{
        idUnidadMedida:-1
      }
    }

  public afterClosed: EventEmitter<void> = new EventEmitter<void>();

  public editar() {

    if (this.data.area == '' || this.data.area == null) {
      this.snack.open('El área es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    
    if (this.data.unidadMedida.idUnidadMedida == -1) {
      this.snack.open('La medida es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    

    this.areaService.actualizar(this.data).subscribe(
      (data) => {
        Swal.fire('Información actualizada', 'El área se actualizo con éxito', 'success').then(
          (e) => {
            this.afterClosed.emit();
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error', 'No se actualizo el área', 'error');
        console.log(error);
      }
    );
  }

}



@Component({
  selector: 'agregar-area',
  templateUrl: 'agregar-area.html',
  styleUrls: ['./view-areas.component.css']
})

export class AgregarArea {
  constructor(
    public dialogRef: MatDialogRef<AgregarArea>,
    @Inject(MAT_DIALOG_DATA) public data1: dataEditar,
    private areaService:AreaService,
    private snack: MatSnackBar,
    private medidaService:MedidaService
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  public data = {
      area: '',
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

  public afterClosed: EventEmitter<void> = new EventEmitter<void>();

  public agregar() {

    if (this.data.area.trim() == '' || this.data.area.trim() == null) {
      this.snack.open('El área es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    
    if (this.data.unidadMedida.idUnidadMedida == -1) {
      this.snack.open('La medida es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }
    

    this.areaService.guardar(this.data).subscribe(
      (data) => {
        Swal.fire('Información guardada', 'El área se añadio con éxito', 'success').then(
          (e) => {
            this.afterClosed.emit();
            this.dialogRef.close();
          })
      }, (error) => {
        Swal.fire('Error', 'No se registro el área', 'error');
        console.log(error);
      }
    );
  }

}



  
  