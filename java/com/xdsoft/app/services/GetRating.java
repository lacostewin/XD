package com.xdsoft.app.services;

import com.xdsoft.app.domain.RatingDb;
import com.xdsoft.app.repos.RatingRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Date;


public class GetRating implements Runnable{

    private final RatingRepo ratingRepo;
    public GetRating(RatingRepo ratingRepo) throws IOException {
        this.ratingRepo = ratingRepo;
    }

    private final Document doc = Jsoup.connect("https://www.kinopoisk.ru/lists/top250/").get();
    private final Elements pos = doc.getElementsByClass("film-item-rating-position__position");
    private final Elements names = doc.getElementsByClass("selection-film-item-meta__original-name");
    private final Elements rating = doc.getElementsByClass("rating__value rating__value_positive");
    private final Elements looks = doc.getElementsByClass("rating__count");
    private final Date date = new Date(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Element name = names.get(i);
                RatingDb ratingDb = new RatingDb();
                ratingDb.setName(name.text());
                for (int j = i; j < 10; j++) {
                    Element rats = rating.get(j);
                    ratingDb.setRating(rats.text());
                    for (int k = j; k < 10; k++) {
                        Element look = looks.get(k);
                        ratingDb.setLook(look.text());
                            for (int z = k; z < 10; z++) {
                                Element position = pos.get(z);
                                ratingDb.setPosition(position.text());
                                ratingDb.setDate(date);
                                ratingRepo.save(ratingDb);
                                break;
                        }
                            break;
                    }
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Нет связи с сервером!");
        }
    }
}
