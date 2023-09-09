package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BusinessLogic.BibliotecaBL;
import BusinessLogic.Entities.Biblioteca;
import FrameWork.AppExceptionAriel;
import UserInterface.CustomerControl.ArielButton;

public class BibliotecaMenu extends JFrame implements ActionListener{
    private BibliotecaBL  bibliotecaBL  = null;
    private Biblioteca   biblioteca    = null;
    private JPanel pnlTabla   = new JPanel();
    public BibliotecaSplash bibliotecaSplash= new BibliotecaSplash();
    public ArielButton mostrarTabla;
    private DefaultTableModel tableModel;
    private int nroRegistros = 3; 
    private int pagActual = 1; 
    private List<Biblioteca> biblioData; 
    private JTable table; 
    private JPanel panelBotonPaginado;
public BibliotecaMenu() throws AppExceptionAriel {
    bibliotecaBL = new BibliotecaBL();
    biblioData = bibliotecaBL.getAllData();
    setTitle("Biblioteca");
    setGridBagLayout();
    mostrarTabla=new ArielButton("/UserInterface/Img/libroAbierto3.png");
    add(mostrarTabla);
    mostrarTabla.addActionListener(new ActionListener() {
        @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==mostrarTabla){
            mostrarTabla.setVisible(false); 
            try {
                mostrarInfo();
            } catch (AppExceptionAriel e1) {
                
                e1.printStackTrace();
            }
        }
    }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
}
private void showTable() throws AppExceptionAriel {
    String[] header = {"Comentario", "Libro", "Autor"};
    Object[][] data = new Object[bibliotecaBL.getAllData().size()][3];  
    int index = 0;
    for(Biblioteca bli : bibliotecaBL.getAllData()) {
        data[index][0] = bli.getReseña();
        data[index][1] = bli.getNombreLibro();
        data[index][2] = bli.getNombreAutor();
        index++;
    }
    
    JTable table  = new JTable(data, header);
    table.setShowHorizontalLines(true);
    table.setGridColor(Color.lightGray);
    table.setRowSelectionAllowed(true);
    table.setColumnSelectionAllowed(false);

    table.setPreferredScrollableViewportSize(new Dimension(650, 150));
    table.setFillsViewportHeight(true);

    pnlTabla.removeAll();
    pnlTabla.add(table);
    JScrollPane scrollPane = new JScrollPane(table);
    pnlTabla.add(scrollPane);
}

    public void setGridBagLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridy = 2;       gbc.gridx=0;
        gbc.gridwidth=3;                   
        gbc.ipady = 250;                   
        gbc.ipadx = 750;                   
        pnlTabla.add(new Label("Loading data..."));
        pnlTabla.setBackground(Color.WHITE);
        add(pnlTabla, gbc);
        
        
    }
    public void mostrarMenu(){
        bibliotecaSplash.mostrarPantallazo();
        setVisible(true); 
    }
    private void mostrarInfo() throws AppExceptionAriel {
        showTable();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}

