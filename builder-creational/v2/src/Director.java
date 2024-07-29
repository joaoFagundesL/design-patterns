public class Director {
    // reuse specific configurations

    public void buildBugatti(CarBuilder builder) {
        builder.weight(200.5)
                .doors(4);
    }

    public void buildLambo(CarBuilder builder) {
        builder.weight(300.5)
                .doors(4);
    }
}
