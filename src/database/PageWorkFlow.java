package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageWorkFlow {
  private final static Map<String, ArrayList<String>> pageWorkFlow = Map.ofEntries(
      Map.entry("HomepageNeautentificat", new ArrayList<>(List.of("Login", "Register"))),
      Map.entry("Login", new ArrayList<>(List.of("HomepageAutentificat", "HomepageNeautentificat"))),
      Map.entry("Register", new ArrayList<>(List.of("HomepageAutentificat", "HomepageNeautentificat"))),
      Map.entry("HomepageAutentificat", new ArrayList<>(List.of("Logout", "Movies", "Upgrades"))),
      Map.entry("Logout", new ArrayList<>(List.of("HomepageNeautentificat"))),
      Map.entry("Movies", new ArrayList<>(List.of("HomepageAutentificat", "Logout", "See details"))),
      Map.entry("Upgrades", new ArrayList<>(List.of("HomepageAutentificat", "Logout", "Movies"))),
      Map.entry("See details", new ArrayList<>(List.of("HomepageAutentificat", "Movies", "Logout", "Upgrades")))
  );
  private final static PageWorkFlow instance = new PageWorkFlow();

  private PageWorkFlow() {
  }

  public static PageWorkFlow getInstance() {
    return instance;
  }

  public Map<String, ArrayList<String>> getPageWorkFlow() {
    return pageWorkFlow;
  }

  boolean checkNextPage(final String currentPage, final String nextPage) {
    return pageWorkFlow.get(currentPage).contains(nextPage);
  }
}
