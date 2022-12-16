package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageWorkFlow {
  private final static Map<String, ArrayList<String>> pageWorkFlow = Map.ofEntries(
      Map.entry("HOMEPAGENEAUTENTIFICAT", new ArrayList<>(List.of("LOGIN", "REGISTER"))),
      Map.entry("LOGIN", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("REGISTER", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("HOMEPAGEAUTENTIFICAT", new ArrayList<>(List.of("LOGOUT", "MOVIES", "UPGRADES"))),
      Map.entry("LOGOUT", new ArrayList<>(List.of("HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("MOVIES", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "LOGOUT", "SEE DETAILS"))),
      Map.entry("UPGRADES", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "LOGOUT", "MOVIES"))),
      Map.entry("SEE DETAILS", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "MOVIES", "LOGOUT", "UPGRADES")))
  );
  private final static Map<String, ArrayList<String>> featureWorkFlow = Map.ofEntries(
      Map.entry("LOGIN", new ArrayList<>(List.of("LOGIN"))),
      Map.entry("REGISTER", new ArrayList<>(List.of("REGISTER"))),
      Map.entry("MOVIES", new ArrayList<>(List.of("SEARCH")))
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

  public Map<String, ArrayList<String>> getFeatureWorkFlow() {
    return featureWorkFlow;
  }

  boolean checkNextPage(final String currentPage, final String nextPage) {
    return pageWorkFlow.get(currentPage).contains(nextPage);
  }

  boolean checkFeature(final String currentPage, final String nextFeature) {
    return featureWorkFlow.get(currentPage).contains(nextFeature);
  }
}
