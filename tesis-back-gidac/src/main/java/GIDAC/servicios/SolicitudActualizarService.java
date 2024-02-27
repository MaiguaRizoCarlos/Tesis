package GIDAC.servicios;


import java.util.List;


public interface SolicitudActualizarService<T> {
    public T save(T objeto);
    public T findById(Integer id);
    public List<T> listarPorEstadoAndIdProyecto(String estado, Integer idProyecto);
}
