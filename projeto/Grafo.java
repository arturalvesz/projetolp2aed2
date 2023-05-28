package edu.ufp.inf.lp2.projeto;

import edu.ufp.inf.lp2.projeto.connection.Connection;
import edu.ufp.inf.lp2.projeto.local.Local;

import java.util.ArrayList;

public class Grafo{

    private final ArrayList<Connection> edges;
    private final ArrayList<Local> vertices;


    public Grafo(ArrayList<Connection> edges, ArrayList<Local> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public ArrayList<Connection> getEdges() {
        return edges;
    }

    public void setEdges(Connection edges) {
        this.edges.add(edges);
    }

    public ArrayList<Local> getVertices() {
        return vertices;
    }

    public void setVertices(Local vertices) {
        this.vertices.add(vertices);
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}' + "\n";
    }
}
