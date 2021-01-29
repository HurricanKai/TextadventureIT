import java.util.Arrays;
import java.util.Random;

public class WeightedTileGenerator implements ITileGenerator
{
    private final Weighted<ITileGenerator>[] tileGenerators;
    private final Random random;
    public WeightedTileGenerator(Weighted<ITileGenerator>[] tileGenerators)
    {
        this.random = new Random();
        this.tileGenerators = new Weighted[tileGenerators.length];

        // normalize weights (sum of all weights == 1.0)
        var totalWeight = Arrays.stream(tileGenerators).map(a -> a.getWeight()).reduce(0d, (a, b) -> a + b);
        for (int i = 0; i < tileGenerators.length; i++)
        {
            this.tileGenerators[i] = new Weighted<>(tileGenerators[i].getWeight() / totalWeight, tileGenerators[i].getValue());
        }
    }

    @Override
    public Tile Generate(int x, int y)
    {
        var rnd = random.nextDouble();

        for (var weight : tileGenerators)
        {
            rnd -= weight.getWeight();
            if (rnd < 0)
                return weight.getValue().Generate(x, y);
        }

        System.out.printf("FAILED TO GENERATE %s %s remaining weight %s\n", x, y, rnd);
        return new Tile()
        {
            @Override
            public char renderFloor()
            {
                return 'E';
            }
        };
    }
}
