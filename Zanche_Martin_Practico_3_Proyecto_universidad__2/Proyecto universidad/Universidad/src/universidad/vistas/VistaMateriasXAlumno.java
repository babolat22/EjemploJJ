/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.vistas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.AlumnoData;
import modelo.Conexion;
import modelo.Cursada;
import modelo.CursadaData;
import modelo.Materia;
import modelo.MateriaData;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Martin
 */
public class VistaMateriasXAlumno extends javax.swing.JInternalFrame {
 private DefaultTableModel modelo;
 private ArrayList<Cursada> listaCursada;
 private ArrayList<Materia> listaMateria;
 private CursadaData cd;
 private MateriaData md;
 private Conexion con;
 private AlumnoData ad;
 private ArrayList <Alumno> listaAlumno;
 

    /**
     * Creates new form VistaMateriasXAlumno
     */
    public VistaMateriasXAlumno() {
        initComponents();
        
        AutoCompleteDecorator.decorate(cbAlumnos);
        
        con =new Conexion();
        modelo= new DefaultTableModel();
       
        cd= new CursadaData(con);
        listaCursada=(ArrayList)cd.obtenerCursadas();
        
        md=new MateriaData(con);
        listaMateria=(ArrayList<Materia>)md.obtenerMaterias();
        
        ad= new AlumnoData(con);
        listaAlumno=(ArrayList<Alumno>)ad.obtenerAlumnos();
           
        borrarFilasTabla();        
       cargarAlumnos();
       armarCabaceraTabla();
       cargarDatos();
        
    }
    private void cargarAlumnos(){
        for (Alumno item:listaAlumno)
        cbAlumnos.addItem(item);
       }
   
    public void armarCabaceraTabla(){
    ArrayList<Object> columns= new ArrayList<>();
    columns.add("ID");
    columns.add("Nombre");
    columns.add("Nota");
    columns.add("Periodo");
    columns.add("Responsable");
    for (Object it:columns)
    {
        modelo.addColumn(it);
    }
    tblMaterias.setModel(modelo);
    }
    
    public void borrarFilasTabla(){
     int a=modelo.getRowCount()-1 ;
      for (int i=a;i>=0;i--){
     modelo.removeRow(i);
     }
    }
    
    private void cargarDatos(){
     borrarFilasTabla();
     Alumno alu=(Alumno)cbAlumnos.getSelectedItem();
     
    for (Cursada a:listaCursada){
      if(a.getAlumno().getId()==alu.getId()){
       modelo.addRow(new Object[]{a.getMateria().getId(),a.getMateria().getNombre(),a.getNota(),a.getMateria().getPeriodo(),a.getMateria().getResponsable()});
      }
     }  
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMatxAlumno = new javax.swing.JLabel();
        lblAlumno = new javax.swing.JLabel();
        cbAlumnos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaterias = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        lblMatxAlumno.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblMatxAlumno.setText("Materias por alumno");

        lblAlumno.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblAlumno.setText("Alumno");

        cbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnosActionPerformed(evt);
            }
        });

        tblMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblMaterias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 197, Short.MAX_VALUE)
                .addComponent(lblAlumno)
                .addGap(28, 28, 28)
                .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(lblMatxAlumno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMatxAlumno)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlumno)
                    .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnosActionPerformed
        // TODO add your handling code here:
        borrarFilasTabla();
        cargarDatos();
    }//GEN-LAST:event_cbAlumnosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alumno> cbAlumnos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlumno;
    private javax.swing.JLabel lblMatxAlumno;
    private javax.swing.JTable tblMaterias;
    // End of variables declaration//GEN-END:variables
}