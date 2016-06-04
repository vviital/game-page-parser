package org.intgame.tester;

import org.intgame.services.interfaces.LoadGameService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vviital on 5/13/16.
 */
public class Tester {

    @Autowired
    LoadGameService service;

    public void test() {
        //service.LoadRawPages();
        service.ProcessQuestionsFromLoadedRawPages();
    }
}
