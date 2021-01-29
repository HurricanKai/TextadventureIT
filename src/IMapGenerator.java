public interface IMapGenerator
{
    Map Generate(int sizeX, int sizeY, ITileGenerator tileGenerator);
}
