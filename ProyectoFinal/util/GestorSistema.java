package ProyectoFinal.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import ProyectoFinal.estructuras.ContenedorEstructuras;
import ProyectoFinal.estructuras.Operacion;
import ProyectoFinal.excepciones.ExcepcionesSistema.ArchivoInvalidoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.ColaDeEsperaVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.CupoLlenoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.EstudianteNoEncontradoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.HorarioConflictivoException;
import ProyectoFinal.excepciones.ExcepcionesSistema.PilaDeshacerVaciaException;
import ProyectoFinal.excepciones.ExcepcionesSistema.PreRequisitoNoAprobadoException;
import ProyectoFinal.modelo.Aula;
import ProyectoFinal.modelo.Campus;
import ProyectoFinal.modelo.Estudiante;
import ProyectoFinal.modelo.Materia;

public class GestorSistema
{
    private Scanner sc;
    private ContenedorEstructuras estructuras;
    private Campus campus;
 
    public GestorSistema(Scanner sc) throws ArchivoInvalidoException, IOException
    {
        this.sc = sc;
        this.estructuras = new ContenedorEstructuras();
        String[] edificios = {"Ingenieria", "Biblioteca", "Cafeteria", "Rectoria", "Laboratorios"};
        this.campus = new Campus(edificios);
 
        // Carga automatica de estudiantes al iniciar el sistema
        cargarEstudiantesCSV("/workspaces/programacion3_1/ProyectoFinal/estudiantes.csv");
        cargarMateriasCSV("/workspaces/programacion3_1/ProyectoFinal/materias.csv");
    }

    public void registrarEstudiante()
    {
        System.out.println("\nREGISTRO DE ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Semestre actual: ");
        int semestre = Integer.parseInt(sc.nextLine().trim());
 
        Estudiante nuevo = new Estudiante(nombre, id, email, semestre);
        estructuras.getEstudiantesMap().put(id, nuevo);
        System.out.println("Estudiante registrado exitosamente.");
    }

