package org.intgame.finder;

import javafx.util.Pair;
import org.intgame.finder.interfaces.ParseGamePage;
import org.intgame.model.Author;
import org.intgame.model.Game;
import org.intgame.model.Image;
import org.intgame.model.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by vviital on 5/13/16.
 */
public class DbChgkParser implements ParseGamePage {


    public Map<String, Author> map = new HashMap<>();
    public int SIZE = 0;

    @Override
    public Game getGame(String url ,String doc) {
        Element html = Jsoup.parse(doc);
        Game game = new Game();
        game.setSourceUrl(url);
        game.setGameTitle(getGameTitle(html));
        game.setAdditionDate(new Date());

        System.out.println(game.getGameTitle());

        Elements questionsList = html.select(".question");

        for(Element questionElement : questionsList) {
            if (questionElement.className().toString().equals("Question")) continue;
            String question = getQuestion(questionElement);
            String answer = getAnswer(questionElement);
            Author authors = getAuthors(questionElement);
            String comments = getComments(questionElement);
            String notices = getNotices(questionElement);
            String passCriteria = getPassCriteria(questionElement);
            String materials = getMaterials(questionElement);
            String sources = getSources(questionElement);
            List<Image> images = getImages(questionElement);

            Question q = new Question();
            q.setQuestion(question);
            q.setAnswer(answer);
            q.setAuthor(authors);
            q.setComments(comments);
            q.setNotices(notices);
            q.setPassCriteria(passCriteria);
            q.setMaterials(materials);
            q.setSources(sources);
            q.setImages(new HashSet<>(images));

            authors.getQuestions().add(q);
            game.getQuestions().add(q);
            SIZE = Math.max(SIZE, question.length());
        }
        game.bind();
        return game;
    }

    @Override
    public String getQuestion(Element element) {
        String question = getTextByClass(element, "strong.Question");
        return question;
    }

    @Override
    public String getAnswer(Element element) {
        String answer = getTextByClass(element, ".Answer");
        return answer;
    }

    @Override
    public String getComments(Element element) {
        String comments = getTextByClass(element, ".Comments");
        return comments;
    }

    @Override
    public String getNotices(Element element) {
        String notices = getTextByClass(element, ".Notices");
        return notices;
    }

    @Override
    public String getPassCriteria(Element element) {
        String passCriteria = getTextByClass(element, ".PassCriteria");
        return passCriteria;
    }

    @Override
    public String getSources(Element element) {
        String sources = getTextByClass(element, ".Sources");
        return sources;
    }

    @Override
    public Author getAuthors(Element element) {
        String authors = "";
        try {
            authors = element.select(".Authors").last().parent().select("a").text();
        } catch (Exception e) {

        }
        if (!map.containsKey(authors))
            map.put(authors, new Author(authors));
        return map.get(authors);
    }

    private String getTextByClass(Element element, String className) {
        String result = "";
        try {
            List<TextNode> nodes = element.select(className).last().parent().textNodes();
            StringBuffer buffer = new StringBuffer();
            for(TextNode node : nodes) {
                buffer.append(node.text());
            }
            result = buffer.toString().replace((char)160, ' ').trim();
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public String getGameTitle(Element element) {
        String title = element.select("title").text();
        int index = title.indexOf("| Мобильная База вопросов");
        title = title.substring(0, index);
        return title;
    }

    @Override
    public String getMaterials(Element element) {
        String materials = getTextByClass(element, ".razdatka");
        return materials;
    }

    @Override
    public List<Image> getImages(Element element) {
        Elements imgs = element.select("img");
        List<Image> images = new ArrayList<>();
        try {
            for(Element img : imgs) {
                String url = img.attr("src");
                Image image = new Image();
                image.setUrl(url);
                images.add(image);
            }
        } catch (Exception e) {

        }
        return images;
    }
}
