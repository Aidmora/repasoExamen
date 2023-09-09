package UserInterface;

import java.awt.*;
import LecturaArchivos.LecturaArchivo;
import LecturaArchivos.MNCargarArchivos;
import BusinessLogic.Entities.MNUsuario;
import BusinessLogic.Facade.MNUsuarioBL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import FrameWork.AppExceptionAriel;

public class PanelTabla extends JPanel {
    
    private JTable mnTabla;
    private DefaultTableModel mnTableModel;
    private String[] mnHeader;
    private String[][] mnData;
    private List<String[]> mnDataList;
    private LecturaArchivo mnLecturaArchivo;
    private MNCargarArchivos mnCargarArchivos;
    private final String RUTA = "MNCoordenadas";

    PanelTabla() throws IOException, AppExceptionAriel, SQLException {
        mnSetCustomization();
        mnInitComponents();
        mnAddComponents();
        mnGenerarTabla();

    }

    private void mnSetCustomization() {
        setBackground(Color.MAGENTA); 
        setLayout(new FlowLayout());
    }

    private void mnInitComponents() throws IOException, AppExceptionAriel, SQLException {
        mnHeader = new String[]{"Usuario", "Tipo Arsenal", "Coord.", "Arsenal", "Día", "Hora"};
        mnDataList = new ArrayList<>();
        mnLecturaArchivo = new LecturaArchivo();
        mnLecturaArchivo.LeerArchivos(RUTA);
        mnCargarArchivos = new MNCargarArchivos(mnLecturaArchivo);
        // mnCargarArchivos.mnCargar();

    }

    private void mnAddComponents() {

    }
    
    private void mnGenerarTabla() throws AppExceptionAriel {
        String[] mnFila;
        MNUsuarioBL mnUsuarrioBL = new MNUsuarioBL();
        List<MNUsuario> listaUsuarios = mnUsuarrioBL.mnGetAll();
        
        // for (String mnData : mnFila) {
            for (int i = 0; i < mnLecturaArchivo.mnCoordenadas.size(); i++) {
                int  mnLetra =  mnLecturaArchivo.mnArsenal.get(i).length();
                System.out.println(mnLetra);
                for (int j = 0; j < mnLetra; j++) {
                    String mnArs = " ";
                    System.out.println("indice i: " + i);
                    System.out.println("indice j: " + j);
                    System.out.println("palabra: " +  mnLecturaArchivo.mnArsenal.get(i));
                    if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'a') mnArs = "Aéreo";
                    if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'b') mnArs = "Marítimo";
                    if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'c') mnArs = "Terrestre";
                    if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'd') mnArs = "Aéreo";
                    if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 't') mnArs = "Terrestre";

                    mnFila = new String[6]; 
                    mnFila[0] = listaUsuarios.get(i%3).getNombreUsuario();
                    System.out.println(mnArs);
                    mnFila[1] = mnArs;
                    mnFila[2] = mnLecturaArchivo.mnCoordenadas.get(i).substring(0, 2);
                    mnFila[3] = mnLecturaArchivo.mnArsenalNombre.get(i);
                    mnFila[4] = mnLecturaArchivo.mnHorarioDia.get(i);
                    mnFila[5] = mnLecturaArchivo.mnHorario.get(i);
                    mnDataList.add(mnFila);
            }
        }
        
        
        mnData = new String[mnDataList.size()][6];
        mnTableModel = new DefaultTableModel(mnData, mnHeader);
        mnTableModel.setRowCount(0);
        
        for (int i = 0; i < mnDataList.size(); i++) {
            mnTableModel.addRow(mnDataList.get(i));
        }

        int rowHeight = 30;
        Font fuente = new Font("Arial", Font.PLAIN, 16);
        Font fuente2 = new Font("Arial", Font.BOLD, 16);

        mnTabla = new JTable(mnTableModel);
        mnTabla.setShowHorizontalLines(true);
        mnTabla.setGridColor(Color.LIGHT_GRAY);
        mnTabla.setRowSelectionAllowed(true);
        mnTabla.setColumnSelectionAllowed(false);
        mnTabla.setPreferredScrollableViewportSize(new Dimension(1000, 400));
        mnTabla.setFillsViewportHeight(true);

        // mnTabla.setRowHeight(rowHeight);
        // mnTabla.getTableHeader().setFont(fuente2); 
        // mnTabla.setFont(fuente);

        add(new JScrollPane(mnTabla));

    }
    // mnTableModel.addRow(mnFila[mnIndex]);
}
