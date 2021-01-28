import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PrimsGen {
    private static class Node
    {
        public int X;
        public int Y;
        public List<Edge> Edges = new ArrayList<>();
        public int CheapestEdge = -1;
        public double CheapestCost = Double.POSITIVE_INFINITY;

        public Node(int x, int y) {
            X = x;
            Y = y;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return X == node.X && Y == node.Y;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(X, Y);
        }
    }

    private static class Edge
    {
        public int A;
        public int B;
        public Double Cost;

        public Edge(int a, int b, Double cost) {
            A = a;
            B = b;
            Cost = cost;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return A == edge.A && B == edge.B && Cost.equals(edge.Cost);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(A, B, Cost);
        }
    }

    public static Map GenerateRandomMap(int xsize, int ysize, ITileGenerator tileGenerator)
    {
        List<Node> toSearch = new ArrayList<>();
        var totalCount = xsize * ysize;

        // this sure is one huge mess, but it's my first time implementing algorithms like this in Java :/
        // (I sure do miss my C#)

        // would've probably been easier & more performant to implement this using a recursive function.
        // but we've got this right now, so just keep it :)
        // todo rework maybe
        var nodes = new Node[totalCount];

        for (int x = 0; x < xsize; x++)
        {
            for (int y = 0; y < ysize; y++)
            {
                var i = Util.Index2D(x, y, xsize, ysize);

                nodes[i] = new Node(x, y);
            }
        }


        var random = new Random();
        for (int x = 0; x < xsize; x++)
        {
            for (int y = 0; y < ysize; y++) {
                var i = Util.Index2D(x, y, xsize, ysize);
                var current = nodes[i];

                if (x + 1 < xsize)
                {
                    var t = Util.Index2D(x + 1, y, xsize, ysize);
                    double cost = random.nextDouble();
                    for (var edge : nodes[t].Edges)
                        if (edge.B == i)
                            cost = edge.Cost;

                    nodes[i].Edges.add(new Edge(i, t, cost));
                }
                if (x - 1 >= 0)
                {
                    var t = Util.Index2D(x - 1, y, xsize, ysize);
                    double cost = random.nextDouble();
                    for (var edge : nodes[t].Edges)
                        if (edge.B == i)
                            cost = edge.Cost;

                    nodes[i].Edges.add(new Edge(i, t, cost));
                }
                if (y + 1 < ysize)
                {
                    var t = Util.Index2D(x, y + 1, xsize, ysize);
                    double cost = random.nextDouble();
                    for (var edge : nodes[t].Edges)
                        if (edge.B == i)
                            cost = edge.Cost;

                    nodes[i].Edges.add(new Edge(i, t, cost));
                }
                if (y - 1 >= 0)
                {
                    var t = Util.Index2D(x, y - 1, xsize, ysize);
                    double cost = random.nextDouble();
                    for (var edge : nodes[t].Edges)
                        if (edge.B == i)
                            cost = edge.Cost;

                    nodes[i].Edges.add(new Edge(i, t, cost));
                }
                var cheapest = nodes[i].Edges.stream().min((a, b) -> a.Cost.compareTo(b.Cost))
                        .get();
                nodes[i].CheapestEdge = nodes[i].Edges.indexOf(cheapest);
                nodes[i].CheapestCost = cheapest.Cost;

                toSearch.add(current);
            }
        }

        var finalEdges = new ArrayList<Edge>(totalCount);
        while(!toSearch.isEmpty())
        {
            Node current = null;
            double cost = Double.POSITIVE_INFINITY;
            for (var t : toSearch)
                if (t.CheapestCost < cost)
                {
                    current = t;
                    cost = t.CheapestCost;
                }
            toSearch.remove(current);

            if (current.CheapestEdge != -1 && current.CheapestEdge < current.Edges.size())
            {
                var v = current.Edges.get(current.CheapestEdge);

                if (v != null)
                {
                    finalEdges.add(v);
                }
            }

            for (var edge : current.Edges)
            {
                var other = nodes[edge.A];
                if (other.equals(current))
                    other = nodes[edge.B];

                if (!toSearch.contains(other)) continue;

                var newCost = current.CheapestCost + edge.Cost;
                if (other.CheapestCost > newCost)
                {
                    other.CheapestCost = newCost;
                    for (int i = 0; i < other.Edges.size(); i++)
                    {
                        var otherEdge = other.Edges.get(i);
                        if (otherEdge.A == edge.B && otherEdge.B == edge.A)
                        {
                            other.CheapestEdge = i;
                            break;
                        }
                    }
                }
            }
        }
        toSearch = null;

        var tiles = new Tile[xsize][ysize];
        for (int x = 0; x < xsize; x++)
        {
            for (int y = 0; y < ysize; y++)
            {
                tiles[x][y] = tileGenerator.Generate(x, y);
            }
        }

        for (var edge : finalEdges)
        {
            var a = nodes[edge.A];
            var b = nodes[edge.B];

            // this assumes all edges to be normalized
            if (b.X > a.X)
            {
                tiles[a.X][a.Y].setCanMoveRight(true);
                tiles[b.X][b.Y].setCanMoveLeft(true);
            }
            else if (b.X < a.X)
            {
                tiles[a.X][a.Y].setCanMoveLeft(true);
                tiles[b.X][b.Y].setCanMoveRight(true);
            }
            else if (b.Y > a.Y)
            {
                tiles[a.X][a.Y].setCanMoveUp(true);
                tiles[b.X][b.Y].setCanMoveDown(true);
            }
            else if (b.Y < a.Y)
            {
                tiles[a.X][a.Y].setCanMoveDown(true);
                tiles[b.X][b.Y].setCanMoveUp(true);
            }
        }

        return new Map(xsize, ysize, tiles);
    }
}
