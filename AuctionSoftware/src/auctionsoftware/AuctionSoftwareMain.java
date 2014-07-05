package auctionsoftware;

import Entities.Auction;
import Entities.Bid;
import Entities.Category;
import GUI.GUI;
import Logic.Logic;

/**
 *
 * @author marfoldi
 */
public class AuctionSoftwareMain {

    private static void Upload() {
        Logic logic = Logic.getInstance();

        //CATEGORIES:
        Category electronics = new Category();
        electronics.setName("Electronics");
        logic.addCategory(electronics);
        Category apparel = new Category();
        apparel.setName("Apparel");
        logic.addCategory(apparel);
        Category home = new Category();
        home.setName("Home");
        logic.addCategory(home);
        Category beauty = new Category();
        beauty.setName("Beauty");
        logic.addCategory(beauty);
        Category toys = new Category();
        toys.setName("Toys");
        logic.addCategory(toys);
        Category sports = new Category();
        sports.setName("Sports");
        logic.addCategory(sports);

        //BIDS
        Bid b1lamp = new Bid();
        b1lamp.setItem("Lamp");
        b1lamp.setBidder("Kiss János");
        b1lamp.setBid(710);
        Bid b2lamp = new Bid();
        b2lamp.setItem("Lamp");
        b2lamp.setBidder("Arany Áron");
        b2lamp.setBid(1000);
        Bid b1roll = new Bid();
        b1roll.setItem("Roller");
        b1roll.setBidder("Ceruza Elemér");
        b1roll.setBid(5550);
        Bid b2roll = new Bid();
        b2roll.setItem("Roller");
        b2roll.setBidder("Bölcs Elek");
        b2roll.setBid(6000);
        Bid b3roll = new Bid();
        b3roll.setItem("Roller");
        b3roll.setBidder("Ceruza Elemér");
        b3roll.setBid(7500);
        Bid b1chair = new Bid();
        b1chair.setItem("Chair");
        b1chair.setBidder("Cserepes Virág");
        b1chair.setBid(710);
        Bid b1nb = new Bid();
        b1nb.setBidder("Csizma Dia");
        b1nb.setItem("Notebook");
        b1nb.setBid(71000);

        //AUCTIONS
        Auction lamp = new Auction();
        lamp.setName("Lamp");
        lamp.setCategory(home);
        lamp.setEndingDate(java.sql.Date.valueOf("2014-06-30"));
        lamp.setMinPrice(700);
        logic.addAuction(lamp);
        logic.addBid(lamp, b1lamp);
        logic.addBid(lamp, b2lamp);
        Auction roller = new Auction();
        roller.setName("Roller");
        roller.setCategory(sports);
        roller.setEndingDate(java.sql.Date.valueOf("2015-01-01"));
        roller.setMinPrice(5000);
        logic.addAuction(roller);
        logic.addBid(roller, b1roll);
        logic.addBid(roller, b2roll);
        logic.addBid(roller, b3roll);
        Auction chair = new Auction();
        chair.setName("Chair");
        chair.setCategory(home);
        chair.setEndingDate(java.sql.Date.valueOf("2017-02-11"));
        chair.setMinPrice(666);
        logic.addAuction(chair);
        logic.addBid(chair, b1chair);
        Auction notebook = new Auction();
        notebook.setName("Notebook");
        notebook.setCategory(electronics);
        notebook.setEndingDate(java.sql.Date.valueOf("2014-07-20"));
        notebook.setMinPrice(69999);
        logic.addAuction(notebook);
        logic.addBid(notebook, b1nb);
    }

    public static void main(String[] args) {
        Upload(); //-Első használat után érdemes kikommentezni!
        new GUI().setVisible(true);
    }
}
