package pl.sobczak.rebel.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryPageItem {

    private final List<String> tags;
    private final String path;
    private final String name;
    private final int stars;                  // max is 5 stars
    private final int numberOfComments;
    private final String deliveryDescription;
    private final double currentPrice;
    private final double oldPrice;
}