    public void buscarEstudiantePorId() throws EstudianteNoEncontradoException
    {
        System.out.println("\nBUSCAR ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(id);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        System.out.println("Resultado encontrado:");
        est.mostrarInformacion();
    }

      public void listarEstudiantes()
    {
        System.out.println("\nLISTA DE ESTUDIANTES");
        Map<String, Estudiante> mapa = estructuras.getEstudiantesMap();
        if (mapa.isEmpty())
        {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
 
        for (Map.Entry<String, Estudiante> entry : mapa.entrySet())
        {
            entry.getValue().mostrarInformacion();
            System.out.println("------------------------------------------");
        }
    }

    public void eliminarEstudiante() throws EstudianteNoEncontradoException
    {
        System.out.println("\nELIMINAR ESTUDIANTE");
        System.out.print("ID: ");
        String id = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(id);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        estructuras.getEstudiantesMap().remove(id);
        estructuras.getPilaDeshacer().push(Operacion.crearOperacionEliminarEstudiante(est));
        estructuras.getPilaRehacer().clear();
        System.out.println("Estudiante eliminado.");
    }

    public void crearMateria()
    {
        System.out.println("\nCREAR MATERIA");
        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Cupos maximos: ");
        int cupos = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Creditos: ");
        int creditos = Integer.parseInt(sc.nextLine().trim());
 
        Materia materia = new Materia(codigo, nombre, cupos, creditos);
        estructuras.getMateriasMap().put(codigo, materia);
        System.out.println("Materia creada exitosamente.");
    }

    public void agregarPreRequisito()
    {
        System.out.print("Codigo de la materia: ");
        String codMateria = sc.nextLine().trim();
        System.out.print("Codigo del pre-requisito: ");
        String codPre = sc.nextLine().trim();
 
        Materia mat = estructuras.getMateriasMap().get(codMateria);
        Materia pre = estructuras.getMateriasMap().get(codPre);
        String msg = (mat != null && pre != null) ? "Pre-requisito agregado." : "Materia o pre-requisito no encontrado.";
        if (mat != null && pre != null)
        {
            mat.agregarPreRequisito(pre);
        }
        System.out.println(msg);
    }

    public void mostrarPreRequisitos()
    {
        System.out.print("Codigo de la materia: ");
        String codMateria = sc.nextLine().trim();
        Materia mat = estructuras.getMateriasMap().get(codMateria);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("Pre-requisitos de " + mat.getCodigo() + " - " + mat.getNombre() + ":");
        if (mat.getPreRequisitos().isEmpty())
        {
            System.out.println("Ninguno.");
            return;
        }
        for (int i = 0; i < mat.getPreRequisitos().size(); i++)
        {
            Materia pre = mat.getPreRequisitos().get(i);
            System.out.println("- " + pre.getCodigo() + ": " + pre.getNombre());
        }
    }

    public void inscribirEstudiante() throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException, CupoLlenoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
 
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        for (int i = 0; i < mat.getPreRequisitos().size(); i++)
        {
            Materia pre = mat.getPreRequisitos().get(i);
            if (!est.tieneMateriaAprobada(pre.getCodigo()))
            {
                throw new PreRequisitoNoAprobadoException("Falta aprobar pre-requisito: " + pre.getCodigo());
            }
        }
        if (mat.estaLlena())
        {
            mat.encolarEstudiante(est);
            throw new CupoLlenoException("Materia llena. Agregada a COLA DE ESPERA");
        }
        mat.inscribirDirecto(est);
        estructuras.getPilaDeshacer().push(new Operacion("INSCRIPCION", est.getId(), mat.getCodigo()));
        estructuras.getPilaRehacer().clear();
        System.out.println("Cupo disponible (cupos restantes: " + mat.getCuposRestantes() + ")");
    }

    public void cancelarInscripcion() throws EstudianteNoEncontradoException, ColaDeEsperaVaciaException, CupoLlenoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
 
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        mat.cancelarInscripcion(est);
        estructuras.getPilaDeshacer().push(new Operacion("CANCELACION", est.getId(), mat.getCodigo()));
        estructuras.getPilaRehacer().clear();
        System.out.println("Cancelacion exitosa. Cupo liberado.");
 
        if (!mat.getColaEspera().isEmpty())
        {
            Estudiante siguiente = mat.desencolarSiguiente();
            mat.inscribirDirecto(siguiente);
            System.out.println("Asignando cupo a " + siguiente.getNombre() + " (primer estudiante en cola)");
        }
    }

