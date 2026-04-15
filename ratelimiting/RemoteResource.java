public class RemoteResource implements Resource {
    @Override
    public void getResponse() {
        System.out.println("Remote Resource says hi");
    }
}