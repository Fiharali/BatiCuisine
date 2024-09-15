package views;

import utils.InputUtils;

public class ClientView {

    public void chercheOrAjouterClient() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println(" 1. Chercher un client existant");
            System.out.println(" 2. Ajouter un nouveau client");
            System.out.println(" 3. Ajouter un nouveau client");

            int choice = InputUtils.readInt("Choisissez une option  : ");

            switch (choice) {
                case 1:
                    System.out.println("111");
                    exit = true;
                    break;
                case 2:
                    System.out.println("222");
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide, veuillez choisir 1 ou 2.");
            }
        }
    }

}
