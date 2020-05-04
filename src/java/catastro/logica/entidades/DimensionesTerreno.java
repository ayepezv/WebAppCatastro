package catastro.logica.entidades;

public class DimensionesTerreno {

    private int idDimension;
    private Predio predio;
    private double frente1;
    private double frente2;
    private double frente3;
    private double frente4;
    private String calle1;
    private String calle2;
    private String calle3;
    private String calle4;
    private double alicuota1;
    private double alicuota2;
    private double alicuota3;
    private double fondoRelativo;
    private double derechosAcciones;
    private double area;

    public DimensionesTerreno() {
        predio = new Predio();
    }

    public int getIdDimension() {
        return idDimension;
    }

    public void setIdDimension(int idDimension) {
        this.idDimension = idDimension;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public double getFrente1() {
        return frente1;
    }

    public void setFrente1(double frente1) {
        this.frente1 = frente1;
    }

    public double getFrente2() {
        return frente2;
    }

    public void setFrente2(double frente2) {
        this.frente2 = frente2;
    }

    public double getFrente3() {
        return frente3;
    }

    public void setFrente3(double frente3) {
        this.frente3 = frente3;
    }

    public double getFrente4() {
        return frente4;
    }

    public void setFrente4(double frente4) {
        this.frente4 = frente4;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getCalle3() {
        return calle3;
    }

    public void setCalle3(String calle3) {
        this.calle3 = calle3;
    }

    public String getCalle4() {
        return calle4;
    }

    public void setCalle4(String calle4) {
        this.calle4 = calle4;
    }

    public double getAlicuota1() {
        return alicuota1;
    }

    public void setAlicuota1(double alicuota1) {
        this.alicuota1 = alicuota1;
    }

    public double getAlicuota2() {
        return alicuota2;
    }

    public void setAlicuota2(double alicuota2) {
        this.alicuota2 = alicuota2;
    }

    public double getAlicuota3() {
        return alicuota3;
    }

    public void setAlicuota3(double alicuota3) {
        this.alicuota3 = alicuota3;
    }

    public double getFondoRelativo() {
        return fondoRelativo;
    }

    public void setFondoRelativo(double fondoRelativo) {
        this.fondoRelativo = fondoRelativo;
    }

    public double getDerechosAcciones() {
        return derechosAcciones;
    }

    public void setDerechosAcciones(double derechosAcciones) {
        this.derechosAcciones = derechosAcciones;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

}
