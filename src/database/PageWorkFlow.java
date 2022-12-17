package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PageWorkFlow {
  private final Map<String, ArrayList<String>> pageWorkFlow = Map.ofEntries(
      Map.entry("HOMEPAGENEAUTENTIFICAT", new ArrayList<>(List.of("LOGIN", "REGISTER"))),
      Map.entry("LOGIN", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT",
          "HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("REGISTER", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT",
          "HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("HOMEPAGEAUTENTIFICAT", new ArrayList<>(List.of("LOGOUT", "MOVIES", "UPGRADES"))),
      Map.entry("LOGOUT", new ArrayList<>(List.of("HOMEPAGENEAUTENTIFICAT"))),
      Map.entry("MOVIES", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT",
          "LOGOUT", "SEE DETAILS", "MOVIES"))),
      Map.entry("UPGRADES", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "LOGOUT", "MOVIES"))),
      Map.entry("SEE DETAILS", new ArrayList<>(List.of("HOMEPAGEAUTENTIFICAT", "MOVIES",
          "LOGOUT", "UPGRADES")))
  );
  private final Map<String, ArrayList<String>> featureWorkFlow = Map.ofEntries(
      Map.entry("LOGIN", new ArrayList<>(List.of("LOGIN"))),
      Map.entry("REGISTER", new ArrayList<>(List.of("REGISTER"))),
      Map.entry("MOVIES", new ArrayList<>(List.of("SEARCH", "FILTER"))),
      Map.entry("UPGRADES", new ArrayList<>(List.of("BUY TOKENS", "BUY PREMIUM ACCOUNT"))),
      Map.entry("SEE DETAILS", new ArrayList<>(List.of("PURCHASE", "WATCH", "LIKE", "RATE")))
  );
  private static final PageWorkFlow INSTANCE = new PageWorkFlow();

  private PageWorkFlow() {
  }

  public static PageWorkFlow getInstance() {
    return INSTANCE;
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
