package controller;

import database.ConfigDB;
import entity.Autor;
import model.AutorModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutorController {

    public static  void create(){
        AutorModel objAutorModel = new AutorModel();

        String nameAutor = JOptionPane.showInputDialog("Ingresa el nombre del autor");
        String nationalityAutor = JOptionPane.showInputDialog("Ingresa la nacionalidad");

        Autor objAutor = new Autor();
        objAutor.setName(nameAutor);
        objAutor.setNationality(nationalityAutor);

        objAutor = (Autor) objAutorModel.insert(objAutor);

        JOptionPane.showMessageDialog(null,objAutor.toString());

    }

    public static void getAll(){
        AutorModel objModel = new AutorModel();

        String listAutores = "LISTA DE AUTORES \n";

        for (Object autor : objModel.findAll()){
            Autor objAutor = (Autor) autor;
            listAutores += objAutor.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null,listAutores);

    }

    public static String getAllString(){
        AutorModel objModel = new AutorModel();

        String listAutores = "LISTA DE AUTORES \n";

        for (Object autor : objModel.findAll()){
            Autor objAutor = (Autor) autor;
            listAutores += objAutor.toString() + "\n";
        }

        return listAutores;
    }

    public static void update(){
        AutorModel objAutorModel = new AutorModel();

        String listCoders = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listCoders+"\nIngresa el Id del autor que desea editar"));

        Autor objAutor = objAutorModel.findById(idUpdate);

        if (objAutor == null){
            JOptionPane.showMessageDialog(null,"No se encontro el autor");
        } else {

            String name = JOptionPane.showInputDialog(null,"Ingresa el nuevo nombre",objAutor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Ingresa la nacionalidad",objAutor.getNationality());

            objAutor.setName(name);
            objAutor.setNationality(nationality);

            objAutorModel.update(objAutor);
        }

    }

    public static void delete(){

        AutorModel objModel = new AutorModel();

        String listAutors = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAutors + "\n Ingresa el ID del autor que desea eliminar:"));

        Autor objAutor = objModel.findById(idDelete);

        int confirm = 1;

        if (objAutor == null){
            JOptionPane.showMessageDialog(null,"No se encontró el Autor");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que desea eliminar el Autor? \n" + objAutor.toString());

            if (confirm == 0) objModel.delete(objAutor);

        }
    }



}
