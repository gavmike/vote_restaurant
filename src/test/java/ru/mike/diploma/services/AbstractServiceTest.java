package ru.mike.diploma.services;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration("classpath:spring-test.xml")
@RunWith(SpringRunner.class)
@ActiveProfiles({"hsqldb","datajpa"})
@Sql(scripts = "classpath:db/populateHSQLDB.sql",  config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractServiceTest {
    protected static final Logger log = getLogger("result");
}
