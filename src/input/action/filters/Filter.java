package input.action.filters;

public class Filter {
  private Sort sort;
  private Contain contains;

  public Filter() {
  }

  public void setSort(final Sort sort) {
    this.sort = sort;
  }

  public void setContains(final Contain contains) {
    this.contains = contains;
  }

  public Sort getSort() {
    return sort;
  }

  public Contain getContains() {
    return contains;
  }
}
