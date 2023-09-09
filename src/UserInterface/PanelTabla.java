package UserInterface;

import java.awt.*;
import LecturaArchivos.MNLecturaArchivo;
import LecturaArchivos.MNCargarArchivos;
import BusinessLogic.MNUsuarioBL;
import BusinessLogic.Entities.MNUsuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import FrameWork.AppException;

public class PanelTabla extends JPanel {
    
    private JTable mnTabla;
    private DefaultTableModel mnTableModel;
    private String[] mnHeader;
    private String[][] mnData;
    private List<String[]> mnDataList;
    private MNLecturaArchivo mnLecturaArchivo;
    private MNCargarArchivos mnCargarArchivos;
    private final String RUTA = "MNCoordenadas";

    /*
     * Constructor de la clase PanelTabla
     */
    PanelTabla() throws IOException, AppException, SQLException {
        mnSetCustomization();
        mnInitComponents();
        mnGenerarTabla();

    }

    /*
     * mnSetCustomization: establece las propiedades que van a tener los diferentes componentes
     * incluido el propio PanelTabla
     */
    private void mnSetCustomization() {
        // setBackground(Color.MAGENTA); 
        setLayout(new FlowLayout());
    }

    /*
     * mnInitComponents: inicializa el panel y los componentes necesarios para su funcionamiento
     */
    private void mnInitComponents() throws IOException, AppException, SQLException {
        mnHeader = new String[]{"Usuario", "Tipo Arsenal", "Coord.", "Arsenal", "Día", "Hora"};
        mnDataList = new ArrayList<>();
        mnLecturaArchivo = new MNLecturaArchivo();
        mnLecturaArchivo.LeerArchivos(RUTA);
        mnCargarArchivos = new MNCargarArchivos(mnLecturaArchivo);
        mnCargarArchivos.mnCargar();

    }

   
    /*
     * mnGenerarTabla: se encarga de generar la tabla con la información extraída por el archivo csv
     */
    private void mnGenerarTabla() throws AppException {
        String[] mnFila;
        MNUsuarioBL mnUsuarrioBL = new MNUsuarioBL();
        List<MNUsuario> listaUsuarios = mnUsuarrioBL.mnGetAll();
        
        // for (String mnData : mnFila) {
        for (int i = 0; i < mnLecturaArchivo.mnCoordenadas.size(); i++) {
            int  mnLetra =  mnLecturaArchivo.mnArsenal.get(i).length();
            for (int j = 0; j < mnLetra; j++) {
                String mnArs = " ";
                if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'a') mnArs = "Aéreo";
                if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'b') mnArs = "Marítimo";
                if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'c') mnArs = "Terrestre";
                if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 'd') mnArs = "Aéreo";
                if (mnLecturaArchivo.mnArsenal.get(i).charAt(j) == 't') mnArs = "Terrestre";

                mnFila = new String[6]; 
                mnFila[0] = listaUsuarios.get(i%3).getNombreUsuario();
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

}
