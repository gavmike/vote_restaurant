package ru.mike.diploma.service;

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
@Sql(scripts = "classpath:populateHSQLDB.sql",  config = @SqlConfig(encoding = "UTF-8"))
public class AbstractServiceTest {
    protected static final Logger log = getLogger("result");
}
