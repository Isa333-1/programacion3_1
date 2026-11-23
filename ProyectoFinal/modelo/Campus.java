package ProyectoFinal.modelo;
 
public class Campus
{
    private String[] edificios;
    private int[][] matrizAdyacencia;
    private static final int INF = 1_000_000_000;
 
    public Campus(String[] nombresEdificios)
    {
        this.edificios = nombresEdificios;
        int n = nombresEdificios.length;
        this.matrizAdyacencia = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                this.matrizAdyacencia[i][j] = (i == j) ? 0 : INF;
            }
        }
    }
 
    public String[] getEdificios()
    {
        return edificios;
    }
 
    public int[][] getMatrizAdyacencia()
    {
        return matrizAdyacencia;
    }
 
    public void agregarConexion(int origen, int destino, int distancia)
    {
        int n = edificios.length;
        if (origen >= 0 && origen < n && destino >= 0 && destino < n && distancia > 0)
        {
            matrizAdyacencia[origen][destino] = distancia;
            matrizAdyacencia[destino][origen] = distancia;
        }
    }
 
    public String calcularRutaDijkstra(int origen, int destino)
    {
        int n = edificios.length;
        int[] dist = new int[n];
        boolean[] visitado = new boolean[n];
        int[] previo = new int[n];
 
        for (int i = 0; i < n; i++)
        {
            dist[i] = INF;
            visitado[i] = false;
            previo[i] = -1;
        }
        dist[origen] = 0;
 
        for (int count = 0; count < n - 1; count++)
        {
            int u = -1;
            int minDist = INF;
            for (int v = 0; v < n; v++)
            {
                if (!visitado[v] && dist[v] < minDist)
                {
                    minDist = dist[v];
                    u = v;
                }
            }
            if (u == -1)
            {
                break;
            }
            visitado[u] = true;
 
            for (int v = 0; v < n; v++)
            {
                if (!visitado[v] && matrizAdyacencia[u][v] != INF && dist[u] != INF)
                {
                    int nuevaDist = dist[u] + matrizAdyacencia[u][v];
                    if (nuevaDist < dist[v])
                    {
                        dist[v] = nuevaDist;
                        previo[v] = u;
                    }
                }
            }
        }
 
        if (dist[destino] >= INF)
        {
            return "No existe ruta entre " + edificios[origen] + " y " + edificios[destino] + ".";
        }
 
        int[] camino = new int[n];
        int pasos = 0;
        int actual = destino;
        while (actual != -1)
        {
            camino[pasos] = actual;
            pasos++;
            actual = previo[actual];
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("Ruta mas corta:\n");
        for (int i = pasos - 1; i > 0; i--)
        {
            int a = camino[i];
            int b = camino[i - 1];
            sb.append(edificios[a]).append(" -> ").append(edificios[b])
              .append(" (").append(matrizAdyacencia[a][b]).append("m)");
            if (i > 1) {
                sb.append(" -> ");
            }
        }
        sb.append("\nDistancia TOTAL: ").append(dist[destino]).append(" metros");
        return sb.toString();
    }
}
