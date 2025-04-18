package com.bryan.springbootdemo.utils;

import com.bryan.springbootdemo.test.elasticsearch.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: HtmlParseUtils
 * Package: com.bryan.springbootdemo.common.utils
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/4 - 16:02
 * Version: v1.0
 */
public class HtmlParseUtils {

    public static void main(String[] args) {
        try {
            new HtmlParseUtils().parseJD("Hello").forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Content> parseJD(String keyword) throws IOException {

        String url = "https://serch.jd.com/Search?keyword=" + keyword;

        Document document = Jsoup.parse(new URL(url), 3000);
        Element element = document.getElementById("J_goodsList");

        assert element != null;
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        for(Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            goodsList.add(new Content(title, img, price));
        }

        return goodsList;
    }
}
