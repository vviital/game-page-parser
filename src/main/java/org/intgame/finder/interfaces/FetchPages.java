package org.intgame.finder.interfaces;

import org.intgame.model.RawPage;

import java.util.List;

/**
 * Created by vviital on 5/13/16.
 */
public interface FetchPages{

    List<String> getListUrls();

    List<RawPage> getPages();

    RawPage getPage(String url);
}
