import { Component, Inject, ViewChild, ElementRef } from '@angular/core';

import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InvestigacionService } from 'src/app/services/investigacion.service';
import { SolicitudAccesoService } from 'src/app/services/solicitud-acceso.service';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { PopupOptions, tileLayer } from 'leaflet';
import { NgForm } from '@angular/forms';
import { PageEvent } from '@angular/material/paginator';
import { MatDrawer } from '@angular/material/sidenav';
import { CarbonoService } from 'src/app/services/carbono.service';
import { AppWebService } from 'src/app/services/app-web.service';
import { FormControl, Validators } from '@angular/forms';
import * as L from 'leaflet';
import { AccesoService } from 'src/app/services/acceso.service';
import { InvestigacionInvestigadoresService } from 'src/app/services/investigacion-investigadores.service';
import { DirectorAreaInvestigacionService } from 'src/app/services/director-area-investigacion.service';
import { DatoRecolectadoService } from 'src/app/services/dato-recolectado.service';
import { Chart, ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { CatalogoOrganizacionService } from 'src/app/services/catalogo-organizacion.service';
import { SectorImpactoProyectoService } from 'src/app/services/sector-impacto-proyecto.service';
import { LocalizacionProyectoService } from 'src/app/services/localizacion-proyecto.service';
import { LineaInvestigacionProyectoService } from 'src/app/services/linea-investigacion-proyecto.service';
import { AreaInvestigacionProyectoService } from 'src/app/services/area-investigacion-proyecto.service';
import { HttpClient } from '@angular/common/http';
import { Color } from 'ng2-charts';
import { ChartsModule } from 'ng2-charts';
import { VariableService } from 'src/app/services/variable.service';
import { MatSidenav } from '@angular/material/sidenav';
import { OrganizacionService } from 'src/app/services/organizacion.service';
import { UserService } from 'src/app/services/user.service';
import { MedidaService } from 'src/app/services/medida.service';
import { throws } from 'assert';
import { DatasetService } from 'src/app/services/dataset.service';

interface Marker {
  lat: number;
  lng: number;
  content: string;
}

interface Dato {
  profundidades: String;
  valor: number;
}

interface OrganizacionProyecto {
  idOrganizacion: number;
  nombreOrganizacion: String;
  siglas: String;
  descripcion: String
}

interface OpenCageResponse {
  results: {
    geometry: {
      lat: number;
      lng: number;
    };
  }[];
}


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    public login: LoginService,
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private appWebService: AppWebService,
    private snack: MatSnackBar,
    public dialog: MatDialog,
    public datoRecolectadoService: DatoRecolectadoService,
    private investigacionService: InvestigacionService,
    private solicitudAccesoService: SolicitudAccesoService,
    private catalogoOrganizacionService: CatalogoOrganizacionService,
    private VariableService: VariableService,
    private http: HttpClient,
    private carbonoService: CarbonoService,
    private organizacionService: OrganizacionService,
    private userService: UserService,
    private datasetService:DatasetService) {
  }

  toggleMenuProyecto(modelo: any): void {
    if(modelo.idProyecto!=0){
      this.listaDatosDatasetProyecto = [];
      modelo.isMenuOpen = !modelo.isMenuOpen;
      this.listarDatasets(modelo.idProyecto);
    }
    
    this.investigaciones.forEach((item: any) => {
      if (item.idProyecto !== modelo.idProyecto) {
        item.isMenuOpen = false;
      }
    });
  }

  toggleMenuProyectoEsconder(modelo: any): void {
    modelo.isMenuOpen = false;
    this.listaDatosDatasetProyecto = [];
  }

  datasetSeleccionadaBackcraun=-1;
  listaDatosDatasetProyecto: any = [];
  listarDatasets(id:any) {
    this.listaDatosDatasetProyecto= [];
    this.datasetService.obtenerDatasetsAsc(id).subscribe(
      res => {
        this.listaDatosDatasetProyecto = res;
        if(this.listaDatosDatasetProyecto.length!=0){
          if(this.listaDatosDatasetProyecto.length>1){
            this.listaDatosDatasetProyecto.unshift({ codigoDataset: 0, fechaInicioDataset: 'Todo' });
            this.datasetSeleccionadaBackcraun=0;
          }else{
            const auxDataSet=this.listaDatosDatasetProyecto[0];
            this.datasetSeleccionadaBackcraun=auxDataSet.codigoDataset;
          }
        }
      },
      err => console.log(err)
    )
  }

  obtenerDatasetProyecto(idProyecto:any, codigoDataset:any) {
    if(idProyecto==0){
      this.datasetSeleccionadaBackcraun=-1;
    }else{
      this.datasetSeleccionadaBackcraun=codigoDataset;
    }
      this.investigacionSeleccionada=idProyecto;
      this.datasetSeleccionada=codigoDataset;
      this.reloadMarkers(idProyecto);
  }



  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  isMenuOpen = true;

  toggleMenuMapa() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  isLoggedIn = false;
  user: any = null;

  informacionEcoAndes = {
    condicionesUso: '',
    derechoReservado: '',
    descripcion: '',
    licenciaUso: ''
  }

  proyecto0 = {
    idProyecto: 0,
    nombreProyecto: '',
    descripcion: ''
  };

  navbar: any;
  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.listarTipoChart();
    this.login.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
      }
    )

    this.investigacionService.listarInvestigacionesPublicas().subscribe(
      (dato: any) => {
        this.investigaciones = dato;
        if (this.investigaciones.length > 0) {
          this.investigaciones.unshift({ idProyecto: 0, nombreProyecto: 'Todos los proyectos de investigación', descripcion: 'Vizualizar las muestras de todos los proyectos' });
        }
      }, (error) => {

        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
      }
    )

    this.investigacionService.listarInvestigacionesPublicas().subscribe(
      (dato: any) => {
        this.investigacion = dato;
      }, (error) => {

        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
      }
    )

    this.mostrarInformacionVigente();

    this.investigacionService.listarInvestigacionesMapa().subscribe(
      (dato: any) => {
        this.investigacionSeleccion = dato;
      }
    )

    this.listarVariablesDifucion(0, 0);
    this.listarOrganizaciones();
    this.listarFamiliasVariables();
    this.initMap();
    this.conporbarLogin();
  }

  conporbarLogin() {
    if (this.user != null) {
      this.userService.getImagen(this.user.idUsuario).subscribe((imagen: Blob) => {
      },
        (error) => {
          this.logout();
        })
    }
  }


  listarVariablesDifucion(id: any, idOrganizacion: any) {
    this.VariableService.listarVariablesDifusion(id, idOrganizacion).subscribe(
      (dato: any) => {
        this.listaCatalogoOrganizacion = dato;
        console.log(dato);
        if (this.listaCatalogoOrganizacion.length > 0) {
          this.listaCatalogoOrganizacion.unshift({ idVariable: 0, nombreVariable: 'Todos los datos', siglas: 'Todos', nombreOrganizacion: 'Todos' });
        }
      }
    )
  }

  listaOrganizaciones: any = []

  listarOrganizaciones() {
    this.organizacionService.listar().subscribe(
      res => {
        this.listaOrganizaciones = res;

        if (this.listaOrganizaciones.length > 0) {
          this.listaOrganizaciones.unshift({ idOrganizacion: 0, siglas: 'Variables del sistema' });
        }
        this.organizacionSeleccionado.idOrganizacion = 0;

      },
      err => console.log(err)
    )
  }

  public searchOrganizacionVariable: string = '';
  opcionSeleccionada: any;
  organizacionSeleccionado = {
    idOrganizacion: 0,
  }

  onOrganizacionChange(event: any): void {
    this.listarVariablesDifucion(this.familiaOrganizacionSeleccionado.idFamilia, this.organizacionSeleccionado.idOrganizacion);
  }

  listaFamiliaVariable: any = []

  listarFamiliasVariables() {
    this.VariableService.listarFamiliasVariables().subscribe(
      res => {
        this.listaFamiliaVariable = res;
        if (this.listaFamiliaVariable.length > 0) {
          this.listaFamiliaVariable.unshift({ idFamilia: 0, descripcion: 'Todas las familias' });
        }
        this.familiaOrganizacionSeleccionado.idFamilia = 0;
      },
      err => console.log(err)
    )
  }

  public searchFamiliaOrganizacion: Number = 0;
  opcionSeleccionadaFamilia: any;



  familiaOrganizacionSeleccionado = {
    idFamilia: 0,
  }
  onFamiliaChange(event: any): void {
    //this.opcionSeleccionadaFamilia = this.listaFamiliaVariable.find((option: FamiliaOrganizacion) => option.idFamilia === event.value);
    //console.log(this.opcionSeleccionada.idFamilia)
    this.listarVariablesDifucion(this.familiaOrganizacionSeleccionado.idFamilia, this.organizacionSeleccionado.idOrganizacion);
  }


  ngAfterViewInit() {
    this.fetchData();
  }

  private initMap() {
    const southWest = L.latLng(-84.399864, -170.253768);
    const northEast = L.latLng(84.922810, 178.346924);
    const bounds = L.latLngBounds(southWest, northEast);
    this.map = L.map('map', {
      center: [-1.164, -78.201],
      zoom: 7,
      maxBounds: bounds,
      maxBoundsViscosity: 0.7
    });
    this.map.zoomControl.setPosition('bottomright');
    tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
      maxZoom: 50,
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);
    this.map.setMinZoom(2.5);
  }




  datosIformacionAppWeb: any = [];

  public mostrarInformacionVigente() {
    this.appWebService.mostrarInformacionAppWebVigente().subscribe(
      (data: any) => {

        if (data != null) {
          this.datosIformacionAppWeb = data;
          this.informacionEcoAndes.condicionesUso = this.datosIformacionAppWeb.condicionesUso;
          this.informacionEcoAndes.derechoReservado = this.datosIformacionAppWeb.derechoReservado;
          this.informacionEcoAndes.descripcion = this.datosIformacionAppWeb.descripcion;
          this.informacionEcoAndes.licenciaUso = this.datosIformacionAppWeb.licenciaUso;
        }
      },
      (error) => {
        console.log(error);
        this.datosIformacionAppWeb = [];
      }
    )
  }



  public panelContro() {
    if (this.login.getUserRole() == 'ADMINISTRADOR') {
      this.router.navigate(['admin']);
    }
    else if (this.login.getUserRole() == 'INVESTIGADOR') {
      this.router.navigate(['user-dashboard']);
    } else if (this.login.getUserRole() == 'DIRECTOR') {
      this.router.navigate(['director-dashboard']);
    } else if (this.login.getUserRole() == 'ADMINISTRADOR DE DATOS') {
      this.router.navigate(['admin-datos']);
    }
    else {
      this.login.logout();
    }
  }

  public logout() {
    this.snack.open('La sesión se ha cerrado con exito', 'Aceptar', {
      duration: 3000,
    });
    setTimeout(() => {
      this.login.logout();
      window.location.reload();
    }, 3000);
  }

  panelOpenGrafico = false;

  isMenuOpen1 = false;
  isArrowUp1 = false;

  isMenuOpen2 = false;
  isArrowUp2 = false;

  isMenuOpen3 = false;
  isArrowUp3 = false;

  toggleMenu1() {
    this.isMenuOpen1 = !this.isMenuOpen1;
    this.isArrowUp1 = !this.isArrowUp1;
    this.isMenuOpen2 = false;
    this.isArrowUp2 = false;
    this.isMenuOpen3 = false;
    this.isArrowUp3 = false;
  }

  toggleMenu2() {
    this.isMenuOpen2 = !this.isMenuOpen2;
    this.isArrowUp2 = !this.isArrowUp2;
    this.isMenuOpen1 = false;
    this.isArrowUp1 = false;
    this.isMenuOpen3 = false;
    this.isArrowUp3 = false;
  }

  toggleMenu3() {
    this.isMenuOpen3 = !this.isMenuOpen3;
    this.isArrowUp3 = !this.isArrowUp3;
    this.isMenuOpen1 = false;
    this.isArrowUp1 = false;
    this.isMenuOpen2 = false;
    this.isArrowUp2 = false;
  }

  esconderToggleMenu() {
    this.isMenuOpen1 = false;
    this.isArrowUp1 = false;
    this.isMenuOpen2 = false;
    this.isArrowUp2 = false;
    this.isMenuOpen3 = false;
    this.isArrowUp3 = false;
  }

  menuVisible = true;

  botonMenuVisible = true;

  imagenUrl: string = '';

  toggleMenu() {
    this.menuVisible = !this.menuVisible;
  }
  @ViewChild('drawer') drawer!: MatDrawer;
  toggleBoton() {
    this.botonMenuVisible = false;
    this.menuVisible = true;
  }

  @ViewChild('menuLateral1') menuLateral1!: MatDrawer;
  mostrarMenu1() {
    this.menuLateral1.open();
    this.menuLateral2.close();
    this.menuLateral3.close();
    this.menuLateral4.close();
    this.menuLateral5.close();
  }
  ocultarMenu1() {
    this.menuLateral1.close();

  }

  @ViewChild('menuLateral2') menuLateral2!: MatDrawer;
  mostrarMenu2() {
    this.menuLateral2.open();
    this.menuLateral1.close();
    this.menuLateral3.close();
    this.menuLateral4.close();
    this.menuLateral5.close();
  }
  ocultarMenu2() {
    this.menuLateral2.close();
  }

  @ViewChild('menuLateral3') menuLateral3!: MatDrawer;
  mostrarMenu3() {
    this.menuLateral3.open();
    this.menuLateral1.close();
    this.menuLateral2.close();
    this.menuLateral4.close();
    this.menuLateral5.close();
  }
  ocultarMenu3() {
    this.menuLateral3.close();
  }

  @ViewChild('menuLateral4') menuLateral4!: MatDrawer;
  mostrarMenu4() {
    this.menuLateral4.open();
    this.menuLateral1.close();
    this.menuLateral2.close();
    this.menuLateral3.close();
    this.menuLateral5.close();
  }
  ocultarMenu4() {
    this.menuLateral4.close();
  }

  @ViewChild('menuLateral5') menuLateral5!: MatDrawer;
  mostrarMenu5() {
    this.menuLateral5.open();
    this.menuLateral1.close();
    this.menuLateral2.close();
    this.menuLateral3.close();
    this.menuLateral4.close();
  }
  ocultarMenu5() {
    this.menuLateral5.close();
  }


  selectedMenuItem: number = -1;

  openDialog(): void {
    const dialogRef = this.dialog.open(autenticacion);
  }

  openDialogSolicitud(): void {
    const dialogRef = this.dialog.open(DialogSolicitudAcceso);
  }

  investigaciones: any = []

  public search: string = '';

  public searchOrganizacion: string = '';

  onSearch(search: string) {
    this.search = search;
  }

  onSearchOrganizacion(search: string) {
    this.searchOrganizacion = search;
  }

  investigacionSeleccionada = 0;
  variableSeleccionada = 0;
  datasetSeleccionada = 0;


  filtrarInvestigacione(id: any) {
    this.investigacionSeleccionada = id;
    this.datasetSeleccionada=0;
    if(id==0){
      this.datasetSeleccionadaBackcraun=-1;
    }else{
      this.datasetSeleccionadaBackcraun=0;
    }
    //this.variableSeleccionada = -1;
    this.reloadMarkers(id);
  }

  filtrarCatalogo(id: any) {
    //this.investigacionSeleccionada = -1;
    this.variableSeleccionada = id;
    this.reloadMarkersCatalogo(id);
  }

  @ViewChild('radioButton', { static: false }) radioButton!: ElementRef<HTMLInputElement>;


  private reloadMarkers(id: any) {
    this.dataNominal = [];
    this.dataNumerico = [];
    this.openPopup = null;
    this.clearMarkers()
    // Volver a cargar los datos y procesarlos
    this.datoRecolectadoService.listarTodosLosDatosUnidos(id, this.variableSeleccionada,this.datasetSeleccionada).subscribe(
      (response: any) => {
        this.plotData(response);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );

    this.cargarDatosVariableProyecto(id);

    if (this.radioButton) {
      this.radioButton.nativeElement.checked = false;
    }

    if (!this.chartsContainer) {
      return;
    }
    const canvasElements = this.chartsContainer.querySelectorAll('canvas');
    const diverElements = this.chartsContainer.querySelectorAll('hr');
    const tituloElements = this.chartsContainer.querySelectorAll('h4');
    canvasElements.forEach((canvasElement) => {
      canvasElement.remove();
    });
    diverElements.forEach((diverElement) => {
      diverElement.remove();
    });

    tituloElements.forEach((tituloElements) => {
      tituloElements.remove();
    });
  }


  private reloadMarkersCatalogo(id: any) {
    this.dataNominal = [];
    this.dataNumerico = [];
    this.openPopup?.closePopup();
    this.openPopup = null;
    this.clearMarkers()

    this.datoRecolectadoService.listarTodosLosDatosUnidos(this.investigacionSeleccionada, id,this.datasetSeleccionada).subscribe(
      (response: any) => {
        this.plotData(response);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );

    this.cargarDatosVariableCatalogo(id);
    //this.radioButton.nativeElement.checked = false;
    if (this.radioButton) {
      this.radioButton.nativeElement.checked = false;
    }

    if (!this.chartsContainer) {
      return;
    }
    const canvasElements = this.chartsContainer.querySelectorAll('canvas');
    const diverElements = this.chartsContainer.querySelectorAll('hr');
    const tituloElements = this.chartsContainer.querySelectorAll('h4');
    canvasElements.forEach((canvasElement) => {
      canvasElement.remove();
    });
    diverElements.forEach((diverElement) => {
      diverElement.remove();
    });

    tituloElements.forEach((tituloElements) => {
      tituloElements.remove();
    });


  }


  investigacion: any = [];



  solicitudData = {
    nombre: '',
    apellido: '',
    email: '',
    institucion: '',
    motivo: '',
    proyectoInvestigacion: {
      idProyecto: ''
    }
  }
  @ViewChild('formSubmitSolicitudAccesoDatos')
  solicitudForm!: NgForm;

  formSubmitSolicitudAccesoDatos() {
    if (this.solicitudData.nombre == '' || this.solicitudData.nombre == null) {
      this.snack.open('El nombre es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.apellido == '' || this.solicitudData.apellido == null) {
      this.snack.open('El apellido es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.email == '' || this.solicitudData.email == null) {
      this.snack.open('El email es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.institucion == '' || this.solicitudData.institucion == null) {
      this.snack.open('El nombre de la institución es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.motivo == '' || this.solicitudData.motivo == null) {
      this.snack.open('El motivo de la solicitud es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.proyectoInvestigacion.idProyecto == '' || this.solicitudData.proyectoInvestigacion.idProyecto == null) {
      this.snack.open('Debe escojer un proyecto!!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }

    this.solicitudAccesoService.enviarSolictudDescarga(this.solicitudData).subscribe(
      (data) => {
        this.solicitudData.nombre = '';
        this.solicitudData.apellido = '';
        this.solicitudData.email = '';
        this.solicitudData.institucion = '';
        this.solicitudData.motivo = '';
        this.solicitudData.proyectoInvestigacion.idProyecto = '';
        this.snack.open('Solicitud enviada, Este pendiente del correo registrado', 'Aceptar', {
          duration: 5000
        });

        //this.solicitudForm.reset();
      }, (error) => {
        console.log(error);
        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
      }
    )
  }

  // mapa de datos
  map!: L.Map;
  private markers: L.Polygon[] = [];
  private openPopup: L.Layer | null = null;
  private currentMapName: string = 'Mapa de provincias';

  private clearMarkers() {
    this.markers.forEach(marker => marker.removeFrom(this.map));
    this.markers = [];
    this.investigacionDat.country = '';
    this.investigacionDat.state = '';
    this.investigacionDat.county = '';
    this.investigacionDat.parroquia = '';
    this.investigacionDat.coordenadax = '';
    this.investigacionDat.coordenaday = '';
  }


  maps = [

    { name: 'Mapa de provincias', label: 'Mapa de provincias' },
    { name: 'Mapa oscuro', label: 'Mapa oscuro' },
    { name: 'Mapa de calles', label: 'Mapa de calles' },
    { name: 'Mapa satélite', label: 'Mapa satélite' },
    { name: 'Mapa topográfico', label: 'Mapa topográfico' },
  ];

  activarLocalizaciones() {
    var Stamen_TonerLabels = L.tileLayer('https://stamen-tiles-{s}.a.ssl.fastly.net/toner-labels/{z}/{x}/{y}{r}.{ext}', {
      attribution: 'Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
      subdomains: 'abcd',
      minZoom: 0,
      maxZoom: 20,
    });
  }

  onMapChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const mapName = selectElement.value;
    if (mapName !== this.currentMapName) {
      this.currentMapName = mapName;
      this.map.eachLayer(layer => {
        if (layer instanceof L.TileLayer) {
          this.map.removeLayer(layer);
        }
      });

      switch (mapName) {
        case 'Mapa oscuro':
          L.tileLayer('https://{s}.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}{r}.png', {
            attribution: '© OpenStreetMap contributors'
          }).addTo(this.map);
          break;
        case 'Mapa de provincias':
          L.tileLayer('https://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
          }).addTo(this.map);
          break;
        case 'Mapa topográfico':
          L.tileLayer('https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}', {
            attribution: '© OpenStreetMap contributors'
          }).addTo(this.map);
          break;

        case 'Mapa satélite':
          L.tileLayer('http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}', {
            maxZoom: 20,
            subdomains: ['mt0', 'mt1', 'mt2', 'mt3']
          }).addTo(this.map);
          break;
        case 'Mapa de calles':
          L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '© OpenStreetMap contributors'
          }).addTo(this.map);
          break;
      }
    }
  }


  listaMarcadores: any = [];
  listaCatalogoOrganizacion: any = [];
  listaCarbono: any = [];
  listaAux: any = [];
  investigacionData = {
    idInvestigacion: 0
  }
  investigacionSeleccion: any = [];
  searchText = "";


  //abrir el dialogo informacion
  openDialogInformacion(idProyecto: any): void {
    const dialogRef = this.dialog.open(ViewInformacionProyectoInvestigacion, {
      data: { idProyectoInvestigacion: idProyecto },
    });
  }

  //abrir el dialogo informacion proyectos
  openDialogProyectos(): void {
    const dialogRef = this.dialog.open(ViewProyectosHome, {
    });
  }

  //abrir el dialogo informacion catalogo
  openDialogCatalogo(): void {
    const dialogRef = this.dialog.open(ViewCatalogoHome, {
    });
  }

  //abrir el dialogo informacion proyectos
  openDialogInformacionEcoAndes(): void {
    const dialogRef = this.dialog.open(ViewInformacionHome, {
    });
  }

  //abrir el dialogo informacion wxplorar
  openDialogExplorar(): void {
    const dialogRef = this.dialog.open(ViewExplorarHome, {
    });
  }

  //cargar datos en mapa
  private fetchData() {
    this.dataNominal = [];
    this.dataNumerico = [];
    this.datoRecolectadoService.listarTodosLosDatosUnidos(0, 0,0).subscribe(
      (response: any) => {
        this.plotData(response);

      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );
    this.cargarDatosVariableProyecto(0);
    this.openPopup = null;
  }

  location: any = {
    country: '',
    state: '',
    county: ''
  }
  investigacionDat: any = {
    country: '',
    state: '',
    county: '',
    parroquia: '',
    coordenadax: '',
    coordenaday: '',
  }

  listaDatos: any = [];

  modelo: any = {
    investigacionGraficoList: []
  };

  modeloNominal: any = {
    investigacionDatos: []
  };

  chartsContainer = document.getElementById('chartsContainer');

  isNumber: boolean = true;

  private plotData(data: any[]) {
    this.clearMarkers();
    for (const key in data) {
      const coordinates = key.split(',');
      const latLng: L.LatLngTuple = [parseFloat(coordinates[0]), parseFloat(coordinates[1])];
      const square = L.polygon([
        [latLng[0] - 0.001, latLng[1] - 0.001],
        [latLng[0] + 0.001, latLng[1] - 0.001],
        [latLng[0] + 0.001, latLng[1] + 0.001],
        [latLng[0] - 0.001, latLng[1] + 0.001]
      ], { color: 'red', fillOpacity: 100, weight: 1 }).addTo(this.map);

      const info = data[key];
      const locationData = {
        country: '',
        state: '',
        county: '',
        parroquia: ''
      };


      let isProcessingClick = false;
      square.on('click', async (event) => {


        isProcessingClick = true;

        this.modelo.investigacionGraficoList = [];
        this.modeloNominal.investigacionDatos = [];
        const apiUrl = `https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=${latLng[0]}&lon=${latLng[1]}`;
        this.http.get(apiUrl).subscribe((response: any) => {
          locationData.country = response.address.country;
          locationData.state = response.address.state;
          locationData.county = response.address.county;
          locationData.parroquia = response.address.village;
          this.investigacionDat.country = response.address.country;
          this.investigacionDat.state = response.address.state;
          this.investigacionDat.county = response.address.county;
          this.investigacionDat.parroquia = response.address.village;
          this.investigacionDat.coordenadax = latLng[0];
          this.investigacionDat.coordenaday = latLng[1];
          let message = `<div>`;
          message = `<div style="display: flex; justify-content: center; align-items: center;"><b>Muestra</b></div>`;
          message += `<div style="margin-bottom: 7px;"><b>Coordenadas:</b><br> <b>Latitud:</b> ${latLng[0]}, <b>Longitud:</b> ${latLng[1]}</div>`;
          message += `<div style="margin-bottom: 7px;"><b>Ubicación:</b><br> <b>Pais:</b> ${locationData.country}<br><b>Provincia:</b> ${locationData.state}<br><b>Cantón:</b> ${locationData.county}<br><b>Parroquia:</b> ${locationData.parroquia}</div>`;

          for (const tipoValor in info) {
            const investigacionGrafico: any = {
              tipoValor: '',
              valoresLista: []
            }
            if (info.hasOwnProperty(tipoValor)) {


              const datos: Dato[] = info[tipoValor];
              console.log(datos);
              let tipoValorStyles = `position: relative;display: flex;min-width: 20em;height: auto;line-height: 1.5;background: #B2C29A;border-radius: 5px;margin-bottom: 1px;word-wrap: break-word; align-items: center;`;
              message += `<div type="button" id="toggleMenuButton_${tipoValor}" onmouseover="this.style.background='#259441'; this.style.color='#FFFFFF';" onmouseout="this.style.background='#B2C29A'; this.style.color='#000000';" style="${tipoValorStyles}">`;
              message += `<p style="padding-left:10px; margin: 0;">${tipoValor} </p>`;
              message += '</div>';
              message += `<div id="menuContent_${tipoValor}" style="display:none;">`;
              message += "<ul>";
              investigacionGrafico.tipoValor = tipoValor;
              for (let i = 0; i < datos.length; i++) {
                const valores: any = {
                  valor: null,
                  profundidad: '',
                }
                const dato = datos[i];
                valores.valor = dato.valor;
                valores.profundidad = dato.profundidades;
                message += `<li>${dato.valor} (${dato.profundidades})</li>`;
                investigacionGrafico.valoresLista.push(valores);
                if (this.isNumber == true) {
                  this.isNumber = !isNaN(parseFloat(valores.valor)) && isFinite(+valores.valor);
                }
              }
              message += "</ul>"
              message += "</div>"
            }
            if (this.isNumber == true) {
              this.modelo.investigacionGraficoList.push(investigacionGrafico);
            } else {
              this.modeloNominal.investigacionDatos.push(investigacionGrafico);
            }
            this.isNumber = true;
          }

          message += "</div>"
          
          console.log(this.modeloNominal);
          this.openPopup = square.bindPopup(message);
          this.openPopup.openPopup();
          this.generateCharts();
          isProcessingClick = false;
          
          const popupContainer = document.querySelector('.leaflet-popup-content-wrapper') as HTMLElement | null;
          if (popupContainer) {
            popupContainer.style.maxHeight = '300px';
            popupContainer.style.overflowY = 'auto';
          }

          for (const tipoValor in info) {
            const toggleMenuButton = document.getElementById(`toggleMenuButton_${tipoValor}`);
            const menuContent = document.getElementById(`menuContent_${tipoValor}`);
            if (toggleMenuButton && menuContent) {
              toggleMenuButton.addEventListener('click', (() => {
                let isMenuOpen = false;
                return () => {
                  isMenuOpen = !isMenuOpen;
                  menuContent.style.display = isMenuOpen ? 'block' : 'none';
                };
              })())
            };
          }


        });
        //square.bindPopup(message).openPopup();
      });
      this.markers.push(square);
    }
  }




  //cargar datos en mapa
  dataNominal: any = [];
  dataNumerico: any = [];

  private cargarDatosVariableProyecto(id: any) {
    this.datoRecolectadoService.listarTodosLosDatos(id).subscribe(
      (response: any) => {

        this.dataNumerico = response;
        this.getCoordenadas1(this.dataNumerico);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );

    this.datoRecolectadoService.listarTodosLosDatosNominal(id).subscribe(
      (response: any) => {
        this.dataNominal = response;
        this.getCoordenadas2(this.dataNominal);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );
  }

  dataKeys1: string[] = [];
  dataKeys2: string[] = [];
  getCoordenadas1(data: any) {
    this.dataKeys1 = Object.keys(data); // Obtenemos las claves del objeto para usar en el template
  }
  getCoordenadas2(data: any) {
    this.dataKeys2 = Object.keys(data); // Obtenemos las claves del objeto para usar en el template
  }

  private cargarDatosVariableCatalogo(id: any) {
    this.datoRecolectadoService.listarTodosLosDatosCatalogo(id).subscribe(
      (response: any) => {
        this.dataNumerico = response;
        this.getCoordenadas1(this.dataNumerico);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );

    this.datoRecolectadoService.listarTodosLosDatosCatalogoNominal(id).subscribe(
      (response: any) => {
        this.dataNominal = response;
        this.getCoordenadas2(this.dataNominal);
      },
      error => {
        console.log('Error al obtener los datos:', error);
      }
    );
  }

  tipoChart: number = 0;

  listaTipoChart: any = []

  listarTipoChart() {
    this.listaTipoChart.push({ idTipoChart: 0, descripcion: 'Barras' });
    this.listaTipoChart.push({ idTipoChart: 1, descripcion: 'Lineas' });
    this.listaTipoChart.push({ idTipoChart: 2, descripcion: 'Radar' });
    this.listaTipoChart.push({ idTipoChart: 3, descripcion: 'Pastel' });
  }

  tipoChartSeleccionado = {
    idTipoChart: 0,
  }
  onTipoChart(event: any): void {
    this.tipoChart = this.tipoChartSeleccionado.idTipoChart;
    this.generateCharts();
  }



  generateCharts(): void {
    if (this.tipoChart == 0) {
      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'bar', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              responsive: true,
              scales: {
                xAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Profundidad' // Título del eje X
                  }
                }],
                yAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Valor' // Título del eje Y
                  },
                  ticks: {
                    min: 0, // Valor mínimo del eje Y
                    stepSize: 50,
                  }
                }]
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }
    } else if (this.tipoChart == 1) {

      // segundo chart 

      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'line', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              responsive: true,
              scales: {
                xAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Profundidad' // Título del eje X
                  }
                }],
                yAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Valor' // Título del eje Y
                  },
                  ticks: {
                    min: 0, // Valor mínimo del eje Y
                    stepSize: 50,
                  }
                }]
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }

    } else if (this.tipoChart == 2) {

      // segundo chart 

      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'radar', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154,0.5)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              plugins: {
                filler: {
                  propagate: false
                },
                'samples-filler-analyser': {
                  target: 'chart-analyser'
                }
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }

    } else {
      //tercer chart
      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);
          const titulo = document.createElement('h4');
          titulo.innerHTML = tipoValor;

          this.chartsContainer.appendChild(titulo);
          this.chartsContainer.appendChild(canvas);

          const randomColors = Array.from({ length: data.length }, () =>
            `rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)})`
          );

          const chart = new Chart(ctx, {
            type: 'pie', //line //bar//pie//doughnut
            data: {
              labels: labels,

              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: randomColors,
                borderWidth: 1

              }],


            },
            options: {
              plugins: {
                title: {
                  display: true,
                  text: 'Chart Title',
                },
              }
            }
          });
          chartInstances[canvas.id] = chart;
        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }
    }
  }


  @ViewChild('chartContainer', { static: false }) chartContainer!: ElementRef;


  listaDatosGrafico: any = []






  //paginador de proyectos
  page_size1: number = 5
  page_number1: number = 1
  page_size_options1 = [5, 10, 20, 50, 100]

  handlePage1(e: PageEvent) {
    this.page_size1 = e.pageSize
    this.page_number1 = e.pageIndex + 1
  }

  //paginador de catalogo
  page_size2: number = 5
  page_number2: number = 1
  page_size_options2 = [5, 10, 20, 50, 100]

  handlePage2(e: PageEvent) {
    this.page_size2 = e.pageSize
    this.page_number2 = e.pageIndex + 1
  }



}






