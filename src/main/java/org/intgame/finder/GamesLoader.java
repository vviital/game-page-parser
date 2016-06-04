package org.intgame.finder;

import com.sun.rmi.rmid.ExecOptionPermission;
import javafx.util.Pair;
import org.intgame.finder.interfaces.ParseGamePage;
import org.intgame.model.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by vviital on 5/12/16.
 */
public class GamesLoader {

    private final String urlPrefix = "http://db.chgk.info/";
    private final int timeout = 20000;

    private ParseGamePage parser = new DbChgkParser();

    public List<Game> load() {
//        List<Pair<String, String> > urls = getGamesUrls();
        List<Game> games = new ArrayList<>();
//
//        Long start = System.currentTimeMillis();
//        for(int i = 0; i < urls.size(); ++i) {
////        for(int i = 0; i < 10; ++i) {
//            NumberFormat formatter = new DecimalFormat("#0.00");
//            System.out.println(formatter.format((i + 1.) / urls.size() * 100) + "%");
//            Pair<String, String> game = urls.get(i);
//            games.add(load(game));
//        }
//
//        Long end = System.currentTimeMillis();
//        System.out.println(end - start);
        return games;
    }
}
