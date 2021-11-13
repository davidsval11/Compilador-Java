
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Editor extends JFrame implements ActionListener {

    private MenuBar menuBar;
    private Menu menuArc;
    private Menu menuEdi;
    private Menu menuAna;
    private Menu menuEje;
    private Menu menuAyu;
    private MenuItem menuArcAbr;
    private MenuItem menuArcGua;
    private MenuItem menuArcSal;
    private MenuItem menuEdiCop;
    private MenuItem menuEdiCor;
    private MenuItem menuEdiPeg;
    private MenuItem menuEdiSel;
    private MenuItem menuAnaCom;
    private MenuItem menuEjeCor;
    private MenuItem menuAyuAyu;
    private MenuItem menuAyuAce;

    private JPanel status;
    private JPanel panelpp;

    private JLabel statusMsg1;
    private JLabel statusMsg2;

    private JScrollPane parea1;    
    private JScrollPane parea3;

    private static JTextArea area1;    
    public static JTable tabla;
    public static DefaultTableModel modelo;
    public static String[] columnas = new String[]{"TOKEN", "TIPO"};

    private JFileChooser chooser;
    private File selFile;

    private String[][] simbolos;
    private String[] var;
    private int[] tipo;
    private String[][] valor;
    private char[] oprd;
    private int contd;
    private int contt;
    private boolean valid;

    boolean essalvar = true;
    String nombre = " ";
    PalabraR pr = new PalabraR();
    

//******************************************************************************
//******************************************************************************
    public Editor() {
        setSize(new Dimension(640, 480));
        getContentPane().setLayout(new BorderLayout());
        setBackground(Color.black);
        //Barra de menu
        menuBar = new MenuBar();

        //Menu Archivos
        menuArc = new Menu();
        menuArcSal = new MenuItem();
        menuArcAbr = new MenuItem();
        menuArcGua = new MenuItem();
        menuArc.setLabel("Archivo");
        menuArcAbr.setLabel("Abrir");
        menuArcGua.setLabel("Guardar");
        menuArcSal.setLabel("Salir");
        menuArcSal.addActionListener(this);
        menuArcAbr.addActionListener(this);
        menuArcGua.addActionListener(this);
        menuArc.add(menuArcAbr);
        menuArc.add(menuArcGua);
        menuArc.add(menuArcSal);
        menuArc.insertSeparator(2);

        //menu Editor
        menuEdi = new Menu();
        menuEdiCop = new MenuItem();
        menuEdiCor = new MenuItem();
        menuEdiPeg = new MenuItem();
        menuEdiSel = new MenuItem();
        menuEdi.setLabel("Editar");
        menuEdiCop.setLabel("Copiar");
        menuEdiCor.setLabel("Cortar");
        menuEdiPeg.setLabel("Pegar");
        menuEdiSel.setLabel("Seleccionar Todo");
        menuEdiCop.addActionListener(this);
        menuEdiCor.addActionListener(this);
        menuEdiPeg.addActionListener(this);
        menuEdiSel.addActionListener(this);
        menuEdi.add(menuEdiCop);
        menuEdi.add(menuEdiCor);
        menuEdi.add(menuEdiPeg);
        menuEdi.add(menuEdiSel);

        //menu Analizar
        menuAna = new Menu();
        menuAnaCom = new MenuItem();
        menuAna.setLabel("Analizador Lexico");
        menuAnaCom.setLabel("Compilar");
        menuAnaCom.addActionListener(this);
        menuAna.add(menuAnaCom);

        //menu Ejecutar
        menuEje = new Menu();
        menuEjeCor = new MenuItem();
        menuEje.setLabel("Ejecutar");
        menuEjeCor.setLabel("Correr");
        menuEjeCor.addActionListener(this);
        menuEje.add(menuEjeCor);

        //menu Ayuda
        menuAyu = new Menu();
        menuAyuAyu = new MenuItem();
        menuAyuAce = new MenuItem();
        menuAyu.setLabel("Ayuda");
        menuAyuAyu.setLabel("Ayuda");
        menuAyuAce.setLabel("Acerca de...");
        menuAyuAyu.addActionListener(this);
        menuAyuAce.addActionListener(this);
        menuAyu.add(menuAyuAyu);
        menuAyu.add(menuAyuAce);

        //Agregar los elementos creados
        menuBar.add(menuArc);
        menuBar.add(menuEdi);
        menuBar.add(menuAna);
        menuBar.add(menuEje);
        menuBar.add(menuAyu);

        //Panel de control
        status = new JPanel();
        status.setLayout(new BorderLayout());
        statusMsg1 = new JLabel("Estado: ");
        statusMsg2 = new JLabel();
        status.add(statusMsg1, BorderLayout.WEST);
        status.add(statusMsg2, BorderLayout.CENTER);
        getContentPane().add(status, BorderLayout.SOUTH);

        //Creacion area de textos
        area1 = new JTextArea();        

        modelo = new DefaultTableModel(null, columnas);
        tabla = new JTable(modelo);
        
        parea1 = new JScrollPane(area1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);        
        parea3 = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Panel de principal
        panelpp = new JPanel();
        panelpp.setLayout(new GridLayout(1, 1));
        panelpp.add(parea1);        
        panelpp.add(parea3);

        getContentPane().add(panelpp, BorderLayout.CENTER);

        setTitle("Compilador - Pseudocodigo");
        setMenuBar(menuBar);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void WindowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuArcAbr) {
            cargarDatos();
        }
        if (e.getSource() == menuArcSal) {
            dispose();
        }
        if (e.getSource() == menuArcGua) {
            guardarDatos();
        }
        if (e.getSource() == menuEdiCop) {
            area1.copy();
        }
        if (e.getSource() == menuEdiCor) {
            area1.cut();
        }
        if (e.getSource() == menuEdiPeg) {
            area1.paste();
        }
        if (e.getSource() == menuEdiSel) {
            area1.selectAll();
        }
        if (e.getSource() == menuAnaCom) {
            statusMsg2.setText("Analizando");

            analizar();
            statusMsg2.setText("Analizado");
            menuArcGua.setEnabled(true);
            essalvar = false;
        }
        if (e.getSource() == menuAyuAyu) {
            ayuAyuda();
        }
        if (e.getSource() == menuAyuAce) {
            ayuAcerca();
        }
        if (e.getSource() == menuEjeCor) {
            ejeCor();
        }
    }

    public void ejeCor() {
        for (pr.consec_tabla = 0; pr.consec_tabla < tabla.getRowCount(); pr.consec_tabla++) {
            reader(pr.consec_tabla);
        }
    }

    public void ayuAyuda() {
        JOptionPane.showMessageDialog(null, "Dirijase al manual de referencia");
    }

    public void ayuAcerca() {
        JOptionPane.showMessageDialog(null, "Version Beta 1.0 \n\tElaborado por:\nOscar Valdés");
    }

    public void cargarDatos() {
        chooser = new JFileChooser();
        int sel = chooser.showOpenDialog(area1);
        if (sel == chooser.APPROVE_OPTION) {
            selFile = chooser.getSelectedFile();
            statusMsg2.setText("abriendo " + selFile.getAbsolutePath());
            String linea;
            try {
                FileReader fr = new FileReader(selFile.getAbsolutePath());
                BufferedReader entArch = new BufferedReader(fr);
                linea = entArch.readLine();
                area1.setText("");                
                while (linea != null) {
                    area1.setText(area1.getText() + linea + "\n");
                    linea = entArch.readLine();
                }
                entArch.close();
            } catch (IOException e) {
            }
        }
    }

    public void guardarDatos() {
        String Text = "";
        try {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.showSaveDialog(this);
            File Guardar = fc.getSelectedFile();
            if (Guardar != null) {
                nombre = fc.getSelectedFile().getName();
                FileWriter Guardx = new FileWriter(Guardar);
                Guardx.write(area1.getText());
                Guardx.close();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static String texto() {
        return area1.getText();
    }

    public void analizar() {
        Texto.limpiarMatriz();
        Texto.almacenarPalabras();
        Texto.ImprimirMtabla();
        
        
    }
    
    public void reader( int x) {
        String[] lineas_cond = null;
        
        boolean ejecutar = true;
        for(Condicionales a: pr.sent_condicionales) {
            lineas_cond = a.getLineas().split(",");
            
            for (int i = 0; i < lineas_cond.length; i++) {
                if (Integer.parseInt(tabla.getValueAt(x, 1).toString()) == Integer.parseInt(lineas_cond[i])) {
                    ejecutar = a.getEjecutar();
                    break;
                }
            }            
        }
        
        if (ejecutar) {
            int key_palabra = pr.ValidarPalabraR(tabla.getValueAt(x, 0).toString());
        
            if (key_palabra == 26) {
                pr.DefinirVariable(Integer.parseInt(tabla.getValueAt(x, 1).toString()));
            }

            if (key_palabra >= 1 && key_palabra <= 4) {
                pr.Ope_matematicas(Integer.parseInt(tabla.getValueAt(x, 1).toString()),tabla.getValueAt(x, 0).toString());
            }

            if (key_palabra == 5) {
                pr.Escriba(Integer.parseInt(tabla.getValueAt(x, 1).toString()));
            }

            if (key_palabra == 6) {
                pr.Lea(Integer.parseInt(tabla.getValueAt(x, 1).toString()));
            }

            if ((key_palabra >= 7 && key_palabra <= 11) || (key_palabra >= 27 && key_palabra <= 28)) {
                pr.Ope_relacional(Integer.parseInt(tabla.getValueAt(x, 1).toString()),tabla.getValueAt(x, 0).toString());
            }

            if (key_palabra == 15) {
                pr.Si(Integer.parseInt(tabla.getValueAt(x, 1).toString()));
            }
            
            if (key_palabra == 14) {
                pr.MasE(Integer.parseInt(tabla.getValueAt(x, 1).toString()));
            }
            
            if (key_palabra == 12) {
                pr.Mientras(Integer.parseInt(tabla.getValueAt(x, 1).toString()), x);
            }
        }
        
    }

    public static void main(String[] args) {
        new Editor();
    }
}
