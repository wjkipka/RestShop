package com.roche.application.persistence.repository;

import com.roche.application.persistence.PersistenceConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Base class for the persistence tests.
 * <p>
 * Created on 16.04.20.
 *
 * @author waldemarkipka
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@DataJpaTest
public abstract class PersistenceTestBase {
}
