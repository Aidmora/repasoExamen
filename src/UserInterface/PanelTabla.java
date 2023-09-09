package UserInterface;

import java.awt.*;
import LecturaArchivos.LecturaArchivo;
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
    private LecturaArchivo lecturaArchivo;
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
        lecturaArchivo = new LecturaArchivo();
        lecturaArchivo.LeerArchivos(RUTA);
    }

    private void mnAddComponents() {


    }
    
    private void mnGenerarTabla() {
        String[] mnFila;
        int mnIndex = 0;
        // for (String mnData : mnFila) {
        System.out.println(lecturaArchivo.mnCoordenadas.size() + " : tamano coordenadas");
        for (int i = 0; i < lecturaArchivo.mnCoordenadas.size(); i++) {
            mnFila = new String[6]; 
            mnFila[0] = "01";
            System.out.println("01");
            mnFila[1] = " Arsenal ";
            mnFila[2] = lecturaArchivo.mnCoordenadas.get(mnIndex).substring(0, 2);
            System.out.println(lecturaArchivo.mnCoordenadas.get(mnIndex));
            mnFila[3] = lecturaArchivo.mnArsenalNombre.get(mnIndex);
            mnFila[4] = lecturaArchivo.mnHorarioDia.get(mnIndex);
            mnFila[5] = lecturaArchivo.mnHorario.get(mnIndex);
            mnDataList.add(mnFila);
            mnIndex++;
        }
        
        mnData = new String[mnDataList.size()][6];
        mnTableModel = new DefaultTableModel(mnData, mnHeader);
        mnTableModel.setRowCount(0);
        
        for (int i = 0; i < mnDataList.size(); i++) {
        //     mnData[i] = ;
            System.out.println( mnDataList.get(i)[1] + ": coordenada");
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
