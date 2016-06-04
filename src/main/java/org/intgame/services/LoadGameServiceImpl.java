package org.intgame.services;

import com.google.common.collect.Lists;
import org.intgame.finder.DbChgkFetchPages;
import org.intgame.finder.DbChgkParser;
import org.intgame.finder.interfaces.FetchPages;
import org.intgame.model.Author;
import org.intgame.model.Game;
import org.intgame.model.RawPage;
import org.intgame.repository.interfaces.GameRepository;
import org.intgame.repository.interfaces.RawPagesRepository;
import org.intgame.services.interfaces.LoadGameService;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vviital on 5/13/16.
 */
@Service("LoadGamesService")
@Transactional
public class LoadGameServiceImpl implements LoadGameService {

    @Autowired
    RawPagesRepository rawPagesRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public void LoadRawPages() {
        FetchPages fetchPages = new DbChgkFetchPages();

        long start = System.currentTimeMillis();

        List<RawPage> list = fetchPages.getPages();

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();

        for(int i = 0 ; i < list.size(); ++i) {
            rawPagesRepository.save(list.get(i));
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    @Override
    public void ProcessQuestionsFromLoadedRawPages() {
        final int count = 5000;
        long start = System.currentTimeMillis();
        List<Game> games = new ArrayList<>();

        DbChgkParser dbChgkParser = new DbChgkParser();

        for(int i = 0; i < count; ++i) {
            RawPage page = this.rawPagesRepository.findOne((long)i);
            if (page == null) continue;
            if (i % 100 == 0)
                System.out.println(i);
            Game game = dbChgkParser.getGame(page.getUrl(), page.getHtml());
            games.add(game);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

//        for(Map.Entry<String, Author> entry : dbChgkParser.map.entrySet()) {
//            System.out.println(entry.getKey());
//        }
        int cnt = 0;
        start = System.currentTimeMillis();
        for(int i = 3000; i < games.size(); ++i) {
            System.out.println(i);
            cnt += games.get(i).getQuestions().size();
            this.gameRepository.save(games.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(cnt);
        games = new ArrayList<>();
    }
}
