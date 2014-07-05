package GUI;

import Entities.Auction;
import Entities.Bid;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author marfoldi
 */
public class BidTable {

    private JTable table;
    private myTableModel tableModel;

    public BidTable(Auction auct) {
        tableModel = new myTableModel();
        table = new JTable(tableModel);
        if (auct != null) {
            for (Bid bid : auct.getBids()) {
                tableModel.addBid(bid);
            }
        }
    }

    public Bid getSelectedBid() {
        if (table.getSelectedRow() != -1) {
            return tableModel.getSelectedBid(table.getSelectedRow());
        } else {
            return null;
        }
    }

    public JTable getTable() {
        return table;
    }

    private class myTableModel extends AbstractTableModel {

        private String[] columns = {"Item", "Bidder", "Date of bid", "Bid"};
        private List<Bid> data = new ArrayList<>();

        public void addBid(Bid bid) {
            data.add(bid);
            fireTableDataChanged();
        }

        public Bid getSelectedBid(int row) {
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
            Bid bid = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return bid.getItem();
                case 1:
                    return bid.getBidder();
                case 2:
                    return bid.getBidDate();
                case 3:
                    return bid.getBid();
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
    }
}
