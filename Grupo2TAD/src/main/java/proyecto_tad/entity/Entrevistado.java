package proyecto_tad.entity;
// Generated 11-may-2018 11:08:27 by Hibernate Tools 4.3.1



/**
 * Entrevistado generated by hbm2java
 */
public class Entrevistado  implements java.io.Serializable {


     private Integer id;
     private String dni;
     private String nombre;

    public Entrevistado() {
    }

    public Entrevistado(String dni, String nombre) {
       this.dni = dni;
       this.nombre = nombre;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDni() {
        return this.dni;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " ("+ dni +")";
    }

}


