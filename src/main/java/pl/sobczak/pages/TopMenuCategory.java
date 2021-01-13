package pl.sobczak.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TopMenuCategory {

    GRY_PLANSZOWE_I_TOWARZYSKIE("Gry planszowe i towarzyskie"),
    PODRECZNIKI_RPG("Podręczniki RPG"),
    KARCIANKI_KOLEKCJONERSKIE("Karcianki kolekcjonerskie"),
    GRY_FIGURKOWE_I_BITEWNE("Gry figurkowe i bitewne"),
    KSIAZKI_I_KOMIKSY("Książki i komiksy"),
    FUNKO_POP("Funko - POP!"),
    LAMIGLOWKI("Łamigłówki"),
    AKCESORIA("Akcesoria"),
    PROMOCJE("Promocje");


    private String categoryName;
}
