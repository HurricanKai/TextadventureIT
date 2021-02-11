package de.kaij_noah.it.textadventure.mapgen;

import de.kaij_noah.it.textadventure.base.GameState;
import de.kaij_noah.it.textadventure.base.ITileGenerator;
import de.kaij_noah.it.textadventure.base.Tile;
import de.kaij_noah.it.textadventure.base.TileTemplate;

import java.util.Arrays;
import java.util.Random;

public final class WeightedTileGenerator implements ITileGenerator
{
    private final Weighted<ITileGenerator>[] tileGenerators;

    public WeightedTileGenerator(Weighted<ITileGenerator>[] tileGenerators)
    {
        this.tileGenerators = new Weighted[tileGenerators.length];

        // normalize weights (sum of all weights == 1.0)
        var totalWeight = Arrays.stream(tileGenerators).map(a -> a.getWeight()).reduce(0d, (a, b) -> a + b);
        for (int i = 0; i < tileGenerators.length; i++)
        {
            this.tileGenerators[i] = new Weighted<>(tileGenerators[i].getWeight() / totalWeight, tileGenerators[i].getValue());
        }
    }

    @Override
    public Tile Generate(TileTemplate template, int x, int y, int z, int maxx, int maxy, int maxz, Random random)
    {
        var rnd = random.nextDouble();

        for (var weight : tileGenerators)
        {
            rnd -= weight.getWeight();
            if (rnd < 0)
                return weight.getValue().Generate(template, x, y, z, maxx, maxy, maxz, random);
        }

        System.out.printf("FAILED TO GENERATE %s %s remaining weight %s\n", x, y, rnd);
        return new Tile(template.canMoveWest(), template.canMoveEast(), template.canMoveNorth(), template.canMoveSouth())
        {
            private final String[] titleLines = new String[0];

            @Override
            public char renderFloor()
            {
                return 'E';
            }

            @Override
            public String[] getTitleLines(GameState gameState)
            {
                return titleLines;
            }
        };
    }
}
