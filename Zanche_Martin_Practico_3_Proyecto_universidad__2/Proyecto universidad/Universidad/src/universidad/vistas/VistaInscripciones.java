/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.vistas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelo.Alumno;
import modelo.AlumnoData;
import modelo.Conexion;
import modelo.Cursada;
import modelo.CursadaData;
import modelo.Materia;
import modelo.MateriaData;
import modelo.ModeloCeldasEditables;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Martin
 */
public class VistaInscripciones extends javax.swing.JInternalFrame {
 //private DefaultTableModel modelo;
 private ArrayList<Cursada> listaCursada;
 private ArrayList<Materia> listaMateria;
 private CursadaData cd;
 private MateriaData md;
 private Conexion con;
 private AlumnoData ad;
 private ArrayList <Alumno> listaAlumno;
 private ModeloCeldasEditables modelo;

 //private ArrayList<int> materiasDelAlumno;
    /**
     * Creates new form VistaInscripciones
     */
    public VistaInscripciones() {
        initComponents();
            
        AutoCompleteDecorator.decorate(cbAlumnos);
        
        con =new Conexion();
      //  modelo= new DefaultTableModel();
          modelo= new ModeloCeldasEditables();
       
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
    columns.add("ID_Materia");
    columns.add("Nombre");
    columns.add("Nota");
    columns.add("Periodo");
    columns.add("Responsable");
    columns.add("Inscripto");
    columns.add("Codigo Cursada");
     
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
     
    if(jrbInscriptas.isSelected()){ 
    for (Cursada a:listaCursada){
      if(a.getAlumno().getId()==alu.getId()){
       modelo.addRow(new Object[]{a.getMateria().getId(),a.getMateria().getNombre(),a.getNota(),a.getMateria().getPeriodo(),a.getMateria().getResponsable(),true,a.getId()});
        }//fin if
       } //fin for       
      }//fin if
    
      else { 
            if (jrbNoInscriptas.isSelected()){
       //creo lista de id_materias del alumno 
       ArrayList<Integer> materiasDelAlumno=new ArrayList<>() ;              
              for(Cursada a:listaCursada){ 
                 if(a.getAlumno().getId()==alu.getId()){
                   materiasDelAlumno.add(a.getMateria().getId());
                 }//fin if
           }//fin for
           //Recorro la lista de id_materia y consulto si contiene una materia de la lista materia
            for (Materia m:listaMateria){
              if(!materiasDelAlumno.contains(m.getId())){
                  modelo.addRow(new Object[]{m.getId(),m.getNombre(),"",m.getPeriodo(),m.getResponsable(),false});
                }
            }
            }
        } //fin else 
    }//fin cargar datos  
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIncripciones = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbAlumnos = new javax.swing.JComboBox<>();
        lblMaterias = new javax.swing.JLabel();
        jrbInscriptas = new javax.swing.JRadioButton();
        jrbNoInscriptas = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaterias = new javax.swing.JTable();
        btnIncribir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        lblIncripciones.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblIncripciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIncripciones.setText("Inscripcion a materias");
        lblIncripciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel2.setText("Alumnos");

        cbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAlumnosActionPerformed(evt);
            }
        });

        lblMaterias.setFont(new java.awt.Font("Ubuntu", 1, 20)); // NOI18N
        lblMaterias.setText("Listado de materias");

        jrbInscriptas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jrbInscriptas.setSelected(true);
        jrbInscriptas.setText("Incriptas");
        jrbInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbInscriptasActionPerformed(evt);
            }
        });

        jrbNoInscriptas.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jrbNoInscriptas.setText("No inscriptas");
        jrbNoInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbNoInscriptasActionPerformed(evt);
            }
        });

        tblMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "Título 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMaterias);

        btnIncribir.setText("Inscribir");
        btnIncribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncribirActionPerformed(evt);
            }
        });

        btnAnular.setText("Anular inscripcion");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(jrbInscriptas)
                                .addGap(77, 77, 77)
                                .addComponent(jrbNoInscriptas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(216, 216, 216)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaterias)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(lblIncripciones, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 179, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(btnIncribir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnAnular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIncripciones)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addComponent(lblMaterias)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbInscriptas)
                    .addComponent(jrbNoInscriptas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnular)
                    .addComponent(btnIncribir))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrbInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbInscriptasActionPerformed
        // TODO add your handling code here:
        jrbNoInscriptas.setSelected(false);
        borrarFilasTabla();
        cargarDatos();
    }//GEN-LAST:event_jrbInscriptasActionPerformed

    private void cbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAlumnosActionPerformed
        // TODO add your handling code here:
        borrarFilasTabla();
        cargarDatos();
    }//GEN-LAST:event_cbAlumnosActionPerformed

    private void jrbNoInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbNoInscriptasActionPerformed
        // TODO add your handling code here:
        jrbInscriptas.setSelected(false);
        borrarFilasTabla();
        cargarDatos();
    }//GEN-LAST:event_jrbNoInscriptasActionPerformed

    private void btnIncribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncribirActionPerformed
        // TODO add your handling code here:
        boolean seInscribio=false;
        for (int i = 0; i < tblMaterias.getRowCount(); i++){
           Cursada cur;
           Alumno alu1;
           Materia mat1;
            if (jrbNoInscriptas.isSelected() && (boolean)tblMaterias.getValueAt(i, 5)){
                        alu1=(Alumno)cbAlumnos.getSelectedItem();
                        mat1= md.buscarMateria((int)tblMaterias.getValueAt(i, 0));
                        cur= new Cursada(alu1,mat1);
               try {
                   cd.guardarCursada(cur);
                   seInscribio=true;               
               } catch (SQLException ex) {
                   Logger.getLogger(VistaInscripciones.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         } 
         if (seInscribio){
          JOptionPane.showMessageDialog(null, "Se inscribio alumno a materias seleccionadas", "Info", JOptionPane.WARNING_MESSAGE);
         listaCursada.clear();
         listaCursada=(ArrayList)cd.obtenerCursadas();
         borrarFilasTabla();
         cargarDatos();
         }else JOptionPane.showMessageDialog(null, "No inscribio al alumno a ninguna materia", "Info", JOptionPane.WARNING_MESSAGE); 
    }//GEN-LAST:event_btnIncribirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:    int largo=listaCursadaInsertar.size();
       boolean seAnulo=false;
        for (int i = 0; i < tblMaterias.getRowCount(); i++){
                if (jrbInscriptas.isSelected() && !(boolean)tblMaterias.getValueAt(i, 5)){
                   int id= (int)tblMaterias.getValueAt(i, 6);
               try {
                   cd.borrarCursadaPorIdDeCursada(id);
                 //  cd.guardarCursada(cur);
                   seAnulo=true;                                
               } catch (SQLException ex) {
                   Logger.getLogger(VistaInscripciones.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
         } 
         if (seAnulo){
          JOptionPane.showMessageDialog(null, "Se anulo inscripcion del alumno a materias seleccionadas", "Info", JOptionPane.WARNING_MESSAGE);
         listaCursada.clear();
         listaCursada=(ArrayList)cd.obtenerCursadas();
         borrarFilasTabla();
         cargarDatos();
         }else JOptionPane.showMessageDialog(null, "No se anulo inscripcion del alumno en ninguna materia", "Info", JOptionPane.WARNING_MESSAGE); 
   
    }//GEN-LAST:event_btnAnularActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnIncribir;
    private javax.swing.JComboBox<Alumno> cbAlumnos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbInscriptas;
    private javax.swing.JRadioButton jrbNoInscriptas;
    private javax.swing.JLabel lblIncripciones;
    private javax.swing.JLabel lblMaterias;
    private javax.swing.JTable tblMaterias;
    // End of variables declaration//GEN-END:variables
}
