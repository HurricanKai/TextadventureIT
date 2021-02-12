package de.kaij_noah.it.textadventure.pathfinding.lpastar;

import de.kaij_noah.it.textadventure.math.Vector3I;
import de.kaij_noah.it.textadventure.math.Weighted;
import de.kaij_noah.it.textadventure.pathfinding.base.INavigationMap;
import de.kaij_noah.it.textadventure.pathfinding.base.INavigationTile;
import de.kaij_noah.it.textadventure.pathfinding.base.PathFinder;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class LPAStarPathFinder extends PathFinder
{
    private LPANode goal;
    private LPANode start;
    private LPANode currentWalk;

    @Override
    public boolean hasNext()
    {
        return currentWalk.ReconstructedNext != null;
    }

    @Override
    public Vector3I next()
    {
        currentWalk = currentWalk.ReconstructedNext;
        return currentWalk.Position;
    }

    private final class LPANode
    {
        public double G;
        public double RHS;
        public final double H;
        public final Vector3I Position;

        public final INavigationTile NavigationTile;
        public final ArrayList<Weighted<LPANode>> Predecessors = new ArrayList<>();
        public LPANode RHSSource;
        public LPANode ReconstructedNext;

        private LPANode(double h, Vector3I position, INavigationTile navigationTile)
        {
            H = h;
            Position = position;
            G = Double.POSITIVE_INFINITY;
            RHS = Double.POSITIVE_INFINITY;
            NavigationTile = navigationTile;
        }

        public Stream<Weighted<LPANode>> getSuccessors()
        {
            return NavigationTile.getWeightedChildren().stream().map(a ->
            {
                var value = a.getValue();
                return new Weighted(a.getWeight(), lpaMap[value.X][value.Y][value.Z]);
            });
        }
    }

    private final LPANode[][][] lpaMap;

    public LPAStarPathFinder(INavigationMap map)
    {
        super(map);
        lpaMap = new LPANode[map.getSizeX()][map.getSizeY()][map.getSizeZ()];

        for (int x = 0; x < lpaMap.length; x++)
        {
            var row = lpaMap[x];
            for (int y = 0; y < row.length; y++)
            {
                var col = row[y];
                for (int z = 0; z < col.length; z++)
                    col[z] = new LPANode((x * x + y * y + z * z), new Vector3I(x, y, z), map.getNavigationTile(x, y, z));
            }
        }

        for (LPANode[][] row : lpaMap)
        {
            for (LPANode[] col : row)
            {
                for (LPANode lpaNode : col)
                {
                    for (var v : lpaNode.NavigationTile.getWeightedChildren())
                    {
                        var v2 = v.getValue();
                        lpaMap[v2.X][v2.Y][v2.Z].Predecessors.add(new Weighted<>(v.getWeight(), lpaNode));
                    }
                }
            }
        }
    }

    private final Comparator<LPANode> comp = Comparator
            .comparing((LPANode a) -> Math.min(a.G, a.RHS + a.H))
            .thenComparing((LPANode a) -> Math.min(a.G, a.RHS));
    private final PriorityQueue<LPANode> nodes = new PriorityQueue<LPANode>(comp);
    @Override
    public void beginCalculation(Vector3I start, Vector3I goal)
    {
        this.goal = lpaMap[goal.X][goal.Y][goal.Z];
        this.start = lpaMap[start.X][start.Y][start.Z];
        currentWalk = this.start;
        this.start.RHS = 0;
        nodes.add(this.start);
        calculate();
    }

    @Override
    public void notifyCostChange(Vector3I endEdge)
    {
        var na = lpaMap[endEdge.X][endEdge.Y][endEdge.Z];
        updateNode(na);
        calculate();
    }

    private void calculate()
    {
        while(!nodes.isEmpty())
        {
            var node = nodes.remove();
            if (comp.compare(node, goal) >= 0 && (goal.RHS == goal.G))
                break;

            if (node.G > node.RHS)
            {
                node.G = node.RHS;
            }
            else
            {
                node.G = Double.POSITIVE_INFINITY;
                updateNode(node);
            }

            for (var s : node.getSuccessors().toArray())
            {
                updateNode(((Weighted<LPANode>)s).getValue());
            }
        }

        Reconstruct(this.goal);
    }

    private void Reconstruct(LPANode node)
    {
        var c = node.RHSSource;
        if (c == null)
        {
            System.out.println("Oh no! Reconstruction failed!");
            return;
        }
        c.ReconstructedNext = node;
        if (c != start)
            Reconstruct(c);
    }

    private void updateNode(LPANode toUpdate)
    {
        if (toUpdate != start)
        {
            toUpdate.RHS = Double.POSITIVE_INFINITY;
            for (var predecessor : toUpdate.Predecessors)
            {
                var v = predecessor.getValue().G + predecessor.getWeight();
                if (v < toUpdate.RHS)
                {
                    toUpdate.RHS = v;
                    toUpdate.RHSSource = predecessor.getValue();
                }
            }

            if (nodes.contains(toUpdate))
                nodes.remove(toUpdate);

            if (toUpdate.G != toUpdate.RHS)
                nodes.add(toUpdate);
        }
    }
}
