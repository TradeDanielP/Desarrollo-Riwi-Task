package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;
import java.net.Inet4Address;

public class EspecialidadController {

    public static void create(){

        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String nombreEspecialidad = JOptionPane.showInputDialog("Ingresa el nombre de la especialidad");
        String descripcionEspecialidad = JOptionPane.showInputDialog("Escribe una breve descripcion de la especialidad");

        Especialidad objEspecialidad = new Especialidad();
        objEspecialidad.setNombre(nombreEspecialidad);
        objEspecialidad.setDescripcion(descripcionEspecialidad);

        objEspecialidad = (Especialidad) objEspecialidadModel.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null,objEspecialidad.toString());

    }

    public static void getAll(){

        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = "LISTA DE ESPECIALIDADES \n";

        for(Object especialidad : objModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) especialidad;
            listEspecialidades += objEspecialidad.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listEspecialidades);

    }

    public static String getAllString(){

        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = "LISTA DE ESPECIALIDADES \n";

        for(Object especialidad : objModel.findAll()){
            Especialidad objEspecialidad = (Especialidad) especialidad;
            listEspecialidades += objEspecialidad.toString() + "\n";
        }

        return listEspecialidades;
    }

    public static void update(){

        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listEspecialidades = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades+"\nIngresa el id de la especialidad que desea editar"));

        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"No se encontro la especialidad");
        } else {

            String nombre = JOptionPane.showInputDialog(null,"ingresa el nuevo nombre",objEspecialidad.getNombre());
            String descripcion = JOptionPane.showInputDialog(null,"Ingresa la nueva descripcion",objEspecialidad.getDescripcion());

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objEspecialidadModel.update(objEspecialidad);
        }
    }

    public static void delete(){

        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades+"\n ingresa el id de la especialidad que desea eliminar"));

        Especialidad objEspecialidad = objModel.findById(idDelete);

        int confirm = 1;

        if (objEspecialidad == null){
            JOptionPane.showMessageDialog(null,"No se encontro el id de la especialidad");
        } else {
            confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro de eliminar esta especialidad?\nEsta accion no se puede deshacer");

            if (confirm == 0) objModel.delete(objEspecialidad);
        }
    }

    public static void buscarMedicoPorEspecialidad(){

        EspecialidadModel objModel = new EspecialidadModel();

        String listEspecialidades = getAllString();

        int idEspecialidad = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidades + "\n Digite el ID de la especialidad en la que desea filtrar los medicos"));

        if (objModel.buscarMedicosPorEspecialidad(idEspecialidad).isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay ningun medico con esta Especialidad");
        } else {
            JOptionPane.showMessageDialog(null,objModel.buscarMedicosPorEspecialidad(idEspecialidad).toString());
        }

    }

}
