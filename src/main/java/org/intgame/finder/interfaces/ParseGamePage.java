package org.intgame.finder.interfaces;

import javafx.util.Pair;
import org.intgame.model.Author;
import org.intgame.model.Game;
import org.intgame.model.Image;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

import java.util.List;

/**
 * Created by vviital on 5/13/16.
 */
public interface ParseGamePage {

    Game getGame(String url, String html);

    String getQuestion(Element element);

    String getAnswer(Element element);

    String getComments(Element element);

    String getNotices(Element element);

    String getPassCriteria(Element element);

    String getSources(Element element);

    Author getAuthors(Element element);

    String getGameTitle(Element element);

    String getMaterials(Element element);

    List<Image> getImages(Element element);
}
