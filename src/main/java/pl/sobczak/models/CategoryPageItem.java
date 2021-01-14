package pl.sobczak.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryPageItem {

    private List<String> tags;
    private String path;
    private String name;
    private int stars;                  // max is 5 stars
    private int numberOfComments;
    private String deliveryDescription;
    private double currentPrice;
    private double oldPrice;
}