      public void mostrarColaEspera()
    {
        System.out.print("Codigo Materia: ");
        String codMat = sc.nextLine().trim();
        Materia mat = estructuras.getMateriasMap().get(codMat);
        if (mat == null)
        {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("MOSTRAR COLA DE ESPERA");
        int pos = 1;
        for (Estudiante est : mat.getColaEspera())
        {
            System.out.println("Posicion " + pos + ": " + est.getNombre());
            pos++;
        }
        System.out.println("Total en espera: " + mat.getColaEspera().size());
    }

    public void reservarHorario() throws HorarioConflictivoException
    {
        System.out.println("\nRESERVAR HORARIO EN AULA");
        System.out.print("Aula: ");
        String numAula = sc.nextLine().trim();
        System.out.print("Dia (0=Dom..6=Sab, 1=Lunes): ");
        int dia = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Duracion: ");
        int duracion = Integer.parseInt(sc.nextLine().trim());
 
        Aula aula = estructuras.getAulasMap().get(numAula);
        if (aula == null)
        {
            aula = new Aula(numAula, 30);
            estructuras.getAulasMap().put(numAula, aula);
        }
 
        System.out.println("Verificando disponibilidad...");
        for (int h = hora; h < hora + duracion; h++)
        {
            String estado = aula.consultarDisponibilidad(dia, h) ? "LIBRE" : "OCUPADO";
            System.out.println("Dia " + dia + " " + h + ":00 -> " + estado);
        }
        aula.reservar(dia, hora, duracion);
        estructuras.getPilaDeshacer().push(Operacion.crearOperacionHorario(numAula, dia, hora, duracion));
        estructuras.getPilaRehacer().clear();
        System.out.println("Reserva exitosa.");
    }

    public void liberarHorario()
    {
        System.out.print("Aula: ");
        String numAula = sc.nextLine().trim();
        System.out.print("Dia (0-6): ");
        int dia = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Duracion: ");
        int duracion = Integer.parseInt(sc.nextLine().trim());
 
        Aula aula = estructuras.getAulasMap().get(numAula);
        if (aula != null)
        {
            aula.liberar(dia, hora, duracion);
            System.out.println("Horario liberado.");
        } else {
            System.out.println("Aula no encontrada.");
        }
    }

    public void consultarDisponibilidad()
    {
        System.out.print("Aula: ");
        String numAula = sc.nextLine().trim();
        System.out.print("Dia (0-6): ");
        int dia = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Hora (0-23): ");
        int hora = Integer.parseInt(sc.nextLine().trim());
 
        Aula aula = estructuras.getAulasMap().get(numAula);
        if (aula != null)
        {
            String estado = aula.consultarDisponibilidad(dia, hora) ? "DISPONIBLE" : "RESERVADA";
            System.out.println("Estado: " + estado);
        } else {
            System.out.println("Aula no encontrada.");
        }
    }

    public void agregarConexionEdificios()
    {
        String[] eds = campus.getEdificios();
        for (int i = 0; i < eds.length; i++)
        {
            System.out.println(i + ": " + eds[i]);
        }
        System.out.print("Indice origen: ");
        int o = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Indice destino: ");
        int d = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Distancia en metros: ");
        int dist = Integer.parseInt(sc.nextLine().trim());
 
        campus.agregarConexion(o, d, dist);
        System.out.println("Conexion agregada.");
    }

    public void calcularRutaMasCorta()
    {
        String[] eds = campus.getEdificios();
        System.out.println("\nCALCULAR RUTA MAS CORTA");
        System.out.println("Edificios registrados:");
        for (int i = 0; i < eds.length; i++)
        {
            System.out.println(i + ": " + eds[i]);
        }
        System.out.print("Indice origen: ");
        int o = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Indice destino: ");
        int d = Integer.parseInt(sc.nextLine().trim());
 
        System.out.println("\nRESULTADO");
        System.out.println(campus.calcularRutaDijkstra(o, d));
    }

    public void registrarNota() throws EstudianteNoEncontradoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        System.out.print("Semestre (1-10): ");
        int sem = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Indice de materia (0-19): ");
        int idx = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nota (0.0 - 5.0): ");
        Double nota = Double.parseDouble(sc.nextLine().trim());
 
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
        Double anterior = est.getNotas()[sem - 1][idx];
        est.registrarNota(sem, idx, nota);
        estructuras.getPilaDeshacer().push(Operacion.crearOperacionNota(idEst, sem, idx, anterior));
        estructuras.getPilaRehacer().clear();
        System.out.println("Nota registrada.");
    }

    public void verReporteAcademico() throws EstudianteNoEncontradoException
    {
        System.out.print("ID Estudiante: ");
        String idEst = sc.nextLine().trim();
        Estudiante est = estructuras.getEstudiantesMap().get(idEst);
        if (est == null)
        {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + idEst);
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("\nREPORTE ACADEMICO\n");
        sb.append("Estudiante: ").append(est.getNombre()).append(" (ID: ").append(est.getId()).append(")\n");
 
        Double[][] notas = est.getNotas();
        for (int s = 0; s < notas.length; s++)
        {
            boolean hayNota = false;
            for (int m = 0; m < notas[s].length; m++)
            {
                if (notas[s][m] != null)
                {
                    hayNota = true;
                    break;
                }
            }
            if (hayNota)
            {
                sb.append("Semestre ").append(s + 1).append(":\n");
                for (int m = 0; m < notas[s].length; m++)
                {
                    if (notas[s][m] != null)
                    {
                        sb.append("Materia[").append(m).append("]: ").append(notas[s][m]).append("\n");
                    }
                }
                sb.append(String.format("Promedio: %.2f%n", est.calcularPromedioSemestre(s + 1)));
            }
        }

        sb.append("=== RESUMEN ===\n");
        sb.append(String.format("Promedio acumulado: %.2f%n", est.calcularPromedioAcumulado()));
        sb.append("Materias aprobadas: ").append(est.contarAprobadas()).append("\n");
        sb.append("Materias reprobadas: ").append(est.contarReprobadas()).append("\n");
 
        String reporte = sb.toString();
        System.out.println(reporte);
        estructuras.getPilaReportes().push(reporte);
        estructuras.getPilaAdelanteReportes().clear();
    }

