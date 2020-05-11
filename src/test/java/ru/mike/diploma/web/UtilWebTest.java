package ru.mike.diploma.web;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.mike.diploma.model.Restaurant;
import ru.mike.diploma.model.User;
import ru.mike.diploma.model.Vote;
import ru.mike.diploma.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.mike.diploma.web.json.JsonUtil.writeIgnoreProps;
import static ru.mike.diploma.web.json.JsonUtil.writeValue;

public class UtilWebTest {

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }
    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected,"menuList","votes"));
    }
    public static ResultMatcher contentJson(List<Restaurant> expected) {
        return content().json(writeIgnoreProps(expected,"menuList","votes"));
    }
    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }
    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action.andReturn()), clazz);
    }
    public static ResultMatcher contentJson(Vote expected) {
        return content().json(writeValue(expected));
    }
}