@Component({
  selector: 'autenticacion',
  templateUrl: 'autenticacion.html',
  styleUrls: ['./home.component.css']
})
export class autenticacion {
  constructor(
    public dialogRef: MatDialogRef<autenticacion>,
    private loginService: LoginService,
    private datoRecolectadoService: DatoRecolectadoService,
    private router: Router,
    private snack: MatSnackBar,
    public dialog: MatDialog,
    public medidaService: MedidaService,
    public accesoService: AccesoService,

  ) { }

  hidePass = true;

  loginData = {
    "email": '',
    "contrasenia": '',
  }

  acceso = {
    usuario: {
      idUsuario: 0
    }
  }

  datosUsuario: any;

  ngOnInit(): void {
  }

  formSubmit() {
    if (this.loginData.email.trim() == '' || this.loginData.email.trim() == null) {
      this.snack.open('El email es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    if (this.loginData.contrasenia.trim() == '' || this.loginData.contrasenia.trim() == null) {
      this.snack.open('La contraseña es requerida !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    this.loginService.generateToken(this.loginData).subscribe(
      (data: any) => {
        this.loginService.loginUser(data.token);
        this.loginService.getCurrentUser().subscribe((user: any) => {
          this.dialogRef.close();
          this.loginService.setUser(user);
          this.datosUsuario = user;
          this.acceso.usuario.idUsuario = this.datosUsuario.idUsuario;
          this.accesoService.guardarAcceso(this.acceso).subscribe((user: any) => { })
          if (this.loginService.getUserRole() == 'ADMINISTRADOR') {
            //dashboard admin
            //window.location.href = '/admin';
            this.router.navigate(['admin']);
            this.loginService.loginStatusSubjec.next(true);
            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido administrador',
              showConfirmButton: false,
              timer: 3000
            })
          }
          else if (this.loginService.getUserRole() == 'INVESTIGADOR') {
            this.router.navigate(['user-dashboard']);
            this.loginService.loginStatusSubjec.next(true);

            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido investigador',
              showConfirmButton: false,
              timer: 3000
            })
            this.datoRecolectadoService.actualizarEditable().subscribe((data: any) => {
            });

          } else if (this.loginService.getUserRole() == 'ADMINISTRADOR DE DATOS') {
            this.router.navigate(['admin-datos']);
            this.loginService.loginStatusSubjec.next(true);

            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido administrador de datos',
              showConfirmButton: false,
              timer: 3000
            })
          } else if (this.loginService.getUserRole() == 'DIRECTOR') {
            this.router.navigate(['director-dashboard']);
            this.loginService.loginStatusSubjec.next(true);

            Swal.fire({
              position: 'top-end',
              icon: 'info',
              title: 'Bienvenido director',
              showConfirmButton: false,
              timer: 3000
            })
          }
          else {
            this.loginService.logout();
          }
        })
      }, (error) => {
        console.log(error);
        this.snack.open('Detalles inválidos , vuelva a intentar !!', 'Aceptar', {
          duration: 3000
        })
      }
    )
  }

  openDialog(): void {
    this.dialogRef.close();
    const dialogRef = this.dialog.open(ResetPassword);
  }

  email = new FormControl('', [Validators.required, Validators.email]);

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

}



