package Logic;

import Entities.Auction;
import Entities.AuctionJpaController;
import Entities.Bid;
import Entities.BidJpaController;
import Entities.Category;
import Entities.CategoryJpaController;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marfoldi
 */
public class Logic {

    private AuctionJpaController auctionJPAC;
    private BidJpaController bidJPAC;
    private CategoryJpaController catJPAC;
    private Date currDate = new java.sql.Date(System.currentTimeMillis());

    private Logic() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("AuctionSoftwarePU");
        auctionJPAC = new AuctionJpaController(factory);
        bidJPAC = new BidJpaController(factory);
        catJPAC = new CategoryJpaController(factory);
    }

    public static Logic getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {

        private static final Logic instance = new Logic();
    }

    public List<Auction> getAuctions() {
        return auctionJPAC.findAuctionEntities();
    }

    public List<Category> getCategories() {
        return catJPAC.findCategoryEntities();
    }

    public List<String> getCategoriesNames() {
        List<String> names = new ArrayList<>();
        for (Category c : getCategories()) {
            names.add(c.getName());
        }
        return names;
    }

    public Category searchCategory(String catName) {
        for (Category c : getCategories()) {
            if (catName.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    public List<String> getAuctionNames() {
        List<String> names = new ArrayList<>();
        for (Auction auct : getAuctions()) {
            names.add(auct.getName());
        }
        return names;
    }

    public boolean isDateOk(Date date) {
        return date.compareTo(currDate) > 0 ? true : false;
    }

    public void addBid(Auction auct, Bid bid) {
        bidJPAC.create(bid);
        auct.getBids().add(bid);
        updateAuction(auct);
    }

    public void addAuction(Auction auct) {
        auctionJPAC.create(auct);
    }

    public void addCategory(Category cat) {
        catJPAC.create(cat);
    }

    public void updateAuction(Auction a) {
        try {
            auctionJPAC.edit(a);
        } catch (Exception ex) {
            Logger.getLogger(Logic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
