package paul.sydney.commen.result;

/*public class PageResults<T> {

}*/
public class PageResults<T> extends ListResult<T> {
    private static final long serialVersionUID = -6934915596085958833L;
 
    private int               currentPage      = 1;
    private int               pageSize         = 10;
    private int               total;
 
    public PageResults() {
    }
 
    public PageResults(int currentPage, int pageSize) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }
 
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }
 
    public int getCurrentPage() {
        return currentPage;
    }
 
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
 
    public int getPageSize() {
        return pageSize;
    }
 
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
 
    public int getTotal() {
        return total;
    }
 
    public void setTotal(int total) {
        this.total = total;
    }
}
