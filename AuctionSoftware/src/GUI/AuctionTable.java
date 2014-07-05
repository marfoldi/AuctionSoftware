package GUI;

import Entities.Auction;
import Entities.Category;
import Logic.Logic;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marfoldi
 */
public class AuctionTable {

    private JTable table;
    private myTableModel tableModel;
    private Logic logic;

    public AuctionTable(final GUI gui) {
        tableModel = new myTableModel();
        table = new JTable(tableModel);
        logic = Logic.getInstance();
        for (Auction auct : logic.getAuctions()) {
            tableModel.addAuction(auct);
        }


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gui.refreshBidTable();
            }
        });
    }

    public Auction getSelectedAuction() {
        if (table.getSelectedRow() != -1) {
            return tableModel.getSelectedAuction(table.getSelectedRow());
        }
        return null;
    }

    public JTable getTable() {
        return table;
    }

    private class myTableModel extends AbstractTableModel {

        private String[] columns = {"Name", "Category", "Minimum price", "Ending date", "Actual price"};
        private List<Auction> data = new ArrayList<>();

        public void addAuction(Auction auct) {
            data.add(auct);
            fireTableDataChanged();
        }

        public Auction getSelectedAuction(int row) {
            return data.get(row);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Auction auct = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return auct.getName();
                case 1:
                    return auct.getCategory().getName();
                case 2:
                    return auct.getMinPrice();
                case 3:
                    return auct.getEndingDate();
                case 4:
                    return auct.getMaxBid() == auct.getMinPrice() ? "" : auct.getMaxBid();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Object o = getValueAt(0, columnIndex);
            if (o != null) {
                return o.getClass();
            } else {
                return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Auction auct = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    auct.setName((String) aValue);
                    break;
                case 1:
                    auct.setCategory((Category) aValue);
                    break;
                case 2:
                    auct.setMinPrice((Integer) aValue);
                    break;
                case 3:
                    auct.setEndingDate((Date) aValue);
                    break;
            }
            Logic l = Logic.getInstance();
            l.updateAuction(auct);
        }
    }
}
