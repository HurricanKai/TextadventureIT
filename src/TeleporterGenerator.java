import java.util.Random;

public final class TeleporterGenerator implements ITileGenerator
{

    @Override
    public Tile Generate(int x, int y, int maxx, int maxy, Random random)
    {
        var range = random.nextDouble() * 30; // NOTE: this range is a bit difficult to get right
                                              // issue is, if the range is too big, hits will always be out of bounds,
                                              // leading to MOST teleports going to the edges. -> undesirable

        var targetx = (int)Math.round(x + random.nextDouble() * range * 2 - range);
        var targety = (int)Math.round(y + random.nextDouble() * range * 2 - range);
        targetx = Math.max(0, Math.min(targetx - 1, maxx));
        targety = Math.max(0, Math.min(targety - 1, maxy));

        String directionString;
        if (targety > y)
        {
            if (targetx > x)
            {
                directionString = "southeast";
            }
            else if (targetx < x)
            {
                directionString = "southwest";
            }
            else
            {
                directionString = "south";
            }
        }
        else if (targety < y)
        {
            if (targetx > x)
            {
                directionString = "northeast";
            }
            else if (targetx < x)
            {
                directionString = "northwest";
            }
            else
            {
                directionString = "north";
            }
        }
        else
        {
            if (targetx > x)
            {
                directionString = "east";
            }
            else if (targetx < x)
            {
                directionString = "west";
            }
            else
            {
                return Generate(x, y, maxx, maxy, random); // this is very unlikely
            }
        }

        return new Teleporter(new Vector2I(targetx, targety), directionString);
    }
}
