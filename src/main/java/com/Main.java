package com;

public class Main {
    public static void main(String[] args) {
        Book itemno1 = new Book("5142",
                "De ce info e tare.",
                "Ploiesti",
                "2001",
                "cineva",
                "Pentru ca e tare.");
        Article itemno2 = new Article("61235",
                "Prospector",
                "Bucuresti",
                "1999",
                "John");

        Catalog catalog = new Catalog();
        catalog.addItem(itemno1);
        catalog.addItem(itemno2);
        catalog.saveCatalog();
        catalog.loadCatalog();
    }
}
