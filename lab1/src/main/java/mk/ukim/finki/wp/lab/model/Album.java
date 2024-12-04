package mk.ukim.finki.wp.lab.model;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Album {
    private Long id;
    @Setter
    private String name;
    @Setter
    private String genre;
    @Setter
    private String releaseYear;

    public Album( String name, String genre, String releaseYear) {
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.id = UUID.randomUUID().getLeastSignificantBits()+UUID.randomUUID().getMostSignificantBits();
    }

}