@Component({
  selector: 'reset-password',
  templateUrl: 'reset-password.html',
  styleUrls: ['./home.component.css']
})
export class ResetPassword {
  constructor(
    public dialogRef: MatDialogRef<ResetPassword>,
    private loginService: LoginService,
    private router: Router,
    private snack: MatSnackBar,
    public dialog: MatDialog
  ) { }

  datosEmail = {
    "destinatario": '',
    "asunto": '',
    "contenido": '',
  }

  ngOnInit(): void {
  }

  openDialogLogin(): void {
    this.dialogRef.close();
    const dialogRef = this.dialog.open(autenticacion);
  }

  formSubmit() {
    if (this.datosEmail.destinatario.trim() == '' || this.datosEmail.destinatario.trim() == null) {
      this.snack.open('EL correo electronico es requerido !!', 'Aceptar', {
        duration: 3000
      })
      return;
    }

    this.loginService.enviarEmail(this.datosEmail).subscribe(
      (data: any) => {
        
        this.snack.open('Contraseña actualizada , La nueva contrase ha sido enviada al correo electrónico !!', 'Aceptar', {
          duration: 3000
        })
        this.openDialogLogin();
        //this.router.navigate(['/login']);
      }, (error) => {
        console.log(error);
        this.snack.open('El email ingresado no es valido , vuelva a intentar !!', 'Aceptar', {
          duration: 3000
        })
      }
    )
  }

}

