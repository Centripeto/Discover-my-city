package com.boomers.www.discover_my_city;

import com.boomers.www.discover_my_city.model.*;
import com.boomers.www.discover_my_city.persistance.repository.MongoItineraryRepository;
import com.boomers.www.discover_my_city.persistance.repository.MongoPOIRepository;
import com.boomers.www.discover_my_city.repository.ItineraryRepository;
import com.boomers.www.discover_my_city.repository.POIRepository;
import com.boomers.www.discover_my_city.service.ItineraryService;
import com.boomers.www.discover_my_city.service.POIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableAutoConfiguration
public class DiscoverMyCityApplication implements CommandLineRunner {

  private static final Logger LOG = LoggerFactory.getLogger(DiscoverMyCityApplication.class);
  @Autowired
  private MongoPOIRepository mongoPOIRepository;
  @Autowired
  private MongoItineraryRepository mongoItineraryRepository;

  public static void main(String[] args) {
    LOG.info("STARTING THE APPLICATION");
    SpringApplication.run(DiscoverMyCityApplication.class, args);
    LOG.info("APPLICATION FINISHED");
  }

  @Override
  public void run(String... args) {
    Console console = System.console();
    POIRepository poiRepository = new POIRepository(mongoPOIRepository);
    POIService poiService = new POIService(poiRepository);
    ItineraryRepository itineraryRepository = new ItineraryRepository(mongoItineraryRepository);
    ItineraryService itineraryService = new ItineraryService(itineraryRepository);

    init(poiRepository, itineraryRepository);

    Contributor contributor = new Contributor("CONTRIBUTOR", "CONTIBUTOR", "contributor@email.it", poiService, itineraryService);
    Curatore curatore = new Curatore(poiService);
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
          poiService.readAll().stream().filter(p -> p.getStatus() == Status.IN_APPROVAL).forEach(System.out::println);
          console.readLine();
          break;
        case 3:
          System.out.println("Lista punti di interesse approvati: ");
          poiService.readAll().stream().filter(p -> p.getStatus() == Status.APPROVED).forEach(System.out::println);
          console.readLine();
          break;
        case 4:
          System.out.println("Inserisci ID punto di interesse da approvare: ");
          poiService.readAll().stream()
                  .filter(p -> p.getStatus() == Status.IN_APPROVAL)
                  .map(POI::getId)
                  .forEach(System.out::println);
          String id = console.readLine("Inserisci id: ");
          POI toApprove = poiService.readById(id);
          System.out.println(curatore.approve(toApprove));
          console.readLine();
          break;
        case 5:
          System.out.println("Inserisci punti di interesse: ");
          poiService.readAll().stream()
                  .filter(p -> p.getStatus() == Status.APPROVED)
                  .map(POI::getId)
                  .forEach(System.out::println);
          String input = console.readLine("Inserisci id separati da virgola: ");
          List<String> ids = Arrays.asList(input.split(","));
          List<POI> pois = poiService.readAll().stream().filter(p -> ids.contains(p.getId())).collect(Collectors.toList());
          contributor.createItinerary("test", "test", pois, false, null, null);
          itineraryService.readAll().forEach(i -> i.getPois().forEach(p -> System.out.println(p)));
          console.readLine();
          break;
        case 6:
          System.out.println("Lista Itinerari: ");
          this.mongoItineraryRepository.findAllDeep().forEach(System.out::println);
          console.readLine();
          break;
        case 10:
          System.out.println("Fine");
          exit = true;
          break;
          default: System.out.println("Scelta non valida!");
      }
    }
  }

  private void init (POIRepository poiRepository, ItineraryRepository itineraryRepository) {
    POI piazza = poiRepository.create(new POI("piazza", "piazza principale", new Coordinate(0, 0), Status.APPROVED));
    POI municipio = poiRepository.create(new POI("municipio", "municipio città", new Coordinate(0, 0), Status.APPROVED));
    POI chiesa = poiRepository.create(new POI("chiesa", "chiesa madre", new Coordinate(0, 0), Status.APPROVED));
    POI monumento = poiRepository.create(new POI("monumento", "monumento ai caduti", new Coordinate(0, 0), Status.IN_APPROVAL));

    itineraryRepository.create(new Itinerary("Alla scoperta del comune", "Punti di interesse principali del comune", Arrays.asList(piazza, municipio), false, null, null, Status.APPROVED));
    itineraryRepository.create(new Itinerary("Luoghi di culto", "Luoghi di culto principali del comune", Arrays.asList(chiesa), false, null, null, Status.APPROVED));
  }



  public String getMenu() {
    return """
        ######## MENU ###########
        
        1) crea punto interesse
        2) lista punti interesse da approvare
        3) lista punti interesse approvati
        4) approva punto interesse
        5) crea itinerario
        6) mostra itinerari
        10) exit
        
        """;
  }
}