    public void navegadorReportes()
    {
        System.out.println("1. Atras | 2. Adelante");
        System.out.print("Seleccione: ");
        String dir = sc.nextLine().trim();
        if (dir.equals("1"))
        {
            if (!estructuras.getPilaReportes().isEmpty())
            {
                estructuras.getPilaAdelanteReportes().push(estructuras.getPilaReportes().pop());
                String msg = !estructuras.getPilaReportes().isEmpty() ? estructuras.getPilaReportes().peek() : "Inicio de reportes.";
                System.out.println(msg);
            } else {
                System.out.println("No hay reportes anteriores.");
            }
        } else if (dir.equals("2")) {
            if (!estructuras.getPilaAdelanteReportes().isEmpty())
            {
                String sig = estructuras.getPilaAdelanteReportes().pop();
                estructuras.getPilaReportes().push(sig);
                System.out.println(sig);
            } else {
                System.out.println("No hay reportes siguientes.");
            }
        }
    }

    public void deshacerOperacion() throws PilaDeshacerVaciaException, CupoLlenoException
    {
        if (estructuras.getPilaDeshacer().isEmpty())
        {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer.");
        }
        Operacion op = estructuras.getPilaDeshacer().pop();
        estructuras.getPilaRehacer().push(op);
 
        switch (op.getTipo())
        {
            case "INSCRIPCION":
                Estudiante estIns = estructuras.getEstudiantesMap().get(op.getIdEstudiante());
                Materia matIns = estructuras.getMateriasMap().get(op.getCodigoMateria());
                if (estIns != null && matIns != null)
                {
                    matIns.cancelarInscripcion(estIns);
                    System.out.println("Operacion deshecha: " + estIns.getNombre() + " ya NO esta inscrita en " + matIns.getCodigo());
                }
                break;
            case "CANCELACION":
                Estudiante estCan = estructuras.getEstudiantesMap().get(op.getIdEstudiante());
                Materia matCan = estructuras.getMateriasMap().get(op.getCodigoMateria());
                if (estCan != null && matCan != null)
                {
                    matCan.inscribirDirecto(estCan);
                    System.out.println("Operacion deshecha: inscripcion restaurada.");
                }
                break;
            case "HORARIO":
                Aula aula = estructuras.getAulasMap().get(op.getNumeroAula());
                if (aula != null)
                {
                    aula.liberar(op.getDia(), op.getHora(), op.getDuracion());
                    System.out.println("Operacion deshecha: horario liberado.");
                }
                break;
            case "NOTA":
                Estudiante estNota = estructuras.getEstudiantesMap().get(op.getIdEstudiante());
                if (estNota != null)
                {
                    estNota.registrarNota(op.getSemestre(), op.getIndiceNota(), op.getNotaAnterior());
                    System.out.println("Operacion deshecha: nota restaurada.");
                }
                break;
            case "ELIMINAR_ESTUDIANTE":
                Estudiante respaldo = op.getEstudianteRespaldo();
                if (respaldo != null)
                {
                    estructuras.getEstudiantesMap().put(respaldo.getId(), respaldo);
                    System.out.println("Operacion deshecha: estudiante restaurado.");
                }
                break;
            default:
                break;
        }
    }

