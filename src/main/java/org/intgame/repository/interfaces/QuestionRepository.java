package org.intgame.repository.interfaces;

import org.intgame.model.Question;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vviital on 5/13/16.
 */
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
