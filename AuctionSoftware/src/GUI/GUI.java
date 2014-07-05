package GUI;

import Entities.Auction;
import Entities.Bid;
import Logic.Logic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author marfoldi
 */
public class GUI extends JFrame {

    private JTextField auctionName;
    private JComboBox categories;
    private JTextField minPrice;
    private JTextField ending;
    private JTextField bidder;
    private JTextField bid;
    private AuctionTable auctionTable;
    private BidTable bidTable;
    private JPanel panel1;
    private JPanel panel2;
    private JButton addAuction;
    private JButton addBid;
    private AddBidAction addBidAction;
    private AddAuctionAction addAuctionAction;
    private Logic logic;

    public GUI() {
        setSize(1000, 600);
        setTitle("Auction Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        logic = Logic.getInstance();
        setGUI();
    }

    private void setGUI() {
        auctionName = new JTextField(12);
        categories = new JComboBox(logic.getCategoriesNames().toArray());
        categories.setPreferredSize(new Dimension(100, 20));
        minPrice = new JTextField(12);
        ending = new JTextField("(YYYY-MM-DD)", 12);
        bidder = new JTextField(12);
        bid = new JTextField(12);

        setLayout(new FlowLayout());
        auctionTable = new AuctionTable(this);
        panel1 = new JPanel();
        panel1.add(new JScrollPane(auctionTable.getTable()));
        bidTable = new BidTable(null);
        panel2 = new JPanel();
        panel2.add(new JScrollPane(bidTable.getTable()));
        addAuctionAction = new AddAuctionAction();
        addAuction = new JButton(addAuctionAction);
        addAuction.setBackground(Color.WHITE);
        addBidAction = new AddBidAction();
        addBid = new JButton(addBidAction);
        addBid.setBackground(Color.WHITE);
        add(panel1);
        add(panel2);
        add(addAuction);
        for (int i = 0; i < 48; ++i) {
            add(new JSeparator(SwingConstants.VERTICAL));
        }
        add(addBid);

        refreshTables();
    }

    private class AddAuctionAction extends AbstractAction {

        public AddAuctionAction() {
            super("Create new auction");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object[] fields = {"Auction's name", auctionName, "Category", categories, "Minimum price", minPrice, "Ending date", ending};
            int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Add new auction", JOptionPane.OK_CANCEL_OPTION);
            if (dialogResult == JOptionPane.OK_OPTION) {
                if (!logic.isDateOk(java.sql.Date.valueOf((String) ending.getText()))) {
                    JOptionPane.showMessageDialog(new JFrame(), "The ending date should be in the future!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(minPrice.getText()) < 100) {
                    JOptionPane.showMessageDialog(new JFrame(), "The starting price is less than 100!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    Auction newAuction = new Auction();
                    newAuction.setName((String) auctionName.getText());
                    newAuction.setCategory(logic.searchCategory((String) categories.getSelectedItem()));
                    newAuction.setMinPrice(Integer.parseInt(minPrice.getText()));
                    newAuction.setEndingDate(java.sql.Date.valueOf((String) ending.getText()));
                    logic.addAuction(newAuction);
                    refreshTables();
                }
            }
        }
    }

    private class AddBidAction extends AbstractAction {

        public AddBidAction() {
            super("Add new bid");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Auction auct = auctionTable.getSelectedAuction();
            if (auct != null && logic.isDateOk(auct.getEndingDate())) {
                Object[] fields = {"Bidder's name", bidder, "Bid", bid};
                int dialogResult = JOptionPane.showConfirmDialog(null, fields, "Add new bid to item: ".concat(auct.getName()), JOptionPane.OK_CANCEL_OPTION);
                if (dialogResult == JOptionPane.OK_OPTION) {
                    if (Integer.parseInt(bid.getText()) < auct.getMaxBid()) {
                        JOptionPane.showMessageDialog(new JFrame(), "The bid is too low for this auction!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        Bid newBid = new Bid();
                        newBid.setItem((String) auct.getName());
                        newBid.setBidder((String) bidder.getText());
                        newBid.setBid(Integer.parseInt(bid.getText()));
                        logic.addBid(auct, newBid);
                        refreshTables();
                    }
                }
            }
        }
    }

    public void refreshBidTable() {
        panel2.remove(0);
        bidTable = new BidTable(auctionTable.getSelectedAuction());
        panel2.add(new JScrollPane(bidTable.getTable()));
        revalidate();
        repaint();
    }

    public void refreshTables() {
        panel1.remove(0);
        panel2.remove(0);
        auctionTable = new AuctionTable(this);
        bidTable = new BidTable(auctionTable.getSelectedAuction());
        panel1.add(new JScrollPane(auctionTable.getTable()));
        panel2.add(new JScrollPane(bidTable.getTable()));
        revalidate();
        repaint();
    }
}