    public void rehacerOperacion() throws PilaDeshacerVaciaException, CupoLlenoException, HorarioConflictivoException {
        if (estructuras.getPilaRehacer().isEmpty())
        {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer.");
        }
        Operacion op = estructuras.getPilaRehacer().pop();
        estructuras.getPilaDeshacer().push(op);
 
        switch (op.getTipo())
        {
            case "INSCRIPCION":
                Estudiante estIns = estructuras.getEstudiantesMap().get(op.getIdEstudiante());
                Materia matIns = estructuras.getMateriasMap().get(op.getCodigoMateria());
                if (estIns != null && matIns != null)
                {
                    matIns.inscribirDirecto(estIns);
                    System.out.println("Operacion rehecha: " + estIns.getNombre() + " inscrita en " + matIns.getCodigo());
                }
                break;
            case "CANCELACION":
                Estudiante estCan = estructuras.getEstudiantesMap().get(op.getIdEstudiante());
                Materia matCan = estructuras.getMateriasMap().get(op.getCodigoMateria());
                if (estCan != null && matCan != null)
                {
                    matCan.cancelarInscripcion(estCan);
                    System.out.println("Operacion rehecha: cancelacion aplicada.");
                }
                break;
            case "HORARIO":
                Aula aula = estructuras.getAulasMap().get(op.getNumeroAula());
                if (aula != null)
                {
                    aula.reservar(op.getDia(), op.getHora(), op.getDuracion());
                    System.out.println("Operacion rehecha: horario reservado.");
                }
                break;
            case "ELIMINAR_ESTUDIANTE":
                Estudiante respaldo = op.getEstudianteRespaldo();
                if (respaldo != null)
                {
                    estructuras.getEstudiantesMap().remove(respaldo.getId());
                    System.out.println("Operacion rehecha: estudiante eliminado.");
                }
                break;
            default:
                break;
        }
    }

    public void procesarArchivoCSV() throws ArchivoInvalidoException, IOException
    {
        System.out.print("Ruta del archivo CSV (ej. inscripciones.csv): ");
        String ruta = sc.nextLine().trim();
        File archivo = new File(ruta);
        if (!archivo.exists() || !archivo.isFile())
        {
            throw new ArchivoInvalidoException("Archivo no encontrado: " + ruta);
        }
 
        Queue<String[]> cola = estructuras.getColaBatch();
        cola.clear();
 
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null)
        {
            if (!linea.trim().isEmpty())
            {
                String[] partes = linea.split(",");
                if (partes.length < 2) {
                    br.close();
                    throw new ArchivoInvalidoException("Formato invalido en linea: " + linea);
                }
                cola.offer(new String[]{partes[0].trim(), partes[1].trim()});
            }
        }
        br.close();
 
        System.out.println("\nPROCESAMIENTO MASIVO (BATCH)");
        System.out.println("Archivo: " + ruta);
        int total = cola.size();
        System.out.println("Se encolaron " + total + " solicitudes.");
        System.out.println("Procesando cola...");
 
        int exitosas = 0;
        int fallidas = 0;
        int i = 1;
 
        while (!cola.isEmpty())
        {
            String[] sol = cola.poll();
            Estudiante est = estructuras.getEstudiantesMap().get(sol[0]);
            Materia mat = estructuras.getMateriasMap().get(sol[1]);
            boolean inscrito = false;
            String motivo = "";
 
            if (est == null || mat == null)
            {
                motivo = "Estudiante o materia no existe";
            } else {
                boolean cumplePre = true;
                for (int p = 0; p < mat.getPreRequisitos().size(); p++)
                {
                    if (!est.tieneMateriaAprobada(mat.getPreRequisitos().get(p).getCodigo()))
                    {
                        cumplePre = false;
                        motivo = "Pre-requisito faltante: " + mat.getPreRequisitos().get(p).getCodigo();
                        break;
                    }
                }
                if (cumplePre)
                {
                    if (!mat.estaLlena())
                    {
                        mat.getInscritos().add(est);
                        est.agregarMateriaHistorial(mat);
                        inscrito = true;
                    } else {
                        mat.encolarEstudiante(est);
                        motivo = "Cupo lleno (en cola de espera)";
                    }
                }
            }
 
            if (inscrito)
            {
                exitosas++;
                System.out.println("[" + i + "/" + total + "] " + sol[0] + " -> " + sol[1] + " Exitosa");
            } else {
                fallidas++;
                System.out.println("[" + i + "/" + total + "] " + sol[0] + " -> " + sol[1] + " Fallida (" + motivo + ")");
            }
            i++;
        }
 
        System.out.println("=== RESUMEN ===");
        System.out.println("Exitosas: " + exitosas);
        System.out.println("Fallidas: " + fallidas);
    }

}