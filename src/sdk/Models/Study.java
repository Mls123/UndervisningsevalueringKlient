package sdk.Models;

/**
 * Dette er et DTO af study med dets attributter - Denne klasse bruges til og gemme objekter
 * Denne metode burde være super klassen af DTOerne.
 * Dette er dog ikke muligt på grund af opbygningen af databasen.
 */
public class Study {
    private int id;
    private String name;
    private String shortname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
