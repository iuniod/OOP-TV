package input.action.filters;

public final class Filter {
  private Sort sort = null;
  private Contain contains = null;

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
