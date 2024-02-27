package GIDAC.servicios;


import java.util.List;


public interface SolicitudDescargaService<T> {
    public T save(T objeto);
    public T findById(Integer id);
    public List<T> listarPorEstado(String estado);
    public List<T> listarPorEstadoAndIdProyecto(String estado, Integer idProyecto);
}
