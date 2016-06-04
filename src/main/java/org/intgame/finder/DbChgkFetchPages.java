package org.intgame.finder;

import org.intgame.finder.interfaces.FetchPages;
import org.intgame.model.RawPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vviital on 5/13/16.
 */
public class DbChgkFetchPages implements FetchPages {

    private final String urlPrefix = "http://db.chgk.info/";
    private final int timeout = 20000;

    @Override
    public List<String> getListUrls() {
        List<String> resultUrls = new ArrayList<>();
        String url = "http://db.chgk.info/tree";
        try {
            Document doc = Jsoup.connect(url).get();
            Element main = doc.select("ul").first();
            Elements listItems = main.select("li");
            for(Element listItem : listItems) {
                List<String> currentUrls = getGamesUrls(listItem);
                resultUrls.addAll(currentUrls);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return resultUrls;
    }

    private List<String> getGamesUrls(Element item) {
        List<String> result = new ArrayList<>();
        if (item.select("ul").size() == 0) {
            Elements listItems = item.select("li");
            for(Element li : listItems) {
                String url = this.urlPrefix + li.child(0).attr("href");
                result.add(url);
            }
        }
        return result;
    }

    @Override
    public List<RawPage> getPages() {
        List<RawPage> result = new ArrayList<>();
        List<String> urls = getListUrls();
        NumberFormat formatter = new DecimalFormat("#0.00");

        for(int i = 0; i < urls.size(); ++i) {
            System.out.println(formatter.format((i + 1.) / urls.size() * 100) + "%");
            RawPage page = getPage(urls.get(i));
            result.add(page);
        }

        return result;
    }

    @Override
    public RawPage getPage(String url) {
        RawPage page = new RawPage();

        boolean isLoaded = false;
        while(!isLoaded) {
            try {

                Document doc = Jsoup.connect(url).maxBodySize(0).timeout(timeout).get();
                isLoaded = true;
                page.setUrl(url);
                page.setHtml(doc.html());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return page;
    }
}