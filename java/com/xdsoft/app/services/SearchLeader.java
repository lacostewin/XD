package com.xdsoft.app.services;

import com.xdsoft.app.domain.RatingDb;
import com.xdsoft.app.repos.RatingRepo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SearchLeader {

    public String search(RatingRepo ratingRepo, String fromdate, String todate, Map<String, Object> model) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datefrom = sdf.parse(fromdate);
            long frommillis = datefrom.getTime();
            Date dateto = sdf.parse(todate);
            long tomillis = dateto.getTime();

            Iterable<RatingDb> hist;

            if (frommillis - 1 < tomillis) {
                if (fromdate.equals(todate)) {
                    hist = ratingRepo.findByDateNative(sdf.parse(fromdate));
                    model.put("messages", hist);
                    return "main.html";
                } else {
                    hist = ratingRepo.findByDateBetween(sdf.parse(fromdate), sdf.parse(todate));
                    model.put("messages", hist);
                    return "main";
                }
            } else {
                System.out.println("Перепутали местами даты!");
                return "main.html";
            }
        } catch (
                ParseException e) {
            System.out.println("Заполните все поля с датами");
            return "redirect:/";
        }

    }
}
