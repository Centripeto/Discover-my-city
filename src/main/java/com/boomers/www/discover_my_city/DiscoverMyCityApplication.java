package com.boomers.www.discover_my_city;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.util.Optional;

@SpringBootApplication
public class DiscoverMyCityApplication implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(DiscoverMyCityApplication.class);

  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(DiscoverMyCityApplication.class, args);
    LOG.info("APPLICATION FINISHED");
  }

  @Override
  public void run(String... args) {
    Console console = System.console();
    Contributor contributor = new Contributor("CONTRIBUTOR", "CONTIBUTOR", "contributor@email.it");
    Curatore curatore = new Curatore();
    boolean exit = false;
    while (!exit) {
      System.out.println(this.getMenu());
      int choice = Integer.parseInt(console.readLine("Cosa vuoi fare? "));
      switch (choice) {
        case 1:
          System.out.println("Creazione punto di interesse.");
          String name = console.readLine("Inserisci nome: ");
          String description = console.readLine("Inserisci descrizione: ");
          POI poi = contributor.createPOI(name, description, new Coordinate(0, 0));
          System.out.println(poi);
          console.readLine();
          break;
        case 2:
          System.out.println("Lista punti di interesse da approvare: ");
          POI.getPois().stream().filter(p -> p.getStatus() == Status.IN_APPROVAL).forEach(System.out::println);
          console.readLine();
          break;
        case 3:
          System.out.println("Lista punti di interesse approvati: ");
          POI.getPois().stream().filter(p -> p.getStatus() == Status.APPROVED).forEach(System.out::println);
          console.readLine();
          break;
        case 4:
          System.out.println("Inserisci ID punto di interesse da approvare: ");
          POI.getPois().stream()
                  .filter(p -> p.getStatus() == Status.IN_APPROVAL)
                  .map(POI::getId)
                  .forEach(System.out::println);
          String id = console.readLine("Inserisci id: ");
          Optional<POI> toApprove = POI.getPois().stream()
                  .filter(p -> p.getId().equals(id))
                  .findAny();
          toApprove.ifPresent(value -> System.out.println(curatore.approve(value)));
          console.readLine();
          break;
        case 5:
          System.out.println("Fine");
          exit = true;
          break;
          default: System.out.println("Scelta non valida!");
      }
    }
  }

  public String getMenu() {
    return """
        ######## MENU ###########
        
        1) crea punto interesse
        2) lista punti interesse da approvare
        3) lista punti interesse approvati
        4) approva punto interesse
        5) exit
        
        """;
  }
}