export interface DialogData {
  animal: '';
  name: '';
}

@Component({
  selector: 'dialog-solicitud-acceso',
  templateUrl: 'dialog-solicitud-acceso.html',
  styleUrls: ['./home.component.css']
})
export class DialogSolicitudAcceso {
  constructor(
    public dialogRef: MatDialogRef<DialogSolicitudAcceso>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private snack: MatSnackBar,
    private investigacionService: InvestigacionService,
    private solicitudAccesoService: SolicitudAccesoService,
    private appWebService: AppWebService

  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  investigacion: any = [];

  solicitudData = {
    nombre: '',
    apellido: '',
    email: '',
    institucion: '',
    motivo: '',
    proyectoInvestigacion: {
      idProyecto: ''
    },
    estadoSolicitudDescarga: {
      idEstadoDescarga: 1
    }
  }

  ngOnInit(): void {
    this.listarProyectosInvestigacion();
  }

  public listarProyectosInvestigacion() {
    this.investigacionService.listarInvestigaciones().subscribe(
      (dato: any) => {

        this.investigacion = dato;
      }, (error) => {
        console.log(error);

        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
        this.dialogRef.close();

      }
    )
  }

  public mostrarInformacionAppWeb() {

  }

  formSubmit() {
    if (this.solicitudData.nombre == '' || this.solicitudData.nombre == null) {
      this.snack.open('El nombre es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.apellido == '' || this.solicitudData.apellido == null) {
      this.snack.open('El apellido es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.email == '' || this.solicitudData.email == null) {
      this.snack.open('El email es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.institucion == '' || this.solicitudData.institucion == null) {
      this.snack.open('El nombre de la institución es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.motivo == '' || this.solicitudData.motivo == null) {
      this.snack.open('El motivo de la solicitud es requerido !!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }
    if (this.solicitudData.nombre == '' || this.solicitudData.nombre == null) {
      this.snack.open('Debe escojer un proyecto!!', 'Aceptar', {
        duration: 3000,
        verticalPosition: 'bottom',
        horizontalPosition: 'center'
      });
      return;
    }

    this.solicitudAccesoService.enviarSolictudDescarga(this.solicitudData).subscribe(
      (data) => {
        Swal.fire('Solicitud enviada', 'Este pendiente del correo registrado', 'success');
        this.dialogRef.close();

      }, (error) => {
        console.log(error);
        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
      }
    )
  }
}


export interface DialogDataInformacionproyectoInvestigacion {
  idProyectoInvestigacion: '';
}

@Component({
  selector: 'view-informacion-proyecto-investigacion',
  templateUrl: 'view-informacion-proyecto-investigacion.html',
  styleUrls: ['./home.component.css']
})

export class ViewInformacionProyectoInvestigacion {
  constructor(
    public dialogRef: MatDialogRef<ViewInformacionProyectoInvestigacion>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDataInformacionproyectoInvestigacion,
    private investigacionInvestigadoresService: InvestigacionInvestigadoresService,
    private investigacionService: InvestigacionService,
    private sectorImpactoProyectoService: SectorImpactoProyectoService,
    private localizacionProyectoService: LocalizacionProyectoService,
    private lineaInvestigacionProyectoService: LineaInvestigacionProyectoService,
    private areaInvestigacionProyectoService: AreaInvestigacionProyectoService,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  informacionProyectoInvestigacion: any;
  grupoInvestigacion: any = [];
  informacionDirector: any = [];
  informacionImpacto: any = [];
  informacionAreaInvestigacion: any = [];
  informacionUbicacion: any = [];
  informacionLinea: any = [];

  ngOnInit(): void {

    this.investigacionService.obtenerProyectoInvestigacion(this.data.idProyectoInvestigacion).subscribe(
      (dato: any) => {
        this.informacionProyectoInvestigacion = this.transformarFechas(dato);
      }
    )
    this.investigacionInvestigadoresService.listarInvestigadoresEnProyectosInvestigacion(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.grupoInvestigacion = data;
      },
      (error) => {
        console.log(error);
      }
    )
    this.investigacionInvestigadoresService.obtenerDirectorProyecto(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.informacionDirector = data;
      },
      (error) => {
        console.log(error);
      }
    )

    this.sectorImpactoProyectoService.listarPorProyecto(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.informacionImpacto = data;
      },
      (error) => {
        console.log(error);
      }
    )
    this.localizacionProyectoService.listarPorProyecto(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.informacionUbicacion = data;
      },
      (error) => {
        console.log(error);
      }
    )

    this.areaInvestigacionProyectoService.listarPorProyecto(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.informacionAreaInvestigacion = data;
      },
      (error) => {
        console.log(error);
      }
    )

    this.lineaInvestigacionProyectoService.listarPorProyecto(this.data.idProyectoInvestigacion).subscribe(
      (data: any) => {
        this.informacionLinea = data;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  transformarFechas(data: any): any {
    const fechaInicioCompleta = data.fechaInicio;
    const fechaFinCompleta = data.fechaFin;
    const fechaInicioObj = new Date(fechaInicioCompleta);
    const fechaFinObj = new Date(fechaFinCompleta);
    const fechaInicioFormateada = this.formatoFecha(fechaInicioObj);
    const fechaFinFormateada = this.formatoFecha(fechaFinObj);

    // Devolver un nuevo objeto con las fechas formateadas y el resto de la estructura de datos sin cambios
    return { ...data, fechaInicio: fechaInicioFormateada, fechaFin: fechaFinFormateada };
  }

  formatoFecha(fecha: Date): string {
    const dia = fecha.getDate().toString().padStart(2, '0');
    const mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
    const anio = fecha.getFullYear().toString();

    return `${dia}/${mes}/${anio}`;
  }

}


@Component({
  selector: 'view-proyectos-home',
  templateUrl: 'view-proyectos-home.html',
  styleUrls: ['./home.component.css']
})

export class ViewProyectosHome {
  constructor(
    public dialogRef: MatDialogRef<ViewProyectosHome>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDataInformacionproyectoInvestigacion,
    private snack: MatSnackBar,
    public dialog: MatDialog,
    private investigacionService: InvestigacionService,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }


  investigaciones: any = []
  investigacionSeleccionada = 0;

  ngOnInit(): void {
    this.investigacionService.listarInvestigacionesPublicas().subscribe(
      (dato: any) => {
        this.investigaciones = dato;
        if (this.investigaciones.length > 0) {
          this.investigaciones.unshift({ idProyecto: 0, nombreProyecto: 'Todos los proyectos', descripcion: 'Vizualizar todas los proyectos' });
        }
      }, (error) => {

        this.snack.open('Ha ocurrido un error en el sistema !!', 'Aceptar', {
          duration: 3000
        });
      }
    )
  }

  openDialogInformacion(idProyecto: any): void {
    const dialogRef = this.dialog.open(ViewInformacionProyectoInvestigacion, {
      data: { idProyectoInvestigacion: idProyecto },
    });
  }

  //paginador de proyectos
  page_size1: number = 5
  page_number1: number = 1
  page_size_options1 = [5, 10, 20, 50, 100]

  handlePage1(e: PageEvent) {
    this.page_size1 = e.pageSize
    this.page_number1 = e.pageIndex + 1
  }

  public search: string = '';

  onSearch(search: string) {
    this.search = search;
  }

  filtrarInvestigacione(id: any) {
    this.investigacionSeleccionada = id;
  }

}



@Component({
  selector: 'view-catalogo-home',
  templateUrl: 'view-catalogo-home.html',
  styleUrls: ['./home.component.css']
})

export class ViewCatalogoHome {
  constructor(
    public dialogRef: MatDialogRef<ViewCatalogoHome>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDataInformacionproyectoInvestigacion,
    public dialog: MatDialog,
    public VariableService: VariableService,
    private organizacionService: OrganizacionService,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.listarVariablesDifucion(0, 0);
    this.listarOrganizaciones();
    this.listarFamiliasVariables();
  }

  listaCatalogoOrganizacion: any = [];

  listarVariablesDifucion(id: any, idOrganizacion: any) {
    this.VariableService.listarVariablesDifusion(id, idOrganizacion).subscribe(
      (dato: any) => {
        this.listaCatalogoOrganizacion = dato;
        console.log(dato);
        if (this.listaCatalogoOrganizacion.length > 0) {
          this.listaCatalogoOrganizacion.unshift({ idVariable: 0, nombreVariable: 'Todos los datos', siglas: 'Todos', nombreOrganizacion: 'Todos' });
        }
      }
    )
  }

  listarOrganizaciones() {
    this.organizacionService.listar().subscribe(
      res => {
        this.listaOrganizaciones = res;

        if (this.listaOrganizaciones.length > 0) {
          this.listaOrganizaciones.unshift({ idOrganizacion: 0, siglas: 'Variables del sistema' });
        }
        this.organizacionSeleccionado.idOrganizacion = 0;

      },
      err => console.log(err)
    )
  }

  listarFamiliasVariables() {
    this.VariableService.listarFamiliasVariables().subscribe(
      res => {
        this.listaFamiliaVariable = res;
        if (this.listaFamiliaVariable.length > 0) {
          this.listaFamiliaVariable.unshift({ idFamilia: 0, descripcion: 'Todas las familias' });
        }
        this.familiaOrganizacionSeleccionado.idFamilia = 0;
      },
      err => console.log(err)
    )
  }

  variableSeleccionada = 0;

  familiaOrganizacionSeleccionado = {
    idFamilia: 0,
  }

  filtrarCatalogo(id: any) {
    //this.investigacionSeleccionada = -1;
    this.variableSeleccionada = id;
  }

  //paginador de catalogo
  page_size2: number = 5
  page_number2: number = 1
  page_size_options2 = [5, 10, 20, 50, 100]
  public searchOrganizacion: string = '';
  public searchOrganizacionVariable: string = '';
  listaFamiliaVariable: any = []
  listaOrganizaciones: any = []

  handlePage2(e: PageEvent) {
    this.page_size2 = e.pageSize
    this.page_number2 = e.pageIndex + 1
  }

  onFamiliaChange(event: any): void {
    //this.opcionSeleccionadaFamilia = this.listaFamiliaVariable.find((option: FamiliaOrganizacion) => option.idFamilia === event.value);
    //console.log(this.opcionSeleccionada.idFamilia)
    this.listarVariablesDifucion(this.familiaOrganizacionSeleccionado.idFamilia, this.organizacionSeleccionado.idOrganizacion);
  }

  organizacionSeleccionado = {
    idOrganizacion: 0,
  }

  onOrganizacionChange(event: any): void {
    this.listarVariablesDifucion(this.familiaOrganizacionSeleccionado.idFamilia, this.organizacionSeleccionado.idOrganizacion);
  }

  onSearchOrganizacion(search: string) {
    this.searchOrganizacion = search;
  }

}





@Component({
  selector: 'view-informacion-home',
  templateUrl: 'view-informacion-home.html',
  styleUrls: ['./home.component.css']
})

export class ViewInformacionHome {
  constructor(
    public dialogRef: MatDialogRef<ViewInformacionHome>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDataInformacionproyectoInvestigacion,
    public dialog: MatDialog,
    public VariableService: VariableService,
    private appWebService: AppWebService,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.mostrarInformacionVigente();
  }

  informacionEcoAndes = {
    condicionesUso: '',
    derechoReservado: '',
    descripcion: '',
    licenciaUso: ''
  }


  datosIformacionAppWeb: any = [];

  public mostrarInformacionVigente() {
    this.appWebService.mostrarInformacionAppWebVigente().subscribe(
      (data: any) => {

        if (data != null) {
          this.datosIformacionAppWeb = data;
          this.informacionEcoAndes.condicionesUso = this.datosIformacionAppWeb.condicionesUso;
          this.informacionEcoAndes.derechoReservado = this.datosIformacionAppWeb.derechoReservado;
          this.informacionEcoAndes.descripcion = this.datosIformacionAppWeb.descripcion;
          this.informacionEcoAndes.licenciaUso = this.datosIformacionAppWeb.licenciaUso;
        }
      },
      (error) => {
        console.log(error);
        this.datosIformacionAppWeb = [];
      }
    )
  }

}




@Component({
  selector: 'view-explorar-home',
  templateUrl: 'view-explorar-home.html',
  styleUrls: ['./home.component.css']
})

export class ViewExplorarHome {
  constructor(
    public dialogRef: MatDialogRef<ViewExplorarHome>,
    @Inject(MAT_DIALOG_DATA) public data: DialogDataInformacionproyectoInvestigacion,
    public dialog: MatDialog,
    public VariableService: VariableService,
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {

  }

  investigacionDat: any = {
    country: '',
    state: '',
    county: '',
    parroquia: '',
    coordenadax: '',
    coordenaday: '',
  }

  private markers: L.Polygon[] = [];
  //cargar datos en mapa
  dataNominal: any = [];
  dataNumerico: any = [];

  private clearMarkers() {
    this.markers.forEach(marker => marker.removeFrom(this.map));
    this.markers = [];
    this.investigacionDat.country = '';
    this.investigacionDat.state = '';
    this.investigacionDat.county = '';
    this.investigacionDat.parroquia = '';
    this.investigacionDat.coordenadax = '';
    this.investigacionDat.coordenaday = '';
  }

  dataKeys1: string[] = [];
  dataKeys2: string[] = [];
  getCoordenadas1(data: any) {
    this.dataKeys1 = Object.keys(data); // Obtenemos las claves del objeto para usar en el template
  }
  getCoordenadas2(data: any) {
    this.dataKeys2 = Object.keys(data); // Obtenemos las claves del objeto para usar en el template
  }

  tipoChartSeleccionado = {
    idTipoChart: 0,
  }
  onTipoChart(event: any): void {
    this.tipoChart = this.tipoChartSeleccionado.idTipoChart;
    this.generateCharts();
  }

  tipoChart: number = 0;

  chartsContainer = document.getElementById('chartsContainer');

  map!: L.Map;

  selectedMenuItem: number = -1;

  @ViewChild('menuLateral3') menuLateral3!: MatDrawer;

  modelo: any = {
    investigacionGraficoList: []
  };

  ocultarMenu4() {
    this.menuLateral4.close();
  }

  @ViewChild('menuLateral4') menuLateral4!: MatDrawer;

  panelOpenGrafico = false;

  modeloNominal: any = {
    investigacionDatos: []
  };

  listaTipoChart: any = []

  listarTipoChart() {
    this.listaTipoChart.push({ idTipoChart: 0, descripcion: 'Barras' });
    this.listaTipoChart.push({ idTipoChart: 1, descripcion: 'Lineas' });
    this.listaTipoChart.push({ idTipoChart: 2, descripcion: 'Radar' });
    this.listaTipoChart.push({ idTipoChart: 3, descripcion: 'Pastel' });
  }


  generateCharts(): void {
    if (this.tipoChart == 0) {
      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'bar', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              responsive: true,
              scales: {
                xAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Profundidad' // Título del eje X
                  }
                }],
                yAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Valor' // Título del eje Y
                  },
                  ticks: {
                    min: 0, // Valor mínimo del eje Y
                    stepSize: 50,
                  }
                }]
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }
    } else if (this.tipoChart == 1) {

      // segundo chart 

      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'line', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              responsive: true,
              scales: {
                xAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Profundidad' // Título del eje X
                  }
                }],
                yAxes: [{
                  display: true,
                  scaleLabel: {
                    display: true,
                    labelString: 'Valor' // Título del eje Y
                  },
                  ticks: {
                    min: 0, // Valor mínimo del eje Y
                    stepSize: 50,
                  }
                }]
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }

    } else if (this.tipoChart == 2) {

      // segundo chart 

      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);

          this.chartsContainer.appendChild(canvas);


          const chart = new Chart(ctx, {
            type: 'radar', //line //bar//pie//doughnut
            data: {
              labels: labels,
              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: 'rgba(178,194,154,0.5)',
                borderColor: 'rgba(133,143,116)',
                borderWidth: 1
              }]
            },
            options: {
              plugins: {
                filler: {
                  propagate: false
                },
                'samples-filler-analyser': {
                  target: 'chart-analyser'
                }
              }
            }
          });

          chartInstances[canvas.id] = chart;


        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }

    } else {
      //tercer chart
      const investigacionGraficoList = this.modelo.investigacionGraficoList;
      this.chartsContainer = document.getElementById('chartsContainer');

      if (!this.chartsContainer) {
        return;
      }

      // Limpiar gráficos anteriores
      this.chartsContainer.innerHTML = '';

      const chartInstances: { [canvasId: string]: Chart } = {}; // Diccionario para almacenar las instancias de Chart

      investigacionGraficoList.forEach((grafico: any, index: number) => {
        const valores = grafico.valoresLista;
        const labels = valores.map((valor: any) => valor.profundidad);
        const data = valores.map((valor: any) => valor.valor);
        const tipoValor = grafico.tipoValor;

        const canvas = document.createElement('canvas');
        const ctx = canvas.getContext('2d');


        if (ctx) {
          if (!this.chartsContainer) {
            return;
          }
          const divider = document.createElement('hr');
          this.chartsContainer.appendChild(divider);
          const titulo = document.createElement('h4');
          titulo.innerHTML = tipoValor;

          this.chartsContainer.appendChild(titulo);
          this.chartsContainer.appendChild(canvas);

          const randomColors = Array.from({ length: data.length }, () =>
            `rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)})`
          );

          const chart = new Chart(ctx, {
            type: 'pie', //line //bar//pie//doughnut
            data: {
              labels: labels,

              datasets: [{
                label: tipoValor,
                data: data,
                backgroundColor: randomColors,
                borderWidth: 1

              }],


            },
            options: {
              plugins: {
                title: {
                  display: true,
                  text: 'Chart Title',
                },
              }
            }
          });
          chartInstances[canvas.id] = chart;
        }
      });



      const canvasId = 'myCanvasId';
      const chartInstance = chartInstances[canvasId];
      if (chartInstance) {
      }
    }
  }


}


